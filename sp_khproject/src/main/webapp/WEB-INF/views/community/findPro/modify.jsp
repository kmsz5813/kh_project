<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<c:url var="ckeditor" value="/static/ckeditor" />
<script type="text/javascript" src="${ckeditor}/ckeditor.js"></script>
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
	padding :1 .25rem;
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
<script type="text/javascript">
	function formCheck(form) {
		if (form.findPro_title.value === undefined
				|| form.findPro_title.value.trim() === "") {
			var modal = new bootstrap.Modal(document
					.getElementById("errorModal"), {
				keyboard : false
			});
			modal.show();
			return;
		}
		form.submit();
	}
	
</script>
<body>
	<div id="jb-container">
		<%@ include file="../../module/head.jsp" %>


		<div class="container-xl">
			<div class="row">
				<div class="col-md-12" style="position: relative; top: 100px;">
					<h2>
						<b>전문가 찾아요</b>
					</h2>
				</div>
			</div>
		</div>
		
			<div class="container">
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
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
				
					<div class="container"
						style="position: relative; top: 40px; left: 5px;">
						<button class="navbar-toggler" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>

					</div>
				<!-- 커뮤니티 메인 새글 -->
				<article>
					<div id="jb-content">
						<ul class="feed-list">
							<!-- 본문 -->
							<div class="feed-content">
								<div>


									<section class="container">
										<div class="mt-3">
											<c:url var="findProModifyUrl"
												value="/community/findPro/modify" />
											<form action="${findProModifyUrl}" method="post">
												<input type="hidden" name="findPro_id"
													value="${data.findPro_Id}">
												<div class="mb-3">
													<input class="form-control" type="text"
														name="findPro_title" value="${data.findPro_Title}"
														placeholder="제목을 입력하세요.">
												</div>
												<div class="mb-3">
													<textarea class="form-control" name="findPro_content"
														rows="8" placeholder="내용을 입력하세요.">${data.findPro_Content}</textarea>
												</div>			
									<div class="mb-3 text-end">
													<button class="btn btn-primary" type="button"
														onclick="formCheck(this.form);">저장</button>
												</div>

											</form>
										</div>

										<div class="modal fade" id="errorModal" tabindex="-1"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h6 class="modal-title">입력 오류</h6>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body">
														제목은 공란을 입력할 수 없습니다.<br> 반드시 제목을 입력하세요.
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-sm btn-danger"
															data-bs-dismiss="modal">확인</button>
													</div>
												</div>
											</div>
										</div>
									</section>


								</div>
							</div>
							<!--end table-responsive-->
						</ul>
					</div>
				</article>

			</div>
		</section>

					<c:url var="mainurl" value="/main" />


				<footer id="jb-footer">
					<div
						style="width: 1500px; height: 200px; background-color: #f6f7f7; margin: auto; padding: 10px;
						position: relative; left: 50px;">
						이용약관</div>
				</footer>
				<script type="text/javascript">
			CKEDITOR.replace("findPro_content", {
				filebrowserUploadUrl : "${upload}?type=image"
			})
		</script>
				<!-- go to top -->
				<a class="btn-top" href="#"><i class="xi-angle-up-thin"></i></a>
			</div>
</body>
</html>