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
	<h2>영화관 목록</h2><br>
	<table class="table">
		<thead>
			<tr>
				<th>영화관ID</th>
				<th>영화관 이름</th>
			</tr>
		</thead>
		<tbody id="theater_list">
		</tbody>
	</table>
	<div id="timetable_page" style="text-align: center;"></div>
</div>
<script>
	var currentPage = 1;
	var startPage = 1;
	var endPage = 1;
	var totalPage;
	   
	function setTheaterList(data){
		var result;
		$("#theater_list").html("");
		$(data).each(function(){
			result += "<tr>"
			+ "<td>"
			+ this.theater_id
			+ "</td>"
			+ "<td>"
			+ "<a href='/admin/timetable/read/" + this.theater_id + "'>"
			+ this.theater_name
			+ "</a>"
			+ "</td>"
			+ "</tr>";
		});		
		$("#theater_list").html(result);
	}
	function getTheaterList(page){
		if(page == null){
			page = currentPage;
		}
		currentPage = page;
		$.ajax({
			type : 'get',
			url : '/admin/timetable/' + page,
			headers : {
				"Content-Type" : "application/json",
			},
			data : '',
			dataType : 'json',
			success : function(result){
	
				endPage = result.paging.endPage; 
	            startPage = result.paging.startPage;
	            if(totalPage<result.paging.totalPage){
	            	getTheaterList(result.paging.totalPage);
	            }
	            totalPage = result.paging.totalPage;
	            
	            setTheaterList(result.theater_list);
	            setPagePrint(result.paging);
			}
		});
	}
	getTheaterList(currentPage);
	
	function setPagePrint(pm){
		var str = "<ul class='pagination'>";
		if(currentPage > pm.endPage && currentPage > 1){
			getTheaterList(currentPage - 1);
		}
		if(pm.prev){
			str += "<li> <a onclick='getTheaterList("+(pm.startPage-1)+")'>&lt;</a> </li>"
		}
		for(var i = pm.startPage; i <= pm.endPage ; i++){
			if(i == pm.criteria.page){
				str += "<li class='active'><a href='#'>" + i + "</a></li>"
			}else{
				str += "<li><a onclick='getTheaterList("+i+")' style='cursor:pointer'>" + i + "</a></li>"
			}
		}
		if(pm.next){
			str += "<li> <a onclick='getTheaterList("+(pm.endPage+1)+")'>&gt;</a> </li>"
		}
		str += "</ul>"
		document.getElementById("timetable_page").innerHTML = str;
	}
</script>
<%@include file="../include/footer.jsp"%>