<%-- 
    Document   : create_question
    Created on : Jan 25, 2021, 8:05:20 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Question Page</title>
    </head>
    <body>
        <h1>Create New Question</h1>
        <c:set var="subject_list" value="${requestScope.SUBJECT_LIST}"/>
        <c:if test="${not empty subject_list}">
            <form action="create_question">
                Question Content: <input type="text" name="txtQuestionContent" value="${param.txtQuestionContent}" /> <br/>
                <c:if test="${not empty requestScope.ERROR['QUESTION_CONTENT_ERR']}">
                    ${requestScope.ERROR['QUESTION_CONTENT_ERR']}
                </c:if> <br/>
                Answer Content 1: <input type="text" name="txtAnswerContent1" value="${param.txtAnswerContent1}" /> <br/>
                <c:if test="${not empty requestScope.ERROR['ANSWER_CONTENT_ERR1']}">
                    ${requestScope.ERROR['ANSWER_CONTENT_ERR1']}
                </c:if> <br/>
                Answer Content 2: <input type="text" name="txtAnswerContent2" value="${param.txtAnswerContent2}" /> <br/>
                <c:if test="${not empty requestScope.ERROR['ANSWER_CONTENT_ERR2']}">
                    ${requestScope.ERROR['ANSWER_CONTENT_ERR2']}
                </c:if> <br/>
                Answer Content 3: <input type="text" name="txtAnswerContent3" value="${param.txtAnswerContent3}" /> <br/>
                <c:if test="${not empty requestScope.ERROR['ANSWER_CONTENT_ERR3']}">
                    ${requestScope.ERROR['ANSWER_CONTENT_ERR3']}
                </c:if> <br/>
                Answer Content 4: <input type="text" name="txtAnswerContent4" value="${param.txtAnswerContent4}" /> <br/>
                <c:if test="${not empty requestScope.ERROR['ANSWER_CONTENT_ERR4']}">
                    ${requestScope.ERROR['ANSWER_CONTENT_ERR4']}
                </c:if> <br/>
                Answer Correct: 
                <select name="answerCorrect">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                </select> <br/>
                Subject:
                <select name="subjectID">
                    <c:forEach var="subject" items="${subject_list}">
                        <option value="${subject.id}">
                            ${subject.name}
                        </option>
                    </c:forEach>
                </select> <br/>
                <input type="submit" value="Create Question" name="btAction" />
                <input type="reset" value="Reset" name="btAction" />
            </form>
        </c:if>
        <c:if test="${empty subject_list}">
            Oops, There is some error occured when loading subject list. Please try again later.
        </c:if>
        <c:url var="back_link" value="load_question">

        </c:url>
        <a href="${back_link}">Back to admin page</a>
    </body>
</html>
