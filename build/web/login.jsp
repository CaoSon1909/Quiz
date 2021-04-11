<%-- 
    Document   : login
    Created on : Jan 21, 2021, 1:07:51 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>
            <font color="blue">
            Quiz Now
            </font>
        </h1>
        <c:if test="${not empty requestScope.LOGIN_ERR}">
            ${requestScope.LOGIN_ERR}
        </c:if>
        <form action="login" method="POST">
            Email: <input type="text" name="txtEmail" value="" /> <br/>
            Password: <input type="password" name="txtPassword" value="" /> <br/>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" name="btAction" />
        </form>
        <a href="register_page">Register</a>
        


    </body>
</html>
