<%-- 
    Document   : welcome_header
    Created on : Jan 21, 2021, 2:02:33 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER_DTO.fullName}"/>
        <h1>
            Welcome , ${user}
        </h1>
        <a href="logout">Log Out</a>
    </body>
</html>
