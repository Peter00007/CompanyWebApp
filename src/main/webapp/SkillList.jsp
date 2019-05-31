<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Skill Store Application</title>
</head>
<body>
<center>
    <h1>Skill Management</h1>
    <h2>
        <a href="/new">Add New Skill</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Skills</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Skills</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        <c:forEach var="skill" items="${listSkill}">
            <tr>
                <td><c:out value="${skill.id}"/></td>
                <td><c:out value="${skill.name}"/></td>
                <td>
                    <a href="/edit?id=<c:out value='${skill.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete?id=<c:out value='${skill.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>