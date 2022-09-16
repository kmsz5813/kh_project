<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="bs5" value="/static/bs5" />
<c:url var="jQuery" value="/static/js" />
<link rel="stylesheet" type="text/css" href="${bs5}/css/bootstrap.min.css">
<!-- 폰트 적용 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<script type="text/javascript" src="${bs5}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${jQuery}/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${jQuery}/datatables.min.js"></script>

<style>
	a:link {
		text-decoration: none;
		color: black;
		font-weight: bold;
		font-family: 'Noto Sans KR', sans-serif;
	}

	.navbar {
		border-top: 3px solid lightgrey;
		border-bottom: 3px solid lightgrey;
	}
	
	body {
		width: 1500px;
		margin: auto;
		font-family: 'Noto Sans KR', sans-serif;
	}
	
</style>


<header>
<!-- 로그인/회원가입/FAQ -->
	<div style="text-align: right; margin-right: 100px; position:relative; top: 20px;">
		<c:if test="${empty loginData }">
			<a href="${pageContext.request.contextPath}/login">로그인</a>&emsp;/&emsp; 
			<a href="${pageContext.request.contextPath}/login/sign">회원가입</a>&emsp;/&emsp;
		</c:if>
			<c:if test="${not empty loginData }">
			${loginData.ac_name }님 환영합니다!&emsp;/&emsp;
			<a href="${pageContext.request.contextPath}/main/logout">로그아웃&emsp;/&emsp;</a>
			<a href="${pageContext.request.contextPath}/info">마이페이지&emsp;/&emsp;</a>
		</c:if> 
		<a href="#">FAQ</a>
	</div>
	
<!-- nav-bar -->
	
	<a href="${pageContext.request.contextPath}/main" style="display: flex; margin-top: -100px;"> 
      <img src="${pageContext.request.contextPath}/static/img/logo.png" 
      	style="width:300px; margin-bottom: -100px; " class="d-inline-block">
    </a>
	<nav class="navbar navbar-expand-md navbar-light" style="; margin-left : 300px; position: relative; bottom: 60px; margin-right: 80px;" >
	  <div class="container">
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">

	        <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="padding-left: 100px;">
	            카테고리
	          </a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="#">카테고리1</a></li>
	            <li><a class="dropdown-item" href="#">카테고리2</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="#">카테고리3</a></li>
	            <li><a class="dropdown-item" href="#">카테고리4</a></li>
	          </ul>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#" style="padding-left:100px;">커뮤니티</a>
	        </li>
	      </ul>
	      <form class="d-flex">
	        <input class="form-control me-4" type="search" placeholder="내용을 입력해주세요." aria-label="Search" style="min-width: 300px;">
	        <button class="btn btn-outline-success" type="submit" style="width:100px; margin-right: 50px;" id="button">검색</button>
	      </form>
	    </div>
	  </div>
	</nav>
</header>	