<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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


	<table class="table" width="500" cellpadding="0" cellspacing="0" border="1">
	 <thead class="thead-dark">
		<tr>
			<th scope="col">제목</td>
			<th scope="col">${content_view.bTitle}</td>
		</tr>
	 </thead>
	   <tbody>
	<tr>
		<th scope="row">번호</th>
		<td>${content_view.bId}</td>
	</tr>
	<tr>
		<th scope="row">히트</th>
		<td>${content_view.bHit}</td>
	</tr>
	<tr>
		<th scope="row">이름</th>
		<td>${content_view.bName}</td>
	</tr>
	<tr>
		<th scope="row">내용</th>
		<td>${content_view.bContent}</td>
	</tr>
</tbody>
	<tr>
		<td colspan="2">
			<button type="button" class="btn btn-outline-primary" onclick="location.href='modify_view.do?bId=${content_view.bId}'">수정</button>
			<button type="button" class="btn btn-outline-secondary" onclick="location.href='list.do?page=<%= session.getAttribute("cpage") %>'">목록보기</button>
			<button type="button" class="btn btn-outline-success" onclick="location.href='reply_view.do?bId=${content_view.bId}'">답변</button>			
			<button type="button" class="btn btn-outline-danger" onclick="location.href='delete.do?bId=${content_view.bId}'">삭제</button>
		</td>
	</tr>
	</table>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</html>