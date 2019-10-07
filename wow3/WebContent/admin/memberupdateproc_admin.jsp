<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="bean" class="member.MemberBean"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="memberMgr" class="member.MemberMgr"/>   


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<title>Insert title here</title>
</head>
<body>
<%
boolean b = memberMgr.memberUpdate(bean, bean.getId());
if(b){
%>
	<script type="text/javascript">
	alert("수정성공");
	location.href = "membermanager.jsp";
	</script>
<%}else{%>
<script type="text/javascript">
	alert("수정실패");
	history.back();
	</script>

<%
}
%>
</body>
</html>