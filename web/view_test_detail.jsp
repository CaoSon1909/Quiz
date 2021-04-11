<%-- 
    Document   : view_test_detail
    Created on : Feb 2, 2021, 8:41:07 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Test Page</title>
    </head>
    <body>
        <jsp:include page="welcome_header.jsp"/> <br/>
        <c:if test="${not empty requestScope.QUESTION_MAP}">
            The Test ID: ${requestScope.QUESTION_MAP.key} <br/>
            <table border="1">
                <thead>
                    <tr>
                        <th>Question ID</th>
                        <th>Subject Name</th>
                        <th>Question Content</th>
                        <th>Answer Content 1</th>
                        <th>Answer Content 2</th>
                        <th>Answer Content 3</th>
                        <th>Answer Content 4</th>
                        <th>Correct Answer</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            ${requestScope.QUESTION_MAP.value.ID}
                        </td>
                        <td>
                            <c:if test="${requestScope.QUESTION_MAP.value.subjectID eq '1'}">
                                Java
                            </c:if>
                            <c:if test="${requestScope.QUESTION_MAP.value.subjectID eq '2'}">
                                C#
                            </c:if>
                            <c:if test="${requestScope.QUESTION_MAP.value.subjectID eq '3'}">
                                Japanese
                            </c:if>
                        </td>
                        <td>
                            ${requestScope.QUESTION_MAP.value.questionContent}
                        </td>
                        <td>${requestScope.QUESTION_MAP.value.answerContent1}</td>
                        <td>${requestScope.QUESTION_MAP.value.answerContent2}</td>
                        <td>${requestScope.QUESTION_MAP.value.answerContent3}</td>
                        <td>${requestScope.QUESTION_MAP.value.answerContent4}</td>
                        <td>${requestScope.QUESTION_MAP.value.correctAnswer}</td>
                    </tr>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty requestScope.QUESTION_MAP}">
            Opps, something went wrong. Please try again later.
        </c:if>
        <br/>
        <a href="load_test_list">Back</a>
    </body>
</html>
