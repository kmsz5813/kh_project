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

@media ( max-width : 991.98px) .feed-item {
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
<body>
	<div id="jb-container">
		<%@ include file="../../module/head.jsp" %>


		<div class="container-xl">
			<div class="row">
				<div class="col-md-12" style="position: relative; top: 100px;">
					<h2>
						<b>궁금해요</b>
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

				
				<!-- 커뮤니티 메인 새글 -->
				<article>
					<div id="jb-content">
						<ul class="feed-list">
							<!-- 본문 -->
							<div class="feed-content" style="position: relative; top: 50px;">
								<div>



									<section class="container">
										<div class="mt-3">
											<div class="mb-1 border-bottom border-2 border-secondary">
												<h1>${data.question_Title}</h1>
											</div>
											<div class="mb-3">
												<label class="pe-3 text-secondary text-opacity-75">${data.user_Name}</label>
												<fmt:formatDate value="${data.question_Date}"
													var="question_Date" dateStyle="long" />
												<label class="pe-3 text-secondary text-opacity-75">${data.question_Date}</label>
												<label class="pe-3 text-secondary text-opacity-75">조회수:
													${data.question_view}</label>
											</div>
											<div class="mb-1 border-bottom border-2 border-secondary">
												<p>${data.question_Content}</p>
											</div>
											<div class="mb-1">
												<div onclick="ajaxLike(id_like, ${data.question_Id});">
													<i
														class="bi bi-hand-thumbs-up text-secondary text-opacity-50"></i>
													<label id="id_like" class="text-secondary text-opacity-75">${data.question_like}</label>
												</div>
											</div>
			<!-- 								
			<div class="row mb-1">
				<ul class="col-4 ms-auto list-group">
				<c:forEach items="${fileDatas}" var="file">
					<c:url var="downUrl" value="${file.url}/${file.uuidName}" />
					<li class="list-group-item text-truncate">
						<a class="text-info text-decoration-none" href="${downUrl}" download="${file.fileName}">${file.fileName}</a>
					</li>
				</c:forEach>
				</ul>
			</div>
			 --> 
											<div class="mb-1 text-end">
												<c:url var="questionUrl" value="/community/question" />
												<button class="btn btn-primary" type="button"
													onclick="location.href='${questionUrl}/list'">목록</button>
												<c:if
													test="${data.user_Name eq sessionScope.loginData.ac_name}">
													<button class="btn btn-success" type="button"
														onclick="location.href='${questionUrl}/modify?id=${data.question_Id}'">수정</button>
													<button class="btn btn-danger" type="button"
														data-bs-toggle="modal" data-bs-target="#removeModal">삭제</button>
												</c:if>
											</div>
										</div>

										<nav>
											<div>
												<ul class="pagination justify-content-center">
													<c:url var="questionDetailUrl"
														value="/community/question/detail">
														<c:param name="id">${data.question_Id}</c:param>
													</c:url>
													<c:if test="${commentPage.hasPrevPage()}">
														<li class="page-item"><a class="page-link"
															href="${questionDetailUrl}&page=${commentPage.prevPageNumber}"><</a>
														</li>
													</c:if>
													<c:forEach
														items="${commentPage.getPageNumberList(commentPage.currentPageNumber - 2, commentPage.currentPageNumber + 2)}"
														var="num">
														<li
															class="page-item ${commentPage.currentPageNumber eq num ? 'active' : ''}">
															<a class="page-link"
															href="${questionDetailUrl}&page=${num}">${num}</a>
														</li>
													</c:forEach>
													<c:if test="${commentPage.hasNextPage()}">
														<li class="page-item"><a class="page-link"
															href="${questionDetailUrl}&page=${commentPage.nextPageNumber}">></a>
														</li>
													</c:if>
												</ul>
											</div>
										</nav>

										<div class="mt-3 mb-3">
											<c:forEach items="${commentPage.pageData}" var="comment">
												<div class="mb-1">
													<div class="card border-light">
														<div class="card-header">
															<div class="d-flex justify-content-between">
																<span><small>${comment.user_Name}</small></span> <span><small>${comment.comment_Date}</small></span>
															</div>
														</div>
														<div class="card-body">
															<input type="hidden" value="${comment.comment_Id}">
															<c:choose>
																<c:when test="${comment.comment_isDeleted()}">
																	<p class="text-muted">삭제된 댓글 입니다.</p>
																</c:when>
																<c:otherwise>
																	<c:set var="newLine" value="<%=\"\n\"%>" />
																	<p class="card-text">${fn:replace(comment.comment_Content, newLine, '<br>')}</p>
																</c:otherwise>
															</c:choose>
															<c:if
																test="${sessionScope.loginData.ac_name eq comment.user_Name}">
																<c:if test="${not comment.question_isDeleted()}">
																	<div class="text-end">
																		<button class="btn btn-sm btn-outline-dark"
																			type="button" onclick="changeEdit(this);">수정</button>
																		<button class="btn btn-sm btn-outline-dark"
																			type="button"
																			onclick="commentDelete(this, ${comment.comment_Id})">삭제</button>
																	</div>
																</c:if>
															</c:if>
														</div>
													</div>
												</div>
											</c:forEach>
											<div class="mb-1">
											<c:url var="questionUrl"
														value="/community/question">
													</c:url>
												<form action="${questionUrl}/comment/add"
													method="post">
													<input type="hidden" name="bid" value="${data.question_Id}">
													<div class="input-group">
														<textarea class="form-control" name="content" rows="2"></textarea>
														<button class="btn btn-outline-dark" type="button"
															onclick="formCheck(this.form);">등록</button>
													</div>
												</form>
											</div>
										</div>

										<div class="modal fade" id="removeModal" tabindex="-1"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h6 class="modal-title">삭제 확인</h6>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body">해당 데이터를 삭제하겠습니까?</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-sm btn-danger"
															data-bs-dismiss="modal"
															onclick="deleteQuestionDetail(${data.question_Id})">확인</button>
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
					<script type="text/javascript">
		function changeEdit(element) {
			element.innerText = "확인";
			element.nextElementSibling.remove();
			
			var value = element.parentElement.previousElementSibling.innerText;
			var textarea = document.createElement("textarea");
			textarea.setAttribute("class", "form-control");
			textarea.value = value;
			
			element.parentElement.previousElementSibling.innerText = "";
			element.parentElement.previousElementSibling.append(textarea);
			
			element.setAttribute("onclick", "commentUpdate(this);");
		}
		
		function changeText(element) {
			element.innerText = "수정";
			var cid = element.parentElement.parentElement.children[0].value;
			var value = element.parentElement.previousElementSibling.children[0].value;
			element.parentElement.previousElementSibling.children[0].remove();
			element.parentElement.previousElementSibling.innerText = value;
			
			var btnDelete = document.createElement("button");
			btnDelete.innerText = "삭제";
			btnDelete.setAttribute("class", "btn btn-sm btn-outline-dark");
			btnDelete.setAttribute("onclick", "commentDelete(this, " + cid + ");");
			
			element.parentElement.append(btnDelete);
			element.setAttribute("onclick", "changeEdit(this);");
		}
		
		function commentUpdate(element) {
			var cid = element.parentElement.parentElement.children[0].value;
			var value = element.parentElement.previousElementSibling.children[0].value;
			
			$.ajax({
				url: "/comment/modify",
				type: "post",
				data: {
					id: cid,
					content: value
				},
				success: function(data) {
					element.parentElement.previousElementSibling.children[0].value = data.value
					changeText(element);
				}
			});
		}
		
		function commentDelete(element, id) {
			$.ajax({
				url: "/comment/delete",
				type: "post",
				data: {
					id: id
				},
				success: function(data) {
					if(data.code === "success") {
						element.parentElement.parentElement.parentElement.parentElement.remove();
					}
				}
			});
		}
		function formCheck(form) {
			if(form.content.value.trim() === "") {
				alert("댓글 내용을 입력하세요.");
			} else {
				form.submit();
			}
		}
		function deleteQuestionDetail(question_Id) {
			$.ajax({
				url: "${questionUrl}/delete",
				type: "post",
				data: {
					id: question_Id
				},
				dataType: "json",
				success: function(data) {
					if(data.code === "success") {
						alert("삭제 완료");
						location.href = "${questionUrl}/list";
					} else if(data.code === "permissionError") {
						alert("권한이 오류");
					} else if(data.code === "notExists") {
						alert("이미 삭제되었습니다.")
					}
				}
			});
		}
		function ajaxLike(element, id) {
			$.ajax({
				type: "post",
				url: "${questionDetailUrl}/like",
				data: {
					id: id
				},
				success: function(data) {
					if(data.code === "success") {
						element.innerText = data.like;
					} else if(data.code === "noData") {
						alert(data.message);
						location.href = "${questionDetailUrl}";
					}
				}
			});
		}
	</script>


					
					<div
						style="width: 1500px; height: 200px; background-color: #f6f7f7; margin: auto; padding: 10px;
						position: relative; left: 50px;">
						이용약관</div>
				</footer>
				<!-- go to top -->
				<a class="btn-top" href="#"><i class="xi-angle-up-thin"></i></a>
			</div>
</body>
</html>