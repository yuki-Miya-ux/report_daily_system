<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">


<form method="POST" action="<c:url value='/favorites/create'/> ">
    <input type="hidden" name="report_id" value="${report.id}" />
    <input type="hidden" name="_token" value="${_token}" />
    <button type="submit" name="fav" ><i class="far fa-heart"></i></button>
</form>