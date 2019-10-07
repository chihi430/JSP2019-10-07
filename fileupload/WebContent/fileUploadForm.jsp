<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form action="fileUpload.jsp" method="post" enctype="multipart/form-data">
		<table align="center" border="1">
		<tr>
		    <td colspan="2">파일 업로드 폼</td>
		</tr>
		<tr>
		    <td>올린 사람:</td>
		    <td><input type="text" name="name"></td>
		</tr>
		<tr>
		    <td>제목:</td>
		    <td><input type="text" name="subject"></td>
		</tr>
		<tr>
		    <td>파일명1:</td>
		    <td><input type="file" name="fileName1"></td>
		</tr>
		<tr>
		    <td>파일명2:</td>
		    <td><input type="file" name="fileName2"></td>
		</tr>
		<tr>
		    <td colspan="2"><input type="submit" value="전송"></td>
		</tr>
		</table>
		</form>
</body>

</html>


