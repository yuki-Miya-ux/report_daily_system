<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <h2><c:out value="${employee.name }"></c:out>の日報一覧</h2>
    <p>フォロー ${follows_count } </p>
    <p>フォロワー ${follower_count }</p>
    <c:import url="../follows/_follow.jsp" />
    <table id="follow_list">
        <tbody>
            <tr class="follow_report">
                <th class="follow_report_date">日付</th>
                <th class="follow_report_title">タイトル</th>
                <th class="follow_report_fav"></th>
                <th class="follow_report_action">操作</th>
            </tr>
            <c:forEach var="report" items="${reports}" varStatus="status">
                <tr class="row${status.count % 2 }">
                    <td class="follow_report_date"><fmt:formatDate value='${report.report_date }' pattern='yyyy-MM-dd' /></td>
                    <td class="follow_report_title">${report.title}</td>
                    <td class="follow_report_fav"></td>
                    <td class="follow_report_action"><a href="<c:url value='/reports/show?id=${report.id}'/>">詳細を見る</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div id="pagination">
            (全 ${reports_count }件) <br/>
            <c:forEach var="i" begin="1" end="${((reports_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
               </c:choose>
           </c:forEach>
        </div>

        <p><a href="<c:url value='/reports/index'/>">日報一覧に戻る</a></p>
</c:param>

</c:import>