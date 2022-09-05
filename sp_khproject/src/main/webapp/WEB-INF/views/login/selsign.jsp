<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

	<title>전문가 가입페이지</title>
	<style>
		.message-label {
			position: relative;
			bottom: 86px;
			left: 100px;
		}
	</style>
</head>
<body>
	<header></header>

	<section class="container w-25">
		<div class="mt-5">
		<c:url var="selSignurl" value="/login/selsign" />
		<form action="${selSignurl}" method="post">
			<div class="mb-3 center">
				<p class="fw-normal fs-2 text-center">회원가입</p>
			</div>
			<div class="p-1 mb-3 bg-secondary text-white text-center fw-normal">전문가</div>
			
			<div class="mb-3">
				<label class="fw-normal mb-2">이메일</label>
				<input type="email" class="form-control" name="sel_email" placeholder="이메일을 입력해주세요." required>
				<span class="email-alert"></span>
			</div>
			<div>
				<span class="message-label"></span>
			</div>
			
			<div class="mb-3">
				<label class="fw-normal mb-2">닉네임</label>
				<input class="form-control" type="text" name="sel_name" placeholder="별명을 입력해주세요." required>
			</div>
			<div>
				<span class="message-label"></span> 
			</div>
			
			<div class="mb-3">
				<label class="mb-2">비밀번호</label>
				<input class="form-control pw" type="password" name="sel_pw" placeholder="비밀번호를 입력해 주세요.(6자리 이상)" required>
				<span class="pw-alert"></span>
			</div>
			<div>
				<span class="message-label"></span> 
			</div>
			
			<div class="mb-3">
				<label class="mb-2">비밀번호확인</label>
				<input class="form-control pwpw" type="password" name="correct_pw" placeholder="비밀번호를 한 번 더 입력해 주세요." required>
				<span class="pwpw-alert"></span>
			</div>
			<div>
				<span class="message-label"></span> 
			</div>
			
			<div class="mb-3">
				<label class="mb-2">직업</label>
				<select class="form-select" name="sel_job">
					<option value="테스트">테스트</option>
					<option value="테스트1">테스트1</option>
				</select>
			</div>
			
			<div class="mb-3">	
				<label class="mb-2">비즈니스</label>
				<select class="form-select" name="sel_field">
					<option value="비즈니스테스트">비즈니스테스트</option>
					<option value="비즈니스테스트2">비즈니스테스트2</option>
				</select>
			</div>
			
			<div class="mb-3">	
				<label class="mb-2">관심사 선택</label>
				<select class="form-select" name="sel_interest">
					<option value="관심분야테스트">관심분야테스트</option>
					<option value="관심분야테스트2">관심분야테스트2</option>
				</select>
			</div>
			
			<div class="mb-3 form-check">	
				<label class="mb-2">이메일수신동의</label>
				<input type="checkbox"  class="form-check-input" name="sel_sendemail">
			</div>
			<div class="mb-3">	
				<button type="submit" class="form-control p-1 mb-2 bg-secondary  text-center fw-normal" style="--bs-bg-opacity: .5;">가입완료</button>
			</div>
		</form>
		</div>

		<script type="text/javascript">
			window.onload = function() {
				initEventBinding();
			}
			
			/* 필수 텍스트 항목 로직 */
			function initEventBinding() {
				requiredEventBinding();
			}
			function requiredEventBinding() {
				var requiredElements = document.querySelectorAll("input[required]");
				for(let element of requiredElements) {
					element.addEventListener("blur", requriedHandler)
				}
			}	
			function requriedHandler(e) {
				var element = e.target;
				var messageControl = element.parentElement.nextElementSibling;
				var messageLabel = messageControl.getElementsByClassName("message-label")[0];	
				if (element.value) {					
					messageLabel.style.color = "";
					messageLabel.innerText = "";
				} else {					
					messageLabel.style.color = "red";
					messageLabel.innerText = "* 필수 입력 항목입니다.";
				}
			}
			
			/* 이메일 폼 체크 로직*/
			function email_check( email ) {    
			    var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
			    return (email != '' && email != 'undefined' && regex.test(email)); 
			}
			$("input[type=email]").blur(function(){
			  var email = $(this).val();
			  if( email == '' || email == 'undefined') {
				  $(".email-alert").text('');
				  return;
			  }
			  if(! email_check(email) ) {
			  	$(".email-alert").text('* 이메일 형식으로 적어주세요');
			  	$(".email-alert").css('color', 'red');
			    $(this).focus();
			    return false;
			  }else {
				$(".email-alert").text('');
			  }
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
			
		</script>
	</section>
</body>
</html>