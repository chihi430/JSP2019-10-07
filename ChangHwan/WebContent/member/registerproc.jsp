<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="memberBean" class="member.MemberBean"/>
<jsp:setProperty property="*" name="memberBean"/>

<%
out.print(memberBean.getId() + "  " + memberBean.getName());
%>
   <jsp:useBean id="memberMgr" class="member.MemberMgr"></jsp:useBean>
<%   
boolean b = memberMgr.memInsert(memberBean);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<title>Insert title here</title>
</head>
<body>
<%
if(b){
	out.println("회원가입을 축하합니다<br>");
	out.println("<a href='../guest/login.jsp'>로그인 창</a>");
}else{
	out.println("회원가입 실패! 관리자에게 문의바람<br>");
	out.println("<a href='register.jsp'>회원가입하기</a>");
}
%>
</body>
</html>