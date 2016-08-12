<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<c:if test="${ member_id == null }">
	<script>
		<%response.sendRedirect("/login");%>
	</script>
</c:if>
<div class="content" style="margin-left: 250px;">
	<h2>상영시간 등록</h2><br><br>
	<form id="timetableWritefrm" role="form" action="/admin/timetable/new" method="post">
		<label class="col-sm-2">영화 타이틀</label>
		<div class="col-md-3">
			<select id="movie_id" name="movie_id">
				<c:forEach items="${ moviename_list }" var="list">
					<option value="${ list.movie_id }">${ list.title }</option>
				</c:forEach>
			</select>
		</div>
		<label class="col-sm-2">영화관 이름</label>
		<div class="col-md-3">
			<select id="theater_id" name="theater_id">
				<c:forEach items="${ theatername_list }" var="list">
					<option value="${ list.theater_id }">${ list.theater_name }</option>
				</c:forEach>
			</select>
		</div><br><br><br>
		<label class="col-sm-2">상영관 번호</label>
		<div class="col-md-3">
			<select id="plex_number" name="plex_number">
				<c:forEach items="${ plexnumber_list }" var="list">
					<option value="${ list }">${ list }</option>
				</c:forEach>
			</select>
		</div><br><br><br>
		<label class="col-sm-2">상영시간</label>
		<div class="col-md-5">
			<select id="year" name="year">
				<c:forEach var="i" begin="2016" step="1" end="2017">
					<option value="${ i }">${ i }</option>
				</c:forEach>
			</select> - 
			<select id="month" name="month">
				<c:forEach var="i" begin="1" step="1" end="12">
					<option value="${ i }">${ i }</option>
				</c:forEach>
			</select> - 
			<select id="day" name="day">
				<c:forEach var="i" begin="1" step="1" end="31">
					<option value="${ i }">${ i }</option>
				</c:forEach>
			</select>
			<select id="hour" name="hour">
				<c:forEach var="i" begin="00" step="1" end="24">
					<option value="${ i }">${ i }</option>
				</c:forEach>
			</select> : 
			<select id="minute" name="minute">
				<c:forEach var="i" begin="1" step="1" end="60">
					<option value="${ i }">${ i }</option>
				</c:forEach>
			</select>
		</div>
		<div style="clear: both; margin-left: 300px;">
			<button type="submit" class="btn btn-primary">등록</button>
		</div>
	</form>
</div>
<%@include file="../include/footer.jsp"%>