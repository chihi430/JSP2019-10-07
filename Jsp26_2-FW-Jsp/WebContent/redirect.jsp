<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<hr>
	<%
		request.setAttribute("id", "abcde");
		request.setAttribute("pw", "12345");
			
		request.getRequestDispatcher("RequestObj").forward(request, response);
	%>
</body>
</html>