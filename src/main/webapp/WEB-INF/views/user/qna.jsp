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
	<div>
		<h3>나의 1:1 문의</h3>
	</div>
	<div style="margin-top:4px;">
		<b style="float: left;margin-top: 4px;">문의조회</b>
		<div class="col-xs-4" style="float:left">
			<input type="text" class="form-control" id = "search" style="margin-left: 5px;">
		</div>		
		<button class="btn btn-danger" style="height: 30px;" onclick="getQnaList(1)">검색하기</button>
	</div>
	<div>
		<table class="table" style="margin-top: 30px;">
			<tr style="background-color: red">
				<th style="text-align: center;">번호</th>
				<th style="text-align: center;">문의유형</th>
				<th width="60%" style="text-align: center;">문의 내용</th>
			</tr>
			<tbody id="qna_table">
			</tbody>
		</table>
	</div>	
	<div id="qna_page" style="text-align: center;"></div>
	<hr/>
	<div>
		
		<table class="table table-bordered">
			<tr height="100px;">
				<td width="50%">
					<div style="display: inline-block; height: 48px; width: 58px; margin-top: 15px; float: left; margin-left: 30px;">
						<i class="fa fa-question-circle-o fa-4x" aria-hidden="true"></i>
					</div>
					<div style="display: inline-block; height: 50px;float: left; margin-top:15px; margin-left: 10px;">
						<font size="4px;">
							<b>자주하시는 질문</b>
							<button class="btn btn-danger btn-xs" style="margin-bottom: 5px;" onclick="faq()">바로가기</button><br/>
						</font>
						<font size="3px;">
							고객님들께서 주로 하시는 질문에 대한 <br/> 답변을 한곳에 모아두었습니다.
						</font> 
					</div>
				</td>
				<td width="50%">
					<div style="display: inline-block; height: 48px; width: 58px; margin-top: 15px; float: left; margin-left: 30px;">
						<i class="fa fa-commenting-o fa-4x"></i>
					</div>
					<div style="display: inline-block; height: 50px;float: left; margin-top:15px; margin-left: 10px;">
						<font size="4px;">
							<b>고객의 말씀 </b>
							<button class="btn btn-danger btn-xs" style="margin-bottom: 5px;">바로가기</button><br/>
						</font>
						<font size="3px;">
							불만사항이나 문의사항을 남겨주시면 <br/>친절히답변드리겠습니다. 
						</font> 
					</div>
				</td>
			</tr>
		</table>
	</div>		
</div>
<script>
function faq(){
	location.href="/support/faq"
}
function qna_read(no){
	location.href = "/user/qna/read/" + no;
}
getQnaList(1);
function setQnaList(data){
	var result = "<tr>";
		$(data).each(function(){
			result += "<td style='text-align: center;'>" 
			+this.no
			+"</td>"
			+"<td  style='text-align: center;'>"
			+this.qnatype
			+"</td>"
			+"<td>"
			+"<a href='javascript:qna_read("+this.no+")'style='text-decoration:none;'>"+this.title+"</a>"
			+"</td>"
			+"</tr>"
		});
	document.getElementById("qna_table").innerHTML = result;
}

function getQnaList(page){

	$.ajax({
		type : 'get',
		url : '/user/qna/'+page,
		headers : {
			"Content-Type" : "application/json",
			//"X-HTTP-Method-Override" : "GET",  ----  POST 이거나 GET인경우는 생략가능
		},
		dataType : 'json',
		data : {"search" : $("#search").val()},
		success : function(result){
			setQnaList(result.l);
			setQnaPrint(result.p);
		}
	});
}

getQnaList(1);

function setQnaPrint(pm){
	var str = "<ul class='pagination'>";
	
	if(pm.prev){
		str += "<li> &lt; </li>"
	}
	
	for(var i = pm.startPage; i <= pm.endPage ; i++){
		if(i == pm.criteria.page){
			str += "<li class='active'><a href='#'>" + i + "</a></li>"
		}else{
			str += "<li><a href='javascript:getQnaList("+i+")'>" + i + "</a></li>"
		}
	}
	
	if(pm.next){
		str += "<li> &gt; </li>"
	}
	
	str += "</ul>"
	document.getElementById("qna_page").innerHTML = str;
}



</script>


<%@include file="../include/footer.jsp"%>