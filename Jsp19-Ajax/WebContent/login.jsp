<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery.js"></script>
<script>

function submit_ajax(){
	var queryString = $("#LoginProcess").serialize();
	$.ajax({
		url:'/Jsp19-Ajax/LoginProcess',
		type: 'post',		
		data: queryString,
		dataType:'json',
		success: function (json){
			var results = eval(json);
			if(results[0].result=="ok"){
				alert("환영합니다!")
				window.location.replace("loginResult.jsp");
			}else{
				alert(results[0].desc);
			}				
		}
	});
}

</script>


<title>로그인</title>
</head>
<body>


	<form id="LoginProcess">
	아이디 : <input type="text" name="id">
	비밀번호 : <input type="text" name="pw">
	<input type="button" value="로그인" onclick="submit_ajax()" />
	<a href="join.jsp">회원가입</a> &nbsp;
	</form>
</body>
</html>
