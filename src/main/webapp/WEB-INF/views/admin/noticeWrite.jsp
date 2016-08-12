<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<script type="text/javascript" src="/resources/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
<c:if test="${ member_id == null }">
	<script>
		<%response.sendRedirect("/login");%>
	</script>
</c:if>
<style>
	th{
		width: 100px;
	}
</style>
<div class="content" style="margin-left: 250px;">
	<h2>공지 등록</h2><br><br>
	<form id="noticeWritefrm" name="noticeWritefrm" role="form" action="/admin/notice/new" method="post" enctype="multipart/form-data">
		<table class="table">
			<tr>
				<th>카테고리</th>
				<td colspan="3">
					<input type="text" id="notice_category" name="notice_category" size="50">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">
					<input type="text" id="notice_title" name="notice_title" size="50">
				</td>
			</tr>
			<tr>
				<th>파일</th>
				<td colspan="3">
					<input type="file" id="file" name="file" size="50">
				</td>
			</tr>
			<tr>
				<th style="vertical-align: top; padding-top: 20px;">내용</th>
				<td colspan="3">
					<textarea id="notice_content" name="notice_content" rows="5" cols="85"></textarea>
				</td>
			</tr>
		</table><br><br>
		<div style="margin-left: 300px;">
			<input type="button" id="post" class="btn btn-danger" value="등록하기">
		</div>
	</form>
</div>
<script>
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : "notice_content",
		sSkinURI : "/resources/se2/SmartEditor2Skin.html",
		fCreator : "createSEditor2",
	    htParams : { 
	        // 아래 부분을 추가합니다. 
	        // 설정하지 않았을 때읭 기본 값은 Editor 모드입니다. 
	        // - Editor 모드 : WYSIWYG 
	        // - HTML 모드 : HTMLSrc 
	        // - TEXT 모드 : TEXT 
	        SE_EditingAreaManager : { 
	                 sDefaultEditingMode : "WYSIWYG" // HTML 모드로 뜨게 됩니다. 
	        } 
	}, 
	});
	$("#post").click(function(){
		
        oEditors.getById["notice_content"].exec("UPDATE_CONTENTS_FIELD", []);

        $("#noticeWritefrm").submit();
    })
</script>
<%@include file="../include/footer.jsp"%>