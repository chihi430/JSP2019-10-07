<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("ValidMem") != null) {
%>
<jsp:forward page="main.jsp"></jsp:forward>
<%
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery.js"></script>
<script>
function submit_ajax(){
	var queryString = $("#LoginProcess").serialize();
	$.ajax({
		url:'/Jsp21-HW-DAO-Ajax/LoginProcess',
		//url:'/Jsp21-HW-DAO-Ajax/loginOk.jsp',
		type: 'post',		
		data: queryString,
		dataType:'text',
		success: function (json){
			var result = JSON.parse(json);
			if(result.code=="success"){
				alert(result.desc)							
				window.location.replace("main.jsp");
			}else{
				alert(result.desc);
			}				
		}
	});
}

</script>

<title>JSP/Servlet 21-1</title>
</head>
<body>
	<form id="LoginProcess">
		아이디 : <input type="text" id="id" name="id"
			value="<%if (session.getAttribute("id") != null)
				out.println(session.getAttribute("id"));%>">
		<br> 비밀번호 : <input type="password" id="pw" name="pw"><br><p>		
			<input type="submit" value="로그인" onclick="submit_ajax()"> &nbsp;&nbsp; 
			<input type="button" value="회원가입"
				onclick="javascript:window.location='join.jsp'">
	</form>
</body>
</html>