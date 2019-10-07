<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% //페이지에 흘러가는 화면 나오게 하려면 이렇게 만든다? 아직 잘모르겠다 감이 안오네 %>

	<h1>include01.jsp 페이지입니다.</h1>	
	<jsp:include page="include02.jsp" flush="true"/>
	<h1> 다시 include01.jsp 페이지입니다.</h1>
	
	
</body>
</html>