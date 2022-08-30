<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Want</title>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<body>
	<header>
		<a>로그인</a>
		/ 
		<a>회원가입</a>
		/ 
		<a>FAQ</a>
	</header>
	<c:url var="mainurl" value="/main" />
	<footer>이용약관</footer>
</body>
	
</html>