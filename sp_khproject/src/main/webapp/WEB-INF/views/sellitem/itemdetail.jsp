<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	</style>
</head>
	<body>
		<%@ include file="../module/head.jsp"%>
		<%--  <c:if test="${data.ac_index == 20}">--%>
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
				</div>
			</div>
			<c:if test="${data.ac_name == loginData.ac_name}">
				<div>
					<button type="button" onclick="location.href='${pageContext.request.contextPath}/itemmodify'"
						class="btn btn-outline-success">수정</button>
					<button type="button">삭제</button>
					<p>제목 : ${itemdata.sel_title}</p>
					<p>내용 : ${itemdata.sel_content}</p>
					<p>분야 : ${itemdata.sel_field}</p>
					<p>지역 : ${itemdata.sel_location}</p>
					<p>작성일자 : ${itemdata.sel_writeday}</p>
				</div>
			</c:if>
		</section>
		<%--  </c:if>--%>
	</body>
</html>