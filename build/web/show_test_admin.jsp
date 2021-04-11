<%-- 
    Document   : show_test_admin
    Created on : Feb 1, 2021, 3:31:16 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Test Page</title>
    </head>
    <body>
        <jsp:include page="welcome_header.jsp"/> <br/>
        <c:if test="${not empty requestScope.TEST_STATUS}">
            ${requestScope.TEST_STATUS}
        </c:if>
        List of Test That Created By Admin: ${sessionScope.USER_DTO.fullName} <br/>
        <c:if test="${not empty requestScope.TEST_LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Test Name</th>
                        <th>Number of questions</th>
                        <th>Create Date</th>
                        <th>Subject</th>
                        <th>Length Of Test</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="test" items="${requestScope.TEST_LIST}">
                    <form action="view_test_detail">
                        <tr>
                            <td>${test.name}</td>
                            <td>${test.numOfQuestion}</td>
                            <td>${test.createDate}</td>
                            <td>
                                <c:if test="${test.subjectID eq '1'}">
                                    Java
                                </c:if>
                                <c:if test="${test.subjectID eq '2'}">
                                    C#
                                </c:if>
                                <c:if test="${test.subjectID eq '3'}">
                                    Japanese
                                </c:if>
                            </td>
                            <td>${test.length}</td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>

    </c:if>
    <c:if test="${empty requestScope.TEST_LIST}">
        Something gone wrong. Please try again later!
    </c:if>
    <a href="load_question">Back to main page</a>
</body>
</html>
