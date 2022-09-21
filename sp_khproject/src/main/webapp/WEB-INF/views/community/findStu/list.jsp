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
a:link {
	text-decoration: none;
	color: black;
	font-weight: bold;
	font-family: 'Noto Sans KR', sans-serif;
}

body {
	width: 1500px;
	margin: auto;
	font-family: 'Noto Sans KR', sans-serif;
}

#jb-container {
	width: 1500px;
	padding: 20px;
}

#jb-header {
	padding: 20px;
	margin-bottom: 20px;
}

#jb-content-head {
	width: 950px;
	height: 500px;
	padding: 20px;
	margin-bottom: 20px;
	float: left;
}

#jb-sidebar {
	width: 300px;
	height: 900px;
	padding: 20px;
	margin-bottom: 20px;
	float: left;
	padding: 20px;
}

#jb-content {
	width: 950px;
	padding: 20px;
	margin-bottom: 20px;
	float: left;
}

#jb-footer {
	width: 1500px;
	clear: both;
	padding: 10px;
	margin-top: 1200px;
	text-align: center;
	font-family: 'Noto Sans KR', sans-serif;
	clear: both;
}

@media ( max-width : 480px ) {
	#jb-container {
		width: auto;
	}
	#jb-content-head {
		float: none;
		width: auto;
	}
	#jb-sidebar {
		float: none;
		width: auto;
	}
	#jb-content {
		float: none;
		width: auto;
	}
}

.feed-item {
	padding: 1rem 0.5rem;
	border-bottom: 0.0625rem solid #f4f4f4;
	cursor: pointer;
}

ul li {
	list-style: none;
}

* {
	word-break: break-word;
}

*, :after, :before {
	box-sizing: border-box;
}

user agent stylesheet
li {
	display: list-item;
	text-align: -webkit-match-parent;
}

@media ( max-width : 991.98px) .feed-item[data-v-05dbd958] {
	padding
	
	
	
	
		
	
	
	
	
	:
	
	
	
	
	 
	
	
	
	
	1
	
	
	
	
	
	
	
	
	.25rem
	
	
	
	
	
	
	
	
	;
}

.feed-list {
	margin: 0;
}

ul {
	padding: 0;
}

dl, ol, ul {
	margin-top: 0;
}

address, dl, ol, ul {
	margin-bottom: 1rem;
}

* {
	word-break: break-word;
}

*, :after, :before {
	box-sizing: border-box;
}

user agent stylesheet
ul {
	display: block;
	list-style-type: disc;
	margin-block-start: 1em;
	margin-block-end: 1em;
	margin-inline-start: 0px;
	margin-inline-end: 0px;
	padding-inline-start: 40px;
}

feed-item .feed-content {
	display: flex;
	margin-bottom: 0.875rem;
}

* {
	word-break: break-word;
}

*, :after, :before {
	box-sizing: border-box;
}
</style>
</head>

<body>
	<div id="jb-container">
		<header>
			<!-- 로그인/회원가입/FAQ -->
			<div id="jb-header"
				style="text-align: right; margin-right: -30px; position: relative; top: 20px;">
				<c:if test="${empty loginData }">
					<a href="${pageContext.request.contextPath}/login">로그인</a>&emsp;/&emsp; 
			<a href="${pageContext.request.contextPath}/login/sign">회원가입</a>
					&emsp;&emsp;
				</c:if>
				<c:if test="${not empty loginData }">
			${loginData.ac_name }님 환영합니다!&emsp;/&emsp;
			<a href="${pageContext.request.contextPath}/main/logout">로그아웃&emsp;/&emsp;</a>
					<a href="${pageContext.request.contextPath}/info">마이페이지&emsp;&emsp;</a>
				</c:if>
			</div>



			<div style="text-align: center;">
				<a href="#" style="display: table; margin-top: -100px;"> <img
					src="../../static/img/logo.png"
					style="width: 300px; margin-bottom: -100px;" class="d-inline-block">
				</a>
			</div>
			<nav class="navbar navbar-expand-md navbar-light"
				style="margin-left: 1000px; position: relative;">
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
						<b>레슨자 찾아요</b>
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
					<form action="${findStuUrl}/home/community/findStu/add">
						<input type="hidden" name="qid" value="${data.findStu_id}">
						<button class="btn btn-outline-success" type="submit"
							style="width: 150px;" id="button" onclick="formCheck(this.form);">글쓰기</button>
					</form>
				</div>
			</div>
		</nav>

		<script type="text/javascript">
			$('.carousel').carousel()
		</script>

		<!-- 커뮤니티 메뉴 -->
		<aside id="jb-sidebar">
			<div>
				<a href="${pageContext.request.contextPath}/community/main"
					style="position: relative; top: 120px; left: 60px;">
					<button type="button" class="btn btn-outline-success"
						style="width: 130px; height: 50px;">전체</button>
				</a>
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/community/findPro/list"
					style="position: relative; top: 180px; left: 60px;">
					<button type="button" class="btn btn-outline-success"
						style="width: 130px; height: 50px;">전문가 찾아요</button>
				</a>
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/community/findStu/list"
					style="position: relative; top: 240px; left: 60px;">
					<button type="button" class="btn btn-outline-success"
						style="width: 130px; height: 50px;">레슨자 찾아요</button>
				</a>
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/community/question/list"
					style="position: relative; top: 300px; left: 60px;">
					<button type="button" class="btn btn-outline-success"
						style="width: 130px; height: 50px;">궁금해요</button>
				</a>
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/community/life/list"
					style="position: relative; top: 360px; left: 60px;">
					<button type="button" class="btn btn-outline-success"
						style="width: 130px; height: 50px;">일상</button>
				</a>
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/community/notice"
					style="position: relative; top: 420px; left: 60px;">
					<button type="button" class="btn btn-outline-success"
						style="width: 130px; height: 50px;">공지사항</button>
				</a>
			</div>
		</aside>

		<section>

			<div id="jb-content-head">

				<!-- 커뮤니티 키워드 검색 -->
				<nav class="navbar navbar-expand-md navbar-light">
					<div class="container"
						style="position: relative; top: 40px; left: 5px;">
						<button class="navbar-toggler" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<form class="d-flex">
								<input class="form-control me-4" type="search"
									placeholder="키워드를 입력해주세요!" aria-label="Search"
									style="min-width: 800px;">
								<div class="col-3">
									<div class="input-group">
										<button class="btn btn-secondary" type="submit">조회</button>
									</div>
								</div>
							</form>

						</div>
					</div>
				</nav>

					<table class="table table-hover mb-0">
						<colgroup>
							<col class="col-1">
							<col class="col-auto">
							<col class="col-2">
							<col class="col-1">
							<col class="col-1">
							<col class="col-2">
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>조회수</th>
								<th>추천수</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty datas}">
								<c:forEach items="${datas}" var="data">
									<c:url var="findStuDetailUrl"
										value="/community/findStu/detail">
										<c:param name="id">${data.findStu_Id}</c:param>
									</c:url>
									<tr onclick="location.href='${findStuDetailUrl}'">
										<td>${data.findStu_Id}</td>
										<td>${data.findStu_Title}</td>
										<td>${data.user_Name}</td>
										<td>${data.findStu_view}</td>
										<td>${data.findStu_like}</td>
										<td>${data.findStu_Date}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					<nav>
						<div>
							<ul class="pagination justify-content-center">
								<c:if test="${pageData.hasPrevPage()}">
									<li class="page-item"><a class="page-link"
										href="${findStuUrl}?page=${pageData.prevPageNumber}">Prev</a>
									</li>
								</c:if>
								<c:forEach
									items="${pageData.getPageNumberList(pageData.currentPageNumber - 2, pageData.currentPageNumber + 2)}"
									var="num">
									<li
										class="page-item ${pageData.currentPageNumber eq num ? 'active' : ''}">
										<a class="page-link" href="${findStuUrl}?page=${num}">${num}</a>
									</li>
								</c:forEach>
								<c:if test="${pageData.hasNextPage()}">
									<li class="page-item"><a class="page-link"
										href="${findStuUrl}?page=${pageData.nextPageNumber}">Next</a>
									</li>
								</c:if>
							</ul>
						</div>
					</nav>
				</section>

				


				<c:url var="mainurl" value="/main" />


				<footer id="jb-footer">
					<div
						style="width: 1500px; height: 200px; background-color: #f6f7f7; margin: auto; padding: 10px;
						">
						이용약관</div>
				</footer>
				<!-- go to top -->
				<a class="btn-top" href="#"><i class="xi-angle-up-thin"></i></a>
			</div>
</body>
</html>