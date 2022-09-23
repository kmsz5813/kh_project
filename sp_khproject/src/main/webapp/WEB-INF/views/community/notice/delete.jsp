<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FindU</title>
	<%@ include file="../../module/head.jsp" %>
</head>
<body>
	<c:if test="${empty error}">
		<form action="/community/question/delete" method="post">
			<input type="hidden" name="user_Name" value="${data.user_Name}">
			<ul>
				<li>${data.question_Id}</li>
				<li>${data.question_Title}</li>
			</ul>
			<p>삭제하려는 데이터가 맞습니까?</p>
			<div>
				<button type="submit">삭제</button>
				<button type="button" onclick="location.href='/question/detail?id=${data.question_Id}'">취소</button>
			</div>
		</form>
	</c:if>
	<c:if test="${not empty error}">
		<h1>${error}</h1>
	</c:if>
</body>
</html>