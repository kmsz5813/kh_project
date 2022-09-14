<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>1:1 문의</title>
<%-- <%@ include file="./module/head.jsp" %>  --%>
</head>
<body>
 <%-- <header class="mb-3">
		<%@ include file="./module/navigation.jsp" %>
	</header> --%>
	<!-- 헤더 -->
	<%@ include file="../module/head.jsp" %>
	<section class="container">
		<div style="height:500px; overflow:scroll;" id="id_chat">
		</div>
		<div>
			<form onsubmit="return sendMessage(this.context);">
				<input type="text" id="id_context" name="context">
				<button type="submit">전송</button>
			</form>
		</div>
	</section>
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