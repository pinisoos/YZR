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
	<h2>대관 문의</h2>
	<table class="table table-striped" cellpadding="10">
		<thead>
			<tr>
				<th>NO</th>
				<th>회원ID</th>
				<th>대관 극장</th>
				<th>대관일</th>
			</tr>
		</thead>
		<tbody id="rent_list">
		</tbody>
	</table>
	<div id="rent_page" style="text-align: center;"></div>
</div>
<script type="text/javascript">
	var currentPage = 1;
	var startPage = 1;
	var endPage = 1;
	var totalPage;
	
	function setRentList(data){
		var result;
		$("#rent_list").html("");
		$(data).each(function(){
			result += "<tr><td>"
			+ this.no
			+ "</td>"
			+ "<td>"
			+ this.member_id
			+ "</td>"
			+ "<td>"
			+ this.rent_place
			+ "</td>"
			+ "<td>"
			+ this.rent_date
			+ "</td>"
			+ "<td>"
			+ "<input type='button' id='btn_rent' name='btn_rent' class='btn btn-primary' onclick='rentComplete(" + this.no + ")' value='대관' />"
			+ "</td></tr>"
			+ "<tr>"
			+ "<th>"
			+ "내용"
			+ "</th>"
			+ "<td colspan='3' style='text-align: left;'>"
			+ this.rent_content
			+ "</td>"
			+ "<td>"
			+ "<input type='button' id='btn_del' name='btn_del' class='btn btn-danger' onclick='deleteRent(" + this.no + ")' value='삭제' />"
			+ "</td>"
			+ "</tr>"
		});		
		$("#rent_list").html(result);
	}
	
	function getRentList(page){
		if(page == null){
			page = currentPage;
		}
		$.ajax({
			type : 'get',
			url : '/admin/rent/' + page,
			headers : {
				"Content-Type" : "application/json",
			},
			dataType : 'json',
			success : function(result){
				
				endPage = result.paging.endPage; 
	            startPage = result.paging.startPage;
	            if(totalPage<result.paging.totalPage){
	            	getRentList(result.paging.totalPage);
	            }
	            totalPage = result.paging.totalPage;
	            
				setRentList(result.rent_list);
				setPagePrint(result.paging)
			}
		});
		currentPage = page;
	}
	
	
	getRentList(currentPage);
	
	function setPagePrint(pm){
		var str = "<ul class='pagination'>";
		if(currentPage > pm.endPage && currentPage > 1){
			getReplyList(currentPage - 1);
		}
		
		if(pm.prev){
			str += "<li> <a onclick='getRentList("+(pm.startPage-1)+")'>&lt;</a> </li>"
		}
		
		for(var i = pm.startPage; i <= pm.endPage ; i++){
			if(i == pm.criteria.page){
				str += "<li class='active'><a href='#'>" + i + "</a></li>"
			}else{
				str += "<li><a onclick='getRentList("+i+")' style='cursor:pointer'>" + i + "</a></li>"
			}
		}
		
		if(pm.next){
			str += "<li> <a onclick='getRentList("+(pm.endPage+1)+")'>&gt;</a> </li>"
		}
		
		str += "</ul>"
		document.getElementById("rent_page").innerHTML = str;
	}
	function deleteRent(no){
		$.ajax({
			type : 'delete',
			url : '/admin/rent/' + no,
			headers : { 
				"Content-Type" : "application/json"
				},
			dataType : 'text',
			success : function(result){
				if(result == "SUCCESS"){
					getRentList(currentPage);
				}
			}
		});
	}
	function rentComplete(no){
		$.ajax({
			type : 'post',
			url : '/admin/rent/' + no,
			headers : { 
				"Content-Type" : "application/json"
				},
			dataType : 'text',
			success : function(result){
				if(result == "SUCCESS"){
					getRentList(currentPage);
				}
			}
		});
	}
</script>
<%@include file="../include/footer.jsp"%>