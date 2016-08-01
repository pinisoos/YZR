<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<div class="content" style="margin-left: 250px;">
	<h2>공지 읽기</h2><br><br>
	<label class="col-sm-2" style="text-align: center;">카테고리</label>
	<div class="col-md-3">
		<input type="text" class="form-control" placeholder="Enter Category" value="${ vo.notice_category }" readonly="readonly"/>
	</div><br><br><br>
	<label class="col-sm-2" style="text-align: center;">제목</label>
	<div class="col-md-8">
		<input type="text" class="form-control" placeholder="Enter Title" value="${ vo.notice_title }" readonly="readonly"/>
	</div><br><br><br>
	<label class="col-sm-2" style="text-align: center;">내용</label>
	<div class="col-md-8" style="margin-bottom: 30px;">
		<textarea rows="8" disabled="disabled" class="form-control" placeholder="Enter Content" readonly="readonly" style="">${ vo.notice_content }</textarea>
	</div>
	<div style="clear: both; margin-left: 300px;">
		<a href="/support/notice"><button type="button" class="btn btn-primary">목록</button></a>
	</div>
</div>
<%@include file="../include/footer.jsp"%>