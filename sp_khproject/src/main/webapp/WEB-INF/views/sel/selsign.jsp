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
</head>
<body>
	<header></header>
	<c:url var="selSignurl" value="/sel/selsign" />
	<form action="${selSignurl}" method="post">
		<div>
			<label>이메일</label>
			<input type="text" name="sel_email">
		</div>
		<div>
			<label>닉네임</label>
			<input type="text" name="sel_name">
		</div>
		<div>
			<label>비밀번호</label>
			<input type="password" name="sel_pw">
		</div>
		<div>
			<label>비밀번호확인</label>
			<input type="password" name="correct_pw">
		</div>
		<div>
			<label>직업</label>
			<select name="sel_job">
				<option value="테스트">테스트</option>
				<option value="테스트1">테스트1</option>
			</select>
		</div>
		<div>	
			<label>비즈니스</label>
			<select name="sel_field">
				<option value="비즈니스테스트">비즈니스테스트</option>
				<option value="비즈니스테스트2">비즈니스테스트2</option>
			</select>
		</div>
		<div>	
			<label>관심분야</label>
			<select name="sel_interest">
				<option value="관심분야테스트">관심분야테스트</option>
				<option value="관심분야테스트2">관심분야테스트2</option>
			</select>
		</div>
		<div>	
			<label>이메일수신동의</label>
			<input type="checkbox" name="sel_sendemail">
		</div>
		<div>	
			<button type="submit">보내기</button>
		</div>
	</form>
</body>
</html>