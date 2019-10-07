<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<style></style>
		<script type="text/javascript"></script>
	</head>

	<body>
	
	<%
		String saveDir = application.getRealPath("/test/");
		int maxSize = 1024*1024*100;
		String encType = "UTF-8";
	
		MultipartRequest multipartRequest
		= new MultipartRequest(request, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
		
	    out.write("이름 : " + multipartRequest.getParameter("name") + "<br>");
	    out.write("파일 : " + multipartRequest.getParameter("file") + "<br>"); //null 값을 갖는다.
	    out.write("업로드파일명 : " + multipartRequest.getFilesystemName("file") + "<br>");
	    out.write("원래파일명 : " + multipartRequest.getOriginalFileName("file") + "<br>");
	    
	    File file = multipartRequest.getFile("file");
	%>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	파일용량 : <fmt:formatNumber value="<%=file.length() %>" groupingUsed="true"/>

	</body>
</html>
