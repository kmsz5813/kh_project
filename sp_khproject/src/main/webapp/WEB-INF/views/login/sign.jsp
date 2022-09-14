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
	<title>회원가입 페이지</title>
	<link rel="stylesheet" type="text/css" href="${bs5}/css/bootstrap.min.css">
	<script type="text/javascript" src="${bs5}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${jQuery}/jquery-3.6.0.min.js"></script>
</head>
<body>
	<header></header>
	<section class="container w-50">
	<div class="mt-5">
		
		<div class="mb-5 center">
			<p class="fw-normal fs-2 text-center">회원가입</p>
		</div>
		<div class="mb-5 center">
			<p class="fw-normal fs-6 text-center">어떤 서비스를 이용하고 싶으세요?</p>
		</div>
		<div class="row mt-3">
			<div class="col">
				<div class="card">
					<button type="button"  class="btn btn-secondary text-black opacity-50" style="height: 3rem;" onclick="location.href='/home/login/selsign'">전문가</button>
				</div>
			</div>
			<div class="col">
				<div class="card">
					<button type="button" class="btn btn-secondary text-white" style="height: 3rem ;" onclick="location.href='/home/login/cussign'">일반회원</button>
				</div>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col">
				<div class="card">
					<button type="button" class="btn btn-light" style="height: 20rem;">전문가 사진</button>
				</div>
			</div>
			<div class="col">
				<div class="card">
					<button type="button" class="btn btn-light" style="height: 20rem ;">일반회원 사진</button>
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>