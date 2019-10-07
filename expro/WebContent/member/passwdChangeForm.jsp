<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="./memberJoinCheck.js"></script>
	<script>
		$(document).ready(function(){
			if(${result == 1}){
				alert("수정 완료");
				location.href="/expro/MemberUpdateForm.do";
			};
		})
	</script>
</head>
<body>
	<div>
		<%@ include file="/home/sidebar.jsp" %>
	</div>
	<div class="w3-padding-large" style="margin-left:300px;margin-right:70px">
		<h3>비밀번호 변경</h3>
		<form action="/expro/PasswdChange.do" method="post">
			<div>
				<div>
					<p>변경하실 비밀번호를 입력해 주세요.</p>
					<div>
						<input type="password" name="oldPasswd" placeholder="현재 비밀번호" class="w3-input w3-border" required="" />
					</div>
					<div>
						<input type="password" id="passwd" name="passwd" placeholder="새 비밀번호" class="w3-input w3-border" required="" />
					</div>
					<div class="form-group">
						<input type="password" id="passwd2" name="passwd2" placeholder="새 비밀번호 확인" class="w3-input w3-border" required="" />
					</div>
					<input type="hidden" name="id" value="${ id }">
					<input type="submit" value="변경" class="w3-button w3-white w3-text-black">
					<input type="button" value="취소" onclick="location.href='/expro/MemberUpdateForm.do'" class="w3-button w3-white w3-text-black">
				</div>
			</div>
		</form>
	</div>
</body>
</html>