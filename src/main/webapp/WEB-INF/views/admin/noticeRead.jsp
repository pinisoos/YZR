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
	<h2>공지 수정</h2><br><br>
	<form id="noticeWritefrm" role="form" action="/admin/notice/new" method="post">
		<input type="hidden" id="_method" name="_method" value="put">
		<input type="hidden" id="no" name="no" value="${ vo.no }">
		<label class="col-sm-2" style="text-align: center;">카테고리</label>
		<div class="col-md-3">
			<input type="text" id="notice_category" name="notice_category" class="form-control" placeholder="Enter Category" value="${ vo.notice_category }"/>
		</div><br><br><br>
		<label class="col-sm-2" style="text-align: center;">제목</label>
		<div class="col-md-8">
			<input type="text" id="notice_title" name="notice_title" class="form-control" placeholder="Enter Title" value="${ vo.notice_title }"/>
		</div><br><br><br>
		<label class="col-sm-2" style="text-align: center;">내용</label>
		<div class="col-md-8" style="margin-bottom: 30px;">
			<textarea id="notice_content" name="notice_content" rows="8" class="form-control" placeholder="Enter Content">${ vo.notice_content }</textarea>
		</div>
		<div style="clear: both; text-align: center;">
			<button type="submit" class="btn btn-primary">수정</button>
		</div>
	</form>
</div>
<%@include file="../include/footer.jsp"%>