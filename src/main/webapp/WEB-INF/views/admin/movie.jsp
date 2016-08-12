<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<style>
th, td{
	text-align: center;
}
</style>
<c:if test="${ member_id == null }">
	<script>
		<% response.sendRedirect("/login");%> 
	</script>
</c:if>
<div class="content" style="margin-left: 250px;">
	<h2>영화 목록</h2>
	<table class="table">
		<thead>
			<tr>
				<th>영화ID</th>
				<th>제목</th>
				<th>개봉일</th>
				<th>상영시간</th>
				<th>등급</th>
				<th>관객수</th>
			</tr>
		</thead>
		<tbody id="movie_list">
		</tbody>
	</table>
	<div id="movie_page" style="text-align: center;"></div>
	<div class="form-inline">
		<select class="form-control" id="keyword" name=""keyword"">
			<option value="movie_id" selected="selected">영화ID</option>
			<option value="title">제목</option>
		</select>
		<input type="text" class="form-control" id="search" name="search">
		<input type="submit" class="btn btn-default" value="검색" onclick="getMovieList(1);">
	</div>
</div>
<script type="text/javascript">
	var currentPage = 1;
	var startPage = 1;
	var endPage = 1;
	var totalPage;
	   
	function setMovieList(data){
		var result;
		$("#movie_list").html("");
		$(data).each(function(){
			result += "<tr><td>"
			+ this.movie_id
			+ "</td>"
			+ "<td>"
			+ this.title
			+ "</td>"
			+ "<td>"
			+ this.open_date
			+ "</td>"
			+ "<td>"
			+ this.runtime
			+ "</td>"
			+ "<td>"
			+ this.rating
			+ "</td>"
			+ "<td>"
			+ this.moviegoers_cnt
			+ "</td>"
			+ "<td>"
			+ "<input type='button' id='btn_play' name='btn_play' class='btn btn-primary' onclick=playMovie('" + this.movie_id + "'); value='개봉' />"
			+ "</td>"
			+ "<td>"
			+ "<input type='button' id='btn_end' name='btn_end' class='btn btn-primary' onclick=endMovie('" + this.movie_id + "'); value='종료' />"
			+ "</td>"
			+ "<td>"
			+ "<input type='button' id='btn_del' name='btn_del' class='btn btn-danger' onclick=deleteMovie('" + this.movie_id + "'); value='삭제' />"
			+ "</td></tr>";
		});		
		$("#movie_list").html(result);
	}
	function getMovieList(page){
		if(page == null){
			page = currentPage;
		}
		currentPage = page;
		$.ajax({
			type : 'get',
			url : '/admin/movie/' + page,
			headers : {
				"Content-Type" : "application/json",
			},
			data : {"keyword" : $("#keyword").val(), "search" : $("#search").val()},
			dataType : 'json',
			success : function(result){

				endPage = result.paging.endPage; 
	            startPage = result.paging.startPage;
	            if(totalPage<result.paging.totalPage){
	            	getMovieList(result.paging.totalPage);
	            }
	            totalPage = result.paging.totalPage;
	            
	            setMovieList(result.movie_list);
	            setPagePrint(result.paging);
			}
		});
	}
	getMovieList(currentPage);
	
	function setPagePrint(pm){
		var str = "<ul class='pagination'>";
		if(currentPage > pm.endPage && currentPage > 1){
			getMovieList(currentPage - 1);
		}
		if(pm.prev){
			str += "<li> <a onclick='getMovieList("+(pm.startPage-1)+")'>&lt;</a> </li>"
		}
		for(var i = pm.startPage; i <= pm.endPage ; i++){
			if(i == pm.criteria.page){
				str += "<li class='active'><a href='#'>" + i + "</a></li>"
			}else{
				str += "<li><a onclick='getMovieList("+i+")' style='cursor:pointer'>" + i + "</a></li>"
			}
		}
		if(pm.next){
			str += "<li> <a onclick='getMovieList("+(pm.endPage+1)+")'>&gt;</a> </li>"
		}
		str += "</ul>"
		document.getElementById("movie_page").innerHTML = str;
	}
	function deleteMovie(movie_id){
		$.ajax({
			type : 'delete',
			url : '/admin/movie/' + movie_id,
			headers : { 
				"Content-Type" : "application/json"
				},
			dataType : 'text',
			success : function(result){
				if(result == "SUCCESS"){
					getMovieList(currentPage);
				}
			}
		});
	}
	function playMovie(movie_id){
		$.ajax({
			type : 'put',
			url : '/admin/movie/' + movie_id + '/play',
			headers : { 
				"Content-Type" : "application/json"
				},
			dataType : 'text',
			success : function(result){
				alert("변경되었습니다.");
			}
		});
	}
	function endMovie(movie_id){
		$.ajax({
			type : 'put',
			url : '/admin/movie/' + movie_id+ '/end',
			headers : { 
				"Content-Type" : "application/json"
				},
			dataType : 'text',
			success : function(result){
				alert("변경되었습니다.");
			}
		});
	}
</script>
<%@include file="../include/footer.jsp"%>