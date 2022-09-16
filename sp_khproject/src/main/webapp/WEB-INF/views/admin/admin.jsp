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
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<style>
		
		.container1 {
			display : block;
		}
		
		.container2 {
			display : none;
		}
		
		.container3 {
			display : none;
		}
		
		.btn-outline-success {
			margin-right : 10px;
		}
		

	</style>
</head>
<body>
	<div style = "text-align: center;">
		<input type="radio" class="btn-check radio-value" value = "1" name="options-outlined" id="success-outlined" autocomplete="off" checked>
		<label class="btn btn-outline-success" for="success-outlined">회원목록 조회</label>
		
		<input type="radio" class="btn-check radio-value" value = "2" name="options-outlined" id="success-outlined2" autocomplete="off">
		<label class="btn btn-outline-success" for="success-outlined2">섹션2</label>
		
		<input type="radio" class="btn-check radio-value" value = "3" name="options-outlined" id="success-outlined3" autocomplete="off">
		<label class="btn btn-outline-success" for="success-outlined3">섹션3</label>
	</div>

	<section class="container container1">
		<table class="table wide vertical-hidden table-hover ">
			<colgroup>
				<col class="col-120">
				<col class="col-240">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
			</colgroup>
			<thead class="thead-light">
				<tr>
					<th>회원 번호</th>
					<th>회원 이메일</th>
					<th>회원 닉네임</th>
					<th>회원 직업</th>
					<th>회원 비즈니스 분야</th>
					<th>회원 관심사</th>
					<th>회원 분류</th>
					<th>가입일</th>
					<th> </th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${datas}" var="datas">
					<c:if test="${datas.ac_index == 10 || datas.ac_index == 20}">
						<tr onclick="location.href='./detail?search=${datas.ac_name}'" style="cursor:pointer;">
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
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</section>
	
	<section class="container container2">
		<p>섹션2</p>
	</section>
	
	<section class="container container3">
		<p>섹션3</p>
	</section>
	
	<script type="text/javascript">
		// radio active 버튼
		$('.radio-value').on('click', function() {
			var chkValue = $('.radio-value:checked').val();
			if(chkValue == 1) {
				$('.container1').css("display", "block");
				$('.container2').css("display", "none");
				$('.container3').css("display", "none");
			}
			if(chkValue == 2) {
				$('.container1').css("display", "none");
				$('.container2').css("display", "block");
				$('.container3').css("display", "none");
			}
			if(chkValue == 3) {
				$('.container1').css("display", "none");
				$('.container2').css("display", "none");
				$('.container3').css("display", "block");
			}
		});
		
	</script>
</body>
</html>