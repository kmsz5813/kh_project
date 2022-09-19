<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
	<meta charset="UTF-8">
	<title>마이페이지</title>
	
	<style>
		#previewImg {
			max-width : 250px;
			border-radius: 30%;
			height : auto;
		}
		
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
	<%@ include file="../module/head.jsp" %>
	<header></header>
	<section class="container w-75">
		<div class="mt-5">
			<div class="row mb-5 center">
				<div class="col-10">
					<p class="fw-bold fs-2">마이페이지</p>
				</div>
				<div class="col-auto mt-4">
					<button onclick="location.href='${pageContext.request.contextPath}/info/modifycheck'" type="button" class="btn btn-outline-secondary btn-lg">수정</button>
					<button onclick="location.href='${pageContext.request.contextPath}/info/deletecheck'" type="button" class="btn btn-outline-secondary btn-lg">탈퇴</button>
				</div>
			</div>
			<div class="row mt-3">	
				<div class="col-3">
					<div class="image-form left">
						<!-- onerror 속성은 src 에 해당하는 파일이 없으면 대체(null을 설정하는 이유는 그마저도 없으면 안되기때문) -->
						<img id="previewImg" class="image-360" alt="프로필 이미지." src="./static/img/profile/${profileImage}.png" 
						onerror="this.onerror=null; this.src='./static/img/profile/basic.png'">
						<br>
					</div>
				</div>
				<div class="col-4">
					<div class="mt-4">
						<p class="fs-6">이름</p>
						<p class="fs-6">직업</p>
						<c:if test="${loginData.ac_index == 10}">
							<p class="fw-lighter fs-6">비즈니스 분야 </p>
							<p class="fw-lighter fs-6">관심분야</p>
						</c:if>
						<c:if test="${loginData.ac_index == 20}">
							<p class="fw-lighter fs-6">전문분야 </p>
							<p class="fw-lighter fs-6">관심분야</p>
						</c:if>
					</div>
				</div>
				<div class="col">
					<div class="mt-4">
						<p>${loginData.ac_name }</p>
						<p>${loginData.ac_job }</p>
						<p>${loginData.ac_field }</p>
						<p>${loginData.ac_interest }</p>
					</div>
				</div>
			</div>
			<div class="mt-5">
				<input type="radio" class="btn-check radio-value" value = "1" name="options-outlined" id="success-outlined" autocomplete="off" checked>
				<label class="btn btn-outline-success" for="success-outlined">구매내역</label>
				
				<input type="radio" class="btn-check radio-value" value = "2" name="options-outlined" id="success-outlined2" autocomplete="off">
				<label class="btn btn-outline-success" for="success-outlined2">관심상품</label>
				
				<input type="radio" class="btn-check radio-value" value = "3" name="options-outlined" id="success-outlined3" autocomplete="off">
				<label class="btn btn-outline-success" for="success-outlined3">메시지</label>
			</div>
			<section class="container container1">
					<div class="mt-5">
						<table class="table table-hover">
							<colgroup>
								<col class="col-2">
								<col class="col-auto">
								<col class="col-5">
							</colgroup>
							<thead style="background-color: rgb(224, 224, 224)">
								<tr>
									<th class="text-center">test</th>
									<th class="text-center">test</th>
									<th class="text-center">test</th>
								</tr>
							</thead>
						</table>
					</div>
				</section>
				<section class="container container2">
					<div class="mt-5">
						<table class="table table-hover">
							<colgroup>
								<col class="col-2">
								<col class="col-auto">
								<col class="col-5">
							</colgroup>
							<thead style="background-color: rgb(224, 224, 224)">
								<tr>
									<th class="text-center">test2</th>
									<th class="text-center">test2</th>
									<th class="text-center">test2</th>
								</tr>
							</thead>
						</table>
					</div>
				</section>
				<section class="container container3">
					<div class="mt-5">
						<table class="table table-hover">
							<colgroup>
								<col class="col-2">
								<col class="col-auto">
								<col class="col-5">
							</colgroup>
							<thead style="background-color: rgb(224, 224, 224)">
								<tr>
									<th class="text-center">test3</th>
									<th class="text-center">test3</th>
									<th class="text-center">test3</th>
								</tr>
							</thead>
						</table>
					</div>
				</section>
		</div>
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