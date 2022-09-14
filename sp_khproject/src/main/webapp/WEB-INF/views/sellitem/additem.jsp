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
	<%@ include file="../module/head.jsp" %>
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
	<form action="${addurl}" method="post">
		<div class="form-group row">
		서비스
			<form>
       			<select name = "service">
          		<option value = "It" selected>IT</option>
          		<option value = "Sports">운동</option>
          		<option value = "Lesson">음악</option>
          		<option value = "Etc">기타</option>
       			</select>
    		</form>
		</div>
		<div class="form-group row">
		지역
			<form>
       			<select name = "location">
          		<option value = "Seoul" selected>서울</option>
          		<option value = "Gyeonggi">경기</option>
          		<option value = "Busan">부산</option>
          		<option value = "Etc">기타</option>
       			</select>
    		</form>
		</div>
		<div class="form-group row">
		내용추가 / 삭제
		</div>
		<div class="form-group row">
		상품상세설명
		<div>
			<textarea name="description" cols="50" placeholder="내용을 입력해주세요." rows="5" class="form-control"></textarea>
		</div>
		</div>
		<div class="form-group row">
		사진첨부
		</div>
		<div class="form-group row">
		<button type="submit"  class="form-control">등록</button>
		</div>
	</form>
	</div>
	</section>
</body>
</html>