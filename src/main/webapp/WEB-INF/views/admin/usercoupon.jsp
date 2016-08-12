<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<c:if test="${ member_id == null }">
	<script>
		<%response.sendRedirect("/login");%>
	</script>
</c:if>
<style>
	th{
		text-align: center;
	}
</style>
<div class="content" style="margin-left: 250px;">
		<h2>개인쿠폰 발송</h2><br><br>
	<form id="userCouponfrm" name="userCouponfrm" role="form" action="/admin/usercoupon" method="post">
		<input type="hidden" id="member_id" name="member_id">
		<table class="table">
			<tr>
				<th style="width: 200px; padding-top: 15px;">아이디</th>
				<td style="width: 200px;">
					<select id="member_list" name="member_list" style="width: 175px; margin-top: 5px;">
						<option value="basic">아이디 선택</option>
						<c:forEach items="${ member_list }" var="list">
							<option value="${ list }">${ list }</option>
						</c:forEach>
					</select>
				</td>
				<td colspan="1" style="width: 100px;">
					<input type="button" class="btn btn-danger" value="체크" onclick="checkId();">
				</td>
				<td><div id="checkmsg" style="margin-top: 5px;"></div></td>
			</tr>
			<tr>
				<th style="padding-top: 10px;">할인율</th>
				<td colspan="3">
					<input type="text" id="coupon_amount" name="coupon_amount" size="20">
				</td>
			</tr>
		</table><br><br>
		<div style="margin-left: 300px;">
			<input type="button" id="post" class="btn btn-danger" value="발송" onclick="couponPost();">
		</div>
	</form>
</div>
<script>
	function couponPost(){
		$("#member_id").val($("#member_list").val());
		$("#userCouponfrm").submit();
	}
	function checkId(){
		$.ajax({
			type : 'get',
			url : '/admin/usercoupon/check',
			headers : { 
				"Content-Type" : "application/json"
				},
			data : {"member_id" : $("#member_list").val()},
			dataType : 'json',
			success : function(result){
				if(result==true){
					$("#checkmsg").html("<font style='font-weight: bold; color: blue;'>존재하는 아이디입니다.</font>");
				}else{
					$("#checkmsg").html("<font style='font-weight: bold; color: red;'>존재하지 않는 아이디입니다.</font>");
				}
			}
		});
	}
</script>
<%@include file="../include/footer.jsp"%>