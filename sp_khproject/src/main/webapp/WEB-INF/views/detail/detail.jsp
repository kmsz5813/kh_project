<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<style>
#previewImg {
	max-width: 250px;
	border-radius: 30%;
	height: auto;
}

.container1 {
	display: block;
}

.container2 {
	display: none;
}

.container3 {
	display: none;
}

.btn-outline-success {
	margin-right: 10px;
}
</style>
</head>
<body>
	<%@ include file="../module/head.jsp"%>
	<section class="container w-75">
		<div class="mt-5">
			<div class="row mb-5 center">
				<div class="col-10 mb-3">
					<c:if test="${data.ac_index == 10}">
						<p class="fw-bold fs-2">회원정보</p>
					</c:if>
					<c:if test="${data.ac_index == 20}">
						<p class="fw-bold fs-2">전문가 정보</p>
					</c:if>
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-3 image-form left">
					<img id="previewImg" class="image-360" alt="프로필 이미지" src="./static/img/profile/${data.ac_email}.png" onerror="this.onerror=null; this.src='./static/img/profile/basic.png'">
				</div>
				<div class="col-4 mt-4">
					<p>이름</p>
					<p>직업</p>
					<c:if test="${data.ac_index == 10}">
						<p>비즈니스 분야</p>
						<p>관심분야</p>
					</c:if>
					<c:if test="${data.ac_index == 20}">
						<p>전문분야</p>
						<p>관심분야</p>
					</c:if>
				</div>
				<div class="col-4 mt-4">
					<p>${data.ac_name }</p>
					<p>${data.ac_job }</p>
					<p>${data.ac_field }</p>
					<p>${data.ac_interest }</p>
				</div>
				<div class="mt-5">
					<input type="radio" class="btn-check radio-value" value="1" name="options-outlined" id="success-outlined" autocomplete="off" checked> <label class="btn btn-outline-success" for="success-outlined">구매내역</label> <input type="radio" class="btn-check radio-value" value="2" name="options-outlined" id="success-outlined2" autocomplete="off"> <label class="btn btn-outline-success" for="success-outlined2">관심상품</label> <input type="radio" class="btn-check radio-value" value="3" name="options-outlined" id="success-outlined3" autocomplete="off"> <label class="btn btn-outline-success" for="success-outlined3">메시지</label>
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
		</div>
	</section>

	<script type="text/javascript">
		// radio active 버튼
		$('.radio-value').on('click', function() {
			var chkValue = $('.radio-value:checked').val();
			if (chkValue == 1) {
				$('.container1').css("display", "block");
				$('.container2').css("display", "none");
				$('.container3').css("display", "none");
			}
			if (chkValue == 2) {
				$('.container1').css("display", "none");
				$('.container2').css("display", "block");
				$('.container3').css("display", "none");
			}
			if (chkValue == 3) {
				$('.container1').css("display", "none");
				$('.container2').css("display", "none");
				$('.container3').css("display", "block");
			}
		});
	</script>
</body>
</html>