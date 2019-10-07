<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="./member/memberJoinCheck.js">
</script>
</head>
<body>
	<div>
		<%@ include file="/home/sidebar.jsp" %>
	</div>
	<div class="w3-padding-large" style="margin-left:300px;margin-right:70px">
		<h3>로그인</h3>
		<div class="w3-container">
			<form action="/expro/LoginAction.do" method="post">
				<input class="w3-input w3-border" style="width:50%" type="text" name="id" id="id" required="" placeholder="아이디" autofocus>
				<input class="w3-input w3-border" style="width:50%" type="password" name="passwd" id="passwd" required="" placeholder="비밀번호">
				<input class="w3-button w3-text-black w3-white" type="submit" value="로그인">
				<input class="w3-button w3-text-black w3-white" type="button" value="ID/PW 찾기" onclick="location.href=''">
			</form>
		</div>
	</div>
</body>
</html>