<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 페이지</title>
	<%@ include file="../module/head.jsp" %>
	
	<style>

	</style>
</head>
<body>
	<section class="container">
		<table class="table wide vertical-hidden hover">
			<colgroup>
				<col class="col-240">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
			</colgroup>
			<thead>
				<tr>
					<th class="" onclick="">회원 번호</th>
					<th class="" onclick="">회원 이메일</th>
					<th class="" onclick="">회원 닉네임</th>
					<th class="" onclick="">회원 직업</th>
					<th class="" onclick="">회원 비즈니스 분야</th>
					<th class="" onclick="">회원 관심사</th>
					<th class="" onclick="">회원 분류</th>
					<th class="" onclick="">가입일</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${datas}" var="datas">
					<tr>
						<td>${datas.ac_number}</td>
						<td>${datas.ac_email}</td>
						<td>${datas.ac_name}</td>
						<td>${datas.ac_job}</td>
						<td>${datas.ac_field}</td>
						<td>${datas.ac_interest}</td>
						<c:if test="${datas.ac_index == 10}">
							<td>일반회원</td>
						</c:if>
						<c:if test="${datas.ac_index == 20}" >
							<td>판매자</td>
						</c:if>
						<td>${datas.ac_signday}</td>
						<td>
							<button type="button" class="btn btn-danger" onclick="location.href='./admin/addBlacklist?id=${datas.ac_email}'">블랙리스트 지정</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</body>
</html>