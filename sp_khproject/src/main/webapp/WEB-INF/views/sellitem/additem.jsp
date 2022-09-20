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
	<%@ include file="../module/head.jsp" %>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
	
</head>
<script type="text/javascript">
	function formCheck(form) {
		if(form.title.value === undefined || form.title.value.trim() === "") {
			var modal = new bootstrap.Modal(document.getElementById("errorModal"), {
				keyboard: false
			});
			modal.show();
			return;
		}
		form.submit();
	}
	
	function uploadCheck(element) {
		var files = element.files;
		
		var modal = new bootstrap.Modal(document.getElementById("errorModal"), {
			keyboard: false
		});
		var title = modal._element.querySelector(".modal-title");
		var body = modal._element.querySelector(".modal-body");
		
		if(files.length > 1) {
			title.innerText = "파일 업로드 오류";
			body.innerText = "파일 업로드는 최대 1개로 제한되어 있습니다.";
			modal.show();
			element.value = "";
			return;
		}
		
		for(file of files) {
			if(file.size / 1000 / 1000 > 5.0) {
				title.innerText = "파일 업로드 오류";
				body.innerText = "파일당 최대 5MB 까지만 업로드 할 수 있습니다. 5MB 초과 용량에 대해서는 관리자에게 문의하세요.";
				modal.show();
				element.value = "";
				return;
			}
		}
	}
</script>
<body>
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
			<div class="form-group">
				<label class="col-sm-2 control-label">제목</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" name="title" placeholder="제목을 입력하세요.">
				</div>
			</div>
		    <div class="form-group">
		       <label class="col-sm-2 control-label">서비스</label>
		       <div class="col-sm-10">
			       <select class="form-control" name="field">
						<option selected value="무관">-선택-</option>
						<option value="IT">IT</option>
						<option value="레슨">레슨</option>
						<option value="미용">미용</option>
						<option value="국영수">국영수</option>
						<option value="기타">기타</option>
			       	</select>
		       </div>
		    </div>
		    <div class="form-group">
		        <label class="col-sm-2 control-label">지역</label>
		        <div class="col-sm-10">
			        <select class="form-control" name="location">
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
			<div class="form-group row">
				상품상세설명
				<div>
					<textarea name="description" cols="50" placeholder="내용을 입력해주세요." rows="5" class="form-control"></textarea>
				</div>
			</div>
			<div class="form-group row">
				<input name="file" type="file" class="real-upload" accept="image/png" multiple>
			</div>
			<div class="form-group row">
				<button type="submit" class="form-control" onclick="imageUpload()">등록</button>
			</div>
		</form>
	</div>
	</section>

	<footer></footer>
	<c:url var="upload" value="/upload/image" />
	<c:if test="${not empty error}">
		<script type="text/javascript">
			alert("${error}");
		</script>
	</c:if>
</body>
</html>