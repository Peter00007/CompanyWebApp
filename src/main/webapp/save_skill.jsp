<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.05.2019
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save skill</title>
</head>
<body>
<h2>Save skill</h2>
<section>
    <jsp:useBean id="skill" scope="request" type="com.company.controller.SkillServlet"/>
    <form method="post" action="skills.jsp?action=submit">
        <dl>
            <dt>ID: </dt>
            <dd><input type="number" name="id" value="${skill.id}" placeholder="${skill.id}" /></dd>
        </dl>
        <dl>
            <dt>Name: </dt>
            <dd><input type="text" name="name" value="${skill.name}" placeholder="${skill.name}" /></dd>
        </dl>
        <button type="submit">Save</button>
    </form>
</section>
</body>
</html>
