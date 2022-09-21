<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FindU</title>
	<%@ include file="../../module/head.jsp" %>
	<c:url var="ckeditor" value="/static/ckeditor" />
	<script type="text/javascript" src="${ckeditor}/ckeditor.js"></script>
</head>
<script type="text/javascript">
	function formCheck(form) {
		console.log(form)
		console.log(form.question_title.value)
		console.log(form.question_title.value.trim())
		console.log(form.question_title.value === undefined)
		console.log(form.question_title.value.trim() === "")
		if(form.question_title.value === undefined || form.question_title.value.trim() === "") {
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
		var question_title = modal._element.querySelector(".modal-title");
		var question_body = modal._element.querySelector(".modal-body");
		
		if(files.length > 3) {
			question_Title.innerText = "파일 업로드 오류";
			question_Body.innerText = "파일 업로드는 최대 3개로 제한되어 있습니다.";
			modal.show();
			element.value = "";
			return;
		}
		
		for(file of files) {
			if(file.size / 1000 / 1000 > 5.0) {
				question_Title.innerText = "파일 업로드 오류";
				question_Body.innerText = "파일당 최대 5MB 까지만 업로드 할 수 있습니다. 5MB 초과 용량에 대해서는 관리자에게 문의하세요.";
				modal.show();
				element.value = "";
				return;
			}
		}
	}
</script>
<body>
	<header></header>
	<section class="container">
		<div class="mt-3">
			<c:url var="questionModifyUrl" value="/community/question/modify" />
			<form action="${questionModifyUrl}" method="post">
				<input type="hidden" name="question_id" value="${data.question_Id}">
				<div class="mb-3">
					<input class="form-control" type="text" name="question_title" value="${data.question_Title}" placeholder="제목을 입력하세요.">
				</div>
				<div class="mb-3">
					<textarea class="form-control" name="question_content" rows="8"
						placeholder="내용을 입력하세요.">${data.question_Content}</textarea>
				</div>
				<!-- 
				<div class="mb-3">
					<ul class="list-group">
					<c:forEach items="${fileDatas}" var="file">
						<c:url var="downUrl" value="${file.url}/${file.uuidName}" />
						<li class="list-group-item">
							<button class="btn btn-sm btn-danger">삭제</button>
							<a class="text-info text-decoration-none" href="${downUrl}" download="${file.fileName}">${file.fileName}</a>
						</li>
					</c:forEach>
					</ul>
				</div>
				-->
				<div class="mb-3">
					<input class="form-control" type="file" onchange="uploadCheck(this);" name="fileUpload" multiple>
				</div>
				<div class="mb-3 text-end">
					<button class="btn btn-primary" type="button" onclick="formCheck(this.form);">저장</button>
				</div>
			</form>
		</div>
		
		<div class="modal fade" id="errorModal" tabindex="-1" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h6 class="modal-title">입력 오류</h6>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						제목은 공란을 입력할 수 없습니다.<br>
						반드시 제목을 입력하세요.
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-sm btn-danger" data-bs-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<footer></footer>
	<!-- 
	<c:url var="upload" value="/upload/image" />
	<script type="text/javascript">
		CKEDITOR.replace("content", {
			filebrowserUploadUrl: "${upload}?type=image"
		})
	</script>
	 -->
</body>
</html>