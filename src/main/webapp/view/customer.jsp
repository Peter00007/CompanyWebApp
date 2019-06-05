<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.06.2019
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.company.model.Project" %>
<%@ page import="java.util.List" %>
<%@ page import="com.company.model.Customer" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Customers</title>
</head>
<body>

<div class="w3-panel w3-blue">
    <h1 class="w3-text-orange" style="text-shadow:1px 1px 0 #444" align="center"><b>Customer</b></h1>
</div>

<div align="center">
    <form method="post">
        <%--the value of atribute 'name' is used in getParameter() method--%>
        <label>name:
            <input class="w3-input w3-animate-input" type="text" style="width:30%" name="nameOfCustomer"><br/>
        </label>

        <button class="w3-btn w3-grey w3-round-medium" type="submit" name="button" value="add">add new Customer</button>
    </form>

    <table border="1" cellpadding="5">
        <caption><h2>List of all Customer</h2></caption>
        <tr class="w3-light-grey">
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <% List<Customer> Customers = (List<Customer>) request.getAttribute("listOfCustomer");
            for (Customer s : Customers) { %>
        <tr>
            <td><%=s.getId()%>
            </td>
            <td><%=s.getName()%>
            </td>
            <td>
                <form action="customer" method="post">
                    <input type="hidden" name="customerId" value="<%=s.getId()%>"/>
                    <%--the value of atribute 'value' is used in doPost() method, in the switch statement --%>
                    <button class="w3-btn w3-blue w3-round-medium" type="submit" name="button" value="edit">edit
                    </button>
                    <button class="w3-btn w3-red w3-round-medium" type="submit" name="button" value="delete">delete
                    </button>
                </form>
            </td>
        </tr>
        <% } %>

    </table>
</div>
<div align="center">
    <form>
        <button><a href="/index.html">Back to home page</a></button>
    </form>
</div>
</body>

</html>
