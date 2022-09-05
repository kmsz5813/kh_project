<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마이페이지</title>
</head>
<body>
	<header></header>
	<section class="container w-75">
		<c:url var="deletecheck" value="/info/deletecheck" /> 
		<form action="${deletecheck}" method="post"> 
		<div class="mt-5">
			<div class="row mb-5 center">
				<div class="col-10">
					<p class="fw-normal fs-2">마이페이지</p>
				</div>
			</div>
		</div>
		<div class="row mt-3">	
			${loginData.ac_name } 님의 정보를 안전하게 보호하기 위해 이메일 / 비밀번호를 확인합니다.
		</div>
		<div class="mb-3">
			<input class="form-control"  type="text" name="email"  aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="이메일">
		</div>
		<div class="mb-3">
			<input class="form-control" type="password" name="pw"  aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="비밀번호">
		</div>
		<div class="mb-3">
			<c:if test="${msg== false}">
				<p style="color:red">아이디 또는 비밀번호가 틀렸어요</p>
			</c:if>
			<button class="form-control p-1 mb-2 bg-secondary  text-center fw-normal"  type="submit" onclick="location.href='home/info/delete'">회원탈퇴</button>
			<button class="form-control p-1 mb-2 bg-secondary" type="button" onclick="/home/info">뒤로가기</button> 
		</div>
		</form>
	</section>
</body>
</html>