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
/*small/medium side display*/
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
		border-bottom: 1px solid #000;
	}
	/* Colors/style dropdown box*/
	/*allows nav box to use 100% width*/
	nav.sidebar .navbar-collapse, nav.sidebar .container-fluid {
		padding: 0 0px 0 0px;
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
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li>
						<a href="/support"><font style="font-weight: bold;">고객센터 메인 </font></a>
					</li>
					<li>
						<a href="/support/faq"><font style="font-weight: bold;">자주찾는 질문 </font></a>
					</li>
					<li>
						<a href="/support/notice"><font style="font-weight: bold;">공지/뉴스 </font></a>
					</li>
					<li>
						<a href="/support/qna"><font style="font-weight: bold;">1:1 문의 </font></a>
					</li>
					<li>
						<a href="/support/lost"><font style="font-weight: bold;">분실물 문의 </font></a>
					</li>
					<li>
						<a href="/support/rent"><font style="font-weight: bold;">단체/대관 문의 </font></a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</div>