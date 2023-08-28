package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;

public class ResumeTestData {
    public Resume createResume(String fullName) {
        Resume newResume = new Resume(fullName);

        newResume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        newResume.addContact(ContactType.SKYPE, "grigory.kislin");
        newResume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        newResume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        newResume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        newResume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        newResume.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        newResume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры."));
        newResume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения " +
                "по Java Web и Enterprise технологиям."));

        newResume.addSection(SectionType.ACHIEVEMENT,
                new ListSection(
                        "Организация команды и успешная реализация " +
                                "Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/" +
                                "микросервисы, система мониторинга показателей спортсменов на Spring Boot, " +
                                "участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект " +
                                "для комплексных DIY смет",
                        "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение " +
                                "проектов. Более 3500 выпускников.",
                        "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                        "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления " +
                                "окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и " +
                                "авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                        "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
                                "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                        "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов " +
                                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и " +
                                "информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента " +
                                "для администрирования и мониторинга системы по JMX (Jython/ Django).",
                        "Реализация протоколов по приему платежей всех основных платежных системы России " +
                                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."
                ));

        newResume.addSection(SectionType.ACHIEVEMENT,
                new ListSection(
                        "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                        "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, " +
                        "SQLite, MS SQL, HSQLDB",
                        "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",
                        "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                        "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring " +
                                "(MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT" +
                                "(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, " +
                                "Selenium (htmlelements).",
                        "Python: Django.",
                        "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                        "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                        "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, " +
                                "DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, " +
                                "BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                        "Инструменты: Maven + plugin development, Gradle, настройка Ngnix",
                        "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, " +
                                "iReport, OpenCmis, Bonita, pgBouncer",
                        "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                                "архитектурных шаблонов, UML, функционального программирования",
                        "Родной русский, английский \"upper intermediate\""
                ));

        newResume.addSection(SectionType.EXPERIENCE,
                new CompanySection(
                        new Company("Java Online Projects", "https://javaops.ru/",
                                new Company.Period(2013, Month.OCTOBER, "Автор проекта.",
                                        "Создание, организация и проведение Java онлайн проектов и стажировок."))
                ));
        newResume.addSection(SectionType.EXPERIENCE,
                new CompanySection(
                        new Company("Wrike", "https://www.wrike.com/",
                                new Company.Period(2014, Month.OCTOBER , 2016, Month.JANUARY,
                                        "Старший разработчик (backend)", "Проектирование и разработка " +
                                        "онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, " +
                                        "MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                                        "авторизация по OAuth1, OAuth2, JWT SSO."))
                ));
        newResume.addSection(SectionType.EXPERIENCE,
                new CompanySection(
                        new Company("RIT Center", null,
                                new Company.Period(2012, Month.APRIL,2014, Month.OCTOBER,
                                        "Java архитектор","Организация процесса разработки системы ERP " +
                                        "для разных окружений: релизная политика, версионирование, " +
                                        "ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование " +
                                        "системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части " +
                                        "системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), " +
                                        "сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция " +
                                        "Alfresco JLAN для online редактирование из браузера документов MS Office. " +
                                        "Maven + plugin development, Ant, Apache Commons, Spring security, " +
                                        "Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, " +
                                        "Unix shell remote scripting via ssh tunnels, PL/Python"))
                ));
        newResume.addSection(SectionType.EXPERIENCE,
                new CompanySection(
                        new Company("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                                new Company.Period(2010, Month.DECEMBER, 2012, Month.APRIL,
                                        "Ведущий программист", "Участие в проекте Deutsche Bank CRM " +
                                        "(WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). " +
                                        "Реализация клиентской и серверной части CRM. Реализация RIA-приложения для " +
                                        "администрирования, мониторинга и анализа результатов в области " +
                                        "алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), " +
                                        "Highstock, Commet, HTML5."))
                ));
        newResume.addSection(SectionType.EXPERIENCE,
                new CompanySection(
                        new Company("Yota", "https://www.yota.ru/",
                                new Company.Period(2008, Month.JUNE, 2010, Month.DECEMBER,
                                        "Ведущий специалист", "Дизайн и имплементация Java EE " +
                                        "фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, " +
                                        "JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация " +
                                        "администрирования, статистики и мониторинга фреймворка. Разработка online " +
                                        "JMX клиента (Python/ Jython, Django, ExtJS)"))
                ));
        newResume.addSection(SectionType.EXPERIENCE,
                new CompanySection(
                        new Company("Enkata", "http://enkata.com/",
                                new Company.Period(2010, Month.DECEMBER, 2008, Month.JUNE,
                                        "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и " +
                                        "серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного " +
                                        "J2EE приложения (OLAP, Data mining)."))
                ));
        newResume.addSection(SectionType.EXPERIENCE,
                new CompanySection(
                        new Company("Siemens AG", "https://www.siemens.com/global/en.html",
                                new Company.Period(2005, Month.JANUARY, 2007, Month.FEBRUARY,
                                        "Разработчик ПО", "Разработка информационной модели, " +
                                        "проектирование интерфейсов, реализация и отладка ПО на мобильной IN " +
                                        "платформе Siemens @vantage (Java, Unix)."))
                ));
        newResume.addSection(SectionType.EXPERIENCE,
                new CompanySection(
                        new Company("Alcatel", "http://www.alcatel.ru/",
                                new Company.Period(1997, Month.SEPTEMBER, 2005, Month.JANUARY,
                                        "Инженер по аппаратному и программному тестированию",
                                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции " +
                                                "Alcatel 1000 S12 (CHILL, ASM)."))
                ));

        newResume.addSection(SectionType.EDUCATION,
                new CompanySection(
                        new Company("Coursera", "https://www.coursera.org/course/progfun",
                                new Company.Period(2013, Month.MARCH, 2013, Month.MAY,
                                        "'Functional Programming Principles in Scala' by Martin Odersky",
                                        null))
                ));
        newResume.addSection(SectionType.EDUCATION,
                new CompanySection(
                        new Company("Luxoft", "https://prmotion.me/?ID=22366",
                                new Company.Period(2011, Month.MARCH, 2011, Month.APRIL,
                                        "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'",
                                        null))
                ));
        newResume.addSection(SectionType.EDUCATION,
                new CompanySection(
                        new Company("Siemens AG", "http://www.siemens.ru/",
                                new Company.Period(2005, Month.JANUARY, 2005, Month.APRIL,
                                        "3 месяца обучения мобильным IN сетям (Берлин)", null))
                ));
        newResume.addSection(SectionType.EDUCATION,
                new CompanySection(
                        new Company("Alcatel", "http://www.alcatel.ru/",
                                new Company.Period(1997, Month.SEPTEMBER,1998, Month.MARCH,
                                        "6 месяцев обучения цифровым телефонным сетям (Москва)", null))
                ));
        newResume.addSection(SectionType.EDUCATION,
                new CompanySection(
                        new Company("Санкт-Петербургский национальный исследовательский университет " +
                                "информационных технологий, механики и оптики", "https://itmo.ru/",
                                new Company.Period(1993, Month.SEPTEMBER, 1996, Month.JULY,
                                        "Аспирантура (программист С, С++)", null),
                                new Company.Period(1987, Month.SEPTEMBER, 1993, Month.JULY,
                                        "Инженер (программист Fortran, C)", null))
                ));
        newResume.addSection(SectionType.EDUCATION,
                new CompanySection(
                        new Company("Заочная физико-техническая школа при МФТИ", "https://mipt.ru/",
                                new Company.Period(1984, Month.SEPTEMBER, 1987, Month.JUNE,
                                        "Закончил с отличием", null))
                ));
        return newResume;
    }
}