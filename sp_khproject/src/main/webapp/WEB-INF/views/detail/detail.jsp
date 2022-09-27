<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<style>
	#previewImg {
		max-width: 250px;
		border-radius: 30%;
		height: auto;
	}
	
	.container1 {
		display: block;
	}
	
	.container2 {
		display: none;
	}
	
	.container3 {
		display: none;
	}
	
	.container4 {
				display : none;
			}
	
	.btn-outline-success {
		margin-right: 10px;
	}
</style>
</head>
<body>
	<%@ include file="../module/head.jsp"%>
	<section class="container w-75">
		<div class="mt-5">
			<div class="row mb-5 center">
				<div class="col-10 mb-3">
					<c:if test="${data.ac_index == 10}">
						<p class="fw-bold fs-2">회원정보</p>
					</c:if>
					<c:if test="${data.ac_index == 20}">
						<p class="fw-bold fs-2">전문가 정보</p>
					</c:if>
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-3 image-form left">
					<img id="previewImg" class="image-360" alt="프로필 이미지" src="./static/img/profile/${data.ac_email}.png" onerror="this.onerror=null; this.src='./static/img/profile/basic.png'">
				</div>
				<div class="col-4 mt-4">
					<p>이름</p>
					<p>직업</p>
					<c:if test="${data.ac_index == 10}">
						<p class="fw-lighter fs-6">비즈니스 분야 </p>
						<p class="fw-lighter fs-6">관심분야</p>
					</c:if>
					<c:if test="${data.ac_index == 20}">
						<p class="fw-lighter fs-6">전문분야 </p>
						<p class="fw-lighter fs-6">관심분야</p>
					</c:if>
					<c:if test="${data.ac_index == 10}">
						<p class="fw-lighter fs-6">보유 포인트</p>
					</c:if>
				</div>
				<div class="col-4 mt-4">
					<p>${data.ac_name }</p>
					<p>${data.ac_job }</p>
					<p>${data.ac_field }</p>
					<p>${data.ac_interest }</p>
					<c:if test="${data.ac_index == 10}">
						<fmt:formatNumber type="number" maxFractionDigits="3" value="${data.ac_point}"/>
					</c:if>
				</div>
				<div class="mt-5">
					<c:if test="${data.ac_index == 10}">
						<input type="radio" class="btn-check radio-value" value = "1" name="options-outlined" id="success-outlined" autocomplete="off" checked>
						<label class="btn btn-outline-success" for="success-outlined">구매 내역</label>
					</c:if>
					<c:if test="${data.ac_index == 20}">
						<input type="radio" class="btn-check radio-value" value = "1" name="options-outlined" id="success-outlined" autocomplete="off" checked>
						<label class="btn btn-outline-success" for="success-outlined">판매 게시글</label>
					</c:if>
					<c:if test="${data.ac_index == 10}">
						<input type="radio" class="btn-check radio-value" value = "2" name="options-outlined" id="success-outlined2" autocomplete="off">
						<label class="btn btn-outline-success" for="success-outlined2">관심상품</label>
					</c:if>
					<c:if test="${data.ac_index == 20}">
						<input type="radio" class="btn-check radio-value" value = "2" name="options-outlined" id="success-outlined2" autocomplete="off">
						<label class="btn btn-outline-success" for="success-outlined2">판매내역</label>
					</c:if>
					<input type="radio" class="btn-check radio-value" value="3" name="options-outlined" id="success-outlined3" autocomplete="off"> 
					<label class="btn btn-outline-success" for="success-outlined3">메시지</label>
					<c:if test="${data.ac_index == 10}">
						<input type="radio" class="btn-check radio-value" value = "4" name="options-outlined" id="success-outlined4" autocomplete="off">
						<label class="btn btn-outline-success" for="success-outlined4">보유 쿠폰</label>
					</c:if>
				</div>
				<c:if test="${data.ac_index == 10}">
					<section class="container1">	
						<div class="mt-5">			
						<table class="table table-hover">
							<thead style="background-color: rgb(224, 224, 224)">							
								<tr>
									<th scope="col" class="text-center">구매번호</th>
									<th scope="col" class="text-center">상품명</th>
									<th scope="col" class="text-center">구매일자</th>
									<th scope="col" class="text-center">상품가격</th>
									<th scope="col" class="text-center">사용한 포인트</th>
									<th scope="col" class="text-center">사용한 쿠폰</th>
									<th scope="col" class="text-center">구매가</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${purchaseData}" var="purchaseData">
									<tr onclick="location.href='./sellitem/itemdetail?search=${item.sel_name}&itemid=${item.sel_id}'" style="cursor:pointer;">
										<td scope="row" class="text-center">${purchaseData.buy_number}</td>
										<td class="text-center">${purchaseData.buy_itemName}</td>
										<td class="text-center">${purchaseData.buy_buyday}</td>
										<td class="text-center"><fmt:formatNumber type="number" maxFractionDigits="3" value="${purchaseData.buy_price}"/></td>
										<td class="text-center"><fmt:formatNumber type="number" maxFractionDigits="3" value="${purchaseData.buy_usedPoint}"/></td>
										<td class="text-center">${purchaseData.buy_usedCouponName}</td>
										<td class="text-center"><fmt:formatNumber type="number" maxFractionDigits="3" value="${purchaseData.buy_realPrice}"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
					</section>
				</c:if>
				<c:if test="${data.ac_index == 20}">
					<section class="container1">
						<div class="mt-5">
							   <table class="table table-hover">
	                     <thead style="background-color: rgb(224, 224, 224)">                     
	                        <tr>
	                           <th scope="col">제목</th>
	                           <th scope="col">작성일자</th>
	                           <th scope="col">판매횟수</th>
	                           <th scope="col">추천수</th>
	                        </tr>
	                     </thead>
	                     <tbody>
	                        <c:forEach items="${items}" var="item">
	                           <tr onclick="location.href='./sellitem/itemdetail?search=${item.sel_name}&itemid=${item.sel_id}'" style="cursor:pointer;">
	                              <td>${item.sel_title}</td>
	                              <td>${item.sel_writeday}</td>
	                              <td>${item.sel_number}</td>
	                              <td>${item.sel_like}</td>
	                           </tr>
	                        </c:forEach>
	                     </tbody>
	                  </table>
						</div>
					</section>
				</c:if>
				<c:if test="${data.ac_index == 10}">
					<section class="container2">
						<div class="mt-5">
							<table class="table table-hover">
								<colgroup>
									<col class="col-2">
									<col class="col-auto">
									<col class="col-5">
								</colgroup>
								<thead style="background-color: rgb(224, 224, 224)">
									<tr>
										<th class="text-center">test2</th>
										<th class="text-center">test2</th>
										<th class="text-center">test2</th>
									</tr>
								</thead>
							</table>
						</div>
					</section>
				</c:if>
				<c:if test="${data.ac_index == 20}">
					<section class="container2">
						<div class="mt-5">
							<table class="table table-hover">
								<thead style="background-color: rgb(224, 224, 224)">
									<tr>
										<th class="text-center">판매번호</th>
										<th class="text-center">구매자</th>
										<th class="text-center">판매일자</th>
										<th class="text-center">판매가격</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${sellData}" var="sellData">
										<tr>
											<td class="text-center">${sellData.buy_number}</td>
											<td class="text-center">${sellData.buy_buyer}</td>
											<td class="text-center">${sellData.buy_buyday}</td>
											<td class="text-center"><fmt:formatNumber type="number" maxFractionDigits="3" value="${sellData.buy_price}"/></td>
										</tr>
									</c:forEach>
								</tbody>	
							</table>
						</div>
					</section>
				</c:if>	
				<section class="container3">
					<div class="mt-5">
						<table class="table table-hover">
							<colgroup>
								<col class="col-2">
								<col class="col-auto">
								<col class="col-5">
							</colgroup>
							<thead style="background-color: rgb(224, 224, 224)">
								<tr>
									<th class="text-center">test3</th>
									<th class="text-center">test3</th>
									<th class="text-center">test3</th>
								</tr>
							</thead>
						</table>
					</div>
				</section>
				<c:if test="${data.ac_index == 10}">
					<section class="container4">
						<div class="mt-5">
						<div style="float:right;">
							<button style="background-color:#F6CECE; border: none;">유효기간 만료</button>
							<button style="background-color:#E9967A; border: none;">사용됨</button>
						</div>
							<table class="table table-hover">
								<thead style="background-color: rgb(224, 224, 224)">
									<tr>
										<th class="text-center">쿠폰번호</th>
										<th class="text-center">쿠폰명</th>
										<th class="text-center">쿠폰 다운로드일</th>
										<th class="text-center">쿠폰 마감일</th>
										<th class="text-center">쿠폰 할인율</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${couponData}" var="couponData">
										<c:if test="${couponData.coupon_used == null}">
											<tr>
												<td class="text-center">${couponData.coupon_number}</td>
												<td class="text-center">${couponData.coupon_name}</td>
												<td class="text-center">${couponData.coupon_startDate}</td>
												<td class="text-center">${couponData.coupon_endDate}</td>
												<td class="text-center">${couponData.coupon_salePercent}</td>
											</tr>
										</c:if>
									</c:forEach>
									<c:forEach items="${couponData}" var="couponData">
										<c:if test="${couponData.coupon_used == 'F'}">
											<tr style="background-color:#F6CECE ">
												<td class="text-center">${couponData.coupon_number}</td>
												<td class="text-center">${couponData.coupon_name}</td>
												<td class="text-center">${couponData.coupon_startDate}</td>
												<td class="text-center">${couponData.coupon_endDate}</td>
												<td class="text-center">${couponData.coupon_salePercent}</td>
											</tr>
										</c:if>
									</c:forEach>
									<c:forEach items="${couponData}" var="couponData">	
									<c:if test="${couponData.coupon_used == 'Y'}">
										<tr style="background-color:#E9967A ">
											<td class="text-center">${couponData.coupon_number}</td>
											<td class="text-center">${couponData.coupon_name}</td>
											<td class="text-center">${couponData.coupon_startDate}</td>
											<td class="text-center">${couponData.coupon_endDate}</td>
											<td class="text-center">${couponData.coupon_salePercent}%</td>
										</tr>
									</c:if>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</section>
				</c:if>
			</div>
		</div>
	</section>
	<%@ include file="../module/footer.jsp" %>
	<script type="text/javascript">
		// radio active 버튼
		$('.radio-value').on('click', function() {
			var chkValue = $('.radio-value:checked').val();
			if(chkValue == 1) {
				$('.container1').css("display", "block");
				$('.container2').css("display", "none");
				$('.container3').css("display", "none");
				$('.container4').css("display", "none");
			}
			if(chkValue == 2) {
				$('.container1').css("display", "none");
				$('.container2').css("display", "block");
				$('.container3').css("display", "none");
				$('.container4').css("display", "none");
			}
			if(chkValue == 3) {
				$('.container1').css("display", "none");
				$('.container2').css("display", "none");
				$('.container3').css("display", "block");
				$('.container4').css("display", "none");
			}
			if(chkValue == 4) {
				$('.container1').css("display", "none");
				$('.container2').css("display", "none");
				$('.container3').css("display", "none");
				$('.container4').css("display", "block");
			}
		});
	</script>
</body>
</html>