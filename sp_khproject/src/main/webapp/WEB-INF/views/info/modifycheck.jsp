<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="bs5" value="/static/bs5" />
<c:url var="jQuery" value="/static/js" />

    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마이페이지</title>
	<link rel="stylesheet" type="text/css" href="${bs5}/css/bootstrap.min.css">
	<script type="text/javascript" src="${bs5}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${jQuery}/jquery-3.6.0.min.js"></script>
</head>
<body>
	<header></header>
	<section class="container w-75">
		<c:url var="modifycheck" value="/info/modifycheck" /> 
		<form action="${modifycheck}" method="post"> 
		<div class="mt-5">
			<div class="row mb-5 center">
				<div class="col-10">
					<p class="fw-normal fs-2">마이페이지</p>
				</div>
			</div>
		</div>
		<div class="row mt-3">	
			${loginData.ac_name } 님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 설정합니다.
		</div>
		<div class="mb-3">
			<input class="form-control"  type="text" name="email"  aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
		</div>
		<div class="mb-3">
			<input class="form-control" type="password" name="pw"  aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
		</div>
		<c:if test="${errorMsg==false}">
			<div>
				<p style="color:red; margin: auto;">이메일 / 비밀번호를 확인하세요.</p>
			</div>
		</c:if>
		<div class="mb-3">
			<button class="form-control p-1 mb-2 bg-secondary  text-center fw-normal" onclick="location.href='/home/info/modify'" type="submit">확인</button>
			<button class="form-control p-1 mb-2 bg-secondary" type="button" onclick="location.href='/home/info'">뒤로가기</button> 
		</div>
		</form>
	</section>
</body>
</html>