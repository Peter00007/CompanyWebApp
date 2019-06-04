<%@ page import="com.company.service.SkillService" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.05.2019
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="ISO-8859-1">
    <title>Skills page</title>
</head>
<body>
<h2>Skills page</h2>
<% SkillService skillService = new SkillService(); %>
<table cellpadding="2" cellspacing="2" border="1">
    <tr>
        <th>ID</th>
        <th>NAME</th>
    </tr>
    <c:forEach items="<%= skillService.getAll() %>" var="skill">
        <tr>
            <td>${skill.id}</td>
            <td>${skill.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
