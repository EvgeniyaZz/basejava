package com.urise.webapp;

import com.urise.webapp.model.*;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume newResume = new Resume("Григорий Кислин");

        newResume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        newResume.setContact(ContactType.SKYPE, "grigory.kislin");
        newResume.setContact(ContactType.MAIL, "gkislin@yandex.ru");
        newResume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        newResume.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        newResume.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        newResume.setContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        newResume.setSections(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры."));
        newResume.setSections(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения " +
                "по Java Web и Enterprise технологиям."));

        List<String> achievement = new ArrayList<>();
        achievement.add("Организация команды и успешная реализация " +
                "Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, " +
                "система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, " +
                "многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        achievement.add("С 2013 года: разработка проектов " +
                "\"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. " +
                "XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация " +
                "онлайн стажировок и ведение проектов. Более 3500 выпускников.");
        achievement.add("Реализация двухфакторной аутентификации " +
                "для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, " +
                "Google Authenticator, Jira, Zendesk.");
        achievement.add("Налаживание процесса разработки и " +
                "непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. " +
                "Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO " +
                "аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievement.add("Реализация c нуля Rich Internet " +
                "Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, " +
                "HTML5, Highstock для алгоритмического трейдинга.");
        achievement.add("Создание JavaEE фреймворка для " +
                "отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, " +
                "AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievement.add("Реализация протоколов по приему " +
                "платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), " +
                "Белоруcсии(Erip, Osmp) и Никарагуа.");

        newResume.setSections(SectionType.ACHIEVEMENT, new ListSection(achievement));

        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, " +
                "SQLite, MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring " +
                "(MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT)," +
                "Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualifications.add("Python: Django.");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, " +
                "DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, " +
                "OAuth1, OAuth2, JWT.");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        qualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, " +
                "iReport, OpenCmis, Bonita, pgBouncer");
        qualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        qualifications.add("Родной русский, английский \"upper intermediate\"");

        newResume.setSections(SectionType.QUALIFICATIONS, new ListSection(qualifications));

        List<Period> periods = new ArrayList<>();
        periods.add(new Period("10/2013", "Сейчас", "Автор проекта.", "Создание, " +
                "организация и проведение Java онлайн проектов и стажировок."));
        Company company1 = new Company("Java Online Projects", "https://javaops.ru/", periods);

        periods = new ArrayList<>();
        periods.add(new Period("10/2014", "01/2016", "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, " +
                        "Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                        "авторизация по OAuth1, OAuth2, JWT SSO."));
        Company company2 = new Company("Wrike", "https://www.wrike.com/", periods);

        periods = new ArrayList<>();
        periods.add(new Period("04/2012", "10/2014", "Java архитектор",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, " +
                        "версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование " +
                        "системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. " +
                        "Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего " +
                        "назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online " +
                        "редактирование из браузера документов MS Office. Maven + plugin development, Ant, " +
                        "Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, " +
                        "Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"));
        Company company3 = new Company("RIT Center", null, periods);

        periods = new ArrayList<>();
        periods.add(new Period("12/2010", "04/2012", "Ведущий программист",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, " +
                        "GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация " +
                        "RIA-приложения для администрирования, мониторинга и анализа результатов в области " +
                        "алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, " +
                        "Commet, HTML5."));
        Company company4 = new Company("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/", periods);

        periods = new ArrayList<>();
        periods.add(new Period("06/2008", "12/2010", "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                        "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                        "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online " +
                        "JMX клиента (Python/ Jython, Django, ExtJS)"));
        Company company5 = new Company("Yota", "https://www.yota.ru/", periods);

        periods = new ArrayList<>();
        periods.add(new Period("12/2010", "06/2008", "Разработчик ПО",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) " +
                        "частей кластерного J2EE приложения (OLAP, Data mining)."));
        Company company6 = new Company("Enkata", "http://enkata.com/", periods);

        periods = new ArrayList<>();
        periods.add(new Period("01/2005", "02/2007", "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО " +
                        "на мобильной IN платформе Siemens @vantage (Java, Unix)."));
        Company company7 = new Company("Siemens AG", "https://www.siemens.com/global/en.html", periods);

        periods = new ArrayList<>();
        periods.add(new Period("09/1997", "01/2005", "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
        Company company8 = new Company("Alcatel", "http://www.alcatel.ru/", periods);

        List<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);
        companies.add(company3);
        companies.add(company4);
        companies.add(company5);
        companies.add(company6);
        companies.add(company7);
        companies.add(company8);

        newResume.setSections(SectionType.EXPERIENCE, new CompanySection(companies));

        periods = new ArrayList<>();
        periods.add(new Period("03/2013", "05/2013",
                "'Functional Programming Principles in Scala' by Martin Odersky"));
        Company organization1 = new Company("Coursera", "https://www.coursera.org/course/progfun", periods);

        periods = new ArrayList<>();
        periods.add(new Period("03/2011", "04/2011",
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'"));
        Company organization2 = new Company("Luxoft", "https://prmotion.me/?ID=22366", periods);

        periods = new ArrayList<>();
        periods.add(new Period("01/2005", "04/2005",
                "3 месяца обучения мобильным IN сетям (Берлин)"));
        Company organization3 = new Company("Siemens AG", "http://www.siemens.ru/", periods);

        periods = new ArrayList<>();
        periods.add(new Period("09/1997", "03/1998",
                "6 месяцев обучения цифровым телефонным сетям (Москва)"));
        Company organization4 = new Company("Alcatel", "http://www.alcatel.ru/", periods);

        periods = new ArrayList<>();
        periods.add(new Period("09/1993", "07/1996", "Аспирантура (программист С, С++)"));
        periods.add(new Period("09/1987", "07/1993", "Инженер (программист Fortran, C)"));
        Company organization5 = new Company("Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики",
                "https://itmo.ru/", periods);

        periods = new ArrayList<>();
        periods.add(new Period("09/1984", "06/1987", "Закончил с отличием"));
        Company organization6 = new Company("Заочная физико-техническая школа при МФТИ",
                "https://mipt.ru/", periods);

        List<Company> organizations = new ArrayList<>();
        organizations.add(organization1);
        organizations.add(organization2);
        organizations.add(organization3);
        organizations.add(organization4);
        organizations.add(organization5);
        organizations.add(organization6);

        newResume.setSections(SectionType.EDUCATION, new CompanySection(organizations));

        for(ContactType type : ContactType.values()) {
            System.out.print(type.getTitle() + " ");
            System.out.println(newResume.getContacts().get(type));
        }

        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
            System.out.println(newResume.getSections().get(type));
        }
    }
}