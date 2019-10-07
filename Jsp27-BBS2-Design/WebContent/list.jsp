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

	<table class="table table-hover" width="600" cellpadding="0" cellspacing="0" border="1" >
		<thead class="thead-dark">			
			<tr align="center">
			
				<th scope="col">번호</th>
				<th scope="col">이름</th>
				<th scope="col">제목</th>
				<th scope="col">날짜</th>
				<th scope="col">히트</th>
			</tr>
			
			
		</thead>
		 <tbody align="center">
			<c:forEach items="${list}" var="dto">
			<tr>
				<th scope="row">${dto.bId}</th>
				<td>${dto.bName}</td>
				<td>
					<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
					<a href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a></td>
					<td>${dto.bDate}</td>
					<td>${dto.bHit}</td>
			</tr>
			</c:forEach>
		 </tbody>
		<tr>
			<td colspan="5"><a href="write_view.do">글작성</a></td>
		</tr>
	<tr>	
		<td colspan ="5">
		
		<nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		    
		      
		      	<!-- 처음 -->
				<c:choose>
					<c:when test="${(page.curPage -1)< 1}">
				<li class="page-item disabled">	<a class="page-link" href="#" tabindex="-1" aria-disabled="false">First</a> </li>
					</c:when>
					<c:otherwise>
				<li class="page-item"><a class="page-link" href ="list.do?page=1"  tabindex="-1" aria-disabled="true">First</a>
					</c:otherwise>
				</c:choose>			  
		   		<!-- 이전 -->
				<c:choose>
					<c:when test="${(page.curPage -1)< 1}">
						<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="list.do?page=${page.curPage - 1}">Previous</a></li>
					</c:otherwise>	
				</c:choose>
		    
		    <li class="page-item">		   
		    	<!-- 개별 페이지 -->
				<c:forEach var="fEach" begin="${page.startPage}" end="${page.endPage}" step="1">
					<c:choose>
					<c:when test="${page.curPage == fEach}">
					<li class="page-item"><a class="page-link" href="#">[${fEach}]&nbsp; </a></li>
					</c:when>
						<c:otherwise>
						<li class="page-item"><a class="page-link" href="list.do?page=${fEach}">[${fEach}]&nbsp; </a></li>					
						</c:otherwise>			
					</c:choose>
				</c:forEach>		    
		    </li>
		    <li class="page-item">
		    	<!-- 다음 -->
				<c:choose>
					<c:when test="${(page.curPage + 1) > page.totalPage}">
						<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="list.do?page=${page.curPage + 1}">Next</a></li>
					</c:otherwise>
				</c:choose>
		    </li>		    
		    
		    <li class="page-item">
		    	<!-- 끝 -->
				<c:choose>
					<c:when test="${page.curPage == page.totalPage}">
						<li class="page-item disabled"><a class="page-link" href="#">End</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="list.do?page=${page.totalPage}">End</a></li>
					</c:otherwise>
				</c:choose>		    
		  </ul>
		</nav>


		
		</td>
	</tr>
	</table>
	
	
	totalCount : ${page.totalCount}<br>
	listCount : ${page.listCount}<br>
	totalPage : ${page.totalPage}<br>
	curPage : ${page.curPage}<br>
	pageCount : ${page.pageCount}<br>
	startPage : ${page.startPage}<br>
	endPage : ${page.endPage}<br>
	
	
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</html>