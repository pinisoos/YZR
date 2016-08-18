<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="./include/header.jsp"%>
<script type="text/javascript" src="/resources/se2/js/HuskyEZCreator.js"charset="utf-8"></script>
<script type="text/javascript" src="/resources/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" type="text/javascript" charset="utf-8"></script>
<div id="wrap_content" style="min-height: 100%; position: relative;">
	<div id="content" style="padding-bottom: 150px;">
		<h2 style="margin-left: 30px;">리뷰 작성</h2>
		<div style="margin-left: 110px; margin-top: 40px;">
			<div>
				<img src="/resources/images/poster/${movievo.poster}"
					alt="CGV" style="float: left; height: 240px; width: 220px; margin-left: 30px;">
			</div>
			<div>
				<span style="margin-left:20px; margin-top:40px;" > 
					<font size="6px" style="margin-top: 30px;"> ${movievo.title }</font>
				</span> 
			</div>
			<hr>
			<b>	
				<span style="margin-left: 20px; margin-bottom: 10px;"> 감독 :
					<c:forEach items="${directorlist}" var="directorlist">
							 ${directorlist.director_name }	
					</c:forEach> 
				</span>
				<br> 
				<span style="margin-left: 20px; margin-bottom: 10px;">배우 : 
					<c:forEach items="${actorlist}" var="actorlist">
						${actorlist.actor_name }   
					</c:forEach>
				</span>
				<br>
				<span style="margin-left: 20px; margin-bottom: 10px;">장르 : 
					<c:forEach items="${genrelist}" var="genrelist">
							${genrelist.movie_genre }
					</c:forEach>
				</span>
				<br>
				<span style="margin-left: 20px; margin-bottom: 10px;"> 개봉일 : ${movievo.open_date } </span>
				<span style="margin-left: 20px; margin-bottom: 10px;"> 공식 사이트 : ${movievo.site } </span>
			</b>
		</div>	
		<hr>
		<div style="margin-left: 140px; margin-top: 80px;">
			<input type="text" id="review_title" name="review_title" class="form-control" style="width: 864px;"placeholder="제목" />
			<textarea name="review_content" id="review_content" rows="20" cols="120" style="margin-top: 5px;">
			</textarea>
			<div>
				<div>
					<button type="button" class="btn btn-danger"
						onclick="insertReview()" style="float:right; margin-top:5px; margin-right: 135px; width: 70px; height: 50px;">등록</button>
				</div>
			</div>
		</div>
	</div>
</div>	

<script type="text/javascript">
	var movie_id = '${movie_id}';
	var temp = "";

	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : "review_content",
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
	function pasteHTML(review_file) {

		//var sHTML = opener.nhn.husky.PopUpManager.setCallback(window, 'PASTE_HTML', [oFileInfo]);
		//var sHTML = '<img src="/resources/images/review_photo/'+filename+'" style="width:200px; height:200px;">';
		//oEditors.getById["review_content"].exec("PASTE_HTML", [ sHTML ]);
		
		//$("#review_content").text('<img src="/resources/images/review_photo/'+filename+'" style="width:200px; height:200px;">');
		 //oEditors.getById["ir1"].exec("LOAD_CONTENTS_FIELD");
		temp = review_file;
		
    }

	


	function insertReview() {
		oEditors.getById["review_content"].exec("UPDATE_CONTENTS_FIELD", []);
		var review_title = $("#review_title").val();
		var member_id =  "${member_id}";
		var review_content = $("#review_content").val();
		$.ajax({
			type : 'post',
			url : '/review/new/' + movie_id,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			data : JSON.stringify({
				"review_title" : review_title,
				"member_id" : member_id,
				"review_content" : review_content,
				"review_file":temp
			}),
			dataType : 'text',
			success : function(result) {
				if (result == "SUCCESS") {
					location.href = "/movie/" + movie_id
				}
			}
		});
	}
</script>


<%@include file="./include/footer.jsp"%>