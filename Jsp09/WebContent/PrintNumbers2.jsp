<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		String max =request.getParameter("max");
		
	
		if(max !=null){
			int maxValue = Integer.parseInt(max);
			for(int i=0; i<maxValue; i++){
				out.println("<h1>하이룽</h1>");
				out.println(i+"<br>");
			}	
		}else{
			out.println("숫자를 넣어주세요");
		}
		
		
	%>
</body>
</html>