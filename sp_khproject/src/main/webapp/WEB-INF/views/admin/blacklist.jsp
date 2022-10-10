<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>       
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>블랙리스트 추가</title>
	<style>
		#previewImg {
			max-width : 250px;
			border-radius: 30%;
			height : auto;
		}
		
		.center {
			text-align : center;
		}
	</style>
</head>
<body>
	<%@ include file="../module/head.jsp" %>
	<section class="center">
		<img id="previewImg" class="image-360 mb-3" alt="프로필 이미지." src="../static/img/profile/${blackData.ac_email}.png" 
			onerror="this.onerror=null; this.src='../static/img/profile/basic.png'">
		<form action="./addBlacklist" method="post" enctype="multipart/form-data">
			<input type="hidden" name="blackId" value="${blackData.ac_email}">
			<input type="hidden" name="blackName" value="${blackData.ac_name}">
			<div class="mb-3">
				<p>회원 번호 : ${blackData.ac_number}</p>
				<p>이메일 : ${blackData.ac_email}</p>
				<p>닉네임 : ${blackData.ac_name}</p>
				<c:if test="${blackData.ac_index == 10}">
					<p>회원분류 : 일반회원</p>
				</c:if>
				<c:if test="${blackData.ac_index == 20}">
					<p>회원분류 : 판매자</p>
				</c:if>
			</div>
			<div>
				<button type="submit" class="btn btn-danger">블랙리스트 지정</button>
				<button type="button" class ="btn btn-secondary" onclick="location.href='../admin'">뒤로가기</button>
			</div>
		</form>
	</section>
	<%@ include file="../module/footer.jsp" %>
</body>

</html>