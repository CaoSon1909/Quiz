<%-- 
    Document   : register
    Created on : Jan 26, 2021, 4:09:44 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Register an account to take quiz</h1>
        <form action="register" method="POST">
            Email: <input type="text" name="txtEmail" value="${param.txtEmail}" /> <br/>
            <font color="red">
            <c:if test="${not empty requestScope.ERROR['EMAIL_REGEX_ERR']}">
                ${requestScope.ERROR['EMAIL_REGEX_ERR']}
            </c:if>
            </font>  <br/>
            Password: <input type="password" name="txtPassword" value="" /> <br/>
            <font color="red">
            <c:if test="${not empty requestScope.ERROR['PASSWORD_LENGTH_ERR']}">
                ${requestScope.ERROR['PASSWORD_LENGTH_ERR']}
            </c:if>
            </font> <br/>
            Confirm password: <input type="password" name="txtConfirmPass" value="" /> <br/>
            <font color="red">
            <c:if test="${not empty requestScope.ERROR['CONFIRM_PASS_ERR']}">
                ${requestScope.ERROR['CONFIRM_PASS_ERR']}
            </c:if>
            </font> <br/>
            Full Name: <input type="text" name="txtFullName" value="${param.txtFullName}" /> <br/>
            <font color="red">
            <c:if test="${not empty requestScope.ERROR['FULLNAME_ERR']}">
                ${requestScope.ERROR['FULLNAME_ERR']}
            </c:if>
            </font> <br/>
            <input type="submit" value="Register" name="btAction" /> 
            <input type="reset" value="Reset" name="btAction" />
            <br/>
            <font color="red">
            <c:if test="${not empty requestScope.ERROR['DUPLICATED_EMAIL']}">
                ${requestScope.ERROR['DUPLICATED_EMAIL']}
            </c:if>
            </font>
        </form>
        <a href="login_page">Back to login page</a>
    </body>
</html>
