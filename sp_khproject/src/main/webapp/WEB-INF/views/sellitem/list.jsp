<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품목록 리스트</title>
	<c:url var="bs5" value="/static/bs5" />
	<c:url var="jQuery" value="/static/js" />
	<link rel="stylesheet" type="text/css" href="${bs5}/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
	
	
	<style>
		.img-hover-zoom {
	  	overflow: hidden;
		}
		
		.img-hover-zoom img {
		  transition: transform .3s ease;
		}
		
		.img-hover-zoom:hover img {
		  transform: scale(1.15);
		}
		
		.card-img-top{
			height: 15rem;
		}
	</style>
</head>
<body>
	<%@ include file="../module/head.jsp" %>
	<header></header>
	<c:url var="itemUrl" value="/sellitem" />
	<section class="container w-100">
		<div class="mt-5">
			<div class="row mb-5 center">
				<div class="col-10">
					<p class="fw-normal fs-2">상품 목록 </p>
				</div>
			</div>
		</div>
		
		<!-- 전문가면 글 등록가능하게 -->
		<c:if test="${loginData.ac_index == 20}">
			<div style="margin-left:1180px;">
				<button onclick="location.href='./sellitem/additem'" class="btn btn-outline-success" type="button">상품 등록</button>
			</div>
		</c:if>
		
		<!-- 서비스 지역등 태그 페이지ㅣㅣㅣㅣㅣ -->
		<div class="row g-1 mt-3 mb-5">
			<div class="dropdown col-2">
			  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"  style="width: 12rem; margin-right: 0.5rem; background-color: white; color:black;" data-bs-toggle="dropdown" aria-expanded="false" >
			   	서비스
			  </button>
			  <!------------------- 반복문 만들어서 집어넣기---------------------------->
			  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
			  	  <li><a class="dropdown-item" href="sellitem?">전체</a></li>
			  	<c:forEach var="name" items="${Option}" >
			    <li><a class="dropdown-item" href="sellitem?select=${name}">${name}</a></li>
			  	</c:forEach>
			  </ul>
			 </div>
			  
			   <div class="dropdown col-5">
			  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"  style="width: 12rem; margin-right: 0.5rem; background-color: white; color:black;" data-bs-toggle="dropdown" aria-expanded="false">
			    지역
			  </button>
			  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
			 	   <li><a class="dropdown-item" href="sellitem?">전체</a></li>
			    <c:forEach var="lc" items="${lc}" >
			    <li><a class="dropdown-item" href="sellitem?location=${lc}">${lc}</a></li>
			    </c:forEach>
			  </ul>
			 </div>
			  <!-- ---------------------------------------------------------------- -->
			  
			  <!-- 인기순 조회순 만들기 -->
			 <div class="dropdown col-2">
			  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" style="width: 12rem; margin-left: 0.5rem; background-color: white; color:black;"  data-bs-toggle="dropdown" aria-expanded="false">
			    인기순
			  </button>
			  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
			    <li><a class="dropdown-item" href="/login/login">Action</a></li>
			    <li><a class="dropdown-item" href="#">Another action</a></li>
			    <li><a class="dropdown-item" href="#">Something else here</a></li>
			  </ul>
			 </div>
			  
			  <!-- 인풋타입에 적어서 보낼수있는것 -->
			 
			 
			 <div class="col-auto">
			 <form action="${itemUrl}" method="get">
				<div class="input-group">
					<input class="form-control" type="text" name="search">
					<button class="btn btn-secondary" type="submit" style="background-color: white; color:black;">조회</button>
				</div>
			</form>
			</div>
			
			
		</div>
		
		<!-- 메인 내용 페이지 !!!!!!!!!!!!!!!!!!!!!!!!!! -->
		<div class="row g-1 mt-5">
		<c:forEach items="${result}" var="data">	
			<div class="card" style="width: 18rem; margin-right: 2rem; margin-bottom: 2rem;">
			  <div class="img-hover-zoom" style="border-radius:10px;">
				<a href="./sellitem/itemdetail?search=${data.sel_name}&itemid=${data.sel_id}">
			
				 <c:if test="${empty data.uuidName }">
				 <img src="${pageContext.request.contextPath}/static/img/profile/logo.png" class="card-img-top" alt="...">
				 </c:if>
				 <c:if test="${not empty data.uuidName}">
				  <img src="/home/${data.url}/${data.uuidName}" class="card-img-top"/> 
				 
				  </c:if>

				  </a>
			  </div>
			  <div class="card-body">
			  	<h5 class="card-title" style="font-weight:bold; text-align:center">${data.sel_title}</h5>
				 <!-- 좋아요를 보여주는 것 -->
				 <c:forEach items="${likeData}" var="likeData">
				 	<c:if test="${data.sel_id == likeData.sel_id}">
				 		❤
				 	</c:if>

				 </c:forEach>
				 
			   
			    <h4 style="text-align:right"><a href="./detail?search=${data.sel_name}" class="card-text">${data.sel_name}</a></h4>
			    <h4 class="price" style="text-align:right">&#8361; <fmt:formatNumber type="number" maxFractionDigits="3" value="${data.sel_price}"/></h4>
			  </div>
			</div>
		</c:forEach>
		</div>
		
	
		<!-- 순서 네비게이션-->
		<nav>
			<div>
				<ul class="pagination justify-content-center">
					<c:if test="${pageData.hasPrevPage()}">
						<li class="page-item">
							<a class="page-link" href="${itemUrl}?${selectData}&page=${pageData.prevPageNumber}">Prev</a>
						</li>
					</c:if>
					<c:forEach items="${pageData.getPageNumberList(pageData.currentPageNumber - 2, pageData.currentPageNumber + 2)}" var="num">
						<li class="page-item ${pageData.currentPageNumber eq num ? 'active' : ''}">
							<a class="page-link" href="${itemUrl}?${selectData}&page=${num}">${num}</a>
						</li>
					</c:forEach>
					<c:if test="${pageData.hasNextPage()}">
						<li class="page-item">
							<a class="page-link" href="${itemUrl}?${selectData}&page=${pageData.nextPageNumber}">Next</a>
						</li>
					</c:if>
				</ul>
			</div>
		</nav>
	<script type="text/javascript">
		
	</script>
	</section>
</body>
</html>