<%@page import="nl.captcha.Captcha"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();   //콘텍스트명 얻어오기.    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

<img src="<%=ctx%>/simpleCaptcha.png"><br /> //SimpleCaptchaServlet을 통해 captcha 그림이 표시된다.
<form method="post" action="captchaSubmit.jsp">
    Answer: <input name="answer" /><input type="submit" />
</form>
</body>
</html>


