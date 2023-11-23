<%@ page import="java.lang.String" %>
<%@ page import="com.urise.webapp.model.CompanySection" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>
</section>
<section>
    <p>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.Section>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
    <h3>${type.title}</h3>
    <c:choose>
        <c:when test="${type=='PERSONAL' || type== 'OBJECTIVE'}">
            <div>${sectionEntry.value}</div>
        </c:when>
        <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
            <div><%=String.join("<br/>", ((ListSection) sectionEntry.getValue()).getList())%>
            </div>
        </c:when>
        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
            <table>
                <c:forEach var="company" items="<%=((CompanySection) sectionEntry.getValue()).getCompanies()%>">
                    <p>
                    <div><a href="${company.homePage.website}">${company.homePage.name}</a></div>
                    <c:forEach var="period" items="${company.periods}">
                        <p>
                        <c:if test="${period.endDate < LocalDate.now()}">
                            <div>${String.format("%02d",period.startDate.month.value)}/${period.startDate.year}
                                - ${String.format("%02d",period.endDate.month.value)}/${period.endDate.year}</div>
                        </c:if>
                        <c:if test="${period.endDate >= LocalDate.now()}">
                            <div>${String.format("%02d",period.startDate.month.value)}/${period.startDate.year} - Cейчас</div>
                        </c:if>
                        <div>${period.title}</div>
                        <div>${period.description}</div>
                    </c:forEach>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>