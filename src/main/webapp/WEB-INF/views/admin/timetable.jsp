<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<style>
th, td{
	text-align: center;
}
</style>
<c:if test="${ member_id == null }">
	<script>
		<% response.sendRedirect("/login");%> 
	</script>
</c:if>
<div class="content" style="margin-left: 250px;">
	<h2>공지 목록</h2>
	<table class="table">
		<thead>
			<tr>
				<th>영화관ID</th>
				<th>영화관 이름</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ theaterlist }" var="list">
				<tr>
					<td>${ list.theater_id }</td>
					<td><a href="/admin/timetable/read/${ list.theater_id }">${ list.theater_name }</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div id="notice_page" style="text-align: center;"></div>
	<div class="form-inline">
		<select class="form-control" id="keyword" name=""keyword"">
			<option value="theater_name" selected="selected">영화관</option>
		</select> <input type="text" class="form-control" id="search" name="search">
		<input type="submit" class="btn btn-default" value="검색" onclick="getNoticeList(1);">
	</div>
</div>
<%@include file="../include/footer.jsp"%>