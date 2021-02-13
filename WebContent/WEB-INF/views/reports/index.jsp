<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Report" %>
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>日報　一覧</h2>
        <table id ="report_list">
            <tbody>
                <tr>
                    <th class="report_name">氏名</th>
                    <th class="report_date">日付</th>
                    <th class="report_title">タイトル</th>
                    <th class="report_favorite"></th>
                    <th class="report_action">操作</th>
                </tr>
                <% List<Report> favorite = (List<Report>)request.getAttribute("favorite");  %>
                <% Report fav_report; %>
                <c:forEach var="report" items="${reports}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="report_name"><a href="<c:url value='/follows/show?employee_id=${report.employee.id}'/>"><c:out value="${report.employee.name}"/></a></td>
                        <td class="report_date"><fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd'/></td>
                        <td class="report_title">${report.title}</td>
                        <td class="report_favorite"> ${favorites_count}
                            <% fav_report = (Report)pageContext.findAttribute("report"); %>
                            <% for(Report r : favorite){
                                  if(r == fav_report){
                                      request.setAttribute("fav", true);
                                      break;
                                  }
                                  if(r != fav_report){
                                      request.setAttribute("fav",false);
                                  }
                            }%>
                <c:choose>
                    <c:when test="${fav == true}">
                        <form method="POST" action="<c:url value='/favorites/destroy' /> ">
                              <input type="hidden" name="report_id" value="${report.id}" />
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
                        </td>
                        <td class="report_action"><a href="<c:url value='/reports/show?id=${report.id}'/>">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            (全 ${reports_count} 件) <br/>
            <c:forEach var="i" begin="1" end="${((reports_count -1) / 15) +1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/reports/index?page=${i}'/>"><c:out value="{i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href="<c:url value='/reports/new' />">新規日報の登録</a></p>

    </c:param>

</c:import>
