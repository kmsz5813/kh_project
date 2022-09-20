<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!--  -->
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>비밀번호찾기</title>
	<%@ include file="../module/head.jsp" %>

</head>
<body>
	<header></header>
	<section class="container w-25">
	<div class="mt-5" >
	<c:url var="seekurl" value="/login/seekpw" />
	<form action="${seekurl}" method="post">
		<div class="mb-5 center">
			<p class="fw-normal fs-2 text-center">비밀번호 찾기</p>
		</div>
		<c:if test="${empty success && empty emailtest}">
		<div class="mb-3">
			<input class="form-control" style="height:3rem" type="text" name="email" placeholder="이메일 주소">
		</div>
		</c:if>
		
		<!-- 이메일 주소로 보내기 -->
		<c:if test="${not empty success }">
		<div class="mb-3">
			<input class="form-control" style="height:3rem" type="text" value="	${email}" name="email" readonly>
		</div>
		<div id="sendmail_button" class="mb-3">
			
			<button type="button" id="mailAuth" class="form-control p-1 mb-2">메일 전송</button>
			<input type="text" id="auth-number" name="test" class="form-control" style="display:none" placeholder="이메일 인증번호 입력">
			<label class="mb-2" id="auth-warn-label" style="display:none; color:red;">인증번호가 일치하지 않습니다.</label>
			<label class="mb-2" id="auth-ok-label" style="display:none; color:green;">인증번호가 일치합니다.</label>
			<input type="hidden" id="auth-hidden" name="test1" value=""> 
		</div>
		</c:if>
		
		<!-- 이메일 주소 받으면 -->
		<c:if test="${not empty emailtest }">
			<div class="mb-3">
			<input class="form-control" style="height:3rem" type="text" value="	${email}" name="email" readonly>
			</div>
			
			<div class="mb-3">
				<label class="mb-2">비밀번호</label>
				<input class="form-control pw" type="password" id="cus_pw" name="cus_pw" placeholder="비밀번호를 입력해 주세요.(6자리 이상)" required>
				<span class="pw-alert"></span>
			</div>
			<div>
				<span class="message-label"></span> 
			</div>		
					
			<div class="mb-3">
				<label class="mb-2">비밀번호확인</label>
				<input class="form-control pwpw" type="password" id="cor_pw" name="correct_pw" placeholder="비밀번호를 한 번 더 입력해 주세요." required>
				<span class="pwpw-alert"></span>
			</div>
			<div>
				<span class="message-label"></span> 
			</div>
			
		</c:if>
		
		<!-- 이메일 주소가 없을시에-->
		<c:if test="${not empty error}">
			<div class="mb-3">
				<p style="color:red">등록하신 이메일이 없습니다.</p>
			</div>
		</c:if>
		
		
		<div>
		 <button type="submit" class="form-control p-1 mb-2 bg-secondary  text-center fw-normal" style="--bs-bg-opacity: .5; height:3rem;"> 확인</button>
		</div>



	</form>
	</div>
	</section>
	
	<script>
	 /* 메일 전송 클릭 시 이메일 전송 및 인증 확인 */
	$("#mailAuth").on("click",function(){
		$('#auth-number').css("display", "inline-block");
		
	    $.ajax({
	        url : "<c:url value='sendMail' />"
	        ,type:'post'
	        ,data : {"mail" : $("input[name='email']").val()}
	        ,dataType: "Json"
	        ,success: function(data){
	           alert("메일이 전송되었습니다. 인증번호를 입력하세요.");
	           $('#auth-number').blur(function() {
	           		var inputCode = document.getElementById("auth-number").value;
	           		if(inputCode == data.randomNumber && inputCode != "") {
	           			$('#auth-warn-label').css("display", "none");
	           			$('#auth-ok-label').css("display", "inline-block");
	           			
	           			
	           			$('input[name=test1]').attr('value','test2');
	           			authOk = true;
	           			return;
	           		} else if(inputCode != data.randomNumber){
	           			$('#auth-warn-label').css("display", "inline-block");
	           			$('#auth-ok-label').css("display", "none");
	           			authOk = false;
	           			return false;
	           		}
	           });
	           
	        },error : function(req,status,err){
	            console.log(req);
	        }
	    });
	});
	 
	/* 비밀번호 6자리 이상 로직 */
	function pw_check(pw) {
		return (pw != '' && pw != 'undefined' && pw.length > 5);
	}
	$(".pw").blur(function(){
	  var pw = $(this).val();
	  if( pw == '' || pw == 'undefined') {
		  $(".pw-alert").text('');

		  
		  return;
	  }
	  if(! pw_check(pw) ) {
	  	$(".pw-alert").text('* 6자리 이상이어야 합니다.');
	  	$(".pw-alert").css('color', 'red');
	    $(this).focus();
	    return false;
	  }else {
		$(".pw-alert").text('');
		
	  }
	});
	
	/* 비밀번호 동일 로직 */
	function pwpw_check(pwpw) {
		var pw = $(".pw").val();
		return (pwpw != '' && pwpw != 'undefined' && pwpw == pw);
	}
	$(".pwpw").blur(function(){
	  var pwpw = $(this).val();
	  if( pwpw == '' || pwpw == 'undefined') {
		  $(".pwpw-alert").text('');
		  return;
	  }
	  if(! pwpw_check(pwpw)) {
	  	$(".pwpw-alert").text('* 비밀번호가 동일하지 않습니다.');
	  	$(".pwpw-alert").css('color', 'red');
	    $(this).focus();
	    return false;
	  }else {
		$(".pwpw-alert").text('');
	  }
	});	
	 
	
	// 폼버튼을 눌렀을때 작동안하게끔 만든 로직
	 $('form').on('submit', function(e) {

		 //undefined로 앞에서 선제약을 걸어야 다음께 비밀번호 틀렸을시 넘어가는 로직이 실행된다
		 if($('#auth-number').val() !== undefined){
			 if ($('#auth-number').val() == '' || authOk == false) {
		         	e.preventDefault();
		         
		         	swal('인증번호 확인 실패!', "메일 인증번호를 확인하세요.", 'warning');
			 }	 
		 }
	
		 

		 //비밀번호 비밀번호확인 값이 다를시에 prevent

		if($('#cus_pw').val() !== 0 ){
			if ($('#cus_pw').val() != $("#cor_pw").val()) { 
	              e.preventDefault();
	         
	              swal('비밀번호 확인 실패!', "비밀번호를 확인하세요.", 'warning');
	          }	
		}
		 
		
     });
	 
	</script>
</body>
</html>