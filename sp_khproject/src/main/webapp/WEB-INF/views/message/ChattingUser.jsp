<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>1:1 문의</title>



<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
	


<!-- 무료아이콘 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel=stylesheet href=../resources/chatting.css>



<style type="text/css">
	
h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6 {
    font-weight: 600;
}
	
.card {
	position: relative;
	display: flex;
	flex-direction: column;
	min-width: 0;
	word-wrap: break-word;
	background-color: #fff;
	background-clip: border-box;
	border: 0 solid transparent;
	border-radius: 0;
	margin-bottom: 30px;
}
.card-body {
    flex: 1 1 auto;
    padding: 1.3rem;
    background: #f0f0f087;
    border: 1px solid #eee;
    height: 350px;
    position: relative;
}
.card p{
	font-family: "Raleway", Arial, sans-serif;
    font-weight: 400;
    font-size: 16px;
    line-height: 1.7;
}
.note-has-grid .nav-link {
    padding: .5rem
}
.note-has-grid .single-note-item .card {
    border-radius: 10px;
}
.note-has-grid .single-note-item .favourite-note {
    cursor: pointer
}
.note-has-grid .single-note-item .category-dropdown.dropdown-toggle:after {
    display: none
}
.note-has-grid .single-note-item .category [class*=category-] {
    height: 15px;
    width: 15px;
    display: none
}
.note-has-grid .single-note-item .category [class*=category-]::after {
    content: "\f0d7";
    font: normal normal normal 14px/1 FontAwesome;
    font-size: 12px;
    color: #fff;
    position: absolute
}
.note-has-grid .single-note-item.note-business .category .category-business {
    display: inline-block
}
.note-has-grid .single-note-item.note-social .category .category-social {
    display: inline-block
}
.note-has-grid .single-note-item.note-important .category .category-important {
    display: inline-block
}
.note-has-grid .single-note-item.all-category .more-options,
.note-has-grid .single-note-item.all-category.note-favourite .more-options {
    display: block
}
.note-has-grid .single-note-item.all-category.note-business .more-options,
.note-has-grid .single-note-item.all-category.note-favourite.note-business .more-options,
.note-has-grid .single-note-item.all-category.note-favourite.note-important .more-options,
.note-has-grid .single-note-item.all-category.note-favourite.note-social .more-options,
.note-has-grid .single-note-item.all-category.note-important .more-options,
.note-has-grid .single-note-item.all-category.note-social .more-options {
    display: none
}
@media (max-width:767.98px) {
	.note-has-grid .single-note-item {
	    max-width: 100%
	}
}
@media (max-width:991.98px) {
    .note-has-grid .single-note-item {
        max-width: 216px
    }
}
 
.modal-header { background: #17b7945e; } 
 
.modal-header .close {
    margin-top: -20px;
} 
.note-title {
	margin: 10px 0 0;
	text-align: center;
}
                
.star { 
	color: #ffeb00;
	font-size: 20px;
}
.note-content {
	height: 135px;
    margin: 10px;
    overflow: auto;
}
                    
.note-info {
    position: absolute;
    text-align: left;
    left: 8%;
    bottom: 5%;
}
p.note-title {
	color: black;
	font-size: 16px;
	font-weight: bolder;
}
span#singo a {
	color: #777777;
    font-size: 12px;
    position: absolute;
    right: 8%;
    bottom: 18px;
}
span#singo:hover a {
	color: #777777;
	text-decoration: underline;
}
div.col-md-12 { margin-bottom: 15px; } 
.star_on { color: #ffeb00; }
.buttonA { 
	background-color: #17B794; 
	border: none;
	color: white;
	text-align: center;
	text-decoration: none;
	display: inline-block;
    font-size: 16px;
    padding: 10px;
    border-radius: 5px;
    cursor: pointer;
    margin-right: 1px;
}
.buttonB { 
	background-color: #555555;
    border: none;
    color: white;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    padding: 10px;
    border-radius: 5px;
    cursor: pointer;
}
.buttonC { /* 검색버튼 */
	background-color: #eee; 
	color: #666;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	padding: 4px;
	border: 1px solid #aaa;
	border-radius: 3px;
	cursor: pointer;
	width: 60px;
    height: 35px;
    margin-left: 7px;
}
.buttonC:hover { /* 검색버튼 */
	background-color: #dfdfdf; 
	
}
.pagingstyle{
 	width: 50%; 
 	margin: 0 auto;
}
#searchbox{
    float: right;
    width: 270px;
    margin: 0 auto;
    padding: 20px;
}
#row {
    position: relative;
    margin-top: 97px;
}
#form-control {
	width: 180px;
    position: absolute;
    right: 25%;
    height: 35px;
}
.input-group-btn{
	position: absolute;
    right: 25%;
}
</style>


</head>
<body>
	<!-- 헤더 -->
	<%@ include file="../module/head.jsp"%>

	
	
	<div style="text-align: right; margin: 10px;">
			<button class="btn btn-success">거래하기</button>
			<button class="btn btn-danger" data-toggle="modal" data-target="#addnotesmodal">리뷰작성</button>
	</div>
	
	<div style="border-bottom: 4px dashed gray; margin-top: 10px;">
			<h3 class="text-center">견적 상담</h3>
	</div>


	<section class="container">
		<div style="height:500px; overflow:scroll;" id="id_chat">
		</div>
		
		
		<div style="text-align: right; margin: 10px;">
			<form onsubmit="return sendMessage(this.context);">
				<input name="file" type="file" class="real-upload" accept="image/png" multiple>
				<input type="text" id="id_context" name="context" style="width:900px;">
				<button type="submit" class="btn btn-info">전송</button>
			</form>
		</div>
	</section> 
	
	
	
	
	
	
	
	
 
<script type="text/javascript">
	ws = new WebSocket("ws://localhost/home/chatting/cs");
	ws.onopen = function() {
		console.log("Chatting Server Connection...");
	};
	ws.onmessage = function(data) {
		console.log(data);
		id_chat.innerHTML += data.data;
		id_chat.scrollTo(0, id_chat.scrollHeight);
	};
	ws.onclose = function() {
		console.log("Chatting Server Close...");
	};

	function sendMessage(element) {
		value = element.value;
		element.value = "";
		ws.send(value);
		element.focus();
		return false;
	}
</script>


<!-- 모달 - 별점 -->
<script type="text/javascript">
$(document).ready(function(){
	
	$('#mstar i').click(function (){
		/* 별점의 star_on 클래스 전부 제거 */ 
	    $(this).parent().children("i").removeClass("star_on"); 
	    /* 클릭한 별과, 그 앞 까지 별점에 star_on 클래스 추가 */
	    $(this).addClass("star_on").prevAll("i").addClass("star_on"); 
	    
	    var starpoint = this.id;
	    
	    $('#note-has-star').attr("value", starpoint)
		    
	    return false;
	});
});

// 채팅 데이터 받아오기
function getList() {
	$.ajax({
		type : 'get',
		url : '/chat.json',
		dataType : 'json',
		success : function(data) {
			var temp = Handlebars.compile($("#temp").html());
			$("#chat").append(temp(data));
		}
	});
}


</script>



<!-- Modal Add notes -->
<div class="modal fade" id="addnotesmodal" tabindex="-1" role="dialog" aria-labelledby="addnotesmodalTitle" style="display: none;" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content border-0">
        
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title text-white">리뷰 작성</h5>
                <button data-dismiss="modal" type="button" class="close text-white" aria-label="Close">
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>
                </button>
            </div>
            
            <div class="modal-body">
                <div class="notes-box">
                
                    <div class="notes-content">
                        <form action="${pageContext.request.contextPath}/board/review/insert" method="post" id="addnotesmodalTitle">
                            <div class="row">
                            
                                <div class="col-md-12 mb-3">
                                    <div class="modal-title">
                                        <label>구매 상품 목록</label><br>
                                        <select name="lessonList" id="note-has-title">
                                        	<c:if test="${empty lesson}">
                                        		<option value="0">---리뷰를 작성할 수 있는 거래가 없습니다---</option>
                                        	</c:if>
                                        	<c:if test="${not empty lesson}">
                                        			<option value="0">---리뷰를 작성할 거래를 선택하세요---</option>
                                        		<c:forEach items="${lesson }" var="l" varStatus="">
                                        			<option value="${l.CONN_LESSON_NO}">${l.LESSON_TITLE} / ${l.USER_NAME} 선생님</option>
                                        		</c:forEach>
                                        	</c:if>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <div class="modal-star">
                                        <label>별점</label>
                                        <div id="mstar">
                                        	<i class="fa fa-star fa-2x" id="1"></i>
                                        	<i class="fa fa-star fa-2x" id="2"></i>
                                        	<i class="fa fa-star fa-2x" id="3"></i>
                                        	<i class="fa fa-star fa-2x" id="4"></i>
                                        	<i class="fa fa-star fa-2x" id="5"></i>
                                        </div>
                                        <input type="hidden" id="note-has-star" name="modal-star" class="form-control" minlength="25" placeholder="Title" />
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="modal-description">
                                        <label>내용</label>
                                        <textarea id="note-has-description" name="modal-desc" class="form-control" minlength="60" rows="5" 
                                        placeholder="※ 등록된 후기는 수정이 불가능합니다 .&#13;&#10;※ 최소 20글자, 최대 300글자를 입력하실 수 있습니다."></textarea>
                                    </div>
                                </div>
                                
                            </div>
                        </form>
                    </div>
                    
                </div>
            </div>
            
            <div class="modal-footer">
                  <button id="btn-n-add" class="buttonA" onclick="formCheck();">작성</button>
               <button class="buttonB" data-dismiss="modal">취소</button>
            </div>
            
        </div>
    </div>
</div> <!-- end modal -->

<script type="text/javascript">
$('#addnotesmodal').modal('toggle');
function formCheck() {
	formData = new FormData(addnotesmodalTitle);

	addnotesmodalTitle["modal-desc"].value = ""; //작성누르면 초기화 됨
	for (const [key, value] of formData) {
		console.log(key, ":", value);
	}
	$.ajax({
		url: addnotesmodalTitle.action,
		data: formData
		
	});
}
$(function() {
	//모달창 띄우기
    // $('#addnotesmodal').modal('toggle');
	
    //작성 버튼 클릭 시
//     $("#btn-n-add").on('click', function(event) {
    	
//         var noteTitle = document.getElementById('note-has-title').value; //conn_lesson_no
//         var noteStar = document.getElementById('note-has-star').value; //내용
//         var noteDesc = document.getElementById('note-has-description').value; //별점
        
// //         console.log(noteTitle)
// //         console.log(noteStar)
// //         console.log(noteDesc)
        
        
//         if(noteTitle == 0 || noteStar == "" || noteDesc == ""){
        	
//         	alert('모든 항목을 작성해주세요.')
//         }
        
//         if(noteTitle != 0 && noteStar != "" && noteDesc != "" && noteDesc.length < 20) {
        	
//         	alert('최소 20글자 이상 작성하셔야 합니다.')
//         }
        
// 		if(noteTitle != 0 && noteStar != "" && noteDesc != "" && noteDesc.length > 300) {
        	
//         	alert('최대 300글자 이하 작성하셔야 합니다.')
//         }
        
//         if(noteTitle != 0 && noteStar != "" && noteDesc != "" && noteDesc.length >= 20 && noteDesc.length <= 300){ //전부 작성해야 submit
        	
//         	alert('후기를 등록하시겠습니까?')
// 	        $('#addnotesmodal').modal('hide');
//         	$("#addnotesmodalTitle").submit();
//         }
        
//     });
    
//     //모달이 닫히면서 실행되는 함수 - 모달 내용 초기화
//     $('#addnotesmodal').on('hidden.bs.modal', function (event) {
    	
//         event.preventDefault();
        
//         document.getElementById('note-has-title').value = '';
//         document.getElementById('note-has-description').value = '';
//         $('#mstar i').parent().children("i").removeClass("star_on"); 
//         document.getElementById('note-has-star').value = '';
        
//     })
})
</script>
		    
</body>
</html>