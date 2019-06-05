<%@ page import="com.company.model.Customer" %>
<%@ page import="com.company.model.Project" %>
<%@ page import="com.company.service.CustomerService" %>
<%@ page import="com.company.service.ProjectService" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: maya
  Date: 06.05.19
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Title</title>
</head>
<body>
<div align="center">
    <form action="project" method="post">

        <table border="1" cellpadding="10">
            <caption><h3>Edit Project</h3></caption>
            <% ProjectService pr = new ProjectService();
                int idForEdit = Integer.parseInt(request.getParameter("projectId"));
                Project projectForEdit = pr.getById(idForEdit);%>
            <tr>
                <th>id of Project:</th>
                <td>
                    <input type="text" name="idOfUpdatedProject" size="30" value="<%= projectForEdit.getId()%>"
                           readonly/>
                </td>
            </tr>
            <tr>
                <th>name of Project:</th>
                <td>
                    <input type="text" name="nameOfUpdatedProject" size="30" value="<%= projectForEdit.getName()%>"/>
                </td>
            </tr>
            <tr>
                <th>budget of Project:</th>
                <td>
                    <input type="text" name="budget" size="30" value="<%= projectForEdit.getBudget()%>"/>
                </td>
            </tr>
            <label>Customer for project:
                <table>
                    <% CustomerService tr = new CustomerService();
                        List<Customer> customers = tr.getAll();
                        for (Customer t : customers) { %>
                    <tr>
                        <td><input type="checkbox" name="names" value="<%=t.getId()%>"><%=t.getName()%>
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
        <button><a href="/project">Back to project home page</a></button>
    </form>
</div>
</body>
</html>