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
	<h2>상영시간표</h2><br><br>
	<label class="col-sm-2">상영관 번호</label>
	<div class="col-md-3">
		<select id="plex_number" name="plex_number" onchange="getStartDay();">
			<option value="basic">상영관 선택</option>
			<c:forEach begin="1" end="5" step="1" var="i">
				<option value="${ i }"> ${ i } </option>
			</c:forEach>
		</select>
	</div>
	<label class="col-sm-2">상영일</label>
	<div class="col-md-3">
		<select id="startday" name="startday" onchange="getStartTime();">
			<option value="basic">날짜 선택</option>
		</select>
	</div><br><br><br>
	<div id="start_time_list"></div>
</div>
<script>
	function getStartDay(){
		$("#start_time_list").html("");
		$.ajax({
			type : 'get',
			url : '/admin/timetable/getStartDay',
			headers : { 
				"Content-Type" : "application/json"
				},
			data : { "plex_number" : $("#plex_number").val(), "theater_id" : "${ theater_id }" },
			dataType : 'json',
			success : function(result){
				setStartDay(result);
			}
		});
	}
	function setStartDay(data){
		var str="<option value='basic'>날짜 선택</option>";
		$(data).each(function(){
			str += "<option value='" + this.substring(0, 10) + "'>" + this.substring(0, 10) + "</option>";
		});
		$("#startday").html(str);
	}
	function getStartTime(){
		$.ajax({
			type : 'get',
			url : '/admin/timetable/getStartTime',
			headers : { 
				"Content-Type" : "application/json"
				},
			data : { "plex_number" : $("#plex_number").val(), "theater_id" : "${ theater_id }", "startday" : $("#startday").val() },
			dataType : 'json',
			success : function(result){
				setStartTime(result.starttime_list, result.movie_name);
			}
		});
	}
	function setStartTime(data, movie_name){
		var str ="<div style='margin:10px;'><font style='font-size: 20pt; font-weight: bold;'>" + movie_name + "</font></div>";
		$(data).each(function(){
			str +="<div style='float:left; margin:10px; border:1px solid #000; padding:3px;'>" + this.start_time.substring(11, 16) + "</div>";
		});
		$("#start_time_list").html(str);
	}
</script>
<%@include file="../include/footer.jsp"%>