<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="./include/header.jsp"%>
<script type="text/javascript" src="/resources/highcharts.js"></script>
<script type="text/javascript" src="/resources/highcharts-more.js"></script>
<div id="wrap_content" style="min-height: 100%; position: relative;">
	<div id="content">
		<div style="margin-left: 30px;">
			<font size="8px" ;weight="bold">영화 상세</font>
		</div>
		<hr style="border: solid 1px black; margin: 0 auto;">
		<div>
			<img src="/resources/images/poster/${movievo.poster}"
				style="float: left; height: 280px; width: 200px; margin-left: 30px; margin-top: 40px;">
		</div>
		<div>
			<div style="display: inline-block; margin-top: 20px; margin-left: 15px;">	
				<span> 
				<font size="6px" style="margin-top: 10px; float:left;"> ${movievo.title }</font>
				</span> 
			</div>	
			<div style="display: inline-block; margin-top: 20px;margin-left: 5px;">
				<label class="label label-primary" style="padding-bottom: 13px; padding-top: 13px; float:left;"> 
					<c:if test="${movievo.status == 'play' }">
						현재 상영중
					</c:if>
					<c:if test="${movievo.status == 'schedule' }">
						개봉 예정작
					</c:if>
					<c:if test="${movievo.status == 'end' }">
						상영 종료
					</c:if> 
				 </label>
			</div>	 
		</div>
		<div >
			<b><span style="margin-top:15px; margin-left:20px;"> 예매율 : ${movievo.reservation_rate} % </span></b>
		</div>
		<hr>
		<b>	
		<div>
			<span style="margin-left: 20px; margin-bottom: 10px;"> 
			<font face="NanumBarunGothicBold">감독 :</font> 
			<c:forEach items="${directorlist}" var="directorlist">
				 ${directorlist.director_name }	
			</c:forEach> 
			</span>
			<br/>
			<span style="margin-left: 20px; margin-bottom: 10px;">배우 : 
				<c:forEach	items="${actorlist}" var="actorlist">
					${actorlist.actor_name }   
				</c:forEach>
			</span>
		</div>
		<div>
			<span style="margin-left: 20px; margin-bottom: 10px;">장르 :
				 <c:forEach items="${genrelist}" var="genrelist">
							${genrelist.movie_genre }
				</c:forEach>
			</span>
		</div>
		<div>
			<span style="margin-left: 20px; margin-bottom: 10px;"> 개봉일 : ${movievo.open_date } </span>
		</div>
		<div>
			<span style="margin-left: 20px; margin-bottom: 10px;"> 공식 사이트 : ${movievo.site } </span>
		</div>
		<div style="margin-left: 430px;  margin-top: 40px;" >
			<button type="button" class="btn btn-danger" onclick="reservation()">예매하러 가기</button>
			<button type="button" class="btn btn-danger" onclick="SetFocus()">평점/후기</button>
			<button type="button" class="btn btn-danger" onclick="timetable()">상영시간표</button>
		</div>
		</b>
		<hr style="border: 1; border-top: 1px solid black; margin-top : 40px;">
			<div style="width: 1100px; height: 450px; margin-top: 20px; margin-left: 30px;">
				${movievo.story }
			</div>
		</div>
		<div>
			<table class="table" id="gpatable" style="board:0px solid white">
				<tr>
					<td>
						<div id="container1" style="width: 420px; height: 300px;"></div>
					</td>
					<td>
						<div id="container2"
							style="width: 300px; height: 300px; margin-left: 10px;"></div>
					</td>
					<td>
						<div id="container3"
							style="width: 300px; height: 300px; margin-left: 10px;"></div>
					</td>
				</tr>
			</table>
		</div>
		<div align="right">
			<c:choose>	
				<c:when test="${member_id == null || member_id == '' }">
					<button type="button" class="btn btn-info btn-3x" onclick="gpa_login()">평점</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-info btn-3x" data-toggle="modal" data-target="#myModal">평점</button>
				</c:otherwise>
			</c:choose>		
					
			<button type="button" class="btn btn-info btn-3x"
					onclick="review_write('${movievo.movie_id}')">후기작성</button>
		</div>
		<h1>후기 목록</h1>
		<table class="table table-border" >
			<thead>
				<tr>
					<th style="text-align: center; width:200px;">ID</th>		
					<th style="width:900px; margin-left: 800px; text-align: center">제목</th>
					<th style="text-align: center; width: 150px;">등록날짜</th>
					<th style="text-align: center;width: 150px;">조회수</th>
				<tr>	
			</thead>
			<tbody id="reviw_table">
			</tbody>
		</table>
		<div id="review_page" style="text-align: center;"></div>
	</div>
<div class="modal fade" id="myModal" role="dialog"
	style="margin: 0 auto;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<form id="form">
				<div class="modal-header">
					<H2>평점을 입력하세요</H2>
				</div>
				<div class="modal-body">
					<font style="size: 20px; font-weight: bold;">배우연기</font><br> 
					<label class="radio-inline"> 
						<input type="radio" name="acting" value="1">1점
					</label>
					<label class="radio-inline"> 
						<input type="radio" name="acting" value="2">2점
					</label> 
					<label class="radio-inline"> 
						<input type="radio"	name="acting" value="3">3점
					</label> 
					<label class="radio-inline"> 
						<input type="radio" name="acting" value="4">4점
					</label> 
					<label class="radio-inline"> 
						<input type="radio" name="acting" value="5">5점
					</label>
					<br> 
					<font style="size: 20px; font-weight: bold;">감독연출</font
					><br>
					<label class="radio-inline">
						<input type="radio" name="direction" value="1">1점
					</label> 
						<label class="radio-inline"> <input type="radio" name="direction" value="2">2점
					</label> 
					<label class="radio-inline"> 
					<input type="radio"
						name="direction" value="3">3점
					</label> 
					<label class="radio-inline"> <input type="radio"
						name="direction" value="4">4점
					</label> 
					<label class="radio-inline"> <input type="radio"
						name="direction" value="5">5점
					</label
					><br> 
					<font style="size: 20px; font-weight: bold;">영상미</font><br>
					<label class="radio-inline"> 
					<input type="radio"
						name="beauty" value="1">1점
					</label> 
					<label class="radio-inline"> <input type="radio"
						name="beauty" value="2">2점
					</label>
					 <label class="radio-inline"> <input type="radio"
						name="beauty" value="3">3점
					</label> 
					<label class="radio-inline"> <input type="radio"
						name="beauty" value="4">4점
					</label>
					 <label class="radio-inline"> <input type="radio"
						name="beauty" value="5">5점
					</label>
					<br> <font style="size: 20px; font-weight: bold;">OST</font><br>
					<label class="radio-inline">
						<input type="radio" name="ost" value="1">1점
					</label> 
					<label class="radio-inline"> 
						<input type="radio" name="ost" value="2">2점
					</label> 
					<label class="radio-inline"> 
						<input type="radio" name="ost" value="3">3점
					</label> 
					<label class="radio-inline"> 
						<input type="radio" name="ost" value="4">4점
					</label>
					<label class="radio-inline"> 
						<input type="radio" name="ost" value="5">5점
					</label>
					<br> <font style="size: 20px; font-weight: bold;">스토리</font><br>
					<label class="radio-inline"> 
						<input type="radio" name="story" value="1">1점
					</label> 
					<label class="radio-inline"> 
						<input type="radio" name="story" value="2">2점
					</label> 
					<label class="radio-inline">
						<input type="radio" name="story" value="3">3점
					</label> 
					<label class="radio-inline"> 
						<input type="radio" name="story" value="4">4점
					</label> 
					<label class="radio-inline">
						<input type="radio" name="story" value="5">5점
					</label>
					<br> 
					<font style="size: 20px; font-weight: bold;">성별</font><br>
					<label class="radio-inline">
						<input type="radio" name="gender" class="male" value="1">남자
					</label> 
					<label class="radio-inline"> 
						<input type="radio"	name="gender" class="female" value="1">여자
					</label>
					<br> <font style="size: 20px; font-weight: bold;">나이</font><br>
					<label class="radio-inline"> 
						<input type="radio" name="age" class="teenager" value="1">10대
					</label>
					 <label class="radio-inline">
					 	<input type="radio" name="age" class="twenties" value="1">20대
					</label> 
					<label class="radio-inline">
					 	<input type="radio" name="age" class="thirties" value="1">30대
					</label> 
					<label class="radio-inline"> 
						<input type="radio" name="age" class="forties" value="1">40대
					</label><br>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="updategpa()">등록</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script>

function reservation(){
	location.href="/ticket";
}
function timetable(){
	location.href="/timetable";
}


var member_id = "${member_id}";

function review_read(no){
	location.href = "/movie/review_read/" + no;

}

function gpa_login(){
	location.href="/login";
}
function review_write(id) {
	if(member_id ==null || member_id == ""){
		location.href="/login";
	} else{
		location.href = "/review/" + id;
	}
}
	
var resulta = null;

function updategpa() {
	
	var acting = $(':radio[name="acting"]:checked').val();
	var direction = $(':radio[name="direction"]:checked').val();
	var beauty = $(':radio[name="beauty"]:checked').val();
	var ost = $(':radio[name="ost"]:checked').val();
	var story = $(':radio[name="story"]:checked').val();
	var male = $(".male:checked").val();
	var female = $(".female:checked").val();
	var teenager = $(".teenager:checked").val();
	var twenties = $(".twenties:checked").val();
	var thirties = $(".thirties:checked").val();
	var forties = $(".forties:checked").val();
	if(member_id ==null || member_id == ""){
		location.href="/login";
	}else{
		
		$.ajax({
			type : 'post',
			url : '/movie/gpachart/${movie_id}',
			headers : {
				"Content-Type" : "application/json",
			//"X-HTTP-Method-Override" : "PUT"
			},
			data : JSON.stringify({
				"acting" : acting,
				"direction" : direction,
				"beauty" : beauty,
				"ost" : ost,
				"story" : story,
				"male" : male,
				"female" : female,
				"teenager" : teenager,
				"twenties" : twenties,
				"thirties" : thirties,
				"forties" : forties,
			}),
			dataType : 'text',
			success : function(result) {
				if (result == "SUCCESS") {
					$("input:radio[name='acting']").removeAttr('checked');
					$("input:radio[name='direction']").removeAttr('checked');
					$("input:radio[name='beauty']").removeAttr('checked');
					$("input:radio[name='ost']").removeAttr('checked');
					$("input:radio[name='story']").removeAttr('checked');
					$("input:radio[name='gender']").removeAttr('checked');
					$("input:radio[name='age']").removeAttr('checked');


					
					getList();

				}
			}
		});
	}
}

function getList() {
		$.ajax({
		type : 'get',
		url : '/movie/gpachart/${movie_id}',
		headers : {
			"Content-Type" : "application/json",
		//"X-HTTP-Method-Override" : "GET",  ----  POST 이거나 GET인경우는 생략가능
		},
		dataType : 'json',
		data : '',
		success : function(result) {
			getSpider(result);
			getDonut1(result);
			getDonut2(result);
			
			//resulta = result;
		}
	});
}
getList();
function getSpider(data) {
	$('#container1').highcharts(
			{
				chart : {
					polar : true,
					type : 'line'
				},
				title : {
					text : '매력포인트',
					x : -100
				},
				pane : {
					size : '70%'
				},
				xAxis : {
					categories : [ '배우연기', '감독연출', '영상미', 'ost', '스토리' ],
					tickmarkPlacement : 'on',
					lineWidth : 0
				},
				yAxis : {
					gridLineInterpolation : 'polygon',
					lineWidth : 0,
					min : 0
				},
				legend : {
					align : 'right',
					verticalAlign : 'top',
					y : 10,
					layout : 'vertical'
				},
				series : [ {
					name: 'on/off',
					data : [ data.acting, data.direction, data.beauty,
							data.ost, data.story ],
					pointPlacement : 'on'
				} ]
			});
}
function getDonut1(data) {
	// Make monochrome colors and set them as default for all pies
	Highcharts.getOptions().plotOptions.pie.colors = (function() {
		var colors = [], base = Highcharts.getOptions().colors[0], i;
		for (i = 0; i < 10; i += 1) {
			// Start out with a darkened base color (negative brighten), and end
			// up with a much brighter color
			colors.push(Highcharts.Color(base).brighten((i - 3) / 7).get());
		}
		return colors;
	}());
	// Build the chart
	$('#container2')
			.highcharts(
					{
						chart : {
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false,
							type : 'pie'
						},
						title : {
							text : '성별예매'
						},
						tooltip : {
							pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
						},
						plotOptions : {
							pie : {
								allowPointSelect : true,
								cursor : 'pointer',
								dataLabels : {
									enabled : true,
									format : '<b>{point.name}</b>: {point.percentage:.1f} %',
									style : {
										color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
												|| 'black'
									}
								}
							}
						},
						series : [ {
							name : '성별',
							data : [ {
								name : '남자',
								y : data.male
							}, {
								name : '여자',
								y : data.female
							}, ]
						} ]
					});
}
function getDonut2(data) {
	// Make monochrome colors and set them as default for all pies
	Highcharts.getOptions().plotOptions.pie.colors = (function() {
		var colors = [], base = Highcharts.getOptions().colors[0], i;
			for (i = 0; i < 10; i += 1) {
			// Start out with a darkened base color (negative brighten), and end
			// up with a much brighter color
			colors.push(Highcharts.Color(base).brighten((i - 3) / 7).get());
		}
		return colors;
	}());
		// Build the chart
	$('#container3')
			.highcharts(
					{
						chart : {
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false,
							type : 'pie'
						},
						title : {
							text : '연령별 예매'
						},
						tooltip : {
							pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
						},
						plotOptions : {
							pie : {
								allowPointSelect : true,
								cursor : 'pointer',
								dataLabels : {
									enabled : true,
									format : '<b>{point.name}</b>: {point.percentage:.1f} %',
									style : {
										color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
												|| 'black'
									}
								}
							}
						},
						series : [ {
							name : 'Brands',
							data : [ {
								name : '10대',
								y : data.teenager
							}, {
								name : '20대',
								y : data.twenties
							}, {
								name : '30대',
								y : data.thirties
							}, {
								name : '40대',
								y : data.forties
							}, ]
						} ]
					});
}
function SetFocus(gpatable) {
	window.scrollTo(0, document.body.scrollHeight); //추가된 부분
	document.getElementById(gpatable).focus();
}

function setReviewList(data){
	var result = "<tr>";
		$(data).each(function(){
			result += "<td style='text-align: center;'>" 
			+this.member_id
			+"</td>"
			+"<td  style='text-align: left;'>"
			+"<a href='javascript:review_read("+this.no+")'style='text-decoration:none;'>"+this.review_title+"</a>"			
			+"</td>"		
			+"<td  style='text-align: center;'>"
			+this.review_date
			+"</td>"
			+"<td  style='text-align: center;'>"
			+this.review_cnt
			+"</td>"
			+"</tr>"
		});
	document.getElementById("reviw_table").innerHTML = result;
}
setReviewList();

function getReviewList(page){

	$.ajax({
		type : 'get',
		url : '/movie/${movievo.movie_id}/' + page,
		headers : {
			"Content-Type" : "application/json",
			//"X-HTTP-Method-Override" : "GET",  ----  POST 이거나 GET인경우는 생략가능
		},
		dataType : 'json',
		data : '',
		success : function(result){
			setReviewList(result.l);
			setPagePrint(result.p);
		}
	});
}

getReviewList(1);

function setPagePrint(pm){
	var str = "<ul class='pagination'>";
	
	if(pm.prev){
		str += "<li> &lt; </li>"
	}
	
	for(var i = pm.startPage; i <= pm.endPage ; i++){
		if(i == pm.criteria.page){
			str += "<li class='active'><a href='#'>" + i + "</a></li>"
		}else{
			str += "<li><a href='javascript:getReviewList("+i+")'>" + i + "</a></li>"
		}
	}
	
	if(pm.next){
		str += "<li> &gt; </li>"
	}
	
	str += "</ul>"
	document.getElementById("review_page").innerHTML = str;
}

</script>
<%@include file="./include/footer.jsp"%>