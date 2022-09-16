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
	<title>일반회원 가입페이지</title>
	<link rel="stylesheet" type="text/css" href="${bs5}/css/bootstrap.min.css">
	<script type="text/javascript" src="${bs5}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${jQuery}/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<%@ include file="../module/head.jsp" %>
	<style>
		.message-label {
			position: relative;
			bottom: 86px;
			left: 100px;
		}
		
		.id_ok{
		color:#008000;
		display: none;
		}
		
		.id_already{
		color:red; 
		display: none;
		}
		
		.name_ok{
		color:#008000;
		display: none;
		}
		
		.name_already{
		color:red; 
		display: none;
		}
	</style>
</head>

<body>
	<header></header>

	<section class="container w-25">
		<div class="mt-5">
		
		<c:url var="cusSignurl" value="/login/cussign" />
			<form action="${cusSignurl}" method="post">
				<div class="mb-3 center">
					<p class="fw-normal fs-2 text-center">회원가입</p>
				</div>
				<div class="p-1 mb-3 bg-secondary text-white text-center fw-normal">일반회원</div>
				
				
				<c:if test="${not empty email}">
				<div class="mb-3">
					<label class="fw-normal mb-2">이메일</label>
					<input type="email" class="form-control" value="${email}" name="cus_email" readonly>
				</div>
				</c:if>
				
				<c:if test="${empty email }">
				<div class="mb-3">
					<label class="fw-normal mb-2">이메일</label>
					<input type="email" id="id"  class="form-control" onchange="checkId()" name="cus_email" placeholder="이메일을 입력해주세요." required>
					<span id="email-alert" class="email-alert"></span>
					<span class="id_ok">사용 가능한 이메일입니다.</span>
					<span class="id_already">사용 중인 이메일입니다.</span>
				</div>
				<div>
					<span class="message-label"></span>
				</div>
				</c:if>

				<div class="mb-3">
					<label class="fw-normal mb-2">닉네임</label>
					<input class="form-control" id="name" onchange="checkName()" type="text" name="cus_name" placeholder="별명을 입력해주세요." required>
					<span class="name_ok">사용 가능한 닉네임입니다.</span>
					<span class="name_already">사용 중인 닉네임입니다.</span>
				</div>
				 
				<div>
					<span class="message-label"></span> 
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
				
				<div class="mb-3">
					<label class="mb-2">직업</label>
					<select class="form-select" name="cus_job">
						<option value="테스트">테스트</option>
						<option value="테스트1">테스트1</option>
					</select>
				</div>

				<div class="mb-3">	
					<label class="mb-2">비즈니스 분야</label>
					<select class="form-select" name="cus_field">
						<option value="비즈니스테스트">비즈니스테스트</option>
						<option value="비즈니스테스트2">비즈니스테스트2</option>
					</select>
				</div>

				<div class="mb-3">	
					<label class="mb-2">관심사 선택</label>
					<select class="form-select" name="cus_interest">
						<option value="관심분야테스트">관심분야테스트</option>
						<option value="관심분야테스트2">관심분야테스트2</option>
					</select>
				</div>
				<div class="mb-3 form-check">	
					<label id="emailCheckbox" class="mb-2">이메일인증받기</label>
					<label id="emailCheckLabel" class="mb-2" style="display:none; color: red">이메일을 확인하세요.</label>
					<input type="checkbox" id="emailCheck" class="form-check-input" name="cus_sendemail" disabled>
				</div>
				<div id="sendmail_button" class="mb-3" style="display:none;">
					<input type="text" id="auth-number" class="form-control" placeholder="이메일 인증번호 입력">
					<button type="button" id="mailAuth" class="form-control p-1 mb-2">메일 전송</button>
					<label class="mb-2" id="auth-warn-label" style="display:none; color:red;">인증번호가 일치하지 않습니다.</label>
					<label class="mb-2" id="auth-ok-label" style="display:none; color:green;">인증번호가 일치합니다.</label>
				</div>
				<div class="mb-3">	
					<button type="submit" class="form-control p-1 mb-2 bg-secondary  text-center fw-normal" style="--bs-bg-opacity: .5;">가입완료</button>
				</div>
			</form>
		</div>
		</section>
	
		<script type="text/javascript">
				
			
		
			window.onload = function() {
				initEventBinding();
				socialSign();
			}
			
			// 소셜 회원가입이면 이메일 인증 버튼 안뜨게하기
			function socialSign() {
				if($("input[name='cus_email']").prop("readonly")) {
					$('#emailCheck').css("display", "none");
					$('#emailCheckbox').css("display", "none");
				}
			}
			
			/* 필수 텍스트 항목 로직 */
			function initEventBinding() {
				requiredEventBinding();
				var authOk = false;
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
					$('messageLabel').focus();
				} else {					
					messageLabel.style.color = "red";
					messageLabel.innerText = "* 필수 입력 항목입니다.";
					$('messageLabel').focus();
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
			
			function checkEmail(){
				 var email = $('#email').val();
					if(email =='' || email == 'undefined'){
					alert();
				}
			}
			
			/* 이메일 중복검사 */
	
			function checkId(){    
		      
				$('#id').blur(function(){
		        	 var id = $('#id').val(); //id값이 "id"인 입력란의 값을 저장
		        	 
		        	$.ajax({
			            url:'cussign/idCheck', //Controller에서 요청 받을 주소
			            type:'post', //POST 방식으로 전달
			            data:{id: id},
			            dataType: "json",
			            success:function(data){ //컨트롤러에서 넘어온 data값을 받는다 
			            	if(data.code === "success" && email_check(id)){
			            	      $('.id_ok').css("display","inline-block"); 
			                      $('.id_already').css("display", "none");
			                      $('#emailCheck').prop("disabled", false);	// 이메일수신동의 disabled 풀기
			            		  return;
			            	}else if(data.code === "sameid"){
			         		     //같은아이디일때
			            		 $('.id_already').css("display","inline-block");
			                     $('.id_ok').css("display", "none");
			 					 $('#id').focus();
			 					 return false;
			            	} else {
			            		$('.id_already').css("display","none");
			            		 $('.id_ok').css("display", "none");
			            	}
			            },
			        });	
		
		        })   	        	
		        };
		     
			/* 닉네임 중복검사 */
	        function checkName(){
				$('#name').blur(function(){
					 var name = $('#name').val(); //id값이 "id"인 입력란의 값을 저장
					 var label = document.getElementsByClassName("message-label");
				        $.ajax({
				            url:'cussign/nameCheck', //Controller에서 요청 받을 주소
				            type:'post', //POST 방식으로 전달
				            data:{name: name},
				            dataType: "json",
				            success:function(data){ //컨트롤러에서 넘어온 data값을 받는다 
				            	if(data.code === "success" && label.text != ''){
				            	      $('.name_ok').css("display","inline-block"); 
				                      $('.name_already').css("display", "none");
				            		 return;
				            	}else if(data.code === "sameid"){
				            		 $('.name_already').css("display","inline-block");
				                     $('.name_ok').css("display", "none");
				                     $('#name').focus();
				                     return false; 
				            	} else {
				            		$('.name_already').css("display", "none");
				            		$('.name_ok').css("display", "none");
				            	}
				            },
				        });
				})
		 
		        }; 
		        
		        
		        /* 이메일 인증받기 클릭 시 메일전송 버튼 뜨게 하기 */
     		    $("#emailCheck").on("click",function(){
     		    	var check = document.getElementById("emailCheck");
		        	var email = $("#id").val();
     		    	if(email_check(email)) {
			        	if (check.checked == true) {
			        		$(emailCheckLabel).css("display", "none");
				        	$('#sendmail_button').css("display","inline-block");
		        		}
     		    	} else {
	        			$(emailCheckLabel).css("display", "inline-block");
	        		}
		        	
		        });
		        
		        
		        /* 메일 전송 클릭 시 이메일 전송 및 인증 확인 */
				$("#mailAuth").on("click",function(){
				    $.ajax({
				        url : "<c:url value='sendMail' />"
				        ,type:'post'
				        ,data : {"mail" : $("input[name='cus_email']").val()}
				        ,dataType: "Json"
				        ,success: function(data){
				           alert("메일이 전송되었습니다. 인증번호를 입력하세요.");
				           $('#auth-number').blur(function() {
				           		var inputCode = document.getElementById("auth-number").value;
				           		if(inputCode == data.randomNumber && inputCode != "") {
				           			$('#auth-warn-label').css("display", "none");
				           			$('#auth-ok-label').css("display", "inline-block");
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
		        
		        
				// 회원가입버튼눌렀을때 비밀번호가 동일하지 않으면 제출 못하게 막기
		        // 이메일 인증받기를 하지 않으면 제출 못하게 막기
		        // 이메일 인증번호를 입력하지 않으면 제출 못하게 막기
		        $('form').on('submit', function(e) {
					if($('.id_ok').css('display') == 'none'){
						e.preventDefault();
		                alert("이메일이 중복되어 있습니다.");
					}
					
					if($('.name_ok').css('display') == 'none'){
						e.preventDefault();
		                alert("닉네임이 중복되어 있습니다.");
					}

		            if ($('#cus_pw').val() != $("#cor_pw").val()) { 
		                e.preventDefault();
		                alert("비밀번호가 동일하지 않습니다.");
		            }
		            // 소셜 회원가입이 아닐 경우
		            if(! $("input[name='cus_email']").prop("readonly")) {
		            	// 이메일 인증받기를 체크 하지 않았을 경우
			            if (! $('#emailCheck').prop("checked")) {
			            	e.preventDefault();
			            	alert("이메일 인증받기를 체크해주세요.");
			            	$('#emailCheck').prop("disabled", false);
			            	// 인증번호 입력란이 비워져 있거나 인증을 받지 않은 경우
			            } else if ($('#auth-number').val() == '' || authOk == false) {
			            	e.preventDefault();
			            	alert("메일 인증번호를 확인하세요.");
			            }
		            }

		        });
       
		</script>
</body>
</html>