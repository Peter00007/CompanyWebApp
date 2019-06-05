<%@ page import="com.company.model.Customer" %>
<%@ page import="com.company.service.CustomerService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Title</title>
</head>
<body>
<div align="center">
    <form action="customer" method="post">

        <table border="1" cellpadding="10">
            <caption><h3>Edit Customer</h3></caption>
            <% CustomerService cr = new CustomerService();
                int idForEdit = Integer.parseInt(request.getParameter("customerId"));
                Customer customerForEdit = cr.getById(idForEdit);%>
            <tr>
                <th>id of customer:</th>
                <td>
                    <input type="text" name="idOfUpdatedCustomer" size="30" value="<%= customerForEdit.getId()%>"
                           readonly/>
                </td>
            </tr>
            <tr>
                <th>name of Customer:</th>
                <td>
                    <input type="text" name="nameOfUpdatedCustomer" size="30" value="<%= customerForEdit.getName()%>"/>
                </td>
            </tr>
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
        <button><a href="/customer">Back to customer home page</a></button>
    </form>
</div>
</body>
</html>