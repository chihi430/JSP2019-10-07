<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- Bootstrap CSS -->
    
<title>Insert title here</title>
</head>
<body>

<div class="container">
	<table class="table" width="500" cellpadding="0" cellspacing="0" border="1">
	 <thead class="thead-dark">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">${member_content_view.id}</th>
		</tr>
	 </thead>
	   <tbody>
	<tr>
		<th scope="row">Password</th>
		<td>${member_content_view.pw}</td>
	</tr>
	<tr>
		<th scope="row">Email</th>
		<td>${member_content_view.eMail}</td>
	</tr>
	<tr>
		<th scope="row">생성날짜</th>
		<td>${member_content_view.rDate}</td>
	</tr>
	<tr>
		<th scope="row">주소</th>
		<td>${member_content_view.address}</td>
	</tr>
</tbody>
	<tr>
		<td colspan="2">
			<button type="button" class="btn btn-outline-primary" onclick="location.href='./member/modify.jsp?id=${member_content_view.id}'">회원정보 수정</button>
			<button type="button" class="btn btn-outline-success" onclick="location.href='memberlist.do?page=<%= session.getAttribute("cpage") %>'">목록보기</button>
			<c:choose>
				<c:when test="${member_content_view.mgrade eq '0'}">
					<button type="button" class="btn btn-outline-primary" onclick="location.href='free.to?bId=${member_content_view.id}'">블랙리스트 해제</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-outline-secondary" onclick="location.href='blacklist.to?bId=${member_content_view.id}'">블랙리스트 등록</button>
				</c:otherwise>
			</c:choose>					
			<button type="button" class="btn btn-outline-danger" onclick="location.href='deleteOk.to?bId=${member_content_view.id}'">강제 탈퇴</button>
		</td>
	</tr>
	</table>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</html>

