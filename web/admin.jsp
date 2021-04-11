<%-- 
    Document   : admin
    Created on : Jan 21, 2021, 1:42:47 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <jsp:include page="welcome_header.jsp"/> <br/>
        <a href="load_test_list">Show Created Test</a>
        <c:if test="${not empty sessionScope.USER_DTO}">
            <h1>This is admin page</h1>
            <!--Create question-->
            <form action="load_subject_list_for_create_question">
                <input type="submit" value="Create Question" name="btAction" />
            </form> <br/>
            <!--//end create question form-->
            <!--create quiz-->
            <form action="load_subject_list_for_create_test">
                <input type="submit"value="Create A Test" name="btAction" />
            </form>
            <!--//end create quiz form-->
            <form action="search_question">
                <!--search type-->
                Search By: 
                <select name="txtSearchType">
                    <c:if test="${empty param.txtSearchType}">
                        <option value="search_by_name" >Question Name</option>
                        <option value="search_by_subject_name">Subject Name</option>
                    </c:if>
                    <c:if test="${not empty param.txtSearchType && 
                                  param.txtSearchType eq 'search_by_name'}">
                          <option value="search_by_name" selected="true">Question Name</option>
                          <option value="search_by_subject_name">Subject Name</option>
                    </c:if>
                    <c:if test="${not empty param.txtSearchType && 
                                  param.txtSearchType eq 'search_by_subject_name'}">
                          <option value="search_by_name">Question Name</option>
                          <option value="search_by_subject_name" selected="true">Subject Name</option>
                    </c:if>
                    <c:if test="${not empty param.txtSearchType && 
                                  param.txtSearchType eq 'search_all'}">
                          <option value="search_by_name">Question Name</option>
                          <option value="search_by_subject_name">Subject Name</option>
                    </c:if>
                </select>
                <!--//end search type-->
                <!--search value-->
                <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /> <br/>
                <!--//end search value-->
                <!--status check-->
                Status: 
                <c:if test="${empty requestScope.STATUS_CHECK}">
                    <input type="radio" name="status_check" value="1" id="active" checked="checked" />
                    <label for="active">Active</label>
                    <input type="radio" name="status_check" value="0" id="in-active" />
                    <label for="active">In-Active</label> <br/>
                </c:if>
                <c:if test="${not empty requestScope.STATUS_CHECK && 
                              requestScope.STATUS_CHECK eq  '1'}">
                      <input type="radio" name="status_check" value="1" id="active" checked="checked" />
                      <label for="active">Active</label>
                      <input type="radio" name="status_check" value="0" id="in-active" />
                      <label for="active">In-Active</label> <br/>
                </c:if>
                <c:if test="${not empty requestScope.STATUS_CHECK && 
                              requestScope.STATUS_CHECK eq '0'}">
                      <input type="radio" name="status_check" value="1" id="active" />
                      <label for="active">Active</label>
                      <input type="radio" name="status_check" value="0" id="in-active" checked="checked"/>
                      <label for="active">In-Active</label> <br/>
                </c:if>
                <!--end status check-->
                <!--search button-->
                <input type="submit" value="Search" name="btAction" />
                <!--//end search button-->
            </form>
            <!--c set for search type, search value, status check-->
            <c:set var="searchType" value="${param.txtSearchType}"/>
            <c:set var="searchValue" value="${param.txtSearchValue}"/>
            <c:set var="status_check" value="${param.status_check}"/>
            <!--//end-->
            <!--search all questions-->
            <form action="search_question">
                <input type="hidden" name="txtSearchValue" value="${searchValue}" />
                <input type="hidden" name="status_check" value="${status_check}" />
                <input type="hidden" name="txtSearchType" value="search_all" />
                <input type="submit" value="Search All Questions" name="btAction" />
            </form>
            <!--//end search all questions-->
            <!--if search result not empty-->
            <c:if test="${not empty requestScope.QUESTION_LIST}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Question Content</th>
                            <th>Answer 1</th>
                            <th>Answer 2</th>
                            <th>Answer 3</th>
                            <th>Answer 4</th>
                            <th>Correct Answer</th>
                            <th>Create Date</th>
                            <th>Subject</th>
                            <th>Status</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="question" items="${requestScope.QUESTION_LIST}">
                        <form action="update_question">
                            <tr>
                                <td>
                                    <input type="text" name="txtQuestionContent" value="${question.questionContent}" />
                                    <c:if test="${not empty requestScope.QUESTION_ID && 
                                                  not empty requestScope.ERROR['QUESTION_CONTENT_ERR'] &&
                                                  question.ID eq requestScope.QUESTION_ID}">
                                              ${requestScope.ERROR['QUESTION_CONTENT_ERR']}
                                          </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${not empty requestScope.ERROR['ANSWER_CONTENT_ERR1'] && 
                                                      not empty requestScope.QUESTION_ID && 
                                                      question.ID eq requestScope.QUESTION_ID}">
                                                  ${requestScope.ERROR['ANSWER_CONTENT_ERR1']}
                                              </c:if>
                                              <input type="text" name="txtAnswerContent1" value="${question.answerContent1}" />
                                        </td>
                                        <td>
                                            <c:if test="${not empty requestScope.ERROR['ANSWER_CONTENT_ERR2'] && 
                                                          not empty requestScope.QUESTION_ID && 
                                                          question.ID eq requestScope.QUESTION_ID}">
                                                      ${requestScope.ERROR['ANSWER_CONTENT_ERR2']}
                                                  </c:if>
                                                  <input type="text" name="txtAnswerContent2" value="${question.answerContent2}" />
                                            </td>
                                            <td>
                                                <c:if test="${not empty requestScope.ERROR['ANSWER_CONTENT_ERR3'] && 
                                                              not empty requestScope.QUESTION_ID && 
                                                              question.ID eq requestScope.QUESTION_ID}">
                                                          ${requestScope.ERROR['ANSWER_CONTENT_ERR3']}
                                                      </c:if>
                                                      <input type="text" name="txtAnswerContent3" value="${question.answerContent3}" />
                                                </td>
                                                <td>
                                                    <c:if test="${not empty requestScope.ERROR['ANSWER_CONTENT_ERR4'] && 
                                                                  not empty requestScope.QUESTION_ID && 
                                                                  question.ID eq requestScope.QUESTION_ID}">
                                                              ${requestScope.ERROR['ANSWER_CONTENT_ERR4']}
                                                          </c:if>
                                                          <input type="text" name="txtAnswerContent4" value="${question.answerContent4}" />
                                                    </td>
                                                    <td>
                                                        <select name="txtCorrectAnswer">
                                                            <c:if test="${question.correctAnswer eq 1}">
                                                                <option value="1" selected="true">Answer 1</option>
                                                                <option value="2">Answer 2</option>
                                                                <option value="3">Answer 3</option>
                                                                <option value="4">Answer 4</option>
                                                            </c:if>
                                                            <c:if test="${question.correctAnswer eq 2}">
                                                                <option value="1">Answer 1</option>
                                                                <option value="2" selected="true">Answer 2</option>
                                                                <option value="3">Answer 3</option>
                                                                <option value="4">Answer 4</option>
                                                            </c:if>
                                                            <c:if test="${question.correctAnswer eq 3}">
                                                                <option value="1">Answer 1</option>
                                                                <option value="2">Answer 2</option>
                                                                <option value="3" selected="true">Answer 3</option>
                                                                <option value="4">Answer 4</option>
                                                            </c:if>
                                                            <c:if test="${question.correctAnswer eq 4}">
                                                                <option value="1">Answer 1</option>
                                                                <option value="2">Answer 2</option>
                                                                <option value="3">Answer 3</option>
                                                                <option value="4" selected="true">Answer 4</option>
                                                            </c:if>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        ${question.createDate}
                                                    </td>
                                                    <td>
                                                        <select name="txtSubjectID">
                                                            <c:if test="${question.subjectID eq 1}">
                                                                <option value="1" selected="true">
                                                                    Java
                                                                </option>
                                                                <option value="2">
                                                                    C#
                                                                </option>
                                                                <option value="3">
                                                                    Japanese
                                                                </option>
                                                            </c:if>
                                                            <c:if test="${question.subjectID eq 2}">
                                                                <option value="1">
                                                                    Java
                                                                </option>
                                                                <option value="2" selected="true">
                                                                    C#
                                                                </option>
                                                                <option value="3">
                                                                    Japanese
                                                                </option>
                                                            </c:if>
                                                            <c:if test="${question.subjectID eq 3}">
                                                                <option value="1">
                                                                    Java
                                                                </option>
                                                                <option value="2">
                                                                    C#
                                                                </option>
                                                                <option value="3" selected="true">
                                                                    Japanese
                                                                </option>
                                                            </c:if>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <select name="status_adjust">
                                                            <c:if test="${question.status eq 1}">
                                                                <option value="1" selected="true">Active</option>
                                                                <option value="0" >In-Active</option>
                                                            </c:if>
                                                            <c:if test="${question.status eq 0}">
                                                                <option value="1" >Active</option>
                                                                <option value="0" selected="true">In-Active</option>
                                                            </c:if>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <!--session tracking-->
                                                        <input type="hidden" name="txtSearchType" value="${searchType}" />
                                                        <input type="hidden" name="txtSearchValue" value="${searchValue}" />
                                                        <input type="hidden" name="status_check" value="${status_check}" />
                                                        <!---những para ko cho sửa--->
                                                        <input type="hidden" name="txtCreateDate" value="${question.createDate}" />
                                                        <input type="hidden" name="txtQuestionID" value="${question.ID}" />
                                                        <input type="hidden" name="adminEmail" value="${question.adminEmail}" />
                                                        <input type="submit" value="Update" name="btAction" />
                                                    </td>
                                                    <c:if test="${question.status eq 1}">
                                                        <td>
                                                            <c:url var="deleteLink" value="delete_question">
                                                                <c:param name="txtQuestionID" value="${question.ID}"/>
                                                                <c:param name="txtSearchType" value="${searchType}"/>
                                                                <c:param name="txtSearchValue" value="${searchValue}"/>
                                                                <c:param name="status_check" value="${status_check}"/>
                                                            </c:url>
                                                            <a href="${deleteLink}">Delete</a>
                                                        </td>
                                                        <c:if test="${not empty requestScope.QUESTION_ID && 
                                                                      requestScope.QUESTION_ID eq question.ID &&
                                                                      not empty requestScope.DELETE_ERR}">
                                                                  ${requestScope.DELETE_ERR}
                                                              </c:if>
                                                        </c:if>
                                                        <c:if test="${question.status eq 0}">
                                                            <td>
                                                                Not Available
                                                            </td>
                                                        </c:if>
                                                    </tr>
                                                </form>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                
                                <!--paging-->
                                <jsp:include page="paging_footer.jsp"/>
                                <!--//end paging-->
                                </c:if>
                                <!--//end if search result not empty-->
                                <!--if search result empty-->
                                <c:if test="${empty requestScope.QUESTION_LIST}">
                                    Không tìm thấy kết quả
                                </c:if>
                                <!--//end if search result empty-->
                            </c:if>
                            <c:if test="${empty sessionScope.USER_DTO}">
                                <c:url var="loginPage" value="login_page"></c:url>
                                <c:redirect url="${loginPage}"></c:redirect>
                            </c:if>
                        </body>
                    </html>
