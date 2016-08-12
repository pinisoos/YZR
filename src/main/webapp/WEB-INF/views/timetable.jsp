<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%  %>
<%@include file="./include/header.jsp" %>
<div id="wrap_content" style="min-height: 100%; position: relative;">
	<div id="content" style="padding-bottom: 150px; float: left; width:100%">
		<div id="theater_table" style="border:2px solid gray; border-radius:20px; height:400px; background-color:#4B4B4B;">
			<span style="font-weight:bold; color:white; font-size:30px; margin-top:35px; margin-left:110px; float:left;">자주가는 CGV</span>
			<div style="float:left; margin-top:25px; margin-left:20px;">
				<div id="th1" style="border:5px double white; width:130px; height:60px; float:left;">
					<span></span>
				</div>
				<div id="th2" style="border:5px double white; width:130px; height:60px; float:left; margin-left:5px;">
					<span></span>
				</div>
				<div id="th3" style="border:5px double white; width:130px; height:60px; float:left; margin-left:5px;">
					<span></span>
				</div>
				<div id="th4" style="border:5px double white; width:130px; height:60px; float:left; margin-left:5px;">
					<span></span>
				</div>
				<div id="th5" style="border:5px double white; width:130px; height:60px; float:left; margin-left:5px;">
					<span></span>
				</div>
			</div>
			
			<ul id="location" class="nav nav-tabs list-inline" style="clear:both; width:1140px; text-align:center; margin-top:20px; float:left;">
				<li class="lo_li" onclick="getTheater_list('서울')">서울</li>
				<li class="lo_li" onclick="getTheater_list('경기')">경기</li>
				<li class="lo_li" onclick="getTheater_list('인천')">인천</li>
				<li class="lo_li" onclick="getTheater_list('강원')">강원</li>
				<li class="lo_li" onclick="getTheater_list('대전/충청')">대전/충청</li>
				<li class="lo_li" onclick="getTheater_list('광주/전라')">광주/전라</li>
				<li class="lo_li" onclick="getTheater_list('대구/울산/부산')">대구/울산/부산</li>
				<li class="lo_li" onclick="getTheater_list('경상')">경상</li>
				<li class="lo_li" onclick="getTheater_list('제주')">제주</li>
			</ul>
			
			<ul id="theater" class="nav nav-tabs list-inline" style="clear:both; width:1140px; margin-top:20px; float:left;">
				<!-- 지역에 따른 -->
			</ul>
		</div>
		
		<span id="theater_name" style="font-weight:bold; font-size:36px; float:left; margin-top:30px;"></span>
		<span id="theater_id" style="display:none;"></span>
		<div style="border:2px solid #000; width:1140px; margin-top:20px; clear:both;"></div>
		
		<div id="play_date" style="clear:both; margin-top:20px; margin-left:260px;">
			<i id="prev" class="fa fa-calendar fa-3x" aria-hidden="true" style="float:left; margin-top:6px;"></i>
			<div id="date_group" style="float:left; width:600px; margin-left:5px;">
				
				<div id="0d" class="date_div">
					<div style="float:left;">
						<span class="month" style="margin-top:8px; float:left;"></span> <br/>
						<span id="dayoftheweek0" style="margin-left:6px;"></span>
					</div>
					<span id="day0" style="float:left; font-size:40px;"></span>
				</div>
				
				<div id="1d" class="date_div">
					<div style="float:left;">
						<span class="month" style="margin-top:8px; float:left;"></span> <br/>
						<span id="dayoftheweek1" style="margin-left:6px;"></span>
					</div>
					<span id="day1" style="float:left; font-size:40px;"></span>
				</div>
				
				<div id="2d" class="date_div">
					<div style="float:left;">
						<span class="month" style="margin-top:8px; float:left;"></span> <br/>
						<span id="dayoftheweek2" style="margin-left:6px;"></span>
					</div>
					<span id="day2" style="float:left; font-size:40px;"></span>
				</div>
				
				<div id="3d" class="date_div">
					<div style="float:left;">
						<span class="month" style="margin-top:8px; float:left;"></span> <br/>
						<span id="dayoftheweek3" style="margin-left:6px;"></span>
					</div>
					<span id="day3" style="float:left; font-size:40px;"></span>
				</div>
				<div id="4d" class="date_div">
					<div style="float:left;">
						<span class="month" style="margin-top:8px; float:left;"></span> <br/>
						<span id="dayoftheweek4" style="margin-left:6px;"></span>
					</div>
					<span id="day4" style="float:left; font-size:40px;"></span>
				</div>
			</div>
		</div>
		
		<div style="border:1px solid #000; margin-top:25px; width:100%; float:left;"></div>
		
		<div style="margin-top:10px; margin-left:20px; float:left;">
			<i class="fa fa-sun-o fa-1x" aria-hidden="true" style="color:red"><span style="color:black; font-weight:bold;">조조</span></i> &nbsp
			<i class="fa fa-moon-o fa-1x" aria-hidden="true" style="color:blue"><span style="color:black; font-weight:bold;">심야</span></i>
			<span style="margin-left:50px; color:#4B4B4B;">* 시간을 클릭하시면 빠른 예매를 하실 수 있습니다.</span>
		</div>
		
		<div id="movie_place1" style="float:left;">
			<div style="border-top:1px solid #000; margin-top:10px; width:1140px;"></div>
			<div id="movie" style="float:left; margin-top:20px; margin-left:20px;">
				<div id="grade_circle" style="margin-top:3px; float:left; border:1px solid orange; border-radius:20px; width:25px; height:25px; text-align:center; padding-top:1px; background-color:orange; color:white;">15</div>
				<span style="margin-left:20px; font-size:20px;">제임스 본</span>
				<span style="margin-left:10px; border:2px solid #6799FF; border-radius:3px; color:#6799FF; font-weight:bold; font-size:13px;">상영중</span>
				<span>장르 / 상영시간 / 2016-08-02 개봉</span>
				<br/>
				
				<i class="fa fa-caret-right" aria-hidden="true" style="margin-top:10px;"><span>&nbsp 2D > 3관 8층 > 총 150석</span></i>
				<br/>
				<div id="time_table">
					<!-- timetable ajax 구간 -->
				</div>
				
			</div>
		</div>
		<div id="movie_place2" style="clear:both; float:left;">
			<div style="border-top:1px solid #000; margin-top:30px; width:1140px;"></div>
			<div id="movie" style="float:left; margin-top:20px; margin-left:20px;">
				<div id="grade_circle" style="margin-top:3px; float:left; border:1px solid orange; border-radius:20px; width:25px; height:25px; text-align:center; padding-top:1px; background-color:orange; color:white;">15</div>
				<span style="margin-left:20px; font-size:20px;">수어사이드 스쿼드</span>
				<span style="margin-left:10px; border:2px solid #6799FF; border-radius:3px; color:#6799FF; font-weight:bold; font-size:13px;">상영중</span>
				<span>장르 / 상영시간 / 2016-08-03 개봉</span>
				<br/>
				
				<i class="fa fa-caret-right" aria-hidden="true" style="margin-top:10px;"><span>&nbsp 2D > 3관 8층 > 총 150석</span></i>
				<br/>
				<div class="time_table">
					<li class="time_li">
						<a href="#"><span style="font-size:15px; display:block;">13:20</span><span>120석</span></a>
					</li>
					<li class="time_li">
						<a href="#"><span style="font-size:15px; display:block;">15:20</span><span>120석</span></a>
					</li>
					<li class="time_li">
						<a href="#"><span style="font-size:15px; display:block;">17:20</span><span>120석</span></a>
					</li>
					<li class="time_li">
						<a href="#"><span style="font-size:15px; display:block;">19:20</span><span>120석</span></a>
					</li>
					<li class="time_li">
						<a href="#"><span style="font-size:15px; display:block;">21:20</span><span>120석</span></a>
					</li>
				</div>
				
				<i class="fa fa-caret-right" aria-hidden="true" style="clear:both; margin-top:10px;"><span>&nbsp 2D > 1관 3층 > 총 150석</span></i>
				<br/>
				<div class="time_table">
					<li class="time_li">
						<a href="#"><span style="font-size:15px; display:block;">13:20</span><span>120석</span></a>
					</li>
					<li class="time_li">
						<a href="#"><span style="font-size:15px; display:block;">15:20</span><span>120석</span></a>
					</li>
					<li class="time_li">
						<a href="#"><span style="font-size:15px; display:block;">17:20</span><span>120석</span></a>
					</li>
					<li class="time_li">
						<a href="#"><span style="font-size:15px; display:block;">19:20</span><span>120석</span></a>
					</li>
					<li class="time_li">
						<a href="#"><span style="font-size:15px; display:block;">21:20</span><span>120석</span></a>
					</li>
				</div>
			</div>
		</div>
		
		<div style="border:1px solid #000; margin-top:25px; clear:both; float:left; width:100%;"></div>
		
	</div>
</div>

<script>
	var date = new Date();
	
	var month = date.getMonth() + 1;
	var day = date.getDate();
	
	if(month < 10){
		month = "0" + month;
	}
	
	var dayOfWeek = date.getDay();
	
	function getTheater_list(location){
		if(location == null || location == ""){
			location = "서울";
		}
		$.ajax({
			type : 'get',
			url : '/timetable/theater',
			headers : {
			"Content-Type" : "application/json",
			},
			dataType : 'json',
			data : {"location" : location},
			success : function(theater_list){
				setTheater_list(theater_list);
			}
		});
	}
	
	getTheater_list();
	
	function setTheater_list(theater_list){
		var result = "";
		for(var i=0; i<theater_list.length; i++){
			result += '<li class="th_li" onclick="setTheater_name('+"'"+theater_list[i].theater_name+"'"+","+"'"+theater_list[i].theater_id+"'"+')">'
				+ theater_list[i].theater_name
				+ '</li>';
		}
		
		document.getElementById("theater").innerHTML = result;
	}
	
	function setTheater_name(theater_name, theater_id){
		document.getElementById("theater_name").innerHTML = theater_name;
		document.getElementById("theater_id").innerHTML = theater_id;
		document.getElementById("theater_id").value = theater_id;
	}
	
	setTheater_name("CGV강남","T001");
	
	$(document).ready(function(){
		$(".month").text(month+"월");
		
		for(var a=0; a<5; a++){
			if(day < 10){
				day = "0" + day;
			}
			$("#dayoftheweek"+a).html(getDay(dayOfWeek));
			
			$("#day"+a).html(day);
			$("#day"+a).val(day);
			
			day++;
			dayOfWeek++;
		}
	});
	
	function getDay(dayOfWeek){
		var date = "";
		
		if(dayOfWeek > 6){
			dayOfWeek = dayOfWeek - 7 ;
		}
		
		switch(dayOfWeek){
			case 0 : date = "일"; break;
			case 1 : date = "월"; break;
			case 2 : date = "화"; break;
			case 3 : date = "수"; break;
			case 4 : date = "목"; break;
			case 5 : date = "금"; break;
			case 6 : date = "토"; break;
		}
		
		return date;
	}
	
	var cday = "";
	var ti = document.getElementById("theater_id").value;
	
	$(".date_div").click(function(){
		alert($(this));
		var temp=$(this).id.substring(0, 1);
			cday = month + "-" + $("#day" + temp).val();
			getTimetable(ti, cday);
		$(".time_li").html("1");
	})
	
	getTimetable("T001", month+"-0"+day);
	
	function getTimetable(theater_id, cday){
		$.ajax({
			type : 'get',
			url : '/timetable/tt',
			headers : {
			"Content-Type" : "application/json",
			},
			dataType : 'json',
			data : {
				"theater_id" : theater_id,
				"day" : cday	
			},
			success : function(tt){
				setTimetable(tt);
			}
		});
	}
	
	
	function setTimetable(tt){
		var timetable = "";
		$("#time_table").html("");
		for(var i=0; i<tt.length; i++){
			timetable += '<li class="time_li">'
				+ '<a href="#">'
				+ '<span style="font-size:15px; display:block;">'
				+ tt[i].start_time.substring(11,16)
				+ '</span>'
				+ '<span>'
				+ '120석'
				+ '</span>'
				+ '</a>'
				+ '</li>';
		}
		
		$("#time_table").html(timetable);
	}
</script>

<style>
	
	#time_table > .time_li:FIRST-CHILD{
		border-left:1px solid #BDBDBD;
	}
	.time_li{
		margin-top:10px;
		float:left;
		border-top:1px solid #BDBDBD;
		border-right:1px solid #BDBDBD;
		border-bottom:1px solid #BDBDBD;
		width:70px;
		text-align:center;
	}
	.time_li > a{
		text-decoration:none;
	}
	.time_li > a:hover{
		color:red;
	}
	.lo_li{
		color:white;
		font-weight:bold;
		font-size:17px;
		margin-left:32px;
		margin-right:32px;
		cursor:pointer;
	}
	.lo_li:HOVER{
		color:red;
	}
	.th_li{
		color:white;
		font-size:14px;
		margin-left:60px;
		margin-right:25px;
		width:200px;
		margin-top:10px;
	}
	.th_li:HOVER{
		color:red;
		cursor:pointer;
	}
	.nav-tabs{
		border:0px;
	}
	.date_div{
		float:left;
		width:80px;
		margin-left:20px;
		margin-right:20px;
	}
	.date_div:HOVER{
		cursor:pointer;
		color:red;
	}
</style>

<%@include file="./include/footer.jsp" %>