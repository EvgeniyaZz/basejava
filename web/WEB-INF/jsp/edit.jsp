<%@ page import="java.lang.String" %>
<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page import="com.urise.webapp.model.SectionType" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.CompanySection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}" required
                       pattern="^[^\s]+(\s.*)?$"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <dl>
                <dt>${type.title}</dt>
                <c:if test="${section != null}">
                    <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
                    <c:choose>
                        <c:when test="${type=='PERSONAL' || type== 'OBJECTIVE'}">
                            <dd><textarea name="${type.name()}">${section}</textarea></dd>
                        </c:when>
                        <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                            <dd><textarea
                                    name="${type.name()}"><%=String.join("\n", ((ListSection) section).getList())%></textarea>
                            </dd>
                        </c:when>
                        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                            <c:forEach var="company" items="<%=((CompanySection)section).getCompanies()%>"
                                       varStatus="count">
                                <p>
                                <dd><input type="text" placeholder="Название" name="${type.name()}" size=30
                                           value="${company.homePage.name}">
                                </dd>
                                <dd><input type="text" placeholder="Сайт" name="website" size=30
                                           value="${company.homePage.website}"></dd>
                                <c:if test="${company.periods.size() != 0}">
                                    <c:forEach var="period" items="${company.periods}">
                                        <p>
                                        <dd><input type="text" placeholder="Начало"
                                                   name="${type.name()}${count.count}startDate" size=10
                                                   value="${String.format("%02d",period.startDate.month.value)}/${period.startDate.year}">
                                            <input type="text" placeholder="Окончание"
                                                   name="${type.name()}${count.count}endDate" size=10
                                                   value="${String.format("%02d",period.endDate.month.value)}/${period.endDate.year}">
                                        </dd>
                                        <dd><input type="text" placeholder="Заголовок"
                                                   name="${type.name()}${count.count}title" size=30
                                                   value="${period.title}"></dd>
                                        <dd><textarea placeholder="Описание"
                                                      name="${type.name()}${count.count}description">${period.description}</textarea>
                                        </dd>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${company.periods.size() == 0}">
                                    <p>
                                    <dd><input type="text" placeholder="Начало"
                                               name="${type.name()}${count.count}startDate" size=10
                                               value="${null}">
                                        <input type="text" placeholder="Окончание"
                                               name="${type.name()}${count.count}endDate" size=10
                                               value="${null}"></dd>
                                    <dd><input type="text" placeholder="Заголовок"
                                               name="${type.name()}${count.count}title" size=30
                                               value="${null}"></dd>
                                    <dd><textarea placeholder="Описание"
                                                  name="${type.name()}${count.count}description">${null}</textarea>
                                    </dd>
                                </c:if>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </c:if>
                <c:if test="${section == null}">
                    <c:choose>
                        <c:when test="${type=='PERSONAL' || type== 'OBJECTIVE' || type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                            <dd><textarea name="${type.name()}">${null}</textarea></dd>
                        </c:when>
                        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                            <dd><input type="text" placeholder="Название" name="${type.name()}" size=30 value="${null}">
                            </dd>
                            <dd><input type="text" placeholder="Сайт" name="website" size=30 value="${null}"></dd>
                            <p>
                            <dd><input type="text" placeholder="Начало" name="${type.name()}1startDate" size=10
                                       value="${null}">
                                <input type="text" placeholder="Окончание" name="${type.name()}1endDate" size=10
                                       value="${null}"></dd>
                            <dd><input type="text" placeholder="Заголовок" name="${type.name()}1title" size=30
                                       value="${null}"></dd>
                            <dd><textarea placeholder="Описание" name="${type.name()}1description">${null}</textarea>
                            </dd>
                        </c:when>
                    </c:choose>
                </c:if>
            </dl>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>