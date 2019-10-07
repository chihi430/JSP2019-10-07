<%@page import="com.study.jsp.MemberDTO"%>
<%@page import="com.study.jsp.MemberDAO"%>
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
		try{
			MemberDAO memberDAO = new MemberDAO();
			
			MemberDTO dto = new MemberDTO();
			
			dto.setId("abc");
			dto.setPw("123");
			dto.setName("박창환");
			dto.setPhone("010-1232-4564");
			dto.setGender("men");
			int nResult = memberDAO.memberInsert(dto);
			
			dto.setId("def");
			dto.setPw("456");
			dto.setName("전우치");
			dto.setPhone("010-1234-4789");
			dto.setGender("men");
			nResult = memberDAO.memberInsert(dto);
			
			dto.setId("ghi");
			dto.setPw("789");
			dto.setName("손오공");
			dto.setPhone("010-5165-5523");
			dto.setGender("men");
			nResult = memberDAO.memberInsert(dto);
			
		}catch(Exception e){
			
		}
	%>
	<br />
	<a href="memberselect.jsp">회원 정보 보기</a>
</body>
</html>