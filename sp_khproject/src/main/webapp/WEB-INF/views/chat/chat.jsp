<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html, charset=UTF-8">
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
		 <p>${itemid}</p>
         <button class="btn btn-success" type="button" onclick="location.href='${pageContext.request.contextPath}/purchase?itemid=${itemid}'" >구매하기</button>
         <button class="btn btn-danger" data-toggle="modal" data-target="#addnotesmodal">리뷰작성</button>
   </div>
	
	
	
	
	  <div class="chat_wrap">
	      <div class="header">
	         <h3>채팅방</h3>
	      </div>
      <div id="chat"></div>
      <!-- 채팅저장출력 -->
   
       
      <div class="input-div">
         <textarea id="txtMessage" cols="30" rows="10"
            placeholder="메시지를 입력한 후에 엔터키를 누르세요.">
            </textarea>
      </div>

   </div>




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
	      
	      //#char부분에 저장된것 보내기
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


</body>
</html>