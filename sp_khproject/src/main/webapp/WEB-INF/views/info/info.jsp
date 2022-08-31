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
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<header></header>
	<section class="container w-75">
		<div class="mt-5">
		<div class="mb-5 center">
			<p class="fw-normal fs-2">마이페이지 
			<a class="fw-normal fs-6 " href="">수정 / </a>  
			<a class="fw-normal fs-6 " href="">삭제</a></p>
		</div>
		<div class="row mt-3">
			<div class="col">
				<div class="card">
					<button type="button" class="btn btn-secondary text-black opacity-50" style="width:1rem height: 6rem;" onclick="location.href='/home/sel/selsign'">전문가</button>
				</div>
			</div>
			<div class="col">
				<div class="card">
					<p class="fw-normal fs-6">이름 : ${loginData.cus_name }</p>
					<p class="fw-normal fs-6">지역 : ${loginData.cus_field }</p>
					<p class="fw-normal fs-6">흥미 : ${loginData.cus_interest }</p>
				</div>
			</div>
		</div>
		</div>
	</section>
</body>
</html>