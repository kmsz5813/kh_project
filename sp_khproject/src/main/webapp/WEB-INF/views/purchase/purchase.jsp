<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<c:url var="bs5" value="/static/bs5" />
<c:url var="jQuery" value="/static/js" />    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>구매페이지</title>
	<link rel="stylesheet" type="text/css" href="${bs5}/css/bootstrap.min.css">
	<script type="text/javascript" src="${jQuery}/jquery-3.6.0.min.js"></script>
	<style>
		.card {
			height : 700px;
		}
		.btn-lg {
			height : 5rem;
			width:400px;
		}
		
		img {
			border-radius: 30%;
		}
	</style>
</head>
<body>
	<%@ include file="../module/head.jsp"%>
	<div class="mb-5 center">
		<h2 class="fw-normal fs-2 text-center">구매페이지</h2>
	</div>
	<section class="container w-100">
		<div class="row mt-3">
			<div class="col">
				<div class="card" style="border-radius:50px; padding-left:10px; padding-right: 10px;">
					<h2 class="mt-3 mb-3">상품정보</h2>
					<div class="mt-3 mb-3">
						<img src="./static/img/hot/hot-test1.png" style="max-width:200px;">
						${itemdata.sel_title}
					</div>
					<div style="overflow:scroll; height: 500px;">
						${itemdata.sel_content}
					</div>
				</div>
			</div>
			<div class="col">	
				<div class="card" style="border-radius: 50px;">
					<div style="height:350px; border-bottom:1px solid rgba(0,0,0,.125); padding-left:20px; padding-right:20px;" >
						<p class="mt-5">결제방법</p>
					</div>
					<div style="height: 350px;">
						<h4 class="mt-3 mb-5" style="margin-left: 450px;">최종 결제 금액</h4>
						<div class="mt-5 mb-5" style="margin-left: 450px;">
							<h1 id="price" style="font-weight:700; color:red;">${itemdata.sel_price } 원</h1>
						</div>
						<div class="mb-3" style="margin-left: 100px;">
							<input type="checkbox" id="check" style="width:20px;height:20px;" class="form-check-input" name="">							
							<label id="" style="font-size:20px; margin-left:20px;">결제 정보를 확인하였으며, 구매진행에 동의합니다.</label>
						</div>
						<c:url var="purchaseurl" value="/purchase/purchase" />
						<form action="${purchaseurl}" method="post">
							<input type="hidden" name="itemid" value="${itemdata.sel_id}">
							<button style="margin-left: 116px; margin-right: 116px;"
							class="btn btn-outline-primary btn-lg" type="submit">결제하기</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		window.onload = function() {
			toString();
		}
		// 금액에 , 표현
		function toString() {
			var money = $('#price').text();
			var money2 = money.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			$('#price').text(money2);			
		}
		
		// 결제버튼 눌렀을때
		$('form').on('submit', function(e) {
			if (! $('#check').prop("checked")) {
				e.preventDefault();
				swal('오류!', "결제 동의를 체크해주세요.", 'warning');
			}
		});
		</script>
	</section>
	
</body>
</html>