<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원 탈퇴</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(function(){
		$("form").submit(function(){
			if(!confirm("탈퇴하시겠습니까?")){
				return false;
			}
		});
		
		if(${result == 1}){
			alert("회원탈퇴 성공");
			location.href="/expro/Index.do";
		}
	})
</script>
</head>
<body>
	<div>
		<%@ include file="/home/sidebar.jsp" %>
	</div>
	<div class="w3-padding-large" style="margin-left:300px;margin-right:70px">
		<h3>비밀번호를 입력하세요. </h3>
		<form action="/expro/Withdrawal.do" method="post">
			<input type="password" name="passwd" id="passwd" class="w3-input w3-border w3-margin-bottom w3-third" autofocus="autofocus" />
			<input type="submit" value = "탈퇴" class="w3-button w3-white" />
			<input type="button" onclick="history.go(-1)" class="w3-button w3-white" value="취소"/>
		</form>
	</div>
</body>
</html>