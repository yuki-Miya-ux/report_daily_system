<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>フォロー一覧</h2>
        <table id="follow_list">
            <tbody>
                <tr>
                    <th class="follow_name">氏名</th>
                    <th class="follow_currentReport">最新の記事</th>
                </tr>
                <c:forEach var="currentReport" items="${follows_current_report}" varStatus="status">
                    <tr class="row${status.count % 2 }">
                        <td class="follow_name"><a href="<c:url value='/follows/show?employee_id=${currentReport.employee.id }'/>" ><c:out value="${currentReport.employee.name}"/></a></td>
                        <td class="follow_currentReport"><a href="<c:url value='/reports/show?id=${currentReport.id}'/>" ><c:out value="${currentReport.title }"/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            (全 ${follows_count } 件)<br/>
            <c:forEach var="i" begin="1" end="${((follows_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page }">
                        <c:out value="${i }"/>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/follows/index?page=${i }'/>"><c:out value="{i}" /></a>
                    </c:otherwise>
               </c:choose>
           </c:forEach>
        </div>
    </c:param>
</c:import>