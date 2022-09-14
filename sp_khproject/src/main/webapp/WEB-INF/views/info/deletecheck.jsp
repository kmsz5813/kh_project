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
	<%@ include file="../module/head.jsp" %>
	<!--style부분-->
	
	
	<style>
	.find-btn{
		text-align: center;
	}
	.find-btn1{
		display :inline-block;
	}
	</style> 

</head>
<body>
	<header></header>
	<section class="container w-75">
		<c:url var="deletecheck" value="/info/deletecheck" /> 
		<form action="${deletecheck}" method="post" id="deleteForm"> 
			<div class="mt-5">
				<div class="row mb-5 center">
					<div class="col-10">
						<p class="fw-normal fs-2">회원 정보 확인</p>
					</div>
				</div>
			</div>
			<div class="row mt-3 mb-5 ml-5" style="margin-left: 0.1rem">	
			${loginData.ac_name } 님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 확인합니다.
			</div>
			<div class="input-group input-group-lg">
			  <span class="input-group-text" style="font-weight: 300;">   이메일</span>
			  <input type="text" class="form-control" aria-label="Sizing example input"  name="email" aria-describedby="inputGroup-sizing-lg"  >
			</div>
			<div class="input-group input-group-lg mt-3 mb-3">
			  <span class="input-group-text" style="font-weight: 300;">비밀번호</span>
			  <input type="password" class="form-control" aria-label="Sizing example input"  name="pw"  aria-describedby="inputGroup-sizing-lg" >
			</div>
		
			<c:if test="${errorMsg== false}">
				<div>
					<p style="color:red; margin: auto;">이메일 / 비밀번호를 확인하세요.</p>
				</div>
			</c:if>
			<div class="mb-3 find-btn">
				<button id="delete" class="btn btn-primary find-btn1"  type="button">회원탈퇴</button>
				<button class="btn btn-secondary find-btn1" type="button" onclick="location.href='/home/info'">뒤로가기</button> 
			</div>
			<script type="text/javascript">
				$(document).ready(function(){
					$("#delete").on("click", function() {
						if(confirm("탈퇴하시겠습니까?")) {
							$("#deleteForm").submit();
						}
					})
				});
			</script>
		</form>
	</section>
</body>
</html>