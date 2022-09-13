<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header></header>
	<section class="container w-75">
	<div class="mt-5" >
	<c:url var="addurl" value="/sellitem/additem" />
	<form action="${addurl}" method="post">
		<div>
			상품등록
		</div>
		<div>
		서비스
		</div>
		<div>
		지역
		</div>
		<div>
		내용추가 / 삭제
		</div>
		<div>
		상품상세설명
		<input type="text" class="form-control" name="test">
		</div>
		<div>
		사진첨부
		</div>
		<button type="submit"  class="form-control"> 등록</button>
	</form>
	</div>
	</section>
</body>
</html>