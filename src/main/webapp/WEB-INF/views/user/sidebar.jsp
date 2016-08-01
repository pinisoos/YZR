<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
/* remove outer padding */
.main .row {
	padding: 0px;
	margin: 0px;
}
/*Remove rounded coners*/
nav.sidebar.navbar {
	border-radius: 0px;
}
nav.sidebar, .main {
	-webkit-transition: margin 200ms ease-out;
	-moz-transition: margin 200ms ease-out;
	-o-transition: margin 200ms ease-out;
	transition: margin 200ms ease-out;
}
/* Add gap to nav and right windows.*/
.main {
	padding: 10px 10px 0 10px;
}
/* .....NavBar: Icon only with coloring/layout.....*/

/*small/medium side display*/
@media ( min-width : 768px) {
	/*Allow main to be next to Nav*/
	.main {
		position: absolute;
		width: calc(100% - 40px); /*keeps 100% minus nav size*/
		margin-left: 40px;
		float: right;
	}
	/*lets nav bar to be showed on mouseover*/
	nav.sidebar:hover+.main {
		margin-left: 200px;
	}
	/*Center Brand*/
	nav.sidebar.navbar.sidebar>.container .navbar-brand, .navbar>.container-fluid .navbar-brand
		{
		margin-left: 0px;
	}
	/*Center Brand*/
	nav.sidebar .navbar-brand, nav.sidebar .navbar-header {
		text-align: center;
		width: 100%;
		margin-left: 0px;
	}
	/*Center Icons*/
	nav.sidebar a {
		padding-right: 13px;
	}
	/*adds border top to first nav box */
	nav.sidebar .navbar-nav>li:first-child {
		border-top: 1px #000 solid;
	}
	/*adds border to bottom nav boxes*/
	nav.sidebar .navbar-nav>li {
		border-bottom: 1px #000 solid;
	}
	/* Colors/style dropdown box*/
	nav.sidebar .navbar-nav .open .dropdown-menu {
		position: static;
		float: none;
		width: auto;
		margin-top: 0;
		background-color: transparent;
		border: 0;
		-webkit-box-shadow: none;
		box-shadow: none;
	}
	/*allows nav box to use 100% width*/
	nav.sidebar .navbar-collapse, nav.sidebar .container-fluid {
		padding: 0 0px 0 0px;
	}
	/*colors dropdown box text */
	.navbar-inverse .navbar-nav .open .dropdown-menu>li>a {
		color: #000;
	}
	/*gives sidebar width/height*/
	nav.sidebar {
		width: 200px;
		height: 100%;
		margin-left: -160px;
		float: left;
		z-index: 8000;
		margin-bottom: 0px;
	}
	/*give sidebar 100% width;*/
	nav.sidebar li {
		width: 100%;
	}
	/* Move nav to full on mouse over*/
	nav.sidebar:hover {
		margin-left: 0px;
	}
	/*for hiden things when navbar hidden*/
	.forAnimate {
		opacity: 0;
	}
}
/*Allow main to be next to Nav*/
.main {
	width: calc(100% - 200px); /*keeps 100% minus nav size*/
	margin-left: 200px;
}
/*Show all nav*/
nav.sidebar {
	margin-left: 0px;
	float: left;
}
/*Show hidden items on nav*/
nav.sidebar .forAnimate {
	opacity: 1;
}
nav.sidebar .navbar-nav .open .dropdown-menu>li>a:hover, nav.sidebar .navbar-nav .open .dropdown-menu>li>a:focus
	{
	color: #000;
	background-color: transparent;
}
nav:hover .forAnimate {
	opacity: 1;
}
section {
	padding-left: 15px;
}
</style>
<div id="wrap_content" style="min-height: 100%; position: relative;">
	<nav class="navbar navbar-defalt sidebar" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="/user" style="font-weight: bold;">마이페이지 </a></li>
				<li><a href="/user/reservation" style="font-weight: bold;">나의 예매내역 </a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-weight: bold;">나의 문의내역 <span class="caret"></span></a>
					<ul class="dropdown-menu forAnimate" role="menu">
						<li><a href="/user/qna">1:1 문의</a></li>
						<li><a href="/user/lost">분실물 문의</a></li>
						<li><a href="/user/rent">단체/관람 문의</a></li>
					</ul>
				</li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-weight: bold;">쿠폰 관리 <span class="caret"></span></a>
					<ul class="dropdown-menu forAnimate" role="menu">
						<li><a href="/user/coupon">할인 쿠폰</a></li>
					</ul>
				</li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-weight: bold;">포인트 관리 <span class="caret"></span></a>
					<ul class="dropdown-menu forAnimate" role="menu">
						<li><a href="/user/point">포인트 적립/사용내역</a></li>
					</ul>
				</li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-weight: bold;">팝콘스토어 <span class="caret"></span></a>
					<ul class="dropdown-menu forAnimate" role="menu">
						<li><a href="/user/store">매점상품 기프트콘</a></li>
					</ul>
				</li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-weight: bold;">회원 정보 <span class="caret"></span></a>
					<ul class="dropdown-menu forAnimate" role="menu">
						<li><a href="/user/update">개인정보 변경</a></li>
						<li><a href="/user/delete">회원 탈퇴</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	</nav>
</div>