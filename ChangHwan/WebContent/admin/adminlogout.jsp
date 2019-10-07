<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
session.removeAttribute("adminKey");
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
alert("로그아웃 성공!!");
location.href="../index.jsp"; // 메인으로 이동함
</script>
</body>
</html>