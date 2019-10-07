<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Montserrat", sans-serif;
}
.w3-sidebar {
	width: 120px;
	background: #222;
}
</style>
<title>홈페이지 만들기</title>
</head>
<body class="w3-black">
	<div>
		<nav class="w3-sidebar w3-bar-block w3-small w3-hide-small w3-center w3-top" style="width:250px;font-weight:bold;">
			
			<a href="/expro/Index.do" class="w3-bar-item w3-button w3-padding-large w3-black">
				<i class="fa fa-home w3-xxlarge"></i>
				<br />HOME
			</a>
			<c:if test="${ id == null }">
				<a href="/expro/LoginForm.do" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
	   				<i class="fa fa-connectdevelop w3-xxlarge"></i>
	  			 	<br />로그인
	  			</a>
	  			<a href="/expro/MemberJoinForm.do" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
	    			<i class="fa fa-user-plus w3-xxlarge"></i>
	    			<br />회원가입
	  			</a>
  			</c:if>
  			<c:if test="${ id != null }">
				<a href="/expro/MemberUpdateForm.do" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
					<i class="fa fa-user w3-xxlarge"></i>
					<br />${ nickname }(${ id })
				</a>
				<a href="/expro/LogOut.do" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
					<i class="fa fa-sign-out w3-xxlarge"></i>
					<br />로그아웃
				</a>
			</c:if>
			<c:if test="${ id.equals('joker') }">
				<a href="#" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
					<i class="fa fa-cogs w3-xxlarge"></i>
					<br />관리자 모드
				</a>
			</c:if>
			<a href="/expro/BoardList.do?section=menu1" class="w3-bar-item w3-button w3-padding-large w3-hover-black">메뉴1</a>
			<a href="/expro/BoardList.do?section=menu2" class="w3-bar-item w3-button w3-padding-large w3-hover-black">메뉴2</a>
			<a href="/expro/BoardList.do?section=menu3" class="w3-bar-item w3-button w3-padding-large w3-hover-black">메뉴3</a>
			<a href="/expro/BoardList.do?section=menu4" class="w3-bar-item w3-button w3-padding-large w3-hover-black">메뉴4</a>
		</nav>
	</div>
</body>
</html>