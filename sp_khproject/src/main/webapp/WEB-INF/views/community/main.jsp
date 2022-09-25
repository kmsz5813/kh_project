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
		<%@ include file="../module/head.jsp"%>

		<div class="container-xl">
			<div class="row">
				<div class="col-md-12" style="position: relative; top: 100px;">
					<h2>
						<b>커뮤니티</b>
					</h2>
				</div>
			</div>
		</div>

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
				<a href="${pageContext.request.contextPath}/community/notice/list"
					style="position: relative; top: 420px; left: 60px;">
					<button type="button" class="btn btn-outline-success"
						style="width: 130px; height: 50px;">공지사항</button>
				</a>
			</div>
		</aside>

		<section>

			<div id="jb-content-head">

				<!-- 커뮤니티 키워드 검색 -->
				<div>
					<form class="d-flex"
						style="position: relative; top: 80px; left: 50px;">
						<input class="form-control me-4" type="search"
							placeholder="키워드를 입력해주세요!" aria-label="Search"
							style="min-width: 725px;">
						<div class="col-3">
							<div class="input-group">
								<button class="btn btn-secondary" type="submit">조회</button>
							</div>
						</div>
					</form>

				</div>

				<!-- 커뮤니티 HOT -->
				<div style="position: relative; top: 70px;">
					<div
						style="font-weight: bold; font-size: 20px; position: relative; top: 70px; left: 50px;">
						<h2>커뮤니티 HOT🔥</h2>
					</div>
					<div>
						<a href="${pageContext.request.contextPath}/community/notice/list"
							data-testid="curation-item" tabIndex="-1"
							style="width: 100%; display: block;"> <span
							style="position: relative; top: 120px; left: 50px;">
								<button type="button" class="btn btn-outline-success"
									style="width: 200px; height: 200px;">
									<p style="font-weight: bold; font-size: 25px;">공지사항</p>
									<h3 style="font-weight: bold; font-size: 15px;">커뮤니티 사용법✏️</h3>
								</button>
						</span>
						</a>
					</div>
					<div>
						<a href="${pageContext.request.contextPath}/community/question/list" data-testid="curation-item" tabIndex="-1"
							style="width: 100%; display: inline-block;"> <span
							style="position: relative; top: -80px; left: 350px;">
								<button type="button" class="btn btn-outline-success"
									style="width: 200px; height: 200px;">
									<p style="font-weight: bold; font-size: 25px;">궁금해요</p>
									<h3 style="font-weight: bold; font-size: 15px;"></h3>
								</button>
						</span>
						</a>
					</div>
					<div>
						<a href="${pageContext.request.contextPath}/community/life/list" data-testid="curation-item" tabIndex="-1"
							style="width: 100%; display: inline-block;"> <span
							style="position: relative; top: -280px; left: 650px;">
								<button type="button" class="btn btn-outline-success"
									style="width: 200px; height: 200px;">
									<p style="font-weight: bold; font-size: 25px;">일상</p>
									<h3 style="font-weight: bold; font-size: 15px;"></h3>
								</button>
						</span>
						</a>
					</div>
				</div>
			</div>
		</section>

		<!-- 커뮤니티 메인 새글 -->

		<article>
			<div id="jb-content">
				<ul class="feed-list">
					<li class="feed-item"><a href="#"
						data-testid="soomgo-life-feed-item"> <span>게시글 1</span>
							<div class="feed-content">
								<div>
									<section>
										<h3>게시글 1</h3>
										<p>게시글 1</p>
									</section>
									<ul class="tag-list">
										<li>게시글 1</li>
										<li>게시글 1</li>
										<li>게시글 1</li>
									</ul>
								</div>
							</div>
							<div>
								<div>
									<span class="like">0</span> <span class="comment">0</span>
								</div>
								<span class="sg-text-description">6분 전</span>
							</div>
					</a></li>

				</ul>
			</div>
		</article>






		<c:url var="mainurl" value="/main" />


		<footer id="jb-footer">
			<div
				style="width: 1500px; height: 200px; background-color: #f6f7f7; margin: auto; padding: 10px;">
				이용약관</div>
		</footer>
		<!-- go to top -->
		<a class="btn-top" href="#"><i class="xi-angle-up-thin"></i></a>
	</div>




</body>
</html>