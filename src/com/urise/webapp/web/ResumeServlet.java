package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ResumeServlet extends HttpServlet {

    private final Storage storage;

    public ResumeServlet() {
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        response.getWriter().write(
                "<html>\n" +
                        "   <head>\n" +
                        "       <title>\n" +
                        "           All resumes\n" +
                        "       </title>\n" +
                        "       <style type>" +
                        "           table, td, th {\n" +
                        "           border-collapse: collapse;\n" +
                        "           border: 3px solid #245488;\n" +
                        "           }" +
                        "       </style>" +
                        "   </head>\n" +
                        "   <body>\n" +
                        "       <table>\n" +
                        "           <tr>\n" +
                        "               <th>Uuid</th\n>" +
                        "               <th>Full name</th>\n" +
                        "           </tr>\n"
        );
        for (Resume r : storage.getAllSorted()) {
            response.getWriter().write(
                    "               <tr>\n" +
                            "               <td>" + r.getUuid() + "</td>\n" +
                            "               <td>" + r.getFullName() + "</td>\n" +
                            "           </tr\n>"
            );
        }
        response.getWriter().write(
                "           </table>\n" +
                        "   </body>\n" +
                        "</html>"
        );
    }
}