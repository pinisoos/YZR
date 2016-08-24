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
		<label style="font-size: 15pt;">1:1 문의</label><br>
		자주찾는 질문에서 원하는 답변을 찾지 못하셨군요.<br>
 		불편사항이나 문의사항을 남겨주시면 최대한 신속하게 답변 드리겠습니다.<br><br><br>
	</div>
	<form id="qnaWritefrm" action="/support/qna/new" method="post">
		<input type="hidden" id="member_id" name="member_id" value="${ member.member_id }">
		<table class="table">
			<tr style="height: 40px;">
				<td colspan="4" style="background-color: red;"></td>
			</tr>
			<tr>
				<th>아이디</th><td colspan="3">${ member.member_id }</td>
			</tr>
			<tr>
				<th>휴대전화</th><td>${ member.tel }</td><th>이메일</th><td>${ member.email }</td>
			</tr>
			<tr style="border: 0px;">
				<td colspan="4"><br>
					※  문의에 대한 빠른 답변을 위해 회원 가입 시 입력하신 연락처를 확인해주세요.
					<a href="/user/update" style="margin-left: 10px;"><input type="button" class="btn btn-danger" value="수정"></a><br><br><br><br>
				</td>
			</tr>
			<tr style="border: 0px;">
				<th style="border-top: 0px;">문의 유형</th>
				<td colspan="3" style="border-top: 0px;">
					<form role="form">
						<label class="radio-inline"><input type="radio" name="qnatype" checked="checked" value="문의">문의</label>
						<label class="radio-inline"><input type="radio" name="qnatype" value="칭찬">칭찬</label>
						<label class="radio-inline"><input type="radio" name="qnatype" value="불만">불만</label>
						<label class="radio-inline"><input type="radio" name="qnatype" value="제안">제안</label>
					</form>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">
					<input type="text" id="title" name="title" size="50">
				</td>
			</tr>
			<tr>
				<th style="vertical-align: top; padding-top: 20px;">내용</th>
				<td colspan="3">
					<textarea id="content" name="content" rows="5" cols="100"></textarea>
				</td>
			</tr>
		</table><br><br>
		<div style="float: right;">
			<input type="button" class="btn btn-danger" onclick="postLost();" value="등록하기">
			<a href="/support"><input type="button" class="btn btn-default" value="취소"></a>
		</div>
	</form>
</div>
<script>
	function postLost(){
		if($("#title").val()==null || $("#content").val()==""){
			alert("정보를 입력하세요.");
		}else{
			$("#qnaWritefrm").submit();
		}
	}
</script>
<%@include file="../include/footer.jsp"%>