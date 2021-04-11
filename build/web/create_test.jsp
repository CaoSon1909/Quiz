<%-- 
    Document   : create_test
    Created on : Jan 27, 2021, 11:18:59 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Test Page</title>
    </head>
    <body>
        <jsp:include page="welcome_header.jsp"/>
        <h1>Create A Test</h1>
        <c:set var="subject" value="${requestScope.SUBJECT_LIST}"/>
        <c:if test="${not empty subject}">
            <form action="create_test" method="POST">
                Test name: <input type="text" name="txtTestName" value="${param.txtTestName}" /> <br/>
                <font color="red">
                <c:if test="${not empty requestScope.ERROR['TEST_NAME_ERR']}">
                    ${requestScope.ERROR['TEST_NAME_ERR']}
                </c:if>
                </font>  <br/>
                Number of questions: <input type="text" name="txtNumOfQues" value="${param.txtNumOfQues}" /> <br/>
                <font color="red">
                <c:if test="${not empty requestScope.ERROR['NUMBER_OF_QUESTION_ERR']}">
                    ${requestScope.ERROR['NUMBER_OF_QUESTION_ERR']}
                </c:if>
                </font>  <br/>
                Select a subject:
                <select name="subjectID">
                    <c:forEach var="subject_ID" items="${subject}">
                        <option value="${subject_ID.id}">
                            ${subject_ID.name}
                        </option>
                    </c:forEach>
                </select> <br/>
                Test Length (Minutes): <input type="number" name="txtTestLength" value="${param.txtTestLength}" min="1" max="100"  /> <br/>
                <font color="red">
                <c:if test="${not empty requestScope.ERROR['TEST_LENGTH_ERR']}">
                    ${requestScope.ERROR['TEST_LENGTH_ERR']}
                </c:if>
                </font>  <br/>
                Avaiable In (Minutes): <input type="text" name="txtAvailableLength" value="${param.txtAvailableLength}" />
                <font color="red">
                <c:if test="${not empty requestScope.ERROR['AVAILABLE_LENGTH_ERR']}">
                    ${requestScope.ERROR['AVAILABLE_LENGTH_ERR']}
                </c:if>
                </font>  <br/>
                <font color="red">
                <c:if test="${not empty requestScope.ERROR['LOGIC_ERR']}">
                    ${requestScope.ERROR['LOGIC_ERR']}
                </c:if>
                </font>  <br/>
                <input type="submit" value="Create Test" name="btAction" />
                <input type="reset" value="Reset" name="btAction" />
            </form>
            <c:if test="${not empty requestScope.NOT_ENOUGH_QUESTION && 
                          not empty requestScope.EXISTING_QUESTION}">
                  ${requestScope.NOT_ENOUGH_QUESTION} <br/>
                  Số lượng câu hỏi hiện tại:
                  ${requestScope.EXISTING_QUESTION} <br/>
            </c:if>
        </c:if>
        <c:if test="${empty subject}">
            Opps, there is some errors occur with subject. Please try again later.
        </c:if>
        <c:url var="back_link" value="load_question">

        </c:url>
        <a href="${back_link}">Back to admin page</a>
    </body>
</html>
