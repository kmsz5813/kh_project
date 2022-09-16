<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
	<%@ include file="../module/head.jsp" %>
	<h1>디테일 페이지</h1>
	<section class="container w-75">
		<img id="previewImg" class="image-360" alt="프로필 이미지" src="./static/img/profile/${data.ac_email}"
			onerror="this.onerror=null; this.src='./static/img/profile/basic.png'">
		<p>${data.ac_email }</p>
		<p>${data.ac_name }</p>
		<p>${data.ac_job }</p>
		<p>${data.ac_field }</p>
		<p>${data.ac_interest }</p>
	</section>
</body>
</html>