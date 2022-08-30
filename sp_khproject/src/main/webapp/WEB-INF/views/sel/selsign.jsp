<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<body>
	<header></header>
	<c:url var="selSignurl" value="/sel/selsign" />
	<form action="${selSignurl}" method="post">
		<div>
			<header></header>
			<label>이메일</label>
			<label class="message-label"></label><br> 
			<input type="email" name="sel_email" placeholder="이메일을 입력해주세요." required >
			<br>
			<label class="email-alert"></label>
		</div>
		<div>
			<label class="message-label"></label>
		</div>
		<div>
			<label>닉네임</label>
			<label class="message-label"></label><br>
			<input type="text" name="sel_nickname" placeholder="별명을 입력해주세요." required>
			<br>
		</div>
		<div>
			<label class="message-label"></label><br> 
		</div>
		<div>
			<label>비밀번호</label>
			<label class="message-label"></label><br>
			<input type="password" name="sel_pw" class="pw" placeholder="비밀번호를 입력해 주세요.(6자리 이상)" required>
			<br>
			<label class="pw-alert"></label>
		</div>
		<div>
			<label class="message-label"></label><br> 
		</div>
		<div>	

			<label>비밀번호확인</label>
			<label class="message-label"></label><br>
			<input type="password" name="correct_pw" class="pwpw" placeholder="비밀번호를 한 번 더 입력해 주세요." required>
			<br>
			<label class="pwpw-alert"></label>
		</div>
		<div>
			<label class="message-label"></label><br> 
		</div>
		<div>	
			<label>직업</label><br>
			<select name="sel_job">
				<option value="none" hidden>직업을 선택해 주세요.</option>
				<option value="테스트">테스트</option>
				<option value="테스트1">테스트1</option>
			</select>
			<br>
		</div>
		<div>	
			<label>비즈니스</label><br>
			<select name="sel_field">
				<option value="none" hidden>비즈니스 분야를 선택해 주세요.</option>
				<option value="비즈니스테스트">비즈니스테스트</option>
				<option value="비즈니스테스트2">비즈니스테스트2</option>
			</select>
			<br>
		</div>
		<div>	
			<label>관심분야</label><br>
			<select name="sel_interest">
				<option value="none" hidden>관심사를 선택해 주세요.</option>
				<option value="관심분야테스트">관심분야테스트</option>
				<option value="관심분야테스트2">관심분야테스트2</option>
			</select>
			<br>
		</div>
		<div>	
			<label>이메일수신동의</label>
			<input type="checkbox" name="sel_sendemail">
			<br>
			<button type="submit">가입완료</button>
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
			  if( email == '' || email == 'undefined') return;
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
			  if( pw == '' || pw == 'undefined') return;
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
			  if( pwpw == '' || pwpw == 'undefined') return;
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
	</form>
</body>
</html>