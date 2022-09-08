<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>고수 목록</title>
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<header class="mb-3">
		<%@ include file="../module/navigation.jsp" %>
	</header>
	<section class="container">
		<div class="mb-1">
			<c:url var="pboardUrl" value="/pboard" />
			<form action="${pboardUrl}" method="get">
				<div class="row g-1">
					<div class="col-8">
						<button class="btn btn-secondary" type="button" onclick="location.href='${pboardUrl}/add'">상품 등록</button>
					</div>
				</div>
			</form>
		</div>
	</section>
</body>
</html>