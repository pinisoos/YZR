<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="./include/header.jsp"%>
<div id="wrap_content" style="min-height: 100%; position: relative;">
   <div id="content" style="padding-bottom: 50px; float: left;">
      <div id="notice" style="border-bottom:2px solid #000; margin-top:10px;" class="carousel up" data-ride="carousel" data-interval="3000">
         <c:forEach begin="0" end="4" varStatus="n">
            <ol class="carousel-indicators">
                 <li data-target="#myCarousel" data-slide-to="${n.index}"></li>
            </ol>
         </c:forEach>
         <div class="carousel-inner" role="listbox">
            <c:forEach items="${notice}" var="notice" varStatus="noticeN">
               <c:choose>
                  <c:when test="${noticeN.first}">
                     <div class="item active">
                        <i class="fa fa-bullhorn" aria-hidden="true" style="font-weight:bold; font-size:15px;"> &nbsp공지사항 </i>
                        &nbsp [${notice.getNotice_category()}]
                        &nbsp <a href="/support/notice/read/${ notice.no }"> ${notice.getNotice_title()} </a>
                        <span style="float:right;"> ${notice.getNotice_date()} </span>
                     </div>
                  </c:when>
                  <c:otherwise>
                     <div class="item">
                        <i class="fa fa-bullhorn" aria-hidden="true" style="font-weight:bold; font-size:15px;"> &nbsp공지사항 </i>
                        &nbsp [${notice.getNotice_category()}]
                        &nbsp <a href="/support/notice/read/${ notice.no }"> ${notice.getNotice_title()} </a>
                        <span style="float:right;"> ${notice.getNotice_date()} </span>
                     </div>
                  </c:otherwise>
               </c:choose>
            </c:forEach>
         </div>
      </div>      
      
    <!-- 검색창 -->
	<div style="clear: both; width: 100%; height: 34px;">
		<form class="form" action="/search" method="post">
			<div class="form-group" style="clear: both; width: 100%; height: 34px;">
				<div class="col-sm-10" style="padding: 0; height: 34px;">
					<input id="serach" class="form-control" name="search" type="text" placeholder="검색어를 입력하세요.">
				</div>
				<div class="col-sm-2">
					<input type="submit" class="btn btn-danger" value="검색" style="width: 100%;">
				</div>
			</div>	
		</form>
	</div>
      
      <span style="font-weight:bold; font-size:36px; float:left; margin-top:10px;">추천영화</span>
      <div style="border:1px solid #000; margin-top:60px;"></div>
      
      <c:choose>
         <c:when test="${ member_id != null }">
            <c:set value="${ rec_movie }" var="chart"></c:set>
         </c:when>
         <c:otherwise>
            <c:set value="${ rec_basic }" var="chart"></c:set>
         </c:otherwise>
      </c:choose>
      
      <c:if test="${chart == null }">
         <span style="font-weight:bold; font-size:15px;"> ${basic_title} </span>
         <div id="rec_movie" style="border:0px solid #000; width:250px; height:380px; text-align:left; margin-top:5px;">
            <a href="/movie/${ chart.movie_id }"><img src="/resources/images/poster/${chart.poster}" style="width:249px; height:300px;"/></a>
            <span style="font-weight:bold; font-size:15px;"> ${basic_title}  </span><br/>
            관람등급 : <br/>
            예매율 : <br/>
            개봉날짜 : <br/>
            <input type="button" id="res_btn" name="res_btn" class="btn btn-danger btn-sm" onclick="" value="예매" style="width:250px; display:inline-block; margin-top:7px;"/>
         </div>
      </c:if>
      
      <c:forEach items="${chart}" var="chart" begin="0" end="2" varStatus="i">
         <div style="margin-top:20px; float:left; margin-left:92px; margin-right:92spx; ">
            <div id="m1" style="margin-top:20px;">
               <div id="rec_movie_title" style="border:2px solid #000; width:250px; height:28px; padding-top:2px; background-color:#ff3636; text-align:center;">
                  <c:choose>
                     <c:when test="${member_id !=null}">
                        <span style="font-weight:bold; font-size:15px;">
                     </c:when>
                     <c:otherwise>
                        <span style="font-weight:bold; font-size:15px;">
                     </c:otherwise>
                  </c:choose>
                     <c:choose>
                        <c:when test="${ member_id != null }">
                           <c:set value="${ rec_title }" var="chart_title"></c:set>
                        </c:when>
                        <c:otherwise>
                           <c:set value="${ basic_title }" var="chart_title"></c:set>
                        </c:otherwise>
                     </c:choose>
                     ${chart_title[i.index]}
                  </span>
               </div>
               <div id="rec_movie" style="border:0px solid #000; width:250px; height:380px; text-align:left; margin-top:5px;">
                  <a href="/movie/${ chart.movie_id }"><img src="/resources/images/poster/${chart.poster}" style="width:249px; height:300px;"/></a>
                  <span style="font-weight:bold; font-size:15px;"> ${chart.title}  </span><br/>
                  관람등급 : ${chart.rating} <br/>
                  예매율 : ${chart.reservation_rate}%<br/>
                  개봉날짜 : ${chart.open_date} <br/>
                  <input type="button" id="res_btn" name="res_btn" class="btn btn-danger btn-sm" onclick="" value="예매" style="width:250px; display:inline-block; margin-top:7px;"/>
               </div>
            </div>
         </div>
      </c:forEach>
      
      <h2 id="event_line" style="margin-top:550px;">
         <div style="border-top:1px solid #f00; border-bottom:1px solid #f00; width:519px; height:5px; float:left; margin-top:13px;"></div>
            <span style="float:left; color:red;">EVENT</span>
         <div style="border-top:1px solid #f00; border-bottom:1px solid #f00; width:519px; height:5px; float:left; margin-top:13px;"></div>
      </h2>
      
      <div>
         <div id="event_nav" style="margin-top:590px; text-align:center;">
            <ul class="nav nav-tabs" style="display:inline-block;">
               <li><a href="javascript:getEventImg(1)">CGV스페셜</a></li>
               <li><a href="javascript:getEventImg(2)">영화/예매</a></li>
               <li><a href="javascript:getEventImg(3)">제휴/할인</a></li>
               <li><a href="javascript:getEventImg(4)">멤버십</a></li>
            </ul>
         </div>
         <div id="event_img" style="margin-top:10px;">
            <!-- 이벤트 이미지 삽입될 영역 -->
         </div>
      </div>
      
      <div style="clear:both; margin-top:335px; border:1px solid #000;"></div>
      
      <div id="hold_img" style="margin-top:25px;">
         <div style="border:1px solid #000; width:280px; height:300px; float:left; margin-right:6px;">
            <a href=""><img style="width:275px; height:295px;" src="http://img.naver.net/static/www/u/2013/0731/nmms_224940510.gif"></a>
         </div>
         <div style="border:1px solid #000; width:565px; height:300px; float:left; margin-right:6px;">
            <a href=""><img style="width:560px; height:295px;" src="http://icon.daumcdn.net/w/icon/1606/30/105915014.png"></a>
         </div>
         <div style="border:1px solid #000; width:280px; height:300px; float:left;">
            <a href=""><img style="width:275px; height:295px;" src="https://tv.pstatic.net/ugc?t=470x180&q=http://dbscthumb.phinf.naver.net/2315_000_2/20110926125556074_R61MRD5WL.jpg/n1464.jpg?type=m4500_4500_fst"></a>
         </div>
      </div>

         
      <script type="text/javascript">
      
          $(document).ready(function() {
              $('#notice').carousel('cycle');
          });
      
         function setEventImg(list){
            var result = "";
            
            for(var i=0; i<4; i++){
               result += '<div style="border:1px solid #000; width:280px; height:300px; float:left; margin-left:2.5px; margin-right:2.5px;">'
               + '<a href="">'
               + '<img src="' + list[i] + '"/>'
               + '</a>'
               + '</div>';
            }
               document.getElementById("event_img").innerHTML = result;
         }
         
         function getEventImg(category){
            $.ajax({
               type : 'get',
               url : '/index/' + category,
               headers : {
               "Content-Type" : "application/json",
               //"X-HTTP-Method-Override" : "GET",  ----  POST 이거나 GET인경우는 생략가능
               },
               dataType : 'json',
               data : '',
               success : function(list){
                  setEventImg(list);
               }
            });
         }
         
         getEventImg(1);
      </script>
         
      <style>
         .nav-tabs{ border:0px }
         
         .nav-tabs > li > a:hover,
         .nav-tabs > li > a:focus{
         background-color:#fff;
         border:0px
         }
         
         .carousel-inner > .item > a,
         .carousel-inner > .item > a:hover{
         text-decoration: none;
         color: black;
         }
      </style>
   
   </div>   
</div>
<%@include file="./include/footer.jsp"%>