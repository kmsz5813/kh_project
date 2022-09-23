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
		input::-webkit-inner-spin-button {
		  appearance: none;
		  -moz-appearance: none;
		  -webkit-appearance: none;
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
					<div class="input-group mb-2">
						<label id="get_point">보유포인트 : ${loginData.ac_point} &emsp;</label>
						<label style="margin-right:200px;" class="mb-2" id="remain_point">잔여포인트 : ${loginData.ac_point}</label>
						<input class="form-control" form="submit" id="use_point" name="use_point" type="number" style="text-align:right; min-width: 200px; max-width:300px;" placeholder="사용할 포인트">
						<div class="input-group-append">
							<button class="btn btn-outline-secondary" onclick="changePoint(${itemdata.sel_price}, ${loginData.ac_point})" type="button">적용</button>
						</div>
					</div>
					<div class="mb-3">
						<label class="">적용할 쿠폰</label><br>
						<select id="select_value" style="width: 500px;" class="form-select">
							<option value="0">적용할 쿠폰 선택</option>
							<c:forEach items="${coupon}" var="coupon">
								<c:if test="${coupon.coupon_used == null}">
									<option value="${coupon.coupon_salePercent}, ${coupon.coupon_number}">${coupon.coupon_number} ${coupon.coupon_name} ${coupon.coupon_salePercent}%할인</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div style="overflow:scroll; height: 270px;">
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
							<h1 id="price" style="font-weight:700; color:red;"><fmt:formatNumber type="number" maxFractionDigits="3" value="${itemdata.sel_price}"/> 원</h1>
						</div>
						<div class="mb-3" style="margin-left: 100px;">
							<input type="checkbox" id="check" style="width:20px;height:20px;" class="form-check-input" name="">							
							<label id="" style="font-size:20px; margin-left:20px;">결제 정보를 확인하였으며, 구매진행에 동의합니다.</label>
						</div>
						<c:url var="purchaseurl" value="/purchase/purchase" />
						<form id="submit" action="${purchaseurl}" method="post">
							<input type="hidden" name="itemid" value="${itemdata.sel_id}">
							<!-- Controller 에 보낼 실구매가격 -->
							<input type="hidden" id="realprice" name="realprice" value="">
							<!-- Controller 에 보낼 사용 쿠폰 번호 -->
							<input type="hidden" id="used_coupon" name="used_coupon" value="">	
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
		
		function changePoint(amt, pnt) {
			// amt = 상품가격, pnt = 사용가능 포인트, v_point = 입력한 포인트
			var v_point = parseInt(document.getElementById("use_point").value);
			if(v_point > pnt) {	// 입력값이 사용가능 포인트보다 클 때
				v_point = pnt;
				document.getElementById("use_point").value = v_point;
			}
			if(v_point < 0) {	// 입력값 -일경우
				v_point = 0;
				document.getElementById("use_point").value = v_point;
			}
			if(v_point > amt) {	// 결제금액보다 포인트가 더 클 때
				v_point = amt;
				document.getElementById("use_point").value = v_point;
			}
			document.getElementById("remain_point").innerHTML = "잔여포인트 : " + (pnt - v_point);
			document.getElementById("price").innerHTML = (amt - v_point) + " 원";	// 페이지에 표기될 구매가
			document.getElementById("realprice").value = amt - v_point;	// 서버에 저장될 구매가
			
		}
		
		// 쿠폰 적용
		$('#select_value').change(function() {
			var value1 = document.getElementById('select_value').value;
			var value2 = value1.split(',');
			var value = value2[0];
			document.getElementById("price").innerHTML = Math.floor(((${itemdata.sel_price} - document.getElementById("use_point").value) * (100 - value) / 100)) + " 원";
			document.getElementById("realprice").value = Math.floor(((${itemdata.sel_price} - document.getElementById("use_point").value) * (100 - value) / 100));
			// Controller에 넘겨줄 hidden input value (쿠폰 번호) 넣기
			document.getElementById("used_coupon").value = value2[1].replace(" ",""); 
		});
		
		</script>
	</section>
	
</body>
</html>