<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>



<c:url var="bs5" value="/static/bs5" />
<c:url var="jQuery" value="/static/js" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인페이지</title>
	<link rel="stylesheet" type="text/css" href="${bs5}/css/bootstrap.min.css">
	<script type="text/javascript" src="${bs5}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${jQuery}/jquery-3.6.0.min.js"></script>  

</head>
<body>
	<header></header>
	<section class="container w-25">
	<div class="mt-5" >
	<c:url var="loginurl" value="/login" />
	<form action="${loginurl}" method="post">
		<div class="mb-5 center">
			<p class="fw-normal fs-2 text-center">로그인</p>
		</div>
		<div class="mb-3">
			<input class="form-control" style="height:3rem" type="text" name="email"  aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="이메일 주소">
		</div>
		<div class="mb-3">
			<input class="form-control" style="height:3rem" type="password" name="pw"  aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="비밀번호">
		</div>
		<c:if test="${not empty error}">
			<div class="mb-3">
				<p style="color:red">아이디 또는 비밀번호가 틀립니다.</p>
			</div>
		</c:if>
		<c:if test="${not empty email}">
			<div class="mb-3">
				<p style="color:red">아이디 또는 비밀번호가 틀립니다.</p>
			</div>
		</c:if>
		<div class="mb-3 ">
			<button type="button" style="border:none; background-color:transparent;">아이디 / 비밀번호찾기</button> 
		</div>
		<div class="mb-3">
			<button type="submit" class="form-control p-1 mb-2 bg-secondary  text-center fw-normal" style="--bs-bg-opacity: .5; height:3rem;">로그인</button>
		</div>
		<div class="mb-3">
			<p class="fw-normal fs-5 text-center">sns계정으로 시작해보세요</p>
		</div>
		<div class="mb-3">
			<button class="form-control p-1 mb-2 bg-secondary  text-center fw-normal" style="height:3rem;" type="button" onclick="location.href='login/sign'">이메일로 시작하기</button>
			<!--회원가입페이지로 연결 -->
		</div>
		<div class="mb-3">
			<button type="button" class="form-control mw-100" style="background-color: #FEE500; height:3rem;"
			onclick="location.href='login/kakao'">
			<img alt="카카오로 가입하기" src="static/img/naver/kakao.png">
			</button>
		</div>
		<div class="mb-3">
			<button type="button" class="form-control mw-100" style="background-color: rgb(3, 199, 90); height: 3rem"
			onclick="location.href='login/naver'">
			<img  alt="네이버로 가입하기" src="static/img/naver/naverbtn1.png">		
		 </button>
		 
		</div>
	</form>
	</div>
	</section>
</body>
</html>