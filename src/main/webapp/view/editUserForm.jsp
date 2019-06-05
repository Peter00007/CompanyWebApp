<%@ page import="com.company.model.Skill" %>
<%@ page import="com.company.model.Team" %>
<%@ page import="com.company.model.User" %>
<%@ page import="com.company.service.SkillService" %>
<%@ page import="com.company.service.TeamService" %>
<%@ page import="com.company.service.UserService" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: maya
  Date: 06.05.19
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>User edit form</title>
</head>
<body>
<div align="center">
    <form action="user" method="post">

        <table border="1" cellpadding="10">
            <caption><h3>Edit user</h3></caption>
            <% UserService userService = new UserService();
                int idForEdit = Integer.parseInt(request.getParameter("userId"));
                User userForEdit = userService.getById(idForEdit);%>
            <tr>
                <th>Id of user:</th>
                <td>
                    <input type="text" name="idOfUpdatedUser" size="30" value="<%= userForEdit.getId()%>" readonly/>
                </td>
            </tr>

            <tr>
                <th>First name:</th>
                <td>
                    <input type="text" name="firstName" size="30" value="<%= userForEdit.getFirstName()%>"/>
                </td>
            </tr>
            <tr>
                <th>Last name:</th>
                <td>
                    <input type="text" name="lastName" size="30" value="<%= userForEdit.getLastName()%>"/>
                </td>
            </tr>
            <tr>
                <th>Specialty:</th>
                <td>
                    <input type="text" name="specialty" size="30" value="<%= userForEdit.getSpecialty()%>"/>
                </td>
            </tr>
            <label>Skills:
                <table>
                    <% SkillService skillService = new SkillService();
                        List<Skill> skills = skillService.getAll();
                        for (Skill s : skills) { %>
                    <tr>
                        <td><input type="checkbox" name="names" value="<%=s.getId()%>"><%=s.getName()%>
                        </td>
                    </tr>
                    <%}%>
                </table>
            </label>

            <label>Team(choice 1 checkbox please):
                <table>
                    <% TeamService teamService = new TeamService();
                        List<Team> teams = teamService.getAll();
                        for (Team s : teams) { %>
                    <tr>
                        <td><input type="checkbox" name="team"
                                   value="<%=s.getId()%>"><%=s.getName() + ", " + s.getProject()%>
                        </td>
                    </tr>
                    <%}%>
                </table>
            </label>
            <tr>
                <td colspan="2" align="center">
                    <button class="w3-btn w3-deep-purple w3-round-medium" type="submit" name="button" value="update">
                        update
                    </button>
                </td>
            </tr>
        </table>
    </form>
</div>
<div align="center">
    <form>
        <button><a href="/user">Back to user home page</a></button>
    </form>
</div>
</body>
</html>