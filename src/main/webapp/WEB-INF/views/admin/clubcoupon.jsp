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
		<h2>클럽쿠폰 발송</h2><br><br>
	<form id="clubCouponfrm" name="clubCouponfrm" role="form" action="/admin/clubcoupon" method="post">
		<input type="hidden" id="club" name="club">
		<table class="table">
			<tr>
				<th style="padding-top: 15px;">클럽</th>
				<td>
					<select id="club_list" name="club_list" style="width: 175px; margin-top: 5px;">
						<option value="basic">클럽 선택</option>
						<option value="VIP">VIP</option>
						<option value="특별관">특별관</option>
						<option value="액션">액션</option>
						<option value="로맨스">로맨스</option>
					</select>
				</td>
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
		$("#club").val($("#club_list").val());
		$("#clubCouponfrm").submit();
	}
</script>
<%@include file="../include/footer.jsp"%>