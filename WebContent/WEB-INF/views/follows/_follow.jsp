<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form method="POST" action="<c:url value='/follows/create'/>">
                    <input type="hidden" name="follow_id" value="${report.employee.id}"/>
                    <input type="hidden" name="_token" value="${_token}"/>
                    <input type="submit" name="follow" value="フォローする"/>
</form>