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
<%
String id = request.getParameter("id"); //id가 넘어오게 됨
String pwd = request.getParameter("pwd");

if(id.equals("ceo") && pwd.equals("1234")){
	session.setAttribute("adminOk", id);
	out.println("로그인 성공<br>");
}else{
	out.println("로그인 실패<br>");
}
%>
[<a href="javascript:window.close()">창 닫기</a>]

</body>
</html>