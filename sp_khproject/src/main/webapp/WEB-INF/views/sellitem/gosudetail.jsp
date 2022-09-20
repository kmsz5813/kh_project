<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세</title>
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
					<p class="fw-bold fs-2">상품 상세</p>
				</div>
			</div>
			<div class="row mt-3">	
				<div class="col-3">
					<div class="image-form left">
						<img id="previewImg" class="image-360" alt="프로필 이미지." src="./static/img/profile/${profileImage}.png" 
						onerror="this.onerror=null; this.src='./static/img/profile/basic.png'">
						<br>
					</div>
				</div>
				<div class="col-4 mt-4">
					<p>이름</p>
					<p>지역</p>
					<p>전문분야</p>
					<p>소개</p>
				</div>
				<div class="col-4 mt-4">
					<p>${data.ac_name }</p>
					<p>${data.ac_location }</p>
					<p>${data.ac_field }</p>
					<p>${data.ac_interest }</p>
				</div>
			</div>
			<div class="mt-5" >
			상세 설명
				<div class="mt-3">
					생의 찬미를 
				</div>
				<div class="mt-3">
					열매를 맺어 우리인생을 풍부하게하는 것
				</div>
			</div>
			<div class="mt-5">
			경력
				<table class="table table-bordered">
				  <thead>
				    <tr>
				      <th scope="col"></th>
				      <th scope="col"></th>
				      <th scope="col"></th>
				
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row"></th>
				      <td></td>
				      <td></td>
				     
				    </tr>
				    <tr>
				      <th scope="row"></th>
				      <td></td>
				      <td></td>
				    
				    </tr>
				    <tr>
				      <th scope="row"></th>
				      <td></td>
				 	  <td></td>
				    </tr>
				  </tbody>
				</table>
			</div>
			<div class="mt-5 mb-5">
			리뷰
			<table class="table table-bordered">
				  <thead>
				    <tr>
				      <th scope="col">No.</th>
				      <th scope="col">First</th>
				      <th scope="col">Last</th>
				
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row">1</th>
				      <td>Mark</td>
				      <td>Otto</td>
				     
				    </tr>
				    <tr>
				      <th scope="row">2</th>
				      <td>Jacob</td>
				      <td>Thornton</td>
				    
				    </tr>
				    <tr>
				      <th scope="row">3</th>
				      <td>Larry the Bird</td>
				 	  <td>Larry the Bird</td>
				    </tr>
				  </tbody>
				</table>
			</div>
			<div class="mt-5 btn-group" role="group" aria-label="Basic example">
				<button type="button" class="form-control mb-5 w-25" style="background-color:rgb(39,174,96); text:white; margin-left:4rem">견적 요청하기</button>
				<button  type="button"  class="form-control mb-5 w-25" style="background-color:rgb(224,224,224); text:white;" onclick="location.href='message/ChattingUser'">견적 요청하기</button>
				<button  type="button"  class="form-control mb-5 w-50" style="background-color:rgb(39,174,96); text:white;">견적 요청하기</button>
			</div>
		</div>
	</section>
</body>
</html>