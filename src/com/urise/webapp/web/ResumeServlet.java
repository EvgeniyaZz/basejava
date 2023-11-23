package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r;
        try {
            r = storage.get(uuid);
            r.setFullName(fullName);
        } catch (NotExistStorageException e) {
            r = new Resume(uuid, fullName);
            storage.save(r);
        }
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (value != null && value.trim().length() != 0) {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        r.addSection(type, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = Arrays.stream(value.split("\r\n")).filter(item -> !item.equals(""))
                                .collect(Collectors.toList());
                        r.addSection(type, new ListSection(list));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        List<Company> companies = new ArrayList<>();
                        String[] websites = request.getParameterValues("website");
                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];
                            String website = websites[i];

                            List<Company.Period> periods = new ArrayList<>();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

                            String[] startDates = request.getParameterValues(type + "" + (i + 1) + "startDate");
                            String[] endDates = request.getParameterValues(type + "" + (i + 1) + "endDate");
                            String[] titles = request.getParameterValues(type + "" + (i + 1) + "title");
                            String[] descriptions = request.getParameterValues(type + "" + (i + 1) + "description");
                            for (int j = 0; j < titles.length; j++) {
                                if (!titles[j].equals("") && !startDates[j].equals("") && !endDates[j].equals("")) {
                                    periods.add(new Company.Period(LocalDate.parse("1/" + startDates[j], formatter),
                                            LocalDate.parse("1/" + endDates[j], formatter), titles[j], descriptions[j]));
                                }
                            }
                            companies.add(new Company(new Link(name, website), periods));
                        }

                        r.addSection(type, new CompanySection(companies));
                }
            } else {
                r.getSections().remove(type);
            }
        }
        storage.update(r);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                if (uuid.equals("new")) {
                    r = new Resume("");
                } else {
                    r = storage.get(uuid);
                }
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}