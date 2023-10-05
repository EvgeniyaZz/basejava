package com.urise.webapp.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            Map<ContactType, String> contacts = r.getContacts();
            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            Map<SectionType, Section> sections = r.getSections();
            writeWithException(sections.entrySet(), dos, entry -> {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(section.toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        ListSection listSection = (ListSection) section;
                        List<String> list = listSection.getList();
                        writeWithException(list, dos, dos::writeUTF);
                        break;
                    }
                    case EXPERIENCE:
                    case EDUCATION: {
                        CompanySection companySection = (CompanySection) section;
                        List<Company> companies = companySection.getCompanies();
                        writeWithException(companies, dos, company -> {
                            Link homePage = company.getHomePage();
                            dos.writeUTF(homePage.getName());
                            String website = homePage.getWebsite();
                            if (website != null) {
                                dos.writeInt(1);
                                dos.writeUTF(website);
                            } else {
                                dos.writeInt(0);
                            }
                            List<Company.Period> periods = company.getPeriods();
                            writeWithException(periods, dos, period -> {
                                dos.writeInt(period.getStartDate().getYear());
                                dos.writeUTF(period.getStartDate().getMonth().name());
                                dos.writeInt(period.getEndDate().getYear());
                                dos.writeUTF(period.getEndDate().getMonth().name());
                                dos.writeUTF(period.getTitle());
                                String description = period.getDescription();
                                if (description != null) {
                                    dos.writeInt(1);
                                    dos.writeUTF(description);
                                } else {
                                    dos.writeInt(0);
                                }
                            });
                        });
                        break;
                    }
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            readWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        List<String> list = new ArrayList<>();
                        readWithException(dis, () -> list.add(dis.readUTF()));
                        resume.addSection(sectionType, new ListSection(list));
                        break;
                    }
                    case EXPERIENCE:
                    case EDUCATION: {
                        List<Company> companies = new ArrayList<>();
                        readWithException(dis, () -> {
                            String name = dis.readUTF();
                            String website = null;
                            if (dis.readInt() == 1) {
                                website = dis.readUTF();
                            }
                            List<Company.Period> periods = new ArrayList<>();
                            readWithException(dis, () -> {
                                LocalDate startDate = LocalDate.of(dis.readInt(), Month.valueOf(dis.readUTF()), 1);
                                LocalDate endDate = LocalDate.of(dis.readInt(), Month.valueOf(dis.readUTF()), 1);
                                String title = dis.readUTF();
                                String description = null;
                                if (dis.readInt() == 1) {
                                    description = dis.readUTF();
                                }
                                periods.add(new Company.Period(startDate, endDate, title, description));
                            });
                            companies.add(new Company(new Link(name, website), periods));
                        });
                        resume.addSection(sectionType, new CompanySection(companies));
                        break;
                    }
                }
            });
            return resume;
        }
    }

    @FunctionalInterface
    private interface WriteElement<T> {
        void write(T t) throws IOException;
    }

    @FunctionalInterface
    private interface ReadElement {
        void read() throws IOException;
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, WriteElement<T> writeElement) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            writeElement.write(t);
        }
    }

    private void readWithException(DataInputStream dis, ReadElement readElement) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            readElement.read();
        }
    }
}