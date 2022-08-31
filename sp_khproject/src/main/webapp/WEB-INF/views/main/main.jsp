<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Want</title>
	<!-- bootstrap css 적용 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<!-- jQuery 적용 -->
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<!-- ajax 적용 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<!-- bootstrap js 적용 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
	<style>
    .carousel-item {
      width: 2000px; 
    }
  	</style>

</head>

<body>
	<header>
		<div style="text-align: right;">
			<a>로그인</a>/ 
			<a>회원가입</a>/ 
			<a>FAQ</a>
		</div>
	</header>
	
	
<!-- nav-bar -->
	<script type="text/javascript">
		$('.carousel').carousel()
	</script>
	<a class="navbar-brand" href="#">
      <img src="static/img/logo.png" alt="" height="200" class="d-inline-block align-text-top">
    </a>
	<nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-left: 500px; ">
	  <div class="container">
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">

	        <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            카테고리
	          </a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="#">카테고리1</a></li>
	            <li><a class="dropdown-item" href="#">카테고리2</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="#">카테고리3</a></li>
	            <li><a class="dropdown-item" href="#">카테고리4</a></li>
	          </ul>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">커뮤니티</a>
	        </li>
	      </ul>
	      <form class="d-flex">
	        <input class="form-control me-4" type="search" placeholder="내용을 입력해주세요." aria-label="Search">
	        <button class="btn btn-outline-success" type="submit">검색</button>
	      </form>
	    </div>
	  </div>
	</nav>
	
	
	<!-- 배너, carousel -->
	<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
	  <ol class="carousel-indicators">
	    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
	    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
	    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
	  </ol>
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img class="d-block w-100" src="static/img/test1.png" alt="First slide">
	    </div>
	    <div class="carousel-item">
	      <img class="d-block w-100" src="static/img/test2.png" alt="Second slide">
	    </div>
	    <div class="carousel-item">
	      <img class="d-block w-100" src="static/img/test3.png" alt="Third slide">
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


	
	<c:url var="mainurl" value="/main" />
	
	<footer style="margin-top : 300px;">
		이용약관
	</footer>
</body>
	
</html>