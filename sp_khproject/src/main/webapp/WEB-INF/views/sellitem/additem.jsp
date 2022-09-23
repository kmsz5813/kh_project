<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품 등록</title>
	<c:url var="bs5" value="/static/bs5" />
	<c:url var="jQuery" value="/static/js" />
	<link rel="stylesheet" type="text/css" href="${bs5}/css/bootstrap.min.css">
	<c:url var="ckeditor" value="/static/ckeditor" />
	<script type="text/javascript" src="${ckeditor}/ckeditor.js"></script>
	<style>
	#previewImg {
			margin-bottom : 20px;
			border-radius: 30%;
			max-width : 250px;
			height : auto;
		}
	</style>
</head>
<script type="text/javascript">

</script>
<body>
	<%@ include file="../module/head.jsp" %>
  <input type="file" class="real-upload" accept="image/*" required multiple style="display: none;">
  <div class="upload"></div>
  <script>
    const realUpload = document.querySelector('.real-upload');
    const upload = document.querySelector('.upload');

    upload.addEventListener('click', () => realUpload.click());
  </script>

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-10">
				상품 등록
			</h1>
		</div>
	</div>
	
	<header></header>
	<section class="container w-75">
	<div class="mt-5" >
	<c:url var="addurl" value="/sellitem/additem" />
	<form id="fileForm" action="${addurl}" method="post" enctype="multipart/form-data">
			
			<div class="image-form mb-3">
					<!-- 여기 url은 home/ 뒤에 바로 modify 가 아니라 info/ 가 붙으므로 contextPaht 경로를 앞에 붙여야 한다.  -->
					<div style="float:left;">
					<img id="previewImg" class="image-360" alt="프로필 이미지." src="${pageContext.request.contextPath}/static/img/profile/${profileImage}.png"
					onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/static/img/profile/logo.png'"> 
					<div style="float:right; margin-left:4rem; color:green;">
					<p>-필수입력사항입니다
					<p>-기본적으로 보여지는 상품이미지를 등록합니다
					<p>-대표이미지 등록시 축소 이미지로 자동해서 들어갑니다.
					<p>-권장이미지 250 x 250
					</div>
					</div>
					<div class="mb-3">
						<input id="formFile" type="file" name="fileUpload" class="form-control" value="이미지 선택" accept="image/png">
					</div>
			
			</div>
		
			<div class="mb-3">
				<label class="col-sm-2 control-label">제목</label>
				<div class="mt-3">
					<input class="form-control" type="text" name="title" id="title" placeholder="제목을 입력하세요.">
				</div>
			</div>
	
		    <div class="mb-3">
		       <label class="col-sm-2 control-label">서비스</label>
		       <div class="mt-3">
			       <select class="form-select" name="field">
						<option selected value="무관">무관</option>
						
						<c:forEach var="name" items="${Option}" >
						<option value="${name}">${name}</option>
						</c:forEach>
			       	</select>
		       </div>
		    </div>
		    <div class="mb-3">
		        <label class="col-sm-2 control-label">지역</label>
		        <div class="mt-3">
			        <select class="form-select" name="location">
							<option selected value="무관">무관</option>
							<c:forEach var="lc" items="${lc}" >
							<option value="${lc}">${lc}</option>
							</c:forEach>
			        </select>
		     	</div>
		     </div>
		    <div class="mb-3">
				<label class="col-sm-2 control-label">가격</label>
				<div class="mt-3">
					<input class="form-control" type="text" name="price" placeholder="가격을 입력하세요.">
				</div>
			</div> 
	
			<div class="mt-3 mb-3">
				<p>상품상세설명</p>
				<textarea class="form-control" id="content" name="content" rows="10"
					placeholder="내용을 입력하세요."></textarea>
			</div>

		
			<div class="form-group row">
				<button type="submit" class="form-control" onclick="imageUpload()">등록</button>
			</div>
		</form>
	</div>
	</section>

	<footer></footer>
	<c:url var="upload" value="/upload/image" />
		<script type="text/javascript">
			CKEDITOR.replace("content", {
				filebrowserUploadUrl: "${upload}?type=image"
			})
			
			window.onload = function() {
				previewImg.addEventListener("click", function(e) {
					formFile.click();
				});
				
				formFile.addEventListener("change", showImagePreview);
			}
			
			var finalcheck = false;
			
			function showImagePreview(e) {
				var file = e.target.files[0];
				var imgUrl = URL.createObjectURL(file);
				previewImg.src = imgUrl;
			}
			
			   $('form').on('submit', function(e) {
					
					
					if($('#title').val() == ''){
					   e.preventDefault();
			           swal('등록 오류!', "제목을 입력해주세요!.", 'warning');
					}
					
					if($('#content').val() == ''){
					   e.preventDefault();
			           swal('등록 오류!', "내용을 입력해주세요!.", 'warning');
				
					}
					
					if($('#formFile').val() == ''){
					   e.preventDefault();
			           swal('등록 오류!', "상품이미지를 입력해주세요!.", 'warning');
						
					}

		        });
			
			
		</script>

	
</body>
</html>