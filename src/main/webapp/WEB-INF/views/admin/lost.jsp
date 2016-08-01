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
	<h2>분실물 문의</h2>
	<table class="table table-striped" cellpadding="10">
		<thead>
			<tr>
				<th>NO</th>
				<th>회원ID</th>
				<th>장소</th>
				<th>날짜</th>
				<th>유형</th>
			</tr>
		</thead>
		<tbody id="lost_list">
		</tbody>
	</table>
	<div id="lost_page" style="text-align: center;"></div>
	<div class="form-inline">
		<select class="form-control" id="keyword" name=""keyword"">
			<option value="member_id" selected="selected">회원ID</option>
		</select> <input type="text" class="form-control" id="search" name="search">
		<input type="submit" class="btn btn-default" value="검색" onclick="getLostList(1);">
	</div>
</div>
<script type="text/javascript">
	var currentPage = 1;
	var startPage = 1;
	var endPage = 1;
	var totalPage;
	
	function setLostList(data){
		var result;
		$("#lost_list").html("");
		$(data).each(function(){
			result += "<tr><td>"
			+ this.no
			+ "</td>"
			+ "<td>"
			+ this.member_id
			+ "</td>"
			+ "<td>"
			+ this.lost_place
			+ "</td>"
			+ "<td>"
			+ this.lost_date
			+ "</td>"
			+ "<td>"
			+ this.lost_type
			+ "</td>"
			+ "<td>"
			+ "<input type='button' id='btn_lost' name='btn_lost' class='btn btn-primary' onclick='lostComplete(" + this.no + ")' value='완료' />"
			+ "</td></tr>"
			+ "<tr>"
			+ "<th>"
			+ "내용"
			+ "</th>"
			+ "<td colspan='4' style='text-align: left;'>"
			+ this.lost_content
			+ "</td>"
			+ "<td>"
			+ "<input type='button' id='btn_del' name='btn_del' class='btn btn-danger' onclick='deleteLost(" + this.no + ")' value='삭제' />"
			+ "</td>"
			+ "</tr>"
		});		
		$("#lost_list").html(result);
	}
	
	function getLostList(page){
		if(page == null){
			page = currentPage;
		}
		$.ajax({
			type : 'get',
			url : '/admin/lost/' + page,
			headers : {
				"Content-Type" : "application/json",
			},
			data : {"keyword" : $("#keyword").val(), "search" : $("#search").val()},
			dataType : 'json',
			success : function(result){
				
				endPage = result.paging.endPage; 
	            startPage = result.paging.startPage;
	            if(totalPage<result.paging.totalPage){
	            	getLostList(result.paging.totalPage);
	            }
	            totalPage = result.paging.totalPage;
	            
				setLostList(result.lost_list);
				setPagePrint(result.paging)
			}
		});
		currentPage = page;
	}
	
	
	getLostList(currentPage);
	
	function setPagePrint(pm){
		var str = "<ul class='pagination'>";
		if(currentPage > pm.endPage && currentPage > 1){
			getReplyList(currentPage - 1);
		}
		
		if(pm.prev){
			str += "<li> <a onclick='getLostList("+(pm.startPage-1)+")'>&lt;</a> </li>"
		}
		
		for(var i = pm.startPage; i <= pm.endPage ; i++){
			if(i == pm.criteria.page){
				str += "<li class='active'><a href='#'>" + i + "</a></li>"
			}else{
				str += "<li><a onclick='getLostList("+i+")' style='cursor:pointer'>" + i + "</a></li>"
			}
		}
		
		if(pm.next){
			str += "<li> <a onclick='getLostList("+(pm.endPage+1)+")'>&gt;</a> </li>"
		}
		
		str += "</ul>"
		document.getElementById("lost_page").innerHTML = str;
	}
	function deleteLost(no){
		$.ajax({
			type : 'delete',
			url : '/admin/lost/' + no,
			headers : { 
				"Content-Type" : "application/json"
				},
			dataType : 'text',
			success : function(result){
				if(result == "SUCCESS"){
					getLostList(currentPage);
				}
			}
		});
	}
	function lostComplete(no){
		$.ajax({
			type : 'post',
			url : '/admin/lost/' + no,
			headers : { 
				"Content-Type" : "application/json"
				},
			dataType : 'text',
			success : function(result){
				if(result == "SUCCESS"){
					getLostList(currentPage);
				}
			}
		});
	}
</script>
<%@include file="../include/footer.jsp"%>