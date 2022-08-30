<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인페이지</title>
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<header></header>
	<section class="container w-25">
	<div class="mt-5">
	<c:url var="loginurl" value="/login" />
	<form action="${loginurl}" method="post">
		<div class="mb-5 center">
			<p class="fw-normal fs-2 text-center">로그인</p>
		</div>
		<div class="mb-3">
			<select class="form-select" name="index">
				<option value="10">일반회원</option>
				<option value="20">판매자</option>
			</select>
		</div>
		<div class="mb-3">
			<input class="form-control"  type="text" name="email"  aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
		</div>
		<div class="mb-3">
			<input class="form-control" type="password" name="pw"  aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
		</div>
		<div class="mb-3">
			<p class="right">아이디 / 비밀번호 찾기</p>
		</div>
		<div class="mb-3">
			<button type="submit" class="form-control p-1 mb-2 bg-secondary  text-center fw-normal" style="--bs-bg-opacity: .5;">로그인</button>
		</div>
		<div class="mb-3">
			<p class="fw-normal fs-5 text-center">sns계정으로 로그인하세요</p>
		</div>
		<div class="mb-3">
			<button class="form-control p-1 mb-2 bg-secondary  text-center fw-normal"  type="button" onclick="location.href='sel/selsign'">회원가입</button>
			<!--회원가입페이지로 연결 -->
		</div>
	</form>
	</div>
	</section>
</body>
</html>