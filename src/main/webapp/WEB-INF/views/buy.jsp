<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="./include/header.jsp" %>
<div id="wrap_content" style="min-height: 100%; position: relative;">
	<div id="content" style="padding-bottom: 150px; float: left; width:100%">
		<span style="font-weight:bold; font-size:36px; float:left; margin-top:10px;">스낵바</span>
		<div style="border:1px solid #000; margin-top:60px; width:100%;"></div>
		
		<div id="product" style="float:left;">
			<img src="/resources/images/product/${product_img}" style="width:525px; height:490px; margin-left:30px; margin-top:30px; float:left;"/>
			<div id="prd_detail" style="margin-left:30px; float:left;">
				<span style="font-weight:bold; font-size:40px; margin-top:30px; display:block;">${product_name}</span>
				<c:if test="${product_content != ''}">
					<span style="clear:both; font-weight:bold; color:gray; display:inline-block; margin-top:10px;">${product_content}</span>
				</c:if>
				<div style="clear:both; border:1px solid #BDBDBD; margin-top:15px; width:555px;"></div>
				<div style="margin-top:25px;">
					<div>
						<span style="font-weight:bold; color:#5D5D5D;">판매가</span> 
						<span style="color:red; font-size:25px; margin-left:64px; font-weight:bold;">${product_price}</span>
					</div>
					
					<div style="border:1px dashed #BDBDBD; margin-top:25px;"></div>
					
					<div style="margin-top:35px;">
						<span style="font-weight:bold; color:#5D5D5D;">상품설명</span>
						<span style="margin-left:50px; font-weight:bold;">온라인 매점에서 500원 더 저렴하게~!</span>
					</div>
					
					<div style="margin-top:5px;">
						<span style="font-weight:bold; color:#5D5D5D;">사용가능극장</span>
						<span style="margin-left:22px; font-weight:bold;">전체극장</span>
					</div>
					
					<div style="margin-top:5px;">
						<span style="font-weight:bold; color:#5D5D5D;">유효기간</span>
						<span style="margin-left:50px; font-weight:bold;">구매 후 6개월 이내</span>
					</div>
					
					<div style="margin-top:5px;">
						<span style="font-weight:bold; color:#5D5D5D;">상품교환</span>
						<span style="margin-left:50px; font-weight:bold;">관람 예정 극장의 매점</span>
					</div>
					
					<div style="clear:both; border:1px solid #BDBDBD; margin-top:35px; margin-bottom:35px; width:555px;"></div>
					
					<div style="float:right;">
						<button id="goStore" name="goStore" class="btn-lg" onclick="store()" style="background-color:#8c8c8c; color:white;">목록으로</button>
						<button id="goStore" name="goStore" class="btn-lg" onclick="buy()" style="background-color:#CC3D3D; color:white;">구매하기</button>
					</div> 
					
					<form id="frm" name="frm" action="/pay" method="post">
						<input type="hidden" id="product_name" name="product_name" value="${product_name}"/>
						<input type="hidden" id="product_price" name="product_price" value="${product_price}"/>
					</form>
					
				</div>
			</div>
		</div>
	</div>	
</div>

<script>
	function store(){
		location.href = "/culture_shop"
	}
	
	function buy(){
		var frm = document.getElementById("frm");
		alert(product_name + " 을 구매하였습니다")
		frm.submit();
	}
</script>
<%@include file="./include/footer.jsp" %>