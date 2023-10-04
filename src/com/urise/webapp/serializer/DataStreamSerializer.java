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
            dos.writeInt(contacts.size());
            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            writeWithException(sections.entrySet(), dos, entry -> {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(entry.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        ListSection listSection = (ListSection) entry.getValue();
                        List<String> list = listSection.getList();
                        dos.writeInt(list.size());
                        writeWithException(list, dos, dos::writeUTF);
                        break;
                    }
                    case EXPERIENCE:
                    case EDUCATION: {
                        CompanySection companySection = (CompanySection) entry.getValue();
                        List<Company> companies = companySection.getCompanies();
                        int size = companies.size();
                        dos.writeInt(size);
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
                            int sizePeriods = periods.size();
                            dos.writeInt(sizePeriods);
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

            int sizeContact = dis.readInt();
            for (int i = 0; i < sizeContact; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sizeSection = dis.readInt();
            for (int i = 0; i < sizeSection; i++) {
                String sectionType = dis.readUTF();
                switch (sectionType) {
                    case "PERSONAL":
                    case "OBJECTIVE":
                        resume.addSection(SectionType.valueOf(sectionType), new TextSection(dis.readUTF()));
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS": {
                        List<String> list = new ArrayList<>();
                        int sizeList = dis.readInt();
                        for (int j = 0; j < sizeList; j++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(SectionType.valueOf(sectionType), new ListSection(list));
                        break;
                    }
                    case "EXPERIENCE":
                    case "EDUCATION": {
                        int sizeList = dis.readInt();
                        List<Company> companies = new ArrayList<>();
                        for (int j = 0; j < sizeList; j++) {
                            String name = dis.readUTF();
                            String website = null;
                            if (dis.readInt() == 1) {
                                website = dis.readUTF();
                            }
                            List<Company.Period> periods = new ArrayList<>();
                            int sizePeriod = dis.readInt();
                            for (int k = 0; k < sizePeriod; k++) {
                                LocalDate startDate = LocalDate.of(dis.readInt(), Month.valueOf(dis.readUTF()), 1);
                                LocalDate endDate = LocalDate.of(dis.readInt(), Month.valueOf(dis.readUTF()), 1);
                                String title = dis.readUTF();
                                String description = null;
                                if (dis.readInt() == 1) {
                                    description = dis.readUTF();
                                }
                                periods.add(new Company.Period(startDate, endDate, title, description));
                            }
                            companies.add(new Company(new Link(name, website), periods));
                        }
                        resume.addSection(SectionType.valueOf(sectionType), new CompanySection(companies));
                        break;
                    }
                }
            }
            return resume;
        }
    }

    @FunctionalInterface
    public interface WriteElement<T> {
        void write(T t) throws IOException;
    }

    public <T> void writeWithException(Collection<T> collection, DataOutputStream dos, WriteElement<T> writeElement) throws IOException {
        for (T t : collection) {
            writeElement.write(t);
        }
    }
}