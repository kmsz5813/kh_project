<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>전문가 가입페이지</title>
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<header></header>
	<section class="container w-25">
		<div class="mt-5">
		<c:url var="selSignurl" value="/sel/selsign" />
		<form action="${selSignurl}" method="post">
			<div class="mb-3 center">
				<p class="fw-normal fs-2 text-center">회원가입</p>
			</div>
			<div class="p-1 mb-3 bg-secondary text-white text-center fw-normal">전문가</div>
			<div class="mb-3">
				<label class="fw-normal mb-2">이메일</label>
				<input class="form-control" type="text" name="sel_email">
			</div>
			<div class="mb-3">
				<label class="fw-normal mb-2">닉네임</label>
				<input class="form-control" type="text" name="sel_name">
			</div>
			<div class="mb-3">
				<label class="mb-2">비밀번호</label>
				<input class="form-control" type="password" name="sel_pw">
			</div>
			<div class="mb-3">
				<label class="mb-2">비밀번호확인</label>
				<input class="form-control" type="password" name="correct_pw">
			</div>
			<div class="mb-3">
				<label class="mb-2">직업</label>
				<select class="form-select" name="sel_job">
					<option value="테스트">테스트</option>
					<option value="테스트1">테스트1</option>
				</select>
			</div>
			<div class="mb-3">	
				<label class="mb-2">비즈니스</label>
				<select class="form-select" name="sel_field">
					<option value="비즈니스테스트">비즈니스테스트</option>
					<option value="비즈니스테스트2">비즈니스테스트2</option>
				</select>
			</div>
			<div class="mb-3">	
				<label class="mb-2">관심사 선택</label>
				<select class="form-select" name="sel_interest">
					<option value="관심분야테스트">관심분야테스트</option>
					<option value="관심분야테스트2">관심분야테스트2</option>
				</select>
			</div>
			<div class="mb-3 form-check">	
				<label class="mb-2">이메일수신동의</label>
				<input type="checkbox"  class="form-check-input" name="sel_sendemail">
			</div>
			<div class="mb-3">	
				<button type="submit" class="form-control p-1 mb-2 bg-secondary  text-center fw-normal" style="--bs-bg-opacity: .5;">가입완료</button>
			</div>
		</form>
		</div>
	</section>
</body>
</html>