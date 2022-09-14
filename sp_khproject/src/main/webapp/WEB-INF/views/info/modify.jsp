<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원정보 수정</title>
	<%@ include file="../module/head.jsp" %>	
	<style>
		.name_ok{
			color:#008000;
			display: none;
		}
		
		.name_already{
			color:red; 
			display: none;
		}
		
		.image-form {
			text-align : center;
		}
		
		#previewImg {
			margin-bottom : 20px;
			max-width : 250px;
			height : auto;
		}

	</style>
</head>
<body>
	<section class="container w-25">
		<div class="mt-5">
		<c:url var="modifyurl" value="/info/modify" />
			<form action="${modifyurl}" method="post" enctype="multipart/form-data">
				<div class="mb-3 center">
					<p class="fw-normal fs-2 text-center">회원정보 수정</p>
				</div>
				<c:if test="${loginData.ac_index == 10}">
					<div class="mb-3 center">
						<div class="p-1 mb-3 bg-secondary text-white text-center fw-normal">일반회원</div>
					</div>
				</c:if>
				<c:if test="${loginData.ac_index == 20}">
					<div class="mb-3 center">
						<div class="p-1 mb-3 bg-secondary text-white text-center fw-normal">전문가</div>
					</div>
				</c:if>
				<div class="image-form mb-3">
					<!-- 여기 url은 home/ 뒤에 바로 modify 가 아니라 info/ 가 붙으므로 contextPaht 경로를 앞에 붙여야 한다. -->
					<img id="previewImg" class="image-360" alt="프로필 이미지." src="${pageContext.request.contextPath}/static/img/profile/${profileImage}.png"
					onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/static/img/profile/basic.png'">
					<div class="mb-3">
						<input id="formFile" type="file" name="uploadImg" class="form-control" value="이미지 선택" accept="image/png">
					</div>
				</div>
				<div class="mb-3">
					<label class="fw-normal mb-2">이메일</label>
					<input type="email" name="mod_email" value="${loginData.ac_email}" class="form-control" placeholder ="${loginData.ac_email}" readonly ></input>
				</div>
				<div class="mb-3">
					<label class="fw-normal mb-2">닉네임</label>
					<input type="text" name="mod_name" id="name" class="form-control" onchange="checkName()" placeholder ="${loginData.ac_name}" required></input>
					<span class="name_ok">사용 가능한 닉네임입니다.</span>
					<span class="name_already">사용 중인 닉네임입니다.</span>
				</div>
				<div class="mb-3">
					<label class="fw-normal mb-2">비밀번호</label>
					<input type="password" name="mod_pw" class="form-control pw" placeholder="비밀번호를 입력해 주세요. (6자리 이상)" required></input>
					<span class="pw-alert"></span>
				</div>
				<div class="mb-3">
					<label class="fw-normal mb-2">비밀번호 확인</label>
					<input type="password" class="form-control pwpw" placeholder="비밀번호를 한번 더 입력해 주세요." required></input>
					<span class="pwpw-alert"></span>
				</div>
				<div class="mb-3">
					<label class="fw-normal mb-2">직업</label>
					<select name="mod_job" class="form-select">
						<option value="테스트">테스트</option>
						<option value="테스트1">테스트1</option>
					</select>
				</div>
				<div class="mb-3">	
					<label class="mb-2">비즈니스</label>
					<select name="mod_field" class="form-select">
						<option value="비즈니스테스트">비즈니스테스트</option>
						<option value="비즈니스테스트2">비즈니스테스트2</option>
					</select>
				</div>
				<div class="mb-3">	
					<label class="mb-2">관심사 선택</label>
					<select name="mod_interest" class="form-select">
						<option value="관심분야테스트">관심분야테스트</option>
						<option value="관심분야테스트2">관심분야테스트2</option>
					</select>
				</div>
				
				<div class="mb-3">	
					<button type="submit" class="form-control p-1 mb-2 bg-secondary  text-center fw-normal" style="-bs-bg-opacity: .5;">수정</button>
					<button type="button" onclick="location.href='/home/info'" class=" form-control p-1 mb-2 bg-secondary  text-center fw-normal" style="-bs-bg-opacity: .5;">뒤로가기</button>
				</div>
			</form>
		</div>
		<script type="text/javascript">
			window.onload = function() {
				previewImg.addEventListener("click", function(e) {
					formFile.click();
				});
				
				formFile.addEventListener("change", showImagePreview);
			}
			
			function showImagePreview(e) {
				var file = e.target.files[0];
				var imgUrl = URL.createObjectURL(file);
				previewImg.src = imgUrl;
			}
			
				
			/* 닉네임 중복검사 */
	        function checkName(){
				$('#name').blur(function(){
					 var name = $('#name').val(); //id값이 "id"인 입력란의 값을 저장
				        $.ajax({
				            url:'modify/nameCheck', //Controller에서 요청 받을 주소
				            type:'post', //POST 방식으로 전달
				            data:{name: name},
				            dataType: "json",
				            success:function(data){ //컨트롤러에서 넘어온 cnt값을 받는다
				            	var name = $('#name').val();
				            	var origin_name = ${loginData.ac_name};
				            	if(data.code === "success" || origin_name == name) {
				            	      $('.name_ok').css("display","inline-block"); 
				                      $('.name_already').css("display", "none");
				            		 return;
				            	}else if(data.code === "sameid"){
				            		 $('.name_already').css("display","inline-block");
				                     $('.name_ok').css("display", "none");
				                     $('#name').focus();
				                     
				                     $('form').on('submit', function(e) {
				                    	 e.preventDefault();
				                    	 alert("닉네임을 확인하세요.")
				                     });
				                     return false;
				            	}
				            },
				        });
					})
		        };
		        
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
			
			// 회원가입버튼눌렀을때 비밀번호가 동일하지 않으면 제출 못하게 막기
			$('form').on('submit', function(e) {
				if ($('.pw').val() != $(".pwpw").val()) { 
	                e.preventDefault();
	                alert("비밀번호가 동일하지 않습니다.");
	            }
			});
			
		</script>
	</section>
</body>
</html>