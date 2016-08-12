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
		<h3>기프트콘 관리 </h3>
	</div>
	<table class="table" style="margin-top: 30px;">
			<tr>
				<th style="text-align: center;width: 20%" >번호</th>
				<th style="text-align: center;" colspan="3">상품설명</th>
			</tr>
			<tbody id="store_table">
			</tbody>
		</table>
	<div id="store_page" style="text-align: center;"></div>
 	<div class="modal fade" id="myModal" role="dialog">
 		<div class="modal-dialog modal-sm"style="margin-top: 300px;">
 			<div class="modal-content">
 				<div class="modal-header">
					<h4 class="modal-title">상품취소</h4>
				 </div>
 				<div class="modal-body">
 					<p>구매하신 상품을 취소하시겠습니까?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="delete_product()">취소확정</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
var num;
function setNo(no){
	num = no;
}

function setStoreList(data){
	var result = '<tr>'; // 콜라(L), 칠리치즈나초콤보, 더블치즈팝콘, 크리미갈릭핫도그
		$(data).each(function(){
			result += '<td style="text-align: center; vertical-align: middle;">' 
				+this.no
				+'</td>'
				+'<td  style="width:255px; height:100px;">';		
				if(this.product_name== '콜라(L)'){
					result+='<img style="height: 100px; width: 180px; vertical-align: baseline;" src="/resources/images/product/coke.png">';
				}else if(this.product_name== '칠리치즈나초콤보'){
					result+='<img style="height: 100px; width: 180px; vertical-align: baseline;" src="/resources/images/product/combo_set.png">';
				}else if(this.product_name== '더블치즈팝콘'){
					result+='<img style="height: 100px; width: 180px; vertical-align: baseline;" src="/resources/images/product/popcorn.png">';
				}else if(this.product_name== '크리미갈릭핫도그'){
					result+='<img style="height: 100px; width: 180px; vertical-align: baseline;" src="/resources/images/product/hotdog.png">';
				}
				result+='</td>'
				+'<td style="width:200px; vertical-align: middle; margin-left:30px;">'
				+'상품명 : '
				+this.product_name
				+'<br/> 상품가격 : '
				+this.product_price
				+'</td>'
				+'<td  style="text-align: center; width:158px; vertical-align: middle;">'
				+'<input type="button" value="구매취소" onclick="setNo('+ this.no + ')" class="btn btn-danger" data-toggle="modal" data-target="#myModal">'
				+'</td>'
				+'</tr>'
		});
	document.getElementById("store_table").innerHTML = result;
}


function getStoreList(page){

	$.ajax({
		type : 'get',
		url : '/user/store/' + page,
		headers : {
			"Content-Type" : "application/json",
			//"X-HTTP-Method-Override" : "GET",  ----  POST 이거나 GET인경우는 생략가능
		},
		dataType : 'json',
		data : '',
		success : function(result){
			setStoreList(result.l);
			setPagePrint(result.p);
		}
	});
}

getStoreList(1);

function setPagePrint(pm){
	var str = "<ul class='pagination'>";
	
	if(pm.prev){
		str += "<li> &lt; </li>"
	}
	
	for(var i = pm.startPage; i <= pm.endPage ; i++){
		if(i == pm.criteria.page){
			str += "<li class='active'><a href='#'>" + i + "</a></li>"
		}else{
			str += "<li><a href='javascript:getStoreList("+i+")'>" + i + "</a></li>"
		}
	}
	
	if(pm.next){
		str += "<li> &gt; </li>"
	}
	
	str += "</ul>"
	document.getElementById("store_page").innerHTML = str;
}

function delete_product(){
	$.ajax({
		type : 'delete',
		url : '/user/store/cancel/' + num ,
		headers : { 
			"Content-Type" : "application/json"
			},
		data : '',
		dataType : 'text',
		success : function(result){
			getStoreList(1);
		}
	});
}
</script>
<%@include file="../include/footer.jsp"%>