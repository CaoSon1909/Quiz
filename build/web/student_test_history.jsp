<%-- 
    Document   : student_test_history
    Created on : Feb 3, 2021, 4:05:02 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test History Page</title>
    </head>
    <body>
        <jsp:include page="welcome_header.jsp"/>
        <c:if test="${not empty requestScope.TEST_RESULT_LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Student Email</th>
                        <th>Test ID</th>
                        <th>Attemp Date</th>
                        <th>Correct Answer Number</th>
                        <th>Student Answer Choice</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="result" items="${requestScope.TEST_RESULT_LIST}">
                        <tr>
                            <td>
                                ${result.studentEmail}
                            </td>
                            <td>
                                ${result.testID}
                            </td>
                            <td>
                                ${result.attempDate}
                            </td>
                            <td>
                                ${result.correctAnswerNumber}
                            </td>
                            <td>
                                ${result.studentChoice}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty requestScope.RESULT_MESSAGE}">
            ${requestScope.RESULT_MESSAGE}
        </c:if>
        <c:url var="backlink" value="search_subject_to_attemp_quiz">
            <c:param name="subjectID" value="${requestScope.SUBJECT_ID}"/>
        </c:url>
        <a href="${backlink}">Back</a>
    </body>
</html>
