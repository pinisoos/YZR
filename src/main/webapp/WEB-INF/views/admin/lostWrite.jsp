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
	<h2>영화 등록</h2><br><br>
	<form id="movieWritefrm" role="form" action="/admin/movie/new" method="post" enctype="multipart/form-data">
		<label class="col-sm-1">영화ID</label>
		<div class="col-md-3">
			<input type="text" id="movie_id" name="movie_id" class="form-control" placeholder="Enter MovieID" />
		</div>
		<label class="col-sm-1">제목</label>
		<div class="col-md-5">
			<input type="text" id="title" name="title" class="form-control" placeholder="Enter Title" />
		</div><br><br><br>
		<label class="col-sm-1">개봉일</label>
		<div class="col-md-3">
			<input type="text" id="open_date" name="open_date" class="form-control" placeholder="YYYY-MM-DD" />
		</div>
		<label class="col-sm-1">런타임</label>
		<div class="col-md-5">
			<input type="text" id="runtime" name="runtime" class="form-control" placeholder="Enter Runtime" />
		</div><br><br><br>
		<label class="col-sm-1">등급</label>
		<div class="col-md-3">
			<input type="text" id="rating" name="rating" class="form-control" placeholder="Enter Rating" />
		</div>
		<label class="col-sm-1">사이트</label>
		<div class="col-md-5">
			<input type="text" id="site" name="site" class="form-control" placeholder="Enter Site" />
		</div><br><br><br>
		<label class="col-sm-1">줄거리</label><br><br>
			<textarea id="story" name="story" rows="5" class="form-control" placeholder="Enter Story"></textarea><br>
		<div>
			<input type="file" id="file" name="file" size="60" />
		</div>
		<div style="text-align: center;">
			<button type="submit" class="btn btn-primary">등록</button>
		</div>
	</form>
</div>
<%@include file="../include/footer.jsp"%>