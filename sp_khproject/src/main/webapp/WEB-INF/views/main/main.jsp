<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Find</title>
	<!-- jQuery 적용 -->
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<!-- ajax 적용 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<!-- bootstrap js 적용 (캐러셀 구동에 필요) -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
  
	<!-- style, css -->
	<style>
	
	footer {
		font-family: 'Noto Sans KR', sans-serif;
	}
	
    .carousel-item {
      width: 1500px;
      height: 500px;
    }
  
    
    /* 인기서비스 슬라이드 */
    body {
		font-family: 'Noto Sans KR', sans-serif;
	}
	h2 {
		color: #000;
		font-size: 26px;
		font-weight: 300;
		text-align: center;
		text-transform: uppercase;
		position: relative;
		margin: 30px 0 60px;
		font-family: 'Noto Sans KR', sans-serif;
	}
	h2::after {
		content: "";
		width: 100px;
		position: absolute;
		margin: 0 auto;
		height: 4px;
		border-radius: 1px;
		background: #7ac400;
		left: 0;
		right: 0;
		bottom: -20px;
	}
	.carousel {
		margin: 50px auto;
		padding: 0 0px;
	}
	.carousel .item {
		color: #747d89;
		min-height: 325px;
		text-align: center;
		overflow: hidden;
	}
	.carousel .thumb-wrapper {
		padding: 25px 15px;
		background: #F2F3F7;
		border-radius: 6px;
		text-align: center;
		position: relative;
		box-shadow: 0 2px 3px rgba(0,0,0,0.2);
	}
	.carousel .item .img-box {
		height: 200px;
		margin-bottom: 20px;
		width: 100%;
		position: relative;
	}
	.carousel .item img {	
		max-width: 100%;
		max-height: 100%;
		display: inline-block;
		position: absolute;
		bottom: 0;
		margin: 0 auto;
		left: 0;
		right: 0;
	}

	.carousel .item h4 {
		font-size: 18px;
	}
	.carousel .item h4, .carousel .item p, .carousel .item ul {
		margin-bottom: 5px;
	}
	.carousel .thumb-content .btn {
		color: #7ac400;
		font-size: 11px;
		text-transform: uppercase;
		font-weight: bold;
		background: none;
		border: 1px solid #7ac400;
		padding: 6px 14px;
		margin-top: 5px;
		line-height: 16px;
		border-radius: 20px;
	}
	.carousel .thumb-content .btn:hover, .carousel .thumb-content .btn:focus {
		color: #fff;
		background: #7ac400;
		box-shadow: none;
	}
	.carousel-control-prev, .carousel-control-next {
		height: 40px;
		max-width: 100px;
		background: none;	
		margin: auto 0;
		border-radius: 4px;
		opacity: 0.8;
	}
	.carousel-control-prev:hover, .carousel-control-next:hover {
		background: #78bf00;
		opacity: 1;
	}
	
	.carousel-control-prev .slide-icon {
		max-width : 20px;
		margin-left : 10px;
	}
	
	.carousel-control-next .slide-icon {
		max-width : 20px;
		margin-right : 10px;
	}
	
	.control2 {
		max-width : 40px;	
		height : 150px;
	}
	
	.carousel-control-prev i, .carousel-control-next i {
		font-size: 36px;
		position: absolute;
		top: 50%;
		display: inline-block;
		margin: -19px 0 0 0;
		z-index: 5;
		left: 0;
		right: 0;
		color: #fff;
		text-shadow: none;
		font-weight: bold;
	}	
	.carousel-indicators {
		bottom: -50px;
	}
	.carousel-indicators li, .carousel-indicators li.active {
		width: 10px;
		height: 10px;
		margin: 4px;
		border-radius: 50%;
		border: none;
	}
	.carousel-indicators li {	
		background: rgba(0, 0, 0, 0.2);
	}
	.carousel-indicators li.active {	
		background: rgba(0, 0, 0, 0.6);
	}

	
	.col-sm-3 {
		max-width : 290px;
		min-width : 290px;
	}
	
	.img-hover-zoom {
	  overflow: hidden;
	}
	
	.img-hover-zoom img {
	  transition: transform .3s ease;
	}
	
	.img-hover-zoom:hover img {
	  transform: scale(1.2);
	}
	
	.container3 img {
		border-radius : 20%;
	}
	
	.modal {
        text-align: center;
	}
	 
	@media screen and (min-width: 768px) { 
	        .modal:before {
	                display: inline-block;
	                vertical-align: middle;
	                content: " ";
	                height: 100%;
	        }
	}
	.modal-dialog {
        display: inline-block;
        text-align: left;
        vertical-align: middle;
	}
	
	
	</style>

	<script type="text/javascript">
		$(document).ready(function(){
			$(".wish-icon i").click(function(){
				$(this).toggleClass("fa-heart fa-heart-o");
			});
		});
	</script>
	
	<script type="text/javascript">
		$('.carousel').carousel()
	</script>

</head>

<aside style="float:right; position:relative; top:76px; left:10px;">
	<a class="btn btn-success dropdown-toggle" href="#" role="button" style="font-weight:10; color:white;" 
	id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    	인기검색어
    </a>
	<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
		<c:forEach items="${wordList}" var="wordList">
		 	<a class="dropdown-item" href="./sellitem?search=${wordList.word}">
		 	<span style="color:green;">${wordList.ranking}&emsp;</span> ${wordList.word}(${wordList.frequency})</a>
		</c:forEach>
    </div>
</aside>
<body>
	<!-- 헤더 -->
	<%@ include file="../module/head.jsp" %>
	
	<!-- 배너, carousel -->
	<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
	  <ol class="carousel-indicators">
	    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
	    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
	    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
	  </ol>
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img id="couponDown" style="cursor:pointer;"data-bs-toggle="modal" data-bs-target="#staticBackdrop" 
	      class="d-block w-100" src="static/img/banner/banner1.png" alt="First slide">
	    </div>
	    <div class="carousel-item">
	      <img class="d-block w-100" src="static/img/banner/banner2.png" alt="Second slide">
	    </div>
	    <div class="carousel-item">
	      <img class="d-block w-100" src="static/img/banner/banner3.png" alt="Third slide">
	    </div>
	  </div>
	  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="sr-only" style="color: black;">Previous</span>
	  </a>
	  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="sr-only" style="color: black;">Next</span>
	  </a>
	</div>
	
<!-- 인기서비스 슬라이드 -->

	<div class="row">
		<div class="col-md-12">
			<h2><b>인기 서비스</b></h2>
			<div id="myCarousel1" class="carousel slide" data-ride="carousel" data-interval="0" style="height: 350px;">
			<!-- Carousel indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel1" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel1" data-slide-to="1"></li>
				<li data-target="#myCarousel1" data-slide-to="2"></li>
			</ol>   
			<!-- Wrapper for carousel items -->
			<div class="carousel-inner">
				<div class="item carousel-item active">
					<div class="row">
						<div class="col">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box ">
									<a href="${pageContext.request.contextPath}/sellitem?select=IT.프로그래밍"><img src="static/img/hot/IT.png" class="img-fluid" alt=""></a>									
								</div>
								<div class="thumb-content">
									<h4>IT, 프로그래밍</h4>																		
								</div>						
							</div>
						</div>
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="${pageContext.request.contextPath}/sellitem?select=디자인"><img src="static/img/hot/Design.png" class="img-fluid " alt=""></a>
								</div>
								<div class="thumb-content">
									<h4>디자인</h4>									
								</div>						
							</div>
						</div>		
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="${pageContext.request.contextPath}/sellitem?select=비즈니스"><img src="static/img/hot/Business.png" class="img-fluid " alt=""></a>
								</div>
								<div class="thumb-content">
									<h4>비즈니스</h4>									
								</div>						
							</div>
						</div>								
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="${pageContext.request.contextPath}/sellitem?select=마케팅"><img src="static/img/hot/Marketing.png" class="img-fluid " alt=""></a>
								</div>
								<div class="thumb-content">
									<h4>마케팅</h4>									
								</div>						
							</div>
						</div>
					</div>
				</div>
				<div class="item carousel-item">
					<div class="row">
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="${pageContext.request.contextPath}/sellitem?select=번역.통역"><img src="static/img/hot/Translation.png" class="img-fluid " alt=""></a>
								</div>
								<div class="thumb-content">
									<h4>번역, 통역</h4>
								</div>						
							</div>
						</div>
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="${pageContext.request.contextPath}/sellitem?select=문서, 글쓰기"><img src="static/img/hot/Writing.png" class="img-fluid " alt=""></a>
								</div>
								<div class="thumb-content">
									<h4>문서, 글쓰기</h4>	
								</div>						
							</div>
						</div>
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="${pageContext.request.contextPath}/sellitem?select=주문 제작"><img src="static/img/hot/CustomOrder.png" class="img-fluid " alt=""></a>
								</div>
								<div class="thumb-content">
									<h4>주문제작</h4>										
								</div>						
							</div>
						</div>
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="${pageContext.request.contextPath}/sellitem?select=세무.법무"><img src="static/img/hot/Tax.png" class="img-fluid " alt=""></a>
								</div>
								<div class="thumb-content">
									<h4>세무, 법무</h4>
								</div>						
							</div>
						</div>						
					</div>
				</div>
				<div class="item carousel-item">
					<div class="row">
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="${pageContext.request.contextPath}/sellitem?select=기타"><img src="static/img/hot/Etc.png" class="img-fluid " alt="iPhone"></a>
								</div>
								<div class="thumb-content">
									<h4>기타</h4>
								</div>						
							</div>
						</div>
					</div>
				</div>
				
				
			</div>
			<!-- Carousel controls -->
			<a class="carousel-control-prev control2" href="#myCarousel1" data-slide="prev">
				<img src="static/img/prev-icon.png" class="slide-icon">
			</a>
			<a class="carousel-control-next control2" href="#myCarousel1" data-slide="next">
				<img src="static/img/next-icon.png" class="slide-icon">
			</a>
		</div>
		</div>
	</div>
	
<!-- 인기 전문가 슬라이드 -->
	<div class="row container3">
		<div class="col-md-12">
			<h2><b>인기 전문가</b></h2>
			<div id="myCarousel2" class="carousel slide" data-ride="carousel" data-interval="0" style="height: 350px;">
			<!-- Carousel indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel2" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel2" data-slide-to="1"></li>
				<li data-target="#myCarousel2" data-slide-to="2"></li>
			</ol>   
			<!-- Wrapper for carousel items -->
			<div class="carousel-inner">
				<div class="item carousel-item active">
					<div class="row">
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="./sellitem/itemdetail?search=${list1.sel_name}&itemid=${list1.sel_id}">
									<img src="${pageContext.request.contextPath}/${list1.url}/${list1.uuidName}" class="img-fluid"
									onerror="this.onerror=null; this.src='./static/img/NoData.png'">
									</a>
								</div>
								<div class="thumb-content">
									<h4>${list1.sel_name}</h4>
								</div>						
							</div>
						</div>
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="./sellitem/itemdetail?search=${list2.sel_name}&itemid=${list2.sel_id}">
									<img src="${pageContext.request.contextPath}/${list2.url}/${list2.uuidName}" class="img-fluid " 
									onerror="this.onerror=null; this.src='./static/img/NoData.png'">
									</a>
								</div>
								<div class="thumb-content">
									<h4>${list2.sel_name}</h4>
								</div>						
							</div>
						</div>	
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="./sellitem/itemdetail?search=${list3.sel_name}&itemid=${list3.sel_id}">
									<img src="${pageContext.request.contextPath}/${list3.url}/${list3.uuidName}" class="img-fluid"
									onerror="this.onerror=null; this.src='./static/img/NoData.png'">
									</a>
								</div>
								<div class="thumb-content">
									<h4>${list3.sel_name}</h4>
								</div>						
							</div>
						</div>								
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="./sellitem/itemdetail?search=${list4.sel_name}&itemid=${list4.sel_id}">
									<img src="${pageContext.request.contextPath}/${list4.url}/${list4.uuidName}" class="img-fluid" 
									onerror="this.onerror=null; this.src='./static/img/NoData.png'">
									</a>
								</div>
								<div class="thumb-content">
									<h4>${list4.sel_name}</h4>
								</div>						
							</div>
						</div>
					</div>
				</div>
				<div class="item carousel-item">
					<div class="row">
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="./sellitem/itemdetail?search=${list5.sel_name}&itemid=${list5.sel_id}">
									<img src="${pageContext.request.contextPath}/${list5.url}/${list5.uuidName}" class="img-fluid"
									onerror="this.onerror=null; this.src='./static/img/NoData.png'">
									</a>
								</div>
								<div class="thumb-content">
									<h4>${list5.sel_name}</h4>
								</div>						
							</div>
						</div>
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="./sellitem/itemdetail?search=${list6.sel_name}&itemid=${list6.sel_id}">
									<img src="${pageContext.request.contextPath}/${list6.url}/${list6.uuidName}" class="img-fluid"
									onerror="this.onerror=null; this.src='./static/img/NoData.png'">
									</a>
								</div>
								<div class="thumb-content">
									<h4>${list6.sel_name}</h4>
								</div>						
							</div>
						</div>
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="./sellitem/itemdetail?search=${list7.sel_name}&itemid=${list7.sel_id}">
									<img src="${pageContext.request.contextPath}/${list7.url}/${list7.uuidName}" class="img-fluid"
									onerror="this.onerror=null; this.src='./static/img/NoData.png'" >
									</a>
								</div>
								<div class="thumb-content">
									<h4>${list7.sel_name}</h4>
								</div>						
							</div>
						</div>
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="./sellitem/itemdetail?search=${list8.sel_name}&itemid=${list8.sel_id}">
									<img src="${pageContext.request.contextPath}/${list8.url}/${list8.uuidName}" class="img-fluid"
									onerror="this.onerror=null; this.src='./static/img/NoData.png'">
									</a>
								</div>
								<div class="thumb-content">
									<h4>${list8.sel_name}</h4>
								</div>						
							</div>
						</div>						
					</div>
				</div>
				<div class="item carousel-item">
					<div class="row">
						<div class="col-sm-3">
							<div class="thumb-wrapper img-hover-zoom">
								<span class="wish-icon"><i class="fa fa-heart-o"></i></span>
								<div class="img-box">
									<a href="./sellitem/itemdetail?search=${list9.sel_name}&itemid=${list9.sel_id}">
									<img src="${pageContext.request.contextPath}/${list9.url}/${list9.uuidName}" class="img-fluid"
									onerror="this.onerror=null; this.src='./static/img/NoData.png'">
									</a>
								</div>
								<div class="thumb-content">
									<h4>${list9.sel_name}</h4>
								</div>						
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Carousel controls -->
			<a class="carousel-control-prev control2" href="#myCarousel2" data-slide="prev">
				<i class="fa fa-angle-left"></i>
				<img src="static/img/prev-icon.png" class="slide-icon">
			</a>
			<a class="carousel-control-next control2" href="#myCarousel2" data-slide="next">
				<i class="fa fa-angle-right"></i>
				<img src="static/img/next-icon.png" class="slide-icon">
			</a>
		</div>
		</div>
	</div>

	
	<!-- 커뮤니티 HOT -->
	<article style="background-color:#F2F3F7; margin-top: 80px; padding-top:30px; padding-bottom:50px; border-radius:30px;">
		<div style="margin-bottom:30px;">
			<span style="font-weight: bold; font-size: 25px; margin-left: 112px;">커뮤니티 HOT</span>
			<img src="static/img/fire.png" style="width: 30px; position:relative; bottom: 2px;">
			<button onclick="location.href='${pageContext.request.contextPath}/community/main'" class="btn btn-outline-success" 
			style="margin-left:50px; width:100px;">전체보기</button>
		</div>
		<div class="row mt-3 mb-3" style="margin-left:100px; color: #747D89;">
			<div class="col img-hover-zoom" onclick="location.href='${pageContext.request.contextPath}/community/findPro/list'" style="cursor:pointer;">			
				<img src="static/img/community/pro.png" style="max-width: 300px; border-radius:20px;">
				<span style="font-size:1rem; font-weight:bold; margin-left:3rem;">전문가 구해요</span>			
			</div>
			<div class="col img-hover-zoom" onclick="location.href='${pageContext.request.contextPath}/community/findStu/list'" style="cursor:pointer;">
				<img src="static/img/community/lesson.png" style="max-width: 300px; border-radius:20px;">
				<span style="font-size:1rem; font-weight:bold; margin-left:3rem;">레슨자 구해요</span>
			</div>
		</div>
		<div class="row mt-3 mb-3" style="margin-left:100px; margin-botton:50px; color: #747D89;">
			<div class="col img-hover-zoom" onclick="location.href='${pageContext.request.contextPath}/community/question/list'" style="cursor:pointer;">
				<img src="static/img/community/question.png" style="max-width: 300px; border-radius:20px;">
				<span style="font-size:1rem; font-weight:bold; margin-left:3rem;">궁금해요</span>
			</div>
			<div class="col img-hover-zoom" onclick="location.href='${pageContext.request.contextPath}/community/life/list'" style="cursor:pointer;">
				<img src="static/img/community/daily.png" style="max-width: 300px; border-radius:20px;">
				<span style="font-size:1rem; font-weight:bold; margin-left:3rem;">일상</span>
			</div>
		</div>
	</article>
	
	<!-- 이벤트 쿠폰 Modal -->
	<%-- <div class="modal fade" id="staticBackdrop"  data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">오픈 기념 15% 할인쿠폰</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        1회만 다운로드 가능하며, 유효기간은 발급일로부터 30일입니다.
	      </div>
	      <div class="modal-footer">
	      	<c:url var="couponUrl" value="/main" />
			<form action="${couponUrl}" method="post">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
		        <button id="submit" type="submit" class="btn btn-success">다운받기</button>
	      	</form>
	      </div>
	    </div>
	  </div>
	</div> --%>
	
	<footer class="footer" style="margin-top : 200px; text-align:center; border-top: 3px solid #dcdcde; ">
		<div style="height:100px; padding: 22px; font-weight:bold; color:#747D89; background-color:#F2F3F7; margin: auto; margin-bottom: 50px; margin-top: 20px;" >
			<p style="margin-bottom:0px;">© 2020 Copyright: Find.com</p>
			<p>이메일 : findofficial9@gmail.com</p>
		</div>
	</footer>
	
	<script type="text/javascript">	
	/* 배너에서 쿠폰다운 */
		/* $('#couponDown').on('click', function(e) {
			if(${empty loginData}) {
				$('#submit').prop('disabled', true);
				$('.modal-body').text("로그인이 필요한 서비스입니다.");
			} else {
				$('.modal-body').text("1회만 다운로드 가능하며, 유효기간은 발급일로부터 30일입니다.");
			}
		});
				
		$('form').on('submit', function(e) {
			if(${loginData.ac_index == 10}) {
				if(${couponcheck} == 1) {
					swal("쿠폰 다운로드 완료!", "마이페이지에서 확인하세요.", "success");					
				} else {
					e.preventDefault();
					swal("쿠폰 다운로드 실패!", "이미 발급받은 쿠폰입니다.", "warning");
					$('#staticBackdrop').modal('hide');
				}
			} else {
				e.preventDefault();
				alert("일반회원만 다운로드 받을 수 있습니다.");
			}
		}); */
		
	</script>
</body>
	
</html>