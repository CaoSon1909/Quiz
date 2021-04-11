<%-- 
    Document   : do_test
    Created on : Feb 2, 2021, 10:15:10 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Do Test Page</title>
    </head>
    <body>
        <jsp:include page="welcome_header.jsp"/> <br/>
        <!------------------------------------------------->
        The test ID:
        <c:if test="${not empty requestScope.TEST_ID}">
            <input type="text" name="txtTestID" value="${requestScope.TEST_ID}" readonly="readonly" />
        </c:if> <br/>
        <!------------------------------------------------->
        Test Name:
        <c:if test="${not empty requestScope.TEST_NAME}">
            <input type="text" name="txtTestName" value="${requestScope.TEST_NAME}" readonly="readonly" />
        </c:if> <br/>
        <!------------------------------------------------->
        Number of question:
        <c:if test="${not empty requestScope.NUM_OF_QUES}">
            <input type="text" name="txtNumOfQues" value="${requestScope.NUM_OF_QUES}" readonly="readonly" /> 
        </c:if> <br/>
        <!------------------------------------------------->
        Subject:
        <c:if test="${not empty requestScope.SUBJECT_ID 
                      && requestScope.SUBJECT_ID eq '1'}">
              <input type="text" name="txtSubjectName" value="Java" readonly="readonly" />
        </c:if> 
        <c:if test="${not empty requestScope.SUBJECT_ID 
                      && requestScope.SUBJECT_ID eq '2'}">
              <input type="text" name="txtSubjectName" value="C#" readonly="readonly" />
        </c:if> 
        <c:if test="${not empty requestScope.SUBJECT_ID 
                      && requestScope.SUBJECT_ID eq '3'}">
              <input type="text" name="txtSubjectName" value="Japanese" readonly="readonly" />
        </c:if> <br/>
        <!------------------------------------------------->
        <!--Duration-->
        <c:if test="${not empty requestScope.DURATION}">
            Duration (Minutes):
            <input id="timeDuration" type="text" name="txtTimeDuration" value="${requestScope.DURATION}" readonly="readonly" />
        </c:if> <br/>
        <!--Available-->
        Available (Minutes):
        <c:if test="${not empty requestScope.AVAIABLE}">
            <input type="text" name="txtAvaiable" value="${requestScope.AVAIABLE}" readonly="readonly" />
        </c:if>
        <br/>
        <!------------------------------------------------->
        <c:if test="${not empty requestScope.QUESTION_LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Question ID</th>
                        <th>Subject Name</th>
                        <th>Question Content</th>
                        <th>Answer 1</th>
                        <th>Answer 2</th>
                        <th>Answer 3</th>
                        <th>Answer 4</th>
                        <th>Correct Answer</th>
                        <th>Submit</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="question" items="${requestScope.QUESTION_LIST}">
                    <form action="submit_test">
                        <tr>
                            <td>
                                ${question.ID}
                            </td>
                            <td>
                                <c:if test="${question.subjectID eq '1'}">
                                    <input type="text" name="txtSubjectName" value="Java" readonly="readonly" />
                                </c:if>
                                <c:if test="${question.subjectID eq '2'}">
                                    <input type="text" name="txtSubjectName" value="C#" readonly="readonly" />
                                </c:if>
                                <c:if test="${question.subjectID eq '3'}">
                                    <input type="text" name="txtSubjectName" value="Japanese" readonly="readonly" />
                                </c:if>
                            </td>
                            <td>
                                ${question.questionContent}
                            </td>
                            <td>
                                ${question.answerContent1}
                            </td>
                            <td>
                                ${question.answerContent2}
                            </td>
                            <td>
                                ${question.answerContent3}
                            </td>
                            <td>
                                ${question.answerContent4}
                            </td>
                            <td>
                                <select name="studentChooseAnswer">
                                    <option value="1">Answer 1</option>
                                    <option value="2">Answer 2</option>
                                    <option value="3">Answer 3</option>
                                    <option value="4">Answer 4</option>
                                </select>
                            </td>
                            <td>
                                <!--//param for session tracking-->
                                <input type="hidden" name="txtNumOfQues" value="${requestScope.NUM_OF_QUES}" />
                                <input type="hidden" name="txtSubjectID" value="${question.subjectID}" />
                                <input type="hidden" name="txtDuration" value="${requestScope.DURATION}" />
                                <input type="hidden" name="txtAvaiableLength" value="${requestScope.AVAIABLE}" />
                                <!--//parameter for grading-->
                                <input type="hidden" name="txtQuestionID" value="${question.ID}" />
                                <!--//parameter for insert tbl_StudentTest-->
                                <input type="hidden" name="txtStudentEmail" value="${sessionScope.USER_DTO.email}" />
                                <input type="hidden" name="txtTestID" value="${requestScope.TEST_ID}" />
                                <!--//button submit-->
                                <input type="submit" value="Submit" name="btAction" id="submit_button" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <!------------------------------------------------->
    <c:if test="${not empty requestScope.SUBMIT_MESSAGE}">
        ${requestScope.SUBMIT_MESSAGE}
    </c:if>
    <!------------------------------------------------->



    <!--auto submit after n second-->
    <!--        <script type="text/javascript">
    
                setTimeout("CallButton()", 3000)
                function CallButton()
                {
                    document.getElementById("submit_button").click();
                }
            </script>-->
    <c:url var="backLink" value="search_subject_to_attemp_quiz">
        <c:param name="btAction" value="Search"/>
        <c:param name="subjectID" value="${requestScope.SUBJECT_ID}"/>
    </c:url>
    <a href="${backLink}">Back To Main Page</a>
</body>
</html>
