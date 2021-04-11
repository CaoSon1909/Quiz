<%-- 
    Document   : student
    Created on : Jan 21, 2021, 1:42:57 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Page</title>
    </head>
    <body>
        <jsp:include page="welcome_header.jsp"/> <br/>
        <c:if test="${not empty sessionScope.USER_DTO}">
            This is user page
            <br/>
            <form action="search_subject_to_attemp_quiz">
                Choose a subject: 
                <c:if test="${not empty requestScope.SUBJECT_LIST}">
                    <select name="subjectID">
                        <c:forEach var="subject_ID" items="${requestScope.SUBJECT_LIST}">
                            <option value="${subject_ID.id}">
                                ${subject_ID.name}
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
                <br/>
                <input type="submit" value="Search" name="btAction" />
                <c:url var="testHistory" value="student_test_history">
                    <c:param name="subjectID" value="${param.subjectID}"/>
                </c:url>
                <a href="${testHistory}">Show test history</a> <br/>
            </form> <br/>
            <c:if test="${not empty requestScope.FOUND_MESSAGE}">
                ${requestScope.FOUND_MESSAGE}
            </c:if>
            <br/>
            <c:if test="${not empty requestScope.TEST_LIST}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Test Name</th>
                            <th>Number of Questions</th>
                            <th>Create Date</th>
                            <th>Creator</th>
                            <th>Subject Name</th>
                            <th>Duration(Mins)</th>
                            <th>Available Duration(Mins)</th>
                            <th>Attemp Quiz</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="test" items="${requestScope.TEST_LIST}">
                        <form action="attempt_quiz">
                            <tr>
                                <td>
                                    ${test.name}
                                </td>
                                <td>
                                    ${test.numOfQuestion}
                                </td>
                                <td>
                                    ${test.createDate}
                                </td>
                                <td>
                                    ${test.adminEmail}
                                </td>
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
                                <td>
                                    ${test.length}
                                </td>
                                <td>
                                    ${test.availableLength}
                                </td>
                                <td>
                                    <input type="hidden" name="txtTestID" value="${test.ID}" />
                                    <input type="hidden" name="txtTestName" value="${test.name}" />
                                    <input type="hidden" name="txtNumOfQues" value="${test.numOfQuestion}" />
                                    <input type="hidden" name="txtSubjectID" value="${test.subjectID}" />
                                    <input type="hidden" name="txtDuration" value="${test.length}" />
                                    <input type="hidden" name="txtAvailable" value="${test.availableLength}" />
                                    <input type="submit" value="Attemp Quiz" name="btAction" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </c:if>
    <c:if test="${empty sessionScope.USER_DTO}">
        <c:url var="loginPage" value="login_page"></c:url>
        <c:redirect url="${loginPage}"></c:redirect>
    </c:if>
</body>
</html>
