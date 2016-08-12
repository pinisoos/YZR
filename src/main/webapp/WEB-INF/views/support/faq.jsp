<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<style>
td{
	text-align: center;
	background-color: ivory;
}
th{
	text-align: center;
	font-weight: bold;
	color: white;
}
</style>
<div class="content" style="margin-left: 250px;">
	<div>
		<label style="font-size: 15pt;">자주찾는 질문</label><br>
		회원님들께서 가장 자주하시는 질문을 모았습니다.<br>
		궁금하신 내용에 대해 검색해보세요.<br><br><br>
	</div>
	<div class="form-inline">
		<input type="text" class="form-control" id="search" name="search" size="40" value="${ search }" placeholder="검색어를 입력해주세요.">
		<input type="button" class="btn btn-danger" value="검색" onclick="getFaqList(1);">
	</div><br>
	<div>
		<ul id="faqCategory" class="nav nav-tabs faqCategory" style="font-size: 15px; font-weight: bold; height: 43px; width: 650px;">
			<li class="active"><a id="all" href="javascript:getFaqList(1)" style="background-color: red; color: white">전체</a></li>
			<li><a id="예매/관람권" href="javascript:getFaqList(1)" style="cursor: pointer;">예매/관람권</a></li>
			<li><a id="할인혜택" href="javascript:getFaqList(1)" style="cursor: pointer;">할인혜택</a></li>
			<li><a id="영화관이용" href="javascript:getFaqList(1)" style="cursor: pointer;">영화관이용</a></li>
			<li><a id="특별관" href="javascript:getFaqList(1)" style="cursor: pointer;">특별관</a></li>
			<li><a id="기프트샵" href="javascript:getFaqList(1)" style="cursor: pointer;">기프트샵</a></li>
			<li><a id="홈페이지" href="javascript:getFaqList(1)" style="cursor: pointer;">홈페이지</a></li>
		</ul>
	</div><br>
	<div id="count"></div><br>
	<table class="table">
		<thead>
			<tr style="background-color: red;">
				<th style="width: 10%;">번호</th>
				<th style="width: 15%;">구분</th>
				<th style="text-align: left;">제목</th>
				<th style="width: 10%;">조회수</th>
			</tr>
		</thead>
		<tbody id="faq_list">
		</tbody>
	</table>
	<div id="faq_page" style="text-align: center;"></div>
</div>
<script>
	var currentPage = 1;
	var startPage = 1;
	var endPage = 1;
	var totalPage;
	var category="all";

	$('#faqCategory a').click(function (e) {
	    $(this).tab('show');
	    $('#faqCategory a').css("background-color", "white");
	    $('#faqCategory a').css("color", "black");
	    $(this).css("background-color", "red");
	    $(this).css("color", "white");
	    category=this.id;
	});
	function setFaqList(data, count){
		var result;
		$("#faq_list").html("");
		$(data).each(function(){
			result += "<tr>"
			+ "<td>"
			+ this.no
			+ "</td>"
			+ "<td>["
			+ this.category
			+ "]</td>"
			+ "<td style='text-align: left;'>"
			+ "<a href='/support/faq/read/" + this.no + "'>"
			+ this.title
			+ "</a>"
			+ "</td>"
			+ "<td>"
			+ this.view_cnt
			+ "</td>"
			+ "</tr>";
		});		
		$("#count").html("총 <font style='font-weight: bold;'>" +  count + "</font>건이 검색 되었습니다.");
		$("#faq_list").html(result);
	}
	function getFaqList(page){
		if(page == null){
			page = currentPage;
		}
		currentPage = page;
		$.ajax({
			type : 'get',
			url : '/support/faq/' + page,
			headers : {
				"Content-Type" : "application/json",
			},
			data : {"search" : $("#search").val(), "category" : category},
			dataType : 'json',
			success : function(result){

				endPage = result.paging.endPage; 
	            startPage = result.paging.startPage;
	            if(totalPage<result.paging.totalPage){
	            	getFaqList(result.paging.totalPage, "");
	            }
	            totalPage = result.paging.totalPage;
	            
	            setFaqList(result.faq_list, result.count);
	            setPagePrint(result.paging);
			}
		});
	}
	getFaqList(currentPage);
	
	function setPagePrint(pm){
		var str = "<ul class='pagination'>";
		if(currentPage > pm.endPage && currentPage > 1){
			getFaqList(currentPage - 1);
		}
		if(pm.prev){
			str += "<li> <a onclick='getFaqList("+(pm.startPage-1)+")'>&lt;</a> </li>"
		}
		for(var i = pm.startPage; i <= pm.endPage ; i++){
			if(i == pm.criteria.page){
				str += "<li class='active'><a href='#'>" + i + "</a></li>"
			}else{
				str += "<li><a onclick='getFaqList("+i+")' style='cursor:pointer'>" + i + "</a></li>"
			}
		}
		if(pm.next){
			str += "<li> <a onclick='getFaqList("+(pm.endPage+1)+")'>&gt;</a> </li>"
		}
		str += "</ul>"
		document.getElementById("faq_page").innerHTML = str;
	}
</script>
<%@include file="../include/footer.jsp"%>