<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>정보수정</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(function(){ // 수정이 정상적으로 완료되었으면
		if(${result == 1}){
			alert("수정완료");
		}
	})
</script>
</head>
<body>
	<div>
		<%@ include file="/home/sidebar.jsp" %>
	</div>
	<div class="w3-padding-large" style="margin-left:300px;margin-right:70px">
		<h3>정보수정</h3>
		<div class="w3-container">
			<form action="/expro/MemberUpdate.do" method="post">
				<div class="w3-row w3-section">
					<div class="w3-col" style="width:70px">
						아이디 : 
					</div>
    				<div class="w3-rest">
						<input type="text" value="${ member.id }" disabled="disabled" class="w3-input w3-border w3-third">
    				</div>
				</div>
				<input type="hidden" name="id" value="${ member.id }">
				<div class="w3-row w3-section">
					<div class="w3-col" style="width:70px">
						이름 : 
					</div>
    				<div class="w3-rest">
						<input type="text" name="name" value="${ member.name }" required="required" class="w3-input w3-border w3-third">
    				</div>
				</div>
				<div class="w3-row w3-section">
					<div class="w3-col" style="width:70px">
						닉네임 : 
					</div>
    				<div class="w3-rest">
						<input type="text" name="nickname" value="${ member.nickname }" required="required" class="w3-input w3-border w3-third">
    				</div>
				</div>
				<div class="w3-row w3-section">
					<div class="w3-col" style="width:70px">
						이메일 : 
					</div>
    				<div class="w3-rest">
						<input type="text" name="email" value="${ member.email }" required="required" class="w3-input w3-border w3-third">
    				</div>
				</div>
				<input type="submit" value="수정" class="w3-button w3-text-black w3-white">
				<input type="button" value="비밀번호 변경" onclick="location.href='/expro/PasswdChangeForm.do'" class="w3-button w3-text-black w3-white">
				<input type="button" value="탈퇴" onclick="location.href='/expro/WithdrawalForm.do'" class="w3-button w3-text-black w3-white">
			</form>
		</div>
	</div>
</body>
</html>