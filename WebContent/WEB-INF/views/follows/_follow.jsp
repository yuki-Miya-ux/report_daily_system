<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:choose>
    <c:when test="${follow != null }">
        <form method="POST" action="<c:url value='/follows/destroy' />">
            <input type="hidden" name="follow_id" value="${follow.id}" />
            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit" class="follow">フォロー解除</button>
        </form>
    </c:when>
    <c:otherwise>

        <form method="POST" action="<c:url value='/follows/create'/>">
            <input type="hidden" name="follow_id" value="${employee.id}"/>
            <input type="hidden" name="_token" value="${_token}"/>
            <button type="submit" class="follow">フォローする</button>
        </form>
    </c:otherwise>
</c:choose>
