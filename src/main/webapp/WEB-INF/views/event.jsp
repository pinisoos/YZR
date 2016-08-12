<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="./include/header.jsp" %>
<div id="wrap_content" style="min-height: 100%; position: relative;">
	<div id="content" style="padding-bottom: 150px; float: left; width:100%">
		<span style="font-weight:bold; font-size:36px; float:left; margin-top:10px;">EVENT</span>
		<div style="border:1px solid #000; margin-top:60px;"></div>
		
		<div id="hold_img" style="margin-top:25px; float:left; height:400px;">
			<div style="width:320px; height:300px; float:left; margin-left:43px;">
				<a href=""><img style="width:320px; height:300px;" src="http://img.cgv.co.kr/Front/Main/2016/0707/insta_240x200.png"></a>
				<div style="margin-top:5px;">
					<span style="color:#4374D9; font-weight:bold;">스페셜 이벤트</span> <br/>
					<span style="font-size:12px;">CGV IMAX 여권 어디까지 찍었니?</span> <br/>
					<span style="font-size:12px; color:#5D5D5D;">기간: 2016.07.06 ~ 2016.07.31 </span>
				</div>
			</div>
			<div style="width:320px; height:300px; float:left; margin-left:43px;">
				<a href=""><img style="width:320px; height:300px;" src="http://img.cgv.co.kr/Front/Main/2016/0718/[CGV_1607_049]web_b_240X200.jpg"></a>
				<div style="margin-top:5px;">
					<span style="color:#4374D9; font-weight:bold;">스페셜 이벤트</span> <br/>
					<span style="font-size:12px;">THANK YOU 40000 FOLLOWERS</span> <br/>
					<span style="font-size:12px; color:#5D5D5D;">기간: 2016.07.18 ~ 2016.08.09 </span>
				</div>
			</div>
			<div style="width:320px; height:300px; float:left; margin-left:43px;">
				<a href=""><img style="width:320px; height:300px;" src="http://img.cgv.co.kr/Front/Main/2016/0727/web_list_240_200.jpg"></a>
				<div style="margin-top:5px;">
					<span style="color:#4374D9; font-weight:bold;">스페셜 이벤트</span> <br/>
					<span style="font-size:12px;">도전! 탈출 영퀴왕</span> <br/>
					<span style="font-size:12px; color:#5D5D5D;">기간: 2016.07.27 ~ 2016.08.16 </span>
				</div>
			</div>
		</div>
		
		<div id="event_nav" style="text-align:center; border:2px dashed red; border-radius:20px; margin-left:360px; width:400px; height:45px; float:left;">
			<ul class="nav nav-tabs" style="display:inline-block;">
				<li style="font-weight:bold;"><a href="javascript:getEventImg(1)">CGV스페셜</a></li>
				<li style="font-weight:bold;"><a href="javascript:getEventImg(2)">영화/예매</a></li>
				<li style="font-weight:bold;"><a href="javascript:getEventImg(3)">제휴/할인</a></li>
				<li style="font-weight:bold;"><a href="javascript:getEventImg(4)">멤버십</a></li>
			</ul>
		</div>
		
		<br/>
		
		<div id="event_img" style="margin-top:30px; float:left;">
			
			<!-- 이벤트 이미지 삽입될 영역 -->
		</div>
		
	</div>	
</div>

<script>
	function setEventImg(list){
		var result = "";
		
		for(var i=0; i<list.length; i++){
			if(i>3 && i<8){
				result += '<div style="float:left; margin-left:25px; margin-top:40px;">'
					+ '<a href="">'
					+ '<img src="' + list[i] + '" style="width:250px; height:250px;"/>'
					+ '</a>'
					+ '</div>';
				if(i==7){
					result += '<button id="more_btn" name="more_btn" onclick="more()" style="width:150px; margin-left:490px; margin-top:40px; font-weight:bold; border-top:2px dotted gray; border-bottom:2px dotted gray; font-size:20px; color:gray;">'
					+ '<i class="fa fa-chevron-circle-down fa-1x" aria-hidden="true" style="color:gray; margin-right:5px;">'
					+ '</i>'
					+ '더보기'
					+ '</button>';
				}
			}else if(i>7){
				result += '<div class="more_img" style="float:left; margin-left:25px; margin-top:40px;">'
					+ '<a href="">'
					+ '<img src="' + list[i] + '" style="width:250px; height:250px;"/>'
					+ '</a>'
					+ '</div>';
			}else{
				result += '<div style="float:left; margin-left:25px;">'
				+ '<a href="">'
				+ '<img src="' + list[i] + '" style="width:250px; height:250px;"/>'
				+ '</a>'
				+ '</div>';
			}
		}
			document.getElementById("event_img").innerHTML = result;
			$(".more_img").hide();
	}
	
	function getEventImg(category){
		$.ajax({
			type : 'get',
			url : '/event/' + category,
			headers : {
			"Content-Type" : "application/json",
			//"X-HTTP-Method-Override" : "GET",  ----  POST 이거나 GET인경우는 생략가능
			},
			dataType : 'json',
			data : '',
			success : function(list){
				setEventImg(list);
			}
		});
	}
	
	getEventImg(1);
	
	function more(){
		$("#more_btn").hide();
		$(".more_img").show();
	}
</script>

<style>
	#more_btn{ border:0px; background-color:white; }
	
	.nav-tabs{ border:0px }
	
	.nav-tabs > li > a:hover,
	.nav-tabs > li > a:focus{
		background-color:#fff;
		border:0px
	}
	
	.nav-tabs > li > a:focus{color:red;}
	
	.carousel-inner > .item > a,
	.carousel-inner > .item > a:hover{
		text-decoration: none;
		color: black;
	}
</style>

<%@include file="./include/footer.jsp" %>