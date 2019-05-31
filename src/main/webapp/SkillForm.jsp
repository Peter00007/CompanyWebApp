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
    <c:if test="${skill != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${skill == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${skill != null}">
                            Edit Skill
                        </c:if>
                        <c:if test="${skill == null}">
                            Add New Skill
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${skill != null}">
                    <input type="hidden" name="id" value="<c:out value='${skill.id}' />"/>
                </c:if>
                <tr>
                    <th>Name:</th>
                    <td>
                        <input type="text" name="title" size="45"
                               value="<c:out value='${skill.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>