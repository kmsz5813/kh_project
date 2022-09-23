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
  	<!-- jQuery -->
  	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
  	<!-- iamport.payment.js -->
  	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
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
						<c:if test="${empty thumbnail.uuidName }">
							<img src="${pageContext.request.contextPath}/static/img/profile/logo.png">
						</c:if>
						<c:if test="${not empty thumbnail.uuidName }">
							<img src="/home/${thumbnail.url}/${thumbnail.uuidName}" style="max-width:200px;">
						</c:if>
						${itemdata.sel_title}
					</div>
					<div class="input-group mb-2">
						<label id="get_point">보유 포인트 : <fmt:formatNumber value="${loginData.ac_point}" pattern="#,###" /> &emsp;</label>
						<label class="mb-2" id="remain_point">잔여 포인트 : ${loginData.ac_point} &emsp;</label>
						<label>적립예정 포인트 : <fmt:formatNumber value="${itemdata.sel_price / 100}" type="number" maxFractionDigits="0" /></label>
						<input class="form-control" form="submit" id="use_point" name="use_point" type="number"
						style="text-align:right; min-width: 250px; max-width:300px;" placeholder="사용할 포인트">
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
					<div style="overflow:scroll; max-height: 270px; max-width:600px;">
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
						<h4 class="mt-3 mb-5" style="margin-left: 220px;">최종 결제 금액 (최소 결제 금액 : 100원)</h4>
						<div class="mt-5 mb-5" style="margin-left: 400px;">
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
							<input type="hidden" id="realprice" name="realprice" value="${itemdata.sel_price}">
							<!-- Controller 에 보낼 사용 쿠폰 번호 -->
							<input type="hidden" id="used_coupon" name="used_coupon" value="">	
							<button style="margin-left: 116px; margin-right: 116px;"
							class="btn btn-outline-primary btn-lg" type="submit" id="purchase">결제하기</button>
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
			var point = $('#remain_point').text();
			var point2 = point.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			$('#remain_point').text(point2);
		}
		
		// 결제버튼 눌렀을때
		$('form').on('submit', function(e) {
			if (! $('#check').prop("checked")) {
				e.preventDefault();
				swal('오류!', "결제 동의를 체크해주세요.", 'warning');
			}
		});
		
		// 포인트 적용
		function changePoint(amt, pnt) {
			// amt = 상품가격, pnt = 사용가능 포인트, v_point = 입력한 포인트
			var v_point = parseInt(document.getElementById("use_point").value);
			if(isNaN(v_point)) {		// 아무것도 입력 안했을경우
				v_point = 0;
				document.getElementById("use_point").value = v_point;
			}
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
			document.getElementById("remain_point").innerHTML = "잔여포인트 : " + (pnt - v_point) + "&emsp;";
			var price1 = Math.floor((amt - v_point) - (${itemdata.sel_price} * $('#select_value').val().split(',')[0]) / 100);
			if(price1 >= 100) {				
				document.getElementById("price").innerHTML = price1 + " 원";	// 페이지에 표기될 구매가
				document.getElementById("realprice").value = price1;	// 서버에 저장될 구매가
				toString();
			} else {	// 결제금액 100원 미만일 경우
				swal('포인트 사용 오류!', "최소 결제 금액은 100원 입니다.", 'warning');
				document.getElementById("remain_point").innerHTML = "잔여포인트 : " + pnt + "&emsp;";
				document.getElementById("use_point").value = 0;
				toString();
			}
		}
		
		// 쿠폰 적용
		$('#select_value').change(function() {
			var value1 = document.getElementById('select_value').value;
			var value2 = value1.split(',');
			var value = value2[0];
			var price1 = Math.floor(document.getElementById("realprice").value - (${itemdata.sel_price} * value / 100));
			if(value1 != 0 && price1 >= 100) {				
				document.getElementById("price").innerHTML = price1 + " 원";	// 페이지 표기될 실구매가
				document.getElementById("realprice").value = price1;		// Controller 에 넘겨줄 실 구매가
				document.getElementById("used_coupon").value = value2[1].replace(" ","");	// Controller에 넘겨줄 hidden input value (쿠폰 번호) 넣기
				toString();
			} else  {		// 기본값 클릭했을 경우 or 결제금액 100원 미만일경우
				if(price1 <= 100) {
					$("#select_value").val('0').prop("selected", true);
					swal('쿠폰 사용 오류!', "최소 결제 금액은 100원 입니다.", 'warning');					
				}
				document.getElementById("price").innerHTML = ${itemdata.sel_price} - document.getElementById("use_point").value + " 원"; 
				document.getElementById("realprice").value = ${itemdata.sel_price} - document.getElementById("use_point").value; 
				document.getElementById("used_coupon").value = "";	
				toString();
			}
			
		});
		
		// IMP 객체 
		var IMP = window.IMP;
		var code = "imp21550052";
		
		IMP.init(code);
		
		// 아임포트 결제
		$('form').on('submit', function(e) {
			e.preventDefault();
	      // IMP.request_pay(param, callback) 결제창 호출
	      IMP.request_pay({ // param
	          pg: "html5_inicis",
	          pay_method: "card",
	          merchant_uid: "item" + new Date().getTime(),		// 이미 결제된 번호는 하면 안됨
	          name: "${itemdata.sel_title}",
	          amount: $('#realprice').val(),
	          buyer_email: "${loginData.ac_email}",
	          buyer_name: "${loginData.ac_name}",
	          buyer_tel: "010-7372-8727",
	      }, function (rsp) { // callback
	          if (rsp.success) {
	        	  swal('결제완료!', "마이페이지에서 구매내역을 확인하세요.", 'success');
	        	  // return true; 안됨
	        	  // e.submit(); 안됨
	        	  // e.unbind(); 안됨
	        	  $('form').unbind();
	        	  $('form').submit();
	          } else {
	              swal('결제를 취소하셨습니다.', '', 'warning')
	          }
	      })
	    });
		
		IMP.request_pay({
		      /* ...중략... */
		    }, function (rsp) { // callback
		      if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
		        // jQuery로 HTTP 요청
		        $.ajax({
		            url: "<c:url value='iamport' />", 
		            method: "POST",
		            headers: { "Content-Type": "application/json" },
		            data: {
		                imp_uid: rsp.imp_uid,
		                merchant_uid: rsp.merchant_uid,
		                paid_amount: rsp.paid_amount,
            			apply_num: apply_num
		            },
		            dataType: 'json',
		            success: function(data) {
		            	let msg = "결제가 완료되었습니다.\n";
	  		              msg += "고유ID: " + imp_uid;
	  		              msg += "\n상점거래ID : " + merchant_uid;
	  		              msg += "\n결제금액 : " + paid_amount;
	  		              msg += "\n 카드승인번호 : " + apply_num;
	  		              swal('결제완료!!!!!!', msg, 'success');
		            }
		        })
		      }
		    });
		</script>
	</section>
	
</body>
</html>