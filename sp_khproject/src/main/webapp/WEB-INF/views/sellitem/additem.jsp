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
					<div style="float:right; margin-top:4rem; color:green;">
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
					<input class="form-control" type="text" name="title" placeholder="제목을 입력하세요.">
				</div>
			</div>
		    <div class="mb-3">
		       <label class="col-sm-2 control-label">서비스</label>
		       <div class="mt-3">
			       <select class="form-select" name="field">
						<option selected value="무관">-선택-</option>
						<option value="IT">IT</option>
						<option value="레슨">레슨</option>
						<option value="미용">미용</option>
						<option value="국영수">국영수</option>
						<option value="기타">기타</option>
			       	</select>
		       </div>
		    </div>
		    <div class="mb-3">
		        <label class="col-sm-2 control-label">지역</label>
		        <div class="mt-3">
			        <select class="form-select" name="location">
							<option selected value="무관">-선택-</option>
							<option value="서울">서울</option>
							<option value="경기">경기</option>
							<option value="부산">부산</option>
							<option value="대구">대구</option>
							<option value="인천">인천</option>
							<option value="대전">대전</option>
							<option value="울산">울산</option>
							<option value="광주">광주</option>
							<option value="세종">세종</option>
							<option value="경남">강원</option>
							<option value="경북">경북</option>
							<option value="경남">경남</option>
							<option value="충북">충북</option>
							<option value="충남">충남</option>
							<option value="전북">전북</option>
							<option value="전남">전남</option>
							<option value="제주">제주</option>
			        </select>
		     	</div>
		     </div>
		    <div class="form-group">
				<label class="col-sm-2 control-label">가격</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" name="price" placeholder="가격을 입력하세요.">
				</div>
			</div> 
			<div class="form-group row">
				<!-- 
				상품상세설명
				<div>
					<textarea name="description" cols="50" placeholder="내용을 입력해주세요." rows="5" class="form-control"></textarea>
				</div>
				 -->
			</div>
	
			<div class="mb-3">
				<p>상품상세설명</p>
				<textarea class="form-control" id="content" name="content" rows="10"
					placeholder="내용을 입력하세요."></textarea>
			</div>
			<!-- 
			<div class="mb-3">
				<input class="form-control" type="file" onchange="uploadCheck(this);" name="fileUpload" multiple>
			</div>
			 -->
		
			<div class="form-group row">
				<button type="submit" class="form-control" onclick="imageUpload()">등록</button>
			</div>
		</form>
	</div>
	</section>

	<%@ include file="../module/footer.jsp" %>
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
			
		</script>

	
</body>
</html>