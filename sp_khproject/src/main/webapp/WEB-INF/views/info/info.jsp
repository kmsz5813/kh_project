<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마이페이지</title>
	<%@ include file="../module/head.jsp" %>
	<style>
		#previewImg {
			max-width : 250px;
			height : auto;
		}
	</style>
</head>
<body>
	<header></header>
	<section class="container w-75">
		<div class="mt-5">
		<div class="row mb-5 center">
			<div class="col-10">
				<p class="fw-normal fs-2">마이페이지</p>
			</div>
			<div class="col-auto mt-4">
				<a href="${pageContext.request.contextPath}/info/modifycheck"><button type="button" style="border:none; font-weight:bold; background-color:transparent;">수정</button></a> / 
				<a href="${pageContext.request.contextPath}/info/deletecheck"><button type="button" style="border:none; font-weight:bold; background-color:transparent;">탈퇴</button></a>
			</div>
		</div>
		<div class="row mt-3">	
			<div class="col-3">
				<div>
					<div class="image-form left">
						<!-- onerror 속성은 src 에 해당하는 파일이 없으면 대체(null을 설정하는 이유는 그마저도 없으면 안되기때문) -->
						<img id="previewImg" class="image-360" alt="여기에는 증명 사진이 배치됩니다." src="./static/img/profile/${profileImage}.png" 
						onerror="this.onerror=null; this.src='./static/img/profile/basic.png'">
						<br>
					</div>
				</div>
			</div>
			<div class="col-4">
				<div class="mt-4">
					<p class="fs-6">이름</p>
					<p class="fw-lighter fs-6">지역 </p>
					<p class="fw-lighter fs-6">관심분야</p>
				</div>
			</div>
			<div class="col">
				<div class="mt-4">
					<p>${loginData.ac_name }</p>
					<p>${loginData.ac_field }</p>
					<p>${loginData.ac_interest }</p>
				</div>
			</div>
		</div>
		<div class="mt-5">
			<div>
				<button type="button" class="btn btn-secondary text-black " style="background-color:rgb(224, 224, 224)">구매내역</button>
				<button type="button" class="btn btn-secondary text-black " style="background-color:rgb(224, 224, 224)">관심상품</button>
				<button type="button" class="btn btn-secondary text-black " style="background-color:rgb(224, 224, 224)">메시지</button>
			</div>
		</div>
		<div class="mt-5">
			<table class="table table-honver">
				<colgroup>
					<col class="col-2">
					<col class="col-auto">
					<col class="col-5">
				</colgroup>
			<thead style="background-color:rgb(224, 224, 224)">
				<tr>
					<th class="text-center">no.</th>
					<th class="text-center">name</th>
					<th class="text-center">value</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="text-center">1번</td>
					<td class="text-center">테스트</td>
					<td class="text-center">테스트</td>
				<tr>
			</tbody>
			</table>
		</div>
		
		</div>
	</section>
</body>
</html>