<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<c:choose>
    <c:when test="${fav == true}">
        <form method="POST" action="<c:url value='/favorites/destroy' /> ">
              <input type="hidden" name="report_id" value="${favorite.id}" />
              <input type="hidden" name="_token" value="${_token }" />
              <button type="submit" class="fav"><i class="fas fa-heart"></i></button>
        </form>
    </c:when>
    <c:otherwise>
        <form method="POST" action="<c:url value='/favorites/create'/> ">
                <input type="hidden" name="report_id" value="${report.id}" />
                <input type="hidden" name="_token" value="${_token}" />
                <button type="submit" class="fav" ><i class="far fa-heart"></i></button>
            </form>
    </c:otherwise>
</c:choose>