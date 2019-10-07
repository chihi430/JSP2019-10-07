<%@page import="com.study.jsp.MemberDto"%>
<%@page import="com.study.jsp.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%request.setCharacterEncoding("UTF-8"); %>
  <% 
  	String id = (String)session.getAttribute("id"); 
  	String pw = (String)session.getAttribute("pw");
  	MemberDao dao = MemberDao.getInstance();
  	MemberDto dto = dao.getMember(id);
  %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery.js"></script>
<script>
function form_check(){
	if($('#pw').val()==""){
		alert("비밀번호는 필수사항 입니다.");
		document.reg_frm.pw.focus();
		return;
	}
	if($('#pw').val() != $('#pw_check').val()){
		alert("비밀번호가 일치하지 않습니다.");
		reg_frm.pw.focus();
		return;
	}
	if($('#eMail').val().length==0){
		alert("메일은 필수사항 입니다.");
		reg_frm.eMail.focus();
		return;
	}
	
	submit_ajax();
}

function submit_ajax(){
	var queryString = $("#ModifyProcess").serialize();
	$.ajax({
		url:'/Jsp21-HW-DAO-Ajax/ModifyProcess',
		type: 'post',		
		data: queryString,
		dataType:'text',
		success: function (json){
			var result = JSON.parse(json);
			if(result.code =="success"){
				alert(result.desc)
				window.location.replace("main.jsp");
			}else{
				alert(result.desc);
			}				
		}
	});
}

</script>

<title>Insert title here</title>

</head>
<body>
	<form id="ModifyProcess">
	아이디 : <%= dto.getId() %><br>
	비밀번호 : <input type="password" id="pw" name="pw" size="20"><br>
	비밀번호확인 : <input type="password" id="pw_check" name="pw_check" size="20"><br>
	이름 : <%= dto.getName() %><br>
	메일 : <input type="text" name="eMail" id="eMail" size="20" value="<%= dto.geteMail() %>"><br>
	주소 : <input type="text" name="address" id="address" size="50" value="<%= dto.getAddress() %>"><br>
	
	<input type="button" value="수정" onclick="form_check()"> &nbsp;&nbsp;&nbsp;
	<input type="reset" value="취소" onclick="javascript:window.location='main.jsp'">
	</form>
</body>
</html>