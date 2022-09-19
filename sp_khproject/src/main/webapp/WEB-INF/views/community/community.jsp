<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>want</title>
<!-- bootstrap css 적용 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<!-- jQuery 적용 -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!-- ajax 적용 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<!-- bootstrap js 적용 -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<!-- 폰트 적용 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">

<!-- style, css -->
<style>
footer {
	font-family: 'Noto Sans KR', sans-serif;
}

.carousel-item {
	width: 1500px;
	height: 500px;
}

body {
	width: 1500px;
	margin: auto;
}
</style>

</head>

<body>
	<header>
		<!-- 로그인/회원가입/FAQ -->
		<div
			style="text-align: right; margin-right: 30px; position: relative; top: 20px;">
			<c:if test="${empty loginData }">
				<a href="${pageContext.request.contextPath}/login">로그인</a>&emsp;/&emsp; 
			<a href="${pageContext.request.contextPath}/login/sign">회원가입</a>
				&emsp;&emsp;
			</c:if>
			<c:if test="${not empty loginData }">
			${loginData.ac_name }님 환영합니다!&emsp;/&emsp;
			<a href="main/logout">로그아웃&emsp;/&emsp;</a>
				<a href="${pageContext.request.contextPath}/info">마이페이지&emsp;&emsp;</a>
			</c:if>
		</div>

		<!-- nav-bar -->
		<script type="text/javascript">
			$('.carousel').carousel()
		</script>
		<div style="text-align: center;">
			<a href="#" style="display: table; margin-top: -100px;"> <img
				src="static/img/logo.png"
				style="width: 300px; margin-bottom: -100px;" class="d-inline-block">
			</a>
		</div>
		<nav class="navbar navbar-expand-md navbar-light"
			style="margin-left: 1000px; position: relative; bottom: 50px;">
			<div class="container">
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<form class="d-flex">
						<input class="form-control me-4" type="search"
							placeholder="어떤 서비스가 필요하세요?" aria-label="Search"
							style="min-width: 300px;">
						<button class="btn btn-outline-success" type="submit"
							style="width: 100px" id="button">검색</button>
					</form>
				</div>
			</div>
		</nav>
	</header>

	<div class="container-xl">
		<div class="row">
			<div class="col-md-12" style="position: relative; top: 100px;">
				<h2>
					<b>커뮤니티</b>
				</h2>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$('.carousel').carousel()
	</script>
	<nav class="navbar navbar-expand-md navbar-light"
		style="margin-left: 1273px; position: relative; top: 50px;">
		<div class="container">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<form class="d-flex">
					<button class="btn btn-outline-success" type="submit"
						style="width: 150px;" id="button">글쓰기</button>
				</form>
			</div>
		</div>
	</nav>

	<script type="text/javascript">
		$('.carousel').carousel()
	</script>
	<nav class="navbar navbar-expand-md navbar-light"
		style="margin-left: 500px; position: relative; top: 100px;">
		<div class="container">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<form class="d-flex">
					<input class="form-control me-4" type="search"
						placeholder="키워드를 입력해주세요!" aria-label="Search"
						style="min-width: 500px;">
				</form>
			</div>
		</div>
	</nav>

	<!-- 커뮤니티 메뉴 -->
	<div>
	<section>
		<a href="#" style="position: relative; top: 200px; left: 100px;">
			<button type="button" class="btn btn-outline-success"
				style="width: 130px; height: 50px;">전체</button>
		</a>
	</section>
	<section>
		<a href="#" style="position: relative; top: 250px; left: 100px;">
			<button type="button" class="btn btn-outline-success"
				style="width: 130px; height: 50px;">전문가 찾아요</button>
		</a>
	</section>
	<section>
		<a href="#" style="position: relative; top: 300px; left: 100px;">
			<button type="button" class="btn btn-outline-success"
				style="width: 130px; height: 50px;">레슨자 찾아요</button>
		</a>
	</section>
	<section>
		<a href="#" style="position: relative; top: 350px; left: 100px;">
			<button type="button" class="btn btn-outline-success"
				style="width: 130px; height: 50px;">궁금해요</button>
		</a>
	</section>
	<section>
		<a href="#" style="position: relative; top: 400px; left: 100px;">
			<button type="button" class="btn btn-outline-success"
				style="width: 130px; height: 50px;">일상</button>
		</a>
	</section>
	<section>
		<a href="#" style="position: relative; top: 450px; left: 100px;">
			<button type="button" class="btn btn-outline-success"
				style="width: 130px; height: 50px;">공지사항</button>
		</a>
	</section>
	</div>

	<!-- 커뮤니티 HOT -->
	<div>
		<span
			style="font-weight: bold; font-size: 20px; margin-left: 515px; position: relative; top: 150px;">
			커뮤니티 HOT <img src="static/img/fire.png"
			style="width: 20px; position: relative; bottom: 2px;">
		</span>

		<section>
			<a href="#" style="position: relative; top: 180px; left: 500px;">
				<button type="button" class="btn btn-outline-success"
					style="width: 200px; height: 200px;">전체</button>
			</a>
		</section>

	</div>




	


	<c:url var="mainurl" value="/main" />

	<footer class="footer"
		style="margin-top: 1200px; text-align: center; border-top: 3px solid #dcdcde;">
		<div
			style="width: 1500px; height: 200px; background-color: #f6f7f7; margin: auto; margin-bottom: 50px; margin-top: 20px; padding: 10px;">
			이용약관</div>
	</footer>
	<!-- go to top -->
	<a class="btn-top" href="#"><i class="xi-angle-up-thin"></i></a>

</body>
</html>