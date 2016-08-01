<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<style>
th, td{
	text-align: center;
}
.list-group > li:FIRST-CHILD > a:HOVER {
	color: black;
	text-decoration: none;	
}
.list-group > li {
	border: 0px;
	background-color: ivory;
}
.glyphicon.glyphicon-search {
    font-size: 50px;
    color: white;
}
.glyphicon.glyphicon-list-alt, .glyphicon-edit, .glyphicon-eye-open {
    font-size: 50px;
    color: black;
}
::-webkit-input-placeholder {
   font-size: 8pt;
   padding-left: 5px;
}
</style>
<div class="content" style="margin-left: 250px; height: 540px;">
	<div style="width: 890px; float: left;">
		<div style="width: 33%; height: 270px; float: left; background-color: red; text-align: center;">
			<span class="glyphicon glyphicon-search" style="margin-top: 70px;"></span><br><br>
			<span style="font-weight: bold; font-size: 15pt; color: white;">자주찾는 질문 빠른검색</span><br><br>
			<div style="padding-left: 30px; padding-right: 30px;">
				<input type="text" style="float: left; height: 30px; padding-left: 5px; border-bottom: 0px; border-left: 0px; border-top: 0px; border-color: gray;" placeholder="검색어를 입력해 주세요.">
				<div style="background-color: white; color: gray; font-size: 10pt; font-weight: bold; padding-top: 5px; height: 30px; cursor: pointer;" onclick="">검색</div>
			</div>
		</div>
		<div style="width: 33%; height: 270px; float: left; border-left: 0px; background-color: ivory; text-align: center;">
			 <span class="glyphicon glyphicon-list-alt" style="margin-top: 70px;"></span><br><br>
			<span style="font-weight: bold; font-size: 15pt; color: black;">예매내역 확인</span><br><br>
			<a href="/support/lost"><input type="button" class="btn btn-danger" value="문의하기"></a>
		</div>
		<div style="width: 33%; height: 270px; float: left; border-left: 0px; background-color: ivory; text-align: center;">
			 <span class="glyphicon glyphicon-edit" style="margin-top: 70px;"></span><br><br>
			<span style="font-weight: bold; font-size: 15pt; color: black;">나의 문의내역 확인</span><br><br>
			<a href="/support/rent"><input type="button" class="btn btn-danger" value="문의하기"></a>
		</div>
	</div>
	<div style="width: 890px; float: left;">
		<div style="width: 33%; height: 270px; float: left; background-color: ivory; text-align: center;">
			<span class="glyphicon glyphicon-eye-open" style="margin-top: 70px;"></span><br><br>
			<span style="font-weight: bold; font-size: 15pt; color: black;">아이디/비밀번호 찾기</span><br><br>
			<a href="#"><input type="button" class="btn btn-danger" value="문의하기"></a>
		</div>
		<div style="width: 66%; height: 270px; margin-left: 33%; border-left: 0px; padding-left: 20px; background-color: ivory;">
			<ul class="list-group">
				<li class="list-group-item"><a href="/support/notice" style="font-weight: bold; font-size: 15pt;">공지/뉴스</a></li>
				<c:forEach items="${ notice_list }" var="list">
					<li class="list-group-item">
						<a href="/support/notice/read/${ list.no }">[${ list.notice_category }] ${ list.notice_title }</a>
						<span style="float: right;">${ list.notice_date }</span>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
<%@include file="../include/footer.jsp"%>