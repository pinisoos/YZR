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
	<h2>배우 등록</h2><br><br>
	<form id="actorWritefrm" role="form" action="/admin/actor/new" method="post" enctype="multipart/form-data">
		<label class="col-sm-1">이름</label>
		<div class="col-md-3">
			<input type="text" id="actor_name" name="actor_name" class="form-control" placeholder="Enter Name" />
		</div>
		<label class="col-sm-1">나이</label>
		<div class="col-md-3">
			<input type="text" id="actor_age" name="actor_age" class="form-control" placeholder="Enter Age" />
		</div><br><br><br>
		<label class="col-sm-1">데뷔작</label>
		<div class="col-md-3">
			<input type="text" id="actor_debut" name="actor_debut" class="form-control" placeholder="Enter Debut" />
		</div>
		<label class="col-sm-1">소속사</label>
		<div class="col-md-3">
			<input type="text" id="actor_company" name="actor_company" class="form-control" placeholder="Enter Company" />
		</div><br><br><br>
		<label class="col-sm-1">영화ID</label>
		<div class="col-md-3">
			<select id="movie_id" name="movie_id" style="width: 193px;">
				<c:forEach items="${ moviename_list }" var="list">
					<option value="${ list.movie_id }">${ list.title }</option>
				</c:forEach>
			</select>
		</div>
		<label class="col-sm-1">사진</label>
		<div>
			<input type="file" id="file" name="file" size="60" style="padding-left: 20px;"/>
		</div>
		<div style="clear: both; margin-left: 300px;">
			<button type="submit" class="btn btn-primary">등록</button>
		</div>
	</form>
</div>
<%@include file="../include/footer.jsp"%>