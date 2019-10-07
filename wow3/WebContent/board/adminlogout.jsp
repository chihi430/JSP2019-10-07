<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<title>Insert title here</title>
</head>
<body>
<% session.removeAttribute("adminOk"); %>
로그아웃 성공<br><br>
[<a href="javascript:window.close()">창 닫기</a>]
</body>
</html>