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

.table th {
	font-weight: 500;
	color: #777777;
	font-weight: bold;
}

.table thead {
	background-color: #fff;
}

.table thead th:first-child {
	padding: 0 30px 8px;
}

.table tbody tr td:first-child {
	padding: 0 30px;
}

.table>tbody>tr>td, .table>thead>tr>td {
	padding: 14px 12px;
	vertical-align: middle;
}

.table tr td {
	color: #555555;
}

.table tbody td a {
	color: #555555;
	padding: 13px 0;
}

.table tbody td a:hover {
	color: #17B794;
}

.pagingstyle {
	width: 50%;
	margin: 0 auto;
}

#searchbox {
	float: right;
	width: 270px;
	margin: 0 auto;
}

#row {
	position: relative;
	margin-top: 10px;
}

#form-control {
	width: 150px;
	position: absolute;
	right: 18%;
	height: 30px;
}
</style>
</head>
<body>
	<div id="jb-container">
		<%@ include file="../../module/head.jsp"%>
		<div class="container-xl">
			<div class="row">
				<div class="col-md-12" style="position: relative; top: 100px;">
					<h2>
						<b>공지사항</b>
					</h2>
				</div>
			</div>
		</div>
		
		
		<c:if test="${loginData.ac_index == 30}">
			<c:url var="noticeUrl" value="/admin/notice/write" />
			<form action="${noticeUrl}" method="post">
			<input type="hidden" name="nid" value="${data.notice_no}">
			<button class="btn btn-outline-success" type="submit"
				style="width: 150px; position: relative; top: 45px; left: 1200px;"
				id="button">글쓰기</button>
			</form>
		</c:if>
		
	
	
		
		

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
				<form class="d-flex"
					style="position: relative; top: 100px; left: 30px;">
					<input class="form-control me-4" type="search"
						placeholder="키워드를 입력해주세요!" aria-label="Search"
						style="min-width: 800px;">
					<div class="col-3">
						<div class="input-group">
							<button class="btn btn-secondary" type="submit">조회</button>
						</div>
					</div>
				</form>

				<!-- 커뮤니티 메인 새글 -->
				<article>
					<div id="jb-content">
						<ul class="feed-list">
							<!-- 본문 -->

							<div class="feed-content">
								<div>
									<section class="container">
										<div class="mb-1" style="position: relative; top: 150px; left: -30px;">
											<table class="table table-hover mb-0">
												<thead>
													<tr class="align-self-center">
														<th style="width: 25%;">No.</th>
														<th style="width: 60%;">Title.</th>
														<th style="width: 15%;">Date.</th>
													</tr>
												</thead>
												<tbody>
													<!--  윤지님이 만드신 부분 
													<c:if test="${empty notice}">
														<tr>
															<td></td>
															<td style="text-align: center;">공지사항이 없습니다</td>
															<td></td>
														</tr>
													</c:if>
													<c:if test="${not empty notice}">
														<c:forEach items="${notice }" var="n">
															<tr>
																<td>${n.notice_no }</td>
																<td><a
																	href="${pageContext.request.contextPath}/community/notice/detail?no=${n.notice_no }">${n.notice_title }</a></td>
																<td>${n.notice_date }</td>
															</tr>
														</c:forEach>
													</c:if>
													 -->
													<c:if test="${empty result}">
														<tr>
															<td></td>
															<td style="text-align: center;">공지사항이 없습니다</td>
															<td></td>
														</tr>
													</c:if>
													<c:if test="${not empty result}">
														<c:forEach items="${result }" var="data">
															<tr>
																<td>${data.notice_no }</td>
																<td><a
																	href="${pageContext.request.contextPath}/community/notice/detail?no=${data.notice_no }">${data.notice_title }</a></td>
																<td>${data.notice_date }</td>
															</tr>
														</c:forEach>
													</c:if>
													<!-- 목차부분 -->


												</tbody>
											</table>
											</div>
											<!-- 네비게이션 부분 -->
											<nav>
												<c:url var="itemUrl" value="/community/notice" />
												<div class="mt-5">
													<ul class="pagination justify-content-center" style="position: relative; top: 200px; left: -40px;">
														<c:if test="${pageData.hasPrevPage()}">
															<li class="page-item"><a class="page-link"
																href="${itemUrl}?page=${pageData.prevPageNumber}"><</a>
															</li>
														</c:if>
														<c:forEach
															items="${pageData.getPageNumberList(pageData.currentPageNumber - 2, pageData.currentPageNumber + 2)}"
															var="num">
															<li
																class="page-item ${pageData.currentPageNumber eq num ? 'active' : ''}">
																<a class="page-link" href="${itemUrl}?page=${num}">${num}</a>
															</li>
														</c:forEach>
														<c:if test="${pageData.hasNextPage()}">
															<li class="page-item"><a class="page-link"
																href="${itemUrl}?page=${pageData.nextPageNumber}">></a>
															</li>
														</c:if>
													</ul>

												</div>
											</nav>
									</section>
								</div>

							</div>
							<!--end table-responsive-->
						</ul>
					</div>
				</article>
		</section>
		<c:url var="mainurl" value="/main" />
		<footer id="jb-footer">
			<div
				style="width: 1500px; height: 200px; background-color: #f6f7f7; margin: auto; padding: 10px; position: relative; left: 50px">
				이용약관</div>
		</footer>
		<!-- go to top -->
		<a class="btn-top" href="#"><i class="xi-angle-up-thin"></i></a>
	</div>
</body>
</html>
