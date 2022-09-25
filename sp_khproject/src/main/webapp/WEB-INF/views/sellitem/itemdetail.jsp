<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>판매정보</title>
	<style>
	#previewImg {
		max-width: 250px;
		border-radius: 30%;
		height: auto;
	}
	
	.container1 {
		display: block;
	}
	
	.bg-small{
		width: 1100px; height: auto;
	}
	</style>
</head>
	<body>
		<%@ include file="../module/head.jsp"%>
		<c:if test="${data.ac_index == 20}">
		<section class="container w-75">
			<div class="mt-5">
				<div class="row mb-5 center">
					<div class="col-10 mb-3">
						<p class="fw-bold fs-2">판매정보</p>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col-3 image-form left">
						<img id="previewImg" class="image-360" alt="프로필 이미지" 
						src="../static/img/profile/${data.ac_email}.png" 
						onerror="this.onerror=null; this.src='../static/img/profile/basic.png'">
					</div>
					<div class="col-4 mt-4">
						<p>이름</p>
						<p>직업</p>
						<p>전문분야</p>
					</div>
					<div class="col-4 mt-4">
						<p>${data.ac_name }</p>
						<p>${data.ac_job }</p>
						<p>${data.ac_field }</p>
					</div>
					<div class="mt-5">
						<button type="button" onclick="location.href='${pageContext.request.contextPath}/chat'"
						class="btn btn-outline-success">메시지 보내기</button>
						<c:if test="${loginData.ac_index == 10}">
							<button style="margin-left:20px;" type="button" 
							onclick="location.href='${pageContext.request.contextPath}/purchase?itemid=${itemdata.sel_id}'" 
							class="btn btn-outline-success">구매하기</button>
						</c:if>
					</div>
					<div class="mt-5" style="background-color: rgb(241, 241, 241); height:10rem;">
						<table class="table table-borderless">
							<thead>
							  <tr>
							      <th class="col-4" style="text-align: center;">고용</th>
							      <th class="col-4" style="text-align: center;">리뷰</th>
							      <th class="col-auto" style="text-align: center;">경력</th>						      
							  </tr>
							</thead>
							<tbody class="mt-5">
							    <tr class="mt-5">
							      <td style="text-align: center;">nn회</td>
							      <td style="text-align: center;">리뷰~</td>
							      <td style="text-align: center;">n년</td>
							    </tr>
							 </tbody>
						</table>
					</div>
				</div>
			</div>
			<c:url var="selitemdetailUrl" value="/selitem/detail" />
			<c:if test="${data.ac_name == loginData.ac_name}">
				<div>
					<button type="button">수정</button>
					<button class="btn btn-danger" type="button" data-bs-toggle="modal" data-bs-target="removeModal">삭제</button>
				</div>
			</c:if>
	
			<div class="mt-5">상세내용</div>
			<div>${itemdata.sel_content}</div>

			<div class="mt-5">경력</div>
				<div class="mt-5">
					<table class="table table-sm">
						<thead style="background-color: rgb(241, 241, 241);">
						  <tr>
						      <th class="col-4" style="text-align: center;">No.</th>
						      <th class="col-4" style="text-align: center;">Name</th>
						      <th class="col-auto" style="text-align: center;">Value</th>						      
						  </tr>
						</thead>
						<tbody class="mt-5">
						    <tr class="mt-5">
						      <td style="text-align: center;">nn회</td>
						      <td style="text-align: center;">리뷰~</td>
						      <td style="text-align: center;">n년</td>
						    </tr>
						 </tbody>
					</table>
				</div>
				
		<div class="modal fade" id="removeModal" tabindex="-1" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h6 class="modal-title">삭제 확인</h6>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						해당 데이터를 삭제하겠습니까?
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-sm btn-danger" data-bs-dismiss="modal" onclick="deleteitemdetail(${data})">확인</button>
					</div>
				</div>
			</div>
		</div>
				
				
		</section>
		</c:if>
	</body>
</html>