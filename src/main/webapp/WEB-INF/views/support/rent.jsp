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
		<label style="font-size: 15pt;">단체/대관 문의</label><br>
		개인이나 단체를 위한 대관서비스로 YZR의 넓고 쾌적한 극장에서 즐거운 추억을 만드실 수 있습니다.<br><br><br>
	</div>
	<form id="rentWritefrm" action="/support/rent/new" method="post">
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
				<th style="border-top: 0px;">영화관 선택</th>
				<td colspan="3" style="border-top: 0px;">
					<select id="theater" name="theater" onchange="typeList();" style="height: 27px; width: 200px;">
						<option value="basic">특별관/일반관 선택</option>
						<option value="special">특별관</option>
						<option value="normal">일반관</option>
					</select>
					<select id="theater_type" name="theater_type" onchange="theaterList();" style="height: 27px; width: 200px;">
						<option value="basic">특별관 선택</option>
					</select>
					<select id="theater_name" name="theater_name" style="height: 27px; width: 200px;">
						<option value="basic">영화관 선택</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>관람 희망일</th>
				<td colspan="3">
					<input type="text" id="rent_date" name="rent_date">
				</td>
			</tr>
			<tr>
				<th style="vertical-align: top; padding-top: 20px;">내용</th>
				<td colspan="3">
					<textarea id="rent_content" name="rent_content" rows="5" cols="100"></textarea>
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
	function typeList(){
		var type=$("#theater").val();
		$.ajax({
			type : 'get',
			url : '/support/rent/typelist',
			headers : { 
				"Content-Type" : "application/json"
				},
			data : { "type" : type },
			dataType : 'json',
			success : function(result){
				setTypeList(result);
			}
		});
	}
	function theaterList(){
		$.ajax({
			type : 'get',
			url : '/support/rent/theaterlist',
			headers : { 
				"Content-Type" : "application/json"
				},
			data : { "theater_type" : $("#theater_type").val(), "type" : $("#theater").val() },
			dataType : 'json',
			success : function(result){
				setTheaterList(result);
			}
		});
	}
	function setTypeList(data){
		var str="";
		if($("#theater").val()=="special"){
			str +="<option value='basic'>특별관 선택</option>";
		}else{
			str +="<option value='basic'>지역 선택</option>";
		}		
		$(data).each(function(){
			str += "<option value='" + this + "'>" + this + "</option>";
		});
		$("#theater_type").html(str);
		$("#theater_name").html("<option value='basic'>영화관 선택</option>");
	}
	function setTheaterList(data){
		var str="<option value='basic'>영화관 선택</option>";
		$(data).each(function(){
			str += "<option value='" + this + "'>" + this + "</option>";
		});
		$("#theater_name").html(str);
	}
	function postLost(){
		if($("#theater").val()=="basic" || $("#theater_type").val()=="basic" || $("#theater_name").val()=="basic" || $("#rent_date").val()==null || $("#rent_content").val()==""){
			alert("정보를 입력하세요.");
		}else{
			$("#rentWritefrm").submit();
		}
	}
</script>
<%@include file="../include/footer.jsp"%>