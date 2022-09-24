<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 페이지</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<style>
		.container1 {
			width: 1500px;
			display : block;
		}
		
		.container2 {
			display : none;
		}
		
		.container3 {
			display : none;
		}
		
		.container4 {
			display : none;
		}
		
		.btn-outline-success {
			margin-right : 10px;
		}
		
		.sort-icon {
			max-width : 20px;
		}
		
		input::-webkit-inner-spin-button {
		  appearance: none;
		  -moz-appearance: none;
		  -webkit-appearance: none;
		}
	</style>
</head>
<body>
	<%@ include file="../module/head.jsp" %>
	<div class="mt-3 mb-3" style = "text-align: center;">
		<input type="radio" class="btn-check radio-value" value = "1" name="options-outlined" id="success-outlined" autocomplete="off" checked>
		<label class="btn btn-outline-success" for="success-outlined">회원목록 조회</label>
		
		<input type="radio" class="btn-check radio-value" value = "2" name="options-outlined" id="success-outlined2" autocomplete="off">
		<label class="btn btn-outline-success" for="success-outlined2">섹션2</label>
		
		<input type="radio" class="btn-check radio-value" value = "3" name="options-outlined" id="success-outlined3" autocomplete="off">
		<label class="btn btn-outline-success" for="success-outlined3">거래내역 조회</label>
		
		<input type="radio" class="btn-check radio-value" value = "4" name="options-outlined" id="success-outlined4" autocomplete="off">
		<label class="btn btn-outline-success" for="success-outlined4">이벤트쿠폰 발행</label>
	</div>

	<section class="container-fluid container1">
	
		<div id="input-form" class="mb-3" style="margin-left : 1200px;">
			<input type="text" class="form-control form-right" id="keyword" placeholder="회원정보 검색">
		</div>
		
		<table class="table wide vertical-hidden table-hover" id="table1">
			<colgroup>
				<col class="col-auto">
				<col class="col-auto">
				<col class="col-auto">
				<col class="col-auto">
				<col class="col-auto">
				<col class="col-auto">
				<col class="col-auto">
				<col class="col-auto">
			</colgroup>
			<thead class="thead-light">
				<tr>
					<th>회원 번호 <img style="cursor:pointer" class="sort-icon" src="./static/img/sort-icon.png"></th>
					<th>이메일 <img style="cursor:pointer" class="sort-icon" src="./static/img/sort-icon.png"></th>
					<th>닉네임 <img style="cursor:pointer" class="sort-icon" src="./static/img/sort-icon.png"></th>
					<th>직업 <img style="cursor:pointer" class="sort-icon" src="./static/img/sort-icon.png"></th>
					<th>비즈니스 분야 <img style="cursor:pointer" class="sort-icon" src="./static/img/sort-icon.png"></th>
					<th>관심사 <img style="cursor:pointer" class="sort-icon" src="./static/img/sort-icon.png"></th>
					<th>분류 <img style="cursor:pointer" class="sort-icon" src="./static/img/sort-icon.png"></th>
					<th>가입일 <img style="cursor:pointer" class="sort-icon" src="./static/img/sort-icon.png"></th>
					<th> </th>
				</tr>
			</thead>
			
			<tbody id="searching">
				<c:forEach items="${datas}" var="datas">
					<c:if test="${datas.ac_index == 10 || datas.ac_index == 20}">
						<tr onclick="location.href='./detail?search=${datas.ac_name}'" style="cursor:pointer;">
							<td>${datas.ac_number}</td>
							<td>${datas.ac_email}</td>
							<td>${datas.ac_name}</td>
							<td>${datas.ac_job}</td>
							<td>${datas.ac_field}</td>
							<td>${datas.ac_interest}</td>
							<c:if test="${datas.ac_index == 10}">
								<td>일반회원</td>
							</c:if>
							<c:if test="${datas.ac_index == 20}" >
								<td>판매자</td>
							</c:if>
							<td>${datas.ac_signday}</td>
							<td class="border-hidden-right" onclick="event.cancelBubble=true">
								<button type="button" class="btn btn-danger" onclick="location.href='./admin/addBlacklist?id=${datas.ac_email}'">블랙리스트 지정</button>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</section>
	
	<section class="container-fluid container2">
		<p>
			웹크롤링 테스트(jsoup, 크몽) --> jsoup은 동적 페이지를 인식하지 못해 안되는 것으로 추정<br>
			다른 정적 페이지는 잘 됨. jsoup의 장점은 웹을 직접 열지 않고도 html만 따올 수 있음.<br>
			웹 크롤링 목표 : 사용자 닉네임 입력시 작성한 댓글 모아서 보여주기 (이건 DB에서 가져와서 굳이 필요한가??)<br>
		</p>
		<input type="text" id="craw_id" name="craw_id" class="form-control" placeholder="카테고리번호입력" style="width: 300px;">
		<input type="button" id="craw_submit" name="craw_submit" class="btn btn-warning" value="조회"/>
		
		<div class="content_craw">
			<!-- 여기엔 검색 결과물이 출력됨.(json 방식) -->
		</div>	
	</section>
	
	<section class="container-fluid container3">

		<div id="input-form" class="mb-3" style="margin-left : 1200px;">
			<input type="text" class="form-control form-right" id="keyword2" placeholder="거래정보 검색">
		</div>
		
		<table class="table wide vertical-hidden table-hover" id="table3">
			<colgroup>
				<col class="col-auto">
				<col class="col-auto">
				<col class="col-auto">
				<col class="col-auto">
				<col class="col-auto">
				<col class="col-auto">
			</colgroup>
			<thead class="thead-light">
				<tr>
					<th>거래번호</th>
					<th>상품번호</th>
					<th>구매자</th>
					<th>판매자</th>
					<th>구매일자</th>
					<th>판매가격</th>
					<th>사용 포인트</th>
					<th>사용 쿠폰</th>
					<th>실구매가</th>
				</tr>
			</thead>
			<tbody id="searching2">
				<c:forEach items="${purchaseDatas}" var="purchaseDatas">
					<tr onclick="location.href='./sellitem/itemdetail?search=${purchaseDatas.buy_seller}&itemid=${purchaseDatas.buy_itemNumber}'" style="cursor:pointer;">
						<td>${purchaseDatas.buy_number}</td>
						<td>${purchaseDatas.buy_itemNumber}</td>
						<td>${purchaseDatas.buy_buyer}</td>
						<td>${purchaseDatas.buy_seller}</td>
						<td>${purchaseDatas.buy_buyday}</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${purchaseDatas.buy_price}"/></td>
						<td>${purchaseDatas.buy_usedPoint}</td>
						<td>${purchaseDatas.buy_usedCouponName}</td>
						<td>${purchaseDatas.buy_realPrice}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	</section>
	
	<section class="container w-25 container4">
		<c:url var="addEventCouponUrl" value="/admin/addEventCoupon" />
		<form action="${addEventCouponUrl}" method="post">
			<div class="mb-3 center">
				<label class="fw-normal mb-2">쿠폰명</label>
				<input type="text" class="form-control" name="evtcouName" placeholder="이벤트명, 할인% 를 기입하세요.">
			</div>
			<div class="mb-3 center">
				<label class="fw-normal mb-2">쿠폰 마감일</label>
				<input type="date" class="form-control" name="endDate" required>
			</div>
			<div class="mb-3 center">
				<label class="fw-normal mb-2">할인%</label>
				<input type="number" id="salePercent" class="form-control" name="salePercent" onchange="noMinus()" placeholder="숫자만 입력하세요">
			</div>
			<button type="submit" class="form-control p-1 btn-outline-success text-center fw-normal">쿠폰 발행</button>
		</form>
	</section>
	
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
		
		// 회원목록 sort 
		$(document).ready(function(){
			  $('#table1 th').each(function (column) {
			    $(this).click(function() {
			      if($(this).is('.asc')) {
			        $(this).removeClass('asc');
			        $(this).addClass('desc');
			        sortdir=-1;

			      } else {
			        $(this).addClass('asc');
			        $(this).removeClass('desc'); sortdir=1;
			      }

			      $(this).siblings().removeClass('asc');
			      $(this).siblings().removeClass('desc');

			      var rec = $('#table1').find('tbody>tr').get();

			      rec.sort(function (a, b) {
			        var val1 = $(a).children('td').eq(column).text().toUpperCase();
			        var val2 = $(b).children('td').eq(column).text().toUpperCase();
			        return (val1 < val2)?-sortdir:(val1>val2)?sortdir:0;
			      });

			      $.each(rec, function(index, row) {
			          $('#table1 tbody').append(row);
			      });
			    });
			 });
			});
		
		// 회원목록 검색(키를 누를때마다)
		$(document).ready(function(){
		
		  $("#keyword").on("keyup", function() { 
		
		    var value = $(this).val().toLowerCase();
		
		    $("#searching tr").filter(function() {
		
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1) 
		
		    });
		
		  });
		
		});
		
		// 거래목록 검색(키를 누를때마다)
		$(document).ready(function(){
		
		  $("#keyword2").on("keyup", function() { 
		
		    var value = $(this).val().toLowerCase();
		
		    $("#searching2 tr").filter(function() {
		
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1) 
		
		    });
		
		  });
		
		});
		
		// jSoup 웹 크롤링(크몽)
		$("#craw_submit").click(function(){
         $.ajax({
             url :"admin/crawling",
             data :{
                 content : $("#craw_id").val(),
             },
             dataType : "json",
             type : "post",
             success:function(data){
                console.log(data.NameResult);
                console.log(data.ReviewCount);
                     $(".content_craw").append("<tr><th>"+data.NameResult+"</th><th>"+data.ReviewCount+"</th></tr>");    
                 
            	 }
        	 })
     	})
     	
     	// Selenium 크롤링
     	$("#craw_kmong").click(function(){
			$.ajax({
				url:"admin/crawling_kmong",
				data:{
					content : $("#craw_content").val(),
				},
				dataType : "json",
				type : "get",
				success:function(data){
					// 로직 작성해야함
				}
			})
     	});
		
		function noMinus () {	
			if($('#salePercent').val() < 0) {	// 할인퍼센트 마이너스 일경우
				$('#salePercent').val(0);	// 0으로
			}
		}
		
		
		
	</script>
</body>
</html>