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
<div class="content" style="margin-left: 250px;">
	<div>
		<label style="font-size: 15pt;">분실물 문의</label><br>
		분실물을 등록해주시면 확인 후 신속하게 답변 드리겠습니다.<br>
		빠르고 정확한 답변을 위해 분실물에 대한 정보를 자세히 작성해주세요.<br><br><br>
	</div>
	<form id="lostWritefrm" action="/support/lost/new" method="post">
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
				<th style="border-top: 0px;">분실장소</th>
				<td colspan="3" style="border-top: 0px;">
					<select id="theater_area" name="theater_area" onchange="theaterList();" style="height: 27px; width: 200px;">
						<option value="basic">지역 선택</option>
						<c:forEach items="${ area_list }" var="list">
							<option value="${ list }">${ list }</option>
						</c:forEach>
					</select>
					<select id="theater_name" name="theater_name" style="height: 27px; width: 200px;">
						<option value="basic">영화관 선택</option>
					</select>
					<input type="text" id="place" name="place" size="30">
				</td>
			</tr>
			<tr>
				<th>분실 날짜</th>
				<td colspan="3">
					<input type="text" id="lost_date" name="lost_date">
				</td>
			</tr>
			<tr>
				<th>분실문 종류</th>
				<td colspan="3">
					<select id="lost_type" name="lost_type" style="height: 27px;">
						<option value="basic">분실물 종류 선택</option>
						<option value="핸드폰">핸드폰</option>
						<option value="지갑">지갑</option>
						<option value="가방">가방</option>
						<option value="의류">의류</option>
						<option value="전자제품">전자제품</option>
						<option value="책">책</option>
						<option value="우산">우산</option>
						<option value="귀금속">귀금속</option>
						<option value="기타">기타</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea id="lost_content" name="lost_content" rows="5" cols="100"></textarea>
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
	function theaterList(){
		$.ajax({
			type : 'get',
			url : '/support/lost/theaterlist',
			headers : { 
				"Content-Type" : "application/json"
				},
			data : { "theater_area" : $("#theater_area").val() },
			dataType : 'json',
			success : function(result){
				setTheaterList(result);
			}
		});
	}
	function setTheaterList(data){
		var str="<option value='basic'>영화관 선택</option>";
		$(data).each(function(){
			str += "<option value='" + this + "'>" + this + "</option>";
		});
		$("#theater_name").html(str);
	}
	function postLost(){
		if($("#theater_name").val()=="basic" || $("#theater_area").val()=="basic" || $("#place").val()==null || $("#lost_date").val()==null || $("#lost_type").val()=="basic" || $("#lost_content").val()==""){
			alert("정보를 입력하세요.");
		}else{
			$("#lostWritefrm").submit();
		}
	}
</script>
<%@include file="../include/footer.jsp"%>