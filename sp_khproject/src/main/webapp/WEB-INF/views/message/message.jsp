<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Message</title>
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    
</head>
<body>
    <div class="container">
        <div class="title_text">
        <c:url var="Messageurl" value="/message" />
            <h2>${pr_title}</h2>
        </div>
        <div class="row">    
                <%--chatHistory와 member가 실시간 입력하는 메시지 출력 --%>
                <div id="content">
                    <c:forEach var="chatRoom" items="${chatHistory}">
                        <p>
                            <span id="chatRoomSenderName">${chatRoom.senderName}</span><br>
                            <span id="chatRoomContent">${chatRoom.content}</span><br>
                            <span id="chatRoomSendTime">${chatRoom.sendTime}</span><br>
                        </p>    
                    </c:forEach>
                </div>
                <%--메시지 입력창과 보내기 버튼 --%>
                <div class="row_3">
                    <div class="input_group" id="sendMessage">
                        <input type="text" placeholder="Message" id="message" class="form_control"/>
                        <div class="input_group_append">
                            <button id="send" class="btn btn-primary" onclick="send()">보내기</button>
                            <input type="hidden" value="${login.getNickName()}" id="buyerName"/>
                            <input type="hidden" value="${login.getEmail()}" id="buyerId"/>
                            <input type="hidden" value="${chatRoomInfo.pr_id}" id="pr_id"/>
                            <input type="hidden" value="${chatRoomInfo.sellerId}" id="sellerId"/>
                            <input type="hidden" value="${chatRoomInfo.sellerName}" id="sellerName"/>                        
                            <input type="hidden" value="${chatRoomInfo.id}" id="id"/>                        
                        </div>                    
                    </div>                
                </div>
            </div>
    </div>
</body>

<script type="text/javascript">
	ws = new WebSocket("ws://localhost/spring/chatting/cs");
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
</html>