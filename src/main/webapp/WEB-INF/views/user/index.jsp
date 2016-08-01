<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<div class="content" style="margin-left: 250px; width: 890px;">
	<div id="myInfo" style="border: 1px solid #aaa; height: 150px; width: 100%; margin-bottom: 10px; border-radius: 15px;">
		<div style="height: 46px; margin: 10px; line-height: 46px;">
			<h3 style="margin-top: 10px; display: inline-block;">${userInfo.member_name}님 등급은 <p style="display: inline; color: red;">${userInfo.grade}</p>입니다</h3>
		</div>
		<div id="coupon" style="border-top: 1px solid #aaa; width: 444px; height:82px; float: left; padding-left: 20px;">
			<span style="display: block; width:110px; margin: 10px; margin-left: 0; border: 2px dashed red; border-radius: 15px; text-align: center;"><h4 style="margin: 5px;">COUPON</h4></span>
			<span>사용 가능 쿠폰 : ${couponTotal}장</span>
		</div>
		<div id="point" style="border-top: 1px solid #aaa; border-left: 1px solid #aaa; width: 444px; height:82px; float: right; padding-left: 20px;">
			<span style="display: block; width:110px; margin: 10px; margin-left: 0; border: 2px dashed red; border-radius: 15px; text-align: center;"><h4 style="margin: 5px;">POINT</h4></span>
			<span>사용 가능 포인트 : ${userInfo.point}점</span>
		</div>
	</div>


	<div style="height: 34px; line-height: 34px;">
		<span style="font-size: 20px; font-weight: bold;">예매내역</span><a style="margin-left: 5px;" href="/user/reservation">${reservationTotal}건<span class="glyphicon glyphicon-plus-sign"></span></a>
	</div>
	<div id="reservationList" style="border: 1px solid #aaa; border-radius: 15px;">
		<div style="width: 888px; height:50px; line-height: 50px; border-bottom: 1px solid #aaa;">
			<div style="width: 200px; text-align: center; font-size: 15px; border-right: 1px solid #aaa; float: left; font-weight: bold;">예매번호</div>
			<div style="width: 688px; text-align: center; font-size: 15px; margin-left: 200px; font-weight: bold;">예매정보</div>
		</div>
		<c:forEach items="${reservation.reservationList}" var="r" begin="0" end="2" step="1">
			<div style="height: 130px; border-top: 1px solid #aaa;">
				<span style="width: 200px; display: block; line-height: 130px; float: left; text-align: center; border-right: 1px solid #aaa;">${r.reservation_code}</span>
				<c:forEach items="${reservation.reservationMovie}" var="rm">
					<c:if test="${r.movie_id == rm.movie_id }">
						<div style="height: 130px; margin-left: 200px;">
							<span style="height: 128px; width: 105px; display: block; margin: 0; float: left;"><a href="/movie/${r.movie_id}"><img style="height: 100%; vertical-align: baseline;" src="${rm.poster}"></a></span>
							<span style="height:30px; display: block; margin-left: 115px; margin-bottom: 10px; padding-top:10px; font-weight: bold;"><a href="/movie/${r.movie_id}">${rm.title}</a></span>
							<span style="height:20px; width: 56px;display: block; margin-left: 10px; margin-bottom: 10px; font-weight: bold; float: left;">관람극장</span>
							<span style="height:20px; display: block; margin-left: 181px; margin-bottom: 10px;">${r.theater_name} ${r.plex_number}관&nbsp/&nbsp${r.ticket_cnt}명</span>
							<span style="height:20px; width: 56px;display: block; margin-left: 10px; margin-bottom: 10px; font-weight: bold; float: left;">관람일시</span>
							<span style="height:20px; display: block; margin-left: 181px; margin-bottom: 10px;">${r.start_time}</span>
							<span style="height:20px; width: 56px;display: block; margin-left: 10px; margin-bottom: 10px; font-weight: bold; float: left;">결제</span>
							<span style="height:20px; display: block; margin-left: 181px;">${r.pay_method} &nbsp/&nbsp ${r.pay}원</span>
						</div>
					</c:if>
				</c:forEach>
				
			</div>
		</c:forEach>
	</div>
</div>
<%@include file="../include/footer.jsp"%>