<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<!-- 무료아이콘 -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel=stylesheet href=../resources/chatting.css>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chat Application</title>
<style>
div.header {
	position: sticky;
	top: 0;
	background-color: blue;
}
* {
	margin: 0px;
	padding: 0px;
	
}

.chat_wrap .header {
	font-size: 14px;
	padding: 15px 0px;
	background: #F18C7E;
	color: white;
	text-align: center;
}

.chat_wrap #chat {
	padding-bottom: 100px;
	width: 100%;
}

.chat_wrap #chat .left {
	text-align: left;
}

.chat_wrap #chat .right {
	text-align: right;
}

.chat_wrap #chat {
	font-size: 12px;
}

.chat_wrap #chat .sender {
	margin: 10px 25px 0px 10px;
	font-weight: bold;
}

.chat_wrap #chat .message {
	display: inline-block;
	margin: 5px 20px 0px 10px;
	max-width: 75%;
	border: 1px solid gray;
	padding: 5px;
	border-radius: 5px;
	background-color: #FCFCFC;
	text-align: left;
}

.chat_wrap #chat .date {
	margin: 5px 20px 10px 10px;
	font-size: 10px;
}

.chat_wrap .input-div {
	position: fixed;
	bottom: 0px;
	width: 100%;
	background-color: #FFF;
	text-align: center;
	border-top: 1px solid #F18C7E;
}

#txtMessage {
	width: 100%;
	height: 80px;
	border: none;
	padding: 5px;
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



</style>
</head>
<body>

<!-- 헤더 -->
	<%@ include file="../module/head.jsp"%>
	
	<div style="text-align: right; margin: 10px;">
         <button class="btn btn-success">거래하기</button>
         <button class="btn btn-danger" data-toggle="modal" data-target="#addnotesmodal">리뷰작성</button>
   </div>
	
	
	
	
	
	<div class="chat_wrap">
		<div class="header">
			<h3>채팅방</h3>
		</div>
		<div id="chat"></div>
		<!-- 채팅저장출력 -->
		<script id="temp" type="text/x-handlebars-template">
        <div class="{{printLeftRight sender}}">
          <div class="sender">{{sender}}</div>
          <div class="message">{{message}}</div>
        </div>
		
       </script>
      <!--  <c:forEach items="${Resultdata}" var="Resultdata">
       		<p>${Resultdata.sender} :  ${Resultdata.message}</p>
  
       </c:forEach> 
             <c:forEach items="${SameData}" var="SameData">
       		<p>${SameData.sender} :  ${SameData.message}</p>
       		${SameData.writeday }
  
       </c:forEach> --> 
       
		<div class="input-div">
			<textarea id="txtMessage" cols="30" rows="10"
				placeholder="메시지를 입력한 후에 엔터키를 누르세요."></textarea>
		</div>

	</div>
</body>


<!-- 메시지 입력시 오른쪽 왼쪽으로 기입되는 방식 지정 -->
<script>
	var uid = "${sessionScope.loginData.ac_name}";
	
	Handlebars.registerHelper("printLeftRight", function(sender) {
		if (uid === sender) {
			return "right";
		} else {
			return "left";
		}
	});
	
</script>
<script>
	
	$("#txtMessage").on("keypress", function(e) {
		if (e.keyCode == 13 && !e.shiftKey) {
			e.preventDefault();
			var message = $("#txtMessage").val();
			if (message == "") {
				alert("메시지를 입력하세요.");
				$("#txtMessage").focus();
				return;
			}

			// 서버로 메시지 보내기
			sock.send(uid + "|" + message +"|" + ${data.item_id} +"|" + "${data.receiver}" );
			
			$("#txtMessage").val("");
			$("#txtMessage").focus();
		}
	})

	// 웹소캣 생성
	var sock = new WebSocket("ws://localhost/home/echo");
	sock.onmessage = onMessage;

	//서버로부터 메세지 받기...
	   function onMessage(msg) {
	      var items = msg.data.split("|");
	      var sender = items[0];
	      
	      if(sender == "delete"){
	    	  getList();
	    	  return;
	      }
	      
	      var message = items[1];
	      var id= items[2];
	      var photo = items[3];
	      var date = items[4];
	      
	      var data = {
	         "message" : message,
	         "sender" : sender,
	         "regdate" : date,
	         "id": id
	      };

	      var template = Handlebars.compile($("#temp").html());
	      $("#chat").append(template(data));

	      //스크롤바 아래 고정
	      window.scrollTo(0, $('#chat').prop('scrollHeight'));
	   }

	    function getList() {
	      $.ajax({
	         type : "get",
	         url : "/chat.json",
	         dataType : "json",
	         success : function(data) {
	            var template = Handlebars.compile($("#temp").html());
	            $("#chat").html(template(data));
	         }
	      });
	   } 
	</script>
	
	
	
<script type="text/javascript">
	ws = new WebSocket("ws://localhost/home/echo");
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
           
//            alert('모든 항목을 작성해주세요.')
//         }
        
//         if(noteTitle != 0 && noteStar != "" && noteDesc != "" && noteDesc.length < 20) {
           
//            alert('최소 20글자 이상 작성하셔야 합니다.')
//         }
        
//       if(noteTitle != 0 && noteStar != "" && noteDesc != "" && noteDesc.length > 300) {
           
//            alert('최대 300글자 이하 작성하셔야 합니다.')
//         }
        
//         if(noteTitle != 0 && noteStar != "" && noteDesc != "" && noteDesc.length >= 20 && noteDesc.length <= 300){ //전부 작성해야 submit
           
//            alert('후기를 등록하시겠습니까?')
//            $('#addnotesmodal').modal('hide');
//            $("#addnotesmodalTitle").submit();
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


</html>