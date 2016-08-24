<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<style>
tr{
	height: 60px;
}
td{
	text-align: left;
}
th{
	width: 100px;
	text-align: left;
	font-weight: bold;
}
.table > tbody > tr > th, .table > tbody > tr > td{
	vertical-align: middle;
	background-color: ivory;
}
</style>
<div class="content" style="margin-left: 120px; width: 890px;">
	<div>
		<h3>나의 분실물 문의</h3>
	</div><br/>
	<div style="height: 40px;background-color: red;">
	</div>
	<table class="table">
		<tr style="border: 0px;">
			<th style="border-top: 0px;">분실장소</th>
			<td colspan="3" style="border-top: 0px;">
				${lostvo.lost_place }
			</td>
		</tr>
		<tr>
			<th>분실 날짜</th>
			<td colspan="3">
				${lostvo.lost_date }
			</td>
		</tr>
		<tr>
			<th>분실문 종류</th>
			<td colspan="3">
				${lostvo.lost_type }
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3">
				${lostvo.lost_content }
			</td>
		</tr>
	</table>
	<div style="float: right;">
		<a href="/user/lost"><input type="button" class="btn btn-danger" value="확인"></a>
	</div>
</div>
<%@include file="../include/footer.jsp"%>