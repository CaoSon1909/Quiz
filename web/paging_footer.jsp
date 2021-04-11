<%-- 
    Document   : paging_footer
    Created on : Feb 1, 2021, 1:56:06 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty requestScope.PRE_PAGE}">
    <c:url var="previousPage" value="search_question">
        <c:param name="page" value="${requestScope.PRE_PAGE}"/>
        <c:param name="txtSearchType" value="${param.txtSearchType}"/>
        <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
        <c:param name="status_check" value="${param.status_check}"/>
    </c:url>
    <a href="${previousPage}"><</a>
</c:if>
${requestScope.PAGE}
<c:if test="${not empty requestScope.NEXT_PAGE}">
    <c:url var="nextPage" value="search_question">
        <c:param name="page" value="${requestScope.NEXT_PAGE}"/>
        <c:param name="txtSearchType" value="${param.txtSearchType}"/>
        <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
        <c:param name="status_check" value="${param.status_check}"/>
        <c:param name="btAction" value="Search"/>
    </c:url>
    <a href="${nextPage}">></a>
</c:if>
