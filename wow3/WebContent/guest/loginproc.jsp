<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<jsp:useBean id="memberMgr" class="member.MemberMgr"/>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String passwd = request.getParameter("passwd");

boolean b = memberMgr.loginCheck(id, passwd);

if(b){
	session.setAttribute("idKey", id);
	//response.sendRedirect("login.jsp");
	response.sendRedirect("guest_index.jsp");
}else{
	response.sendRedirect("loginfail.html");
}
%>