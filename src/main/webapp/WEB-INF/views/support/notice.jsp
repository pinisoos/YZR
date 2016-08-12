<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<style>
th, td{
	text-align: center;
}
th{
	background-color: red;
	color: white;
}
td{
	background-color: ivory;
}
</style>
<div class="content" style="margin-left: 250px;">
	<h2>공지/뉴스</h2><br>
	<table class="table">
		<thead>
			<tr>
				<th style="width: 150px;">카테고리</th>
				<th>제목</th>
				<th style="width: 120px;">작성일</th>
				<th style="width: 80px;">조회수</th>
			</tr>
		</thead>
		<tbody id="notice_list">
		</tbody>
	</table>
	<div id="notice_page" style="text-align: center;"></div>
	<div class="form-inline">
		<select class="form-control" id="keyword" name=""keyword"">
			<option value="notice_category" selected="selected">카테고리</option>
			<option value="notice_title">제목</option>
		</select> <input type="text" class="form-control" id="search" name="search">
		<input type="submit" class="btn btn-default" value="검색" onclick="getNoticeList(1);">
	</div>
</div>
<script type="text/javascript">
	var currentPage = 1;
	var startPage = 1;
	var endPage = 1;
	var totalPage;
	   
	function setNoticeList(data){
		var result;
		$("#notice_list").html("");
		$(data).each(function(){
			result += "<tr>"
			+ "<td>["
			+ this.notice_category
			+ "]</td>"
			+ "<td style='text-align: left;'>"
			+ "<a href='/support/notice/read/" + this.no + "'>"
			+ this.notice_title
			+ "</a>"
			+ "</td>"
			+ "<td>"
			+ this.notice_date
			+ "</td>"
			+ "<td>"
			+ this.notice_view_cnt
			+ "</td>"
			+ "</tr>";
		});		
		$("#notice_list").html(result);
	}
	function getNoticeList(page){
		if(page == null){
			page = currentPage;
		}
		currentPage = page;
		$.ajax({
			type : 'get',
			url : '/admin/notice/' + page,
			headers : {
				"Content-Type" : "application/json"
			},
			data : {"keyword" : $("#keyword").val(), "search" : $("#search").val()},
			dataType : 'json',
			success : function(result){

				endPage = result.paging.endPage; 
	            startPage = result.paging.startPage;
	            if(totalPage<result.paging.totalPage){
	            	getNoticeList(result.paging.totalPage);
	            }
	            totalPage = result.paging.totalPage;
	            
	            setNoticeList(result.notice_list);
	            setPagePrint(result.paging);
			}
		});
	}
	getNoticeList(currentPage);
	
	function setPagePrint(pm){
		var str = "<ul class='pagination'>";
		if(currentPage > pm.endPage && currentPage > 1){
			getNoticeList(currentPage - 1);
		}
		if(pm.prev){
			str += "<li> <a onclick='getNoticeList("+(pm.startPage-1)+")'>&lt;</a> </li>"
		}
		for(var i = pm.startPage; i <= pm.endPage ; i++){
			if(i == pm.criteria.page){
				str += "<li class='active'><a href='#'>" + i + "</a></li>"
			}else{
				str += "<li><a onclick='getNoticeList("+i+")' style='cursor:pointer'>" + i + "</a></li>"
			}
		}
		if(pm.next){
			str += "<li> <a onclick='getNoticeList("+(pm.endPage+1)+")'>&gt;</a> </li>"
		}
		str += "</ul>"
		document.getElementById("notice_page").innerHTML = str;
	}
	function deleteNotice(no){
		$.ajax({
			type : 'delete',
			url : '/admin/notice/' + no,
			headers : { 
				"Content-Type" : "application/json"
				},
			dataType : 'text',
			success : function(result){
				if(result == "SUCCESS"){
					getNoticeList(currentPage);
				}
			}
		});
	}
</script>
<%@include file="../include/footer.jsp"%>