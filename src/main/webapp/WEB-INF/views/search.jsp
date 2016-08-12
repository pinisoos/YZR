<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="./include/header.jsp" %>
	<style>
	
		#movie_list > div > span{
			display: inline-block;
			float: left;
			width: 264px;
		}
		
		#actor_list > div > span{
			display: inline-block;
			float: left;
			width: 1044px;
		}
		
		#director_list > div > span{
			display: inline-block;
			float: left;
			width: 1044px;
		}
		
		.movie{
			border: 0px solid #000; 
			float: left; 
			margin: 10px; 
			width: 264px; 
			height: 508px; 
			text-align: left;
		}
		
		.actor, .director{
			border-bottom: 1px dashed #aaa; 
			float: left; 
			margin: 5px 5px 0 5px; 
			padding-bottom: 5px;
			width: 1128px; 
			height: 105px; 
			line-height: 20px;
		}
		
		.actor:last-child, 
		.director:last-child{
			border: 0;
		}
		
		.line_red{
			clear: both; 
			border: 1px solid red; 
			height: 0; width: 80%; 
			margin: 20px auto; 
			display: inline-block;
		}
		
		.line_black{
			clear: both; 
			border: 1px solid #000; 
			height: 0; width: 80%; 
			margin: 20px auto; 
			display: inline-block;
		}
		
	</style>
	
	<!-- 검색창 -->
	<div style="clear: both; width: 100%; height: 34px;">
		<form class="form" action="/search" method="post">
			<div class="form-group" style="clear: both; width: 100%; height: 34px;">
				<div class="col-sm-10" style="padding: 0; height: 34px;">
					<input id="serach" class="form-control" name="search" type="text" placeholder="검색어를 입력하세요.">
				</div>
				<div class="col-sm-2">
					<input type="submit" class="btn btn-danger" value="검색" style="width: 100%;">
				</div>
			</div>	
		</form>
	</div>
	
	<!-- 카테고리 전체/영화/배우/감독 -->
	<div>
		<ul id="searchCategory" class="nav nav-tabs col-sm-offset-4 searchCategory" style="clear: both; margin-top: 20px; width: 256px; margin: 0 auto; margin-top: 10px; font-size: 15px; font-weight: bold;">
			<li class="active"><a id="searchAll" href="javascript:getSearchList()">전체</a></li>
			<li><a id="searchMovie" href="javascript:getMovieList(1)">영화</a></li>
			<li><a id="searchActor" href="javascript:getActorList(1)">배우</a></li>
			<li><a id="searchDirector" href="javascript:getDirectorList(1)">감독</a></li>
		</ul>
	</div>
	<!-- 목록 -->
	<div id="listWrapper" style="clear: both; width: 100%;margin-top: 20px; height: auto; text-align: center;">
		
		<!-- 영화 목록 -->
		<div id="movie_list" class="tab-pane col-sm-12" style="border: 0px solid #000; height: auto; margin-top: 10px; padding: 0px;">
		</div>
		
		<!-- 구분선 -->
		<div class="line_red"></div>
		
		<!-- 배우 목록 -->
		<div id="actor_list" class="col-sm-12" style="border: 0px solid #000; height: auto; padding: 0px; text-align: left;">
		</div>
		
		<!-- 구분선 -->
		<div class="line_red"></div>
		
		<!-- 감독 목록 -->
		<div id="director_list" class="col-sm-12" style="border: 0px solid #000; height: auto; padding: 0px; text-align: left;">
		</div>
		
		<!-- 페이징 -->
		<div id="page" style="height: auto; width: 100%; display: inline-block;"></div>
		
	</div>
	
	
<script type="text/javascript">
	var search = "${search}";
	
	$('#searchCategory a').click(function (e) {
	    $(this).tab('show');
	});
	
	//더보기 눌렀을 때 카테고리 active & 해당하는 목록 페이징
	function goMovieList(){
		$("#searchMovie").tab('show');
		getMovieList(1);
	}
	
	function goActorList(){
		$("#searchActor").tab('show');
		getActorList(1);
	}
	
	function goDirectorList(){
		$("#searchDirector").tab('show');
		getDirectorList(1);
	}
	
	
	function setDefaultList(ml){
		var a = $("#movie_list");
		var result = "";
		var date = null;
		a.html();
		a.show();
		a.siblings().hide();
		$("#page").show();
		
		$(ml).each(function(i){
			result+=  '<div class="movie">'
					+ '<span style="width: 264px; height: 358px; text-align: center;"><img src="/resources/images/poster/'+ this.poster +'" style="width: 100%; height: 100%;"></span>'
					+ '<span style="font-weight: bold; font-size: 16px;"><a href="/movie/' + this.movie_id + '">' + this.title + '</a></span>'
					+ '<span>등급 : ' + this.rating + '</span>'
					+ '<span>예매율 : ' + this.reservation_rate + '</span>'
					+ '<span>개봉일 : ' + this.open_date +'</span>';
					
			
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
	
	function getSearchList(){
		$("#listWrapper").children().show();
		$("#movie_list").html("");
		$("#actor_list").html("");
		$("#director_list").html("");
		$("#page").hide();
		if(search == null || search == ""){
			$.ajax({
				type : 'get',
				url : '/movie/main',
				headers : {
				"Content-Type" : "application/json",
				//"X-HTTP-Method-Override" : "GET",  ----  POST 이거나 GET인경우는 생략가능
				},
				dataType : 'json',
				data : {"category": "chart"},
				success : function(movie){
					setDefaultList(movie);
				}
			});
		}else{
			$.ajax({
				type : 'get',
				url : '/search/result',
				headers : {
					"Content-Type" : "application/json"
		//			"X-HTTP-Method-Override" : "GET",
				},
				dataType : 'json',
				data : {
					"search" : search
				},
				success : function(result){
					setSearchList(result);
					
				}
			});
		}
	}
	getSearchList();
	function setSearchList(result){
		
		//Movie List
		var movie_list = "";
		var am = result.associateMovie;
	
		movie_list += '<div style="text-align: left; padding-left: 10px; padding-top: 9px; font-weight: bolder; font-size: 15px;"><a id="goMovieList" href="javascript:goMovieList()">영화('+result.movieList.length+'건)<span class="glyphicon glyphicon-plus-sign"></span></a></div>';
		$(result.movieList).each(function(i){
			if(i<4){
				movie_list+=  '<div class="movie">'
							+ '<span style="width: 264px; height: 358px; text-align: center;"><img src="/resources/images/poster/'+ this.poster +'" style="width: 100%; height: 100%;"></span>'
						    + '<span style="font-weight: bold; font-size: 16px;">'+ this.title + '</span>'
							+ '<span>등급 : ' + this.rating + '</span>'
							+ '<span>장르: ';
						
				for(var j = 0; j<am.genre.length; j++){
					if(am.genre[j].movie_id == this.movie_id){
						movie_list += am.genre[j].movie_genre + '&nbsp&nbsp';
					}
				}
				
				movie_list+=  '</span>'
							+ '<span>개봉일 : ' + this.open_date +'</span>'
							+ '<span>감독 : ';
						
				for(var k = 0; k<am.director.length; k++){
					if(am.director[k].movie_id == this.movie_id){
						movie_list += am.director[k].director_name + '&nbsp&nbsp';
					}
				}
						
				movie_list+= '</span>'
					 	   + '<span>배우 : ';
				
				for(var l = 0; l<am.actor.length; l++){
					if(am.actor[l].movie_id == this.movie_id){
						movie_list += am.actor[l].actor_name + '&nbsp&nbsp';
					}
				}
				movie_list+= '</span>';
				
				if(this.status == "play"){
					movie_list+= '<span><button class="btn btn-danger btn-sm" style="width: 262px; border-radius: 5px; border: 0;" onclick="'+ this.movie_id +'">예매</button></span>';
				}else if(this.status == "schedule"){
					movie_list+= '<span style="text-align: center; color: #ff4859; font-weight: bold; letter-spacing: 25px;">&nbsp상영예정</span>';
				}
				
				movie_list+= '</div>';
			}
		});
		
		$("#movie_list").html(movie_list);
		
		//Actor List
		var actor_list = "";
		actor_list += '<div style="text-align: left; padding-left: 10px; padding-top: 9px; font-weight: bolder; font-size: 15px;"><a id="goActorList" href="javascript:goActorList()">배우(' + result.actorList.length + '건)<span class="glyphicon glyphicon-plus-sign"></span></a></div>';
		$(result.actorList).each(function(i){
			if(i<3){
				actor_list+=  '<div class="actor">'
						+ '<span style="height: 100px; width: 72px; margin-right: 10px;"><img src="' + this.actor_photo + '" style="max-width: 100%; height: 100%;"></span>'
						+ '<span style="font-weight: bold; font-size: 16px;">'+ this.actor_name + '</span>'
						+ '<span>나이 : '+ this.actor_age + '</span>'
						+ '<span>데뷔 : '+ this.actor_debut + '</span>'
						+ '<span>소속사 : '+ this.actor_company + '</span>'
						+ '<span>출연영화 : ';
				
				for(var j = 0; j<result.movieActor.length; j++){
					if(result.movieActor[j].actor_name == this.actor_name){
						actor_list += result.movieActor[j].title + '&nbsp&nbsp';
					}
				}
				
				actor_list+= '</span>'
					    + '</div>';
			}		
		});
		
		$("#actor_list").html(actor_list);
		
		//Director List
		var director_list = "";
		director_list += '<div style="text-align: left; padding-left: 10px; padding-top: 9px; font-weight: bolder; font-size: 15px;"><a id="goDirectorList" href="javascript:goDirectorList()">감독(' + result.directorList.length + '건)<span class="glyphicon glyphicon-plus-sign"></span></a></div>';
		
		$(result.directorList).each(function(i){
			if(i<3){
				director_list+=  '<div class="director">'
							   + '<span style="height: 100px; width: 72px; margin-right: 10px;"><img src="' + this.director_photo + '" style="width: 100%; height: 100%;"></span>'
							   + '<span style="font-weight: bold; font-size: 16px;">'+ this.director_name + '</span>'
							   + '<span>나이 : '+ this.director_age + '</span>'
							   + '<span>데뷔 : '+ this.director_debut + '</span>'
							   + '<span>소속사 : '+ this.director_company + '</span>'
							   + '<span>영화 : ';
				
				for(var j = 0; j<result.movieDirector.length; j++){
					if(result.movieDirector[j].director_name == this.director_name){
						director_list += result.movieDirector[j].title + '&nbsp&nbsp';
					}
				}	
	
				director_list+= '</span>'
							  + '</div>';
			}	
		});
		
		$("#director_list").html(director_list);
	}
	
	//movielist
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
			result+=  '<div class="movie">'
					+ '<span style="width: 264px; height: 358px; text-align: center;"><img src="/resources/images/poster/'+ this.poster +'" style="width: 100%; height: 100%;"></span>'
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
//				"X-HTTP-Method-Override" : "GET",
			},
			dataType : 'json',
			data : {
				"search" : search
			},
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
	
	//actor list
	var actorCurrentPage = 1;
	var actorStartPage = 1;
	var actorEndPage = 1;
	var actorTotalPage;
	
	function setActorList(al, am){
		var a = $("#actor_list");
		var result = "";
		a.html();
		a.show();
		a.siblings().hide();
		$("#page").show();
		
		$(al).each(function(){
			
			result+=  '<div class="actor">'
					+ '<span style="height: 100px; width: 72px; margin-right: 10px;"><img src="' + this.actor_photo + '" style="width: 100%; height: 100%;"></span>'
					+ '<span style="font-weight: bold; font-size: 16px;">'+ this.actor_name + '</span>'
					+ '<span>나이 : '+ this.actor_age + '</span>'
					+ '<span>데뷔 : '+ this.actor_debut + '</span>'
					+ '<span>소속사 : '+ this.actor_company + '</span>'
					+ '<span>출연영화 : ';
			
			for(var i = 0; i<am.length; i++){
				if(am[i].actor_name == this.actor_name){
					result += am[i].title + '&nbsp&nbsp';
				}
			}
			
			result+= '</span>'
				    + '</div>';
					
		});
		
		a.html(result);
	}
	
	function setActorPagePrint(pm){
		var str = "";
		
		str += "<ul class='pagination'>";
		
		if(pm.prev){
			str += "<li><a href='javascript:getActorList("+(pm.startPage-1)+")'>&lt;</a> </li>";
		}
		
		for(var i = pm.startPage; i<=pm.endPage; i++){
			if(i == pm.criteria.page){
				str+="<li class='active'><a href='#'>" + i + "</a></li>";
			}else{
				str+="<li><a href='javascript:getActorList("+i+")'>" + i + "</a></li>";
			}
			
		}
		
		
		if(pm.next){
			str += "<li><a href='javascript:getActorList("+(pm.endPage+1)+")'>&gt;</a> </li>";
		}
		str += "</ul>";
		document.getElementById("page").innerHTML = str;
		
	}
	
	function getActorList(page){
		
		if(page==null){
			page = actorCurrentPage; 
		}
		actorCurrentPage = page;
		
		$.ajax({
			type : 'get',
			url : '/search/actor/'+page,
			headers : {
				"Content-Type" : "application/json"
//				"X-HTTP-Method-Override" : "GET",
			},
			dataType : 'json',
			data : {
				"search" : search
				},
			success : function(result){
				actorEndPage = result.p.endPage;
				actorStartPage = result.p.startPage;
				if(actorTotalPage<result.p.totalPage){
					getActorList(result.p.totalPage);
				}
				actorTotalPage = result.p.totalPage;
		
				setActorList(result.l, result.am);
				setActorPagePrint(result.p);	
			}
			
		});
		
	}
	
	//director list
	var directorCurrentPage = 1;
	var directorStartPage = 1;
	var directorEndPage = 1;
	var directorTotalPage;
	
	function setDirectorList(dl,am){
		var a = $("#director_list");
		var result = "";
		a.html();
		a.show();
		a.siblings().hide();
		$("#page").show();
		
		$(dl).each(function(){
			result+=  '<div class="director">'
					+ '<span style="height: 100px; width: 72px; margin-right: 10px;"><img src="' + this.director_photo + '" style="width: 100%; height: 100%;"></span>'
					+ '<span style="font-weight: bold; font-size: 16px;">'+ this.director_name + '</span>'
					+ '<span>나이 : '+ this.director_age + '</span>'
					+ '<span>데뷔 : '+ this.director_debut + '</span>'
					+ '<span>소속사 : '+ this.director_company + '</span>'
					+ '<span>영화 : ';
			
			for(var i = 0; i<am.length; i++){
				if(am[i].director_name == this.director_name){
					result += am[i].title + '&nbsp&nbsp';
				}
			}	
			result+= '</span>'
				    + '</div>';
					
		});
		a.html(result);
	}
	
	function setDirectorPagePrint(pm){
		var str = "";
		
		str += "<ul class='pagination'>";
		
		if(pm.prev){
			str += "<li><a href='javascript:getDirectorList("+(pm.startPage-1)+")'>&lt;</a> </li>";
		}
		
		for(var i = pm.startPage; i<=pm.endPage; i++){
			if(i == pm.criteria.page){
				str+="<li class='active'><a href='#'>" + i + "</a></li>";
			}else{
				str+="<li><a href='javascript:getDirectorList("+i+")'>" + i + "</a></li>";
			}
			
		}
		
		
		if(pm.next){
			str += "<li><a href='javascript:getDirectorList("+(pm.endPage+1)+")'>&gt;</a> </li>";
		}
		str += "</ul>";
		document.getElementById("page").innerHTML = str;
		
	}
	
	function getDirectorList(page){
		
		if(page==null){
			page = directorCurrentPage; 
		}
		directorCurrentPage = page;
		
		$.ajax({
			type : 'get',
			url : '/search/director/'+page,
			headers : {
				"Content-Type" : "application/json"
//				"X-HTTP-Method-Override" : "GET",
			},
			dataType : 'json',
			data : {
				"search" : search
			},
			success : function(result){
				directorEndPage = result.p.endPage;
				directorStartPage = result.p.startPage;
				if(directorTotalPage<result.p.totalPage){
					getDirectorList(result.p.totalPage);
				}
				directorTotalPage = result.p.totalPage;
				
				setDirectorList(result.l, result.am);
				setDirectorPagePrint(result.p);	
			}
			
		});
		
	}
	
</script>
	
<%@ include file="./include/footer.jsp" %>