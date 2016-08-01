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
<div class="content" style="margin-left: 250px;">
	<div style="height: 34px; line-height: 34px;">
		<span style="font-size: 20px;">예매내역</span><span style="margin-left: 5px;">${reservationTotal}건</span>
	</div>
	<div id="reservationList" style="border: 1px solid #aaa; border-radius: 15px;">
		<div style="width: 888px; height:50px; line-height: 50px; border-bottom: 1px solid #aaa;">
			<div style="width: 200px; text-align: center; font-size: 15px; border-right: 1px solid #aaa; float: left;">예매번호</div>
			<div style="width: 688px; text-align: center; font-size: 15px; margin-left: 200px;">예매정보</div>
		</div>
		<c:forEach items="${reservation.reservationList}" var="r">
			<div style="height: 130px; border-top: 1px solid #aaa;">
				<span style="width: 200px; display: block; line-height: 130px; float: left; text-align: center; border-right: 1px solid #aaa;">${r.reservation_code}</span>
				<c:forEach items="${reservation.reservationMovie}" var="rm">
					<c:if test="${r.movie_id == rm.movie_id }">
						<div style="height: 130px; margin-left: 200px;">
							<span style="height: 128px; width: 105px; display: block; margin: 0; float: left;"><a href="/movie/${r.movie_id}"><img style="height: 100%; vertical-align: baseline;" src="${rm.poster}"></a></span>
							<span style="height:30px; display: block; margin-left: 115px; margin-bottom: 10px; padding-top:10px; font-weight: bold;"><a href="/movie/${r.movie_id}">${rm.title}</a></span>
							<span style="height:20px; width: 56px;display: block; margin-left: 10px; margin-bottom: 10px; font-weight: bold; float: left;">관람극장</span>
							<span style="height:20px; display: block; margin-left: 181px; margin-bottom: 10px;">${r.theater_name} ${r.plex_number}관&nbsp/&nbsp${r.ticket_cnt}명</span>
							<span style="height:20px; width: 56px;display: block; margin-left: 10px; margin-bottom: 10px; font-weight: bold; float: left;">관람일시</span>
							<span style="height:20px; display: block; margin-left: 181px; margin-bottom: 10px;">${r.start_time}</span>
							<span style="height:20px; width: 56px;display: block; margin-left: 10px; margin-bottom: 10px; font-weight: bold; float: left;">결제</span>
							<span style="height:20px; display: block; margin-left: 181px;">${r.pay_method} &nbsp/&nbsp ${r.pay}원</span>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
</div>

<script>
var currentPage = 1;
var startPage = 1;
var endPage = 1;
var totalPage;
function setMovieList(ml,am){
	var a = $("#movie_list");
	var result = "";
	var date = null;
	a.html();
	a.show();
	a.siblings().hide();
	$("#page").show();
	
	$(ml).each(function(i){
		result+=  '<div style="border: 0px solid #000; float: left; margin: 10px; width: 264px; height: 508px; text-align: left;">'
				+ '<span style="width: 264px; height: 358px; text-align: center;"><img src="'+ this.poster +'" style="width: 100%; height: 100%;"></span>'
				+ '<span style="font-weight: bold; font-size: 16px;">' + this.title + '</span>'
				+ '<span>등급 : ' + this.rating + '</span>'
				+ '<span>장르: ';
				
		for(var j = 0; j<am.genre.length; j++){
			if(am.genre[j].movie_id == this.movie_id){
				result += am.genre[j].movie_genre + '&nbsp&nbsp';
			}
		}
		
		result+=  '</span>'
				+ '<span>개봉일 : ' + this.open_date +'</span>'
				+ '<span>감독 : ';
				
		for(var k = 0; k<am.director.length; k++){
			if(am.director[k].movie_id == this.movie_id){
				result += am.director[k].director_name + '&nbsp&nbsp';
			}
		}
				
		result+= '</span>'
			   + '<span>배우 : ';
		
		for(var l = 0; l<am.actor.length; l++){
			if(am.actor[l].movie_id == this.movie_id){
				result += am.actor[l].actor_name + '&nbsp&nbsp';
			}
		}
		
		result+= '</span>';
		
		if(this.status == "play"){
			result+= '<span><button class="btn btn-danger btn-sm" style="width: 262px; border-radius: 5px; border: 0;" onclick="'+ this.movie_id +'">예매</button></span>';
		}else if(this.status == "schedule"){
			result+= '<span style="text-align: center; color: #ff4859; font-weight: bold; letter-spacing: 25px;">&nbsp상영예정</span>';
		}
		
		result+= '</div>';
		
		if((i+1)%4 == 0 && (i+1)%8 != 0){
			result+= '<div class="line_black"></div>';
		}
	});
	a.html(result);
}

function setPagePrint(pm){
	var str = "";
	
	str += "<ul class='pagination'>";
	
	if(pm.prev){
		str += "<li><a href='javascript:getMovieList("+(pm.startPage-1)+")'>&lt;</a> </li>";
	}
	
	for(var i = pm.startPage; i<=pm.endPage; i++){
		if(i == pm.criteria.page){
			str+="<li class='active'><a href='#'>" + i + "</a></li>";
		}else{
			str+="<li><a href='javascript:getMovieList("+i+")'>" + i + "</a></li>";
		}
		
	}
	
	
	if(pm.next){
		str += "<li><a href='javascript:getMovieList("+(pm.endPage+1)+")'>&gt;</a> </li>";
	}
	str += "</ul>";
	document.getElementById("page").innerHTML = str;
	
}

function getMovieList(page){
	
	if(page==null){
		page = currentPage; 
	}
	currentPage = page;
	
	$.ajax({
		type : 'get',
		url : '/search/movie/'+page,
		headers : {
			"Content-Type" : "application/json"
//			"X-HTTP-Method-Override" : "GET",
		},
		dataType : 'json',
		data : '',
		success : function(result){
			endPage = result.p.endPage; 
			startPage = result.p.startPage;
			if(totalPage<result.p.totalPage){
				getMovieList(result.p.totalPage);
			}
			totalPage = result.p.totalPage;
			
			setMovieList(result.l,result.am);
			setPagePrint(result.p);
			
		}
	});
	
}
</script>
<%@include file="../include/footer.jsp"%>