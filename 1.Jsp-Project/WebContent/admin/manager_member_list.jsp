<%@page import="com.study.jsp.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Admin 회원관리</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/jumbotron/">

    <!-- Bootstrap core CSS -->
<link href="https://getbootstrap.com/docs/4.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/4.3/examples/jumbotron/jumbotron.css" rel="stylesheet">
  </head>
<body>
<div class="container">
	<h2>회원관리</h2>
	<table class="table table-hover" width="600" cellpadding="0" cellspacing="0" border="1" >
		<thead class="thead-dark">			
			<tr align="center">
			
				<th scope="col">ID</th>
				<th scope="col">이름</th>
				<th scope="col">이메일</th>
				<th scope="col">생성날짜</th>
				<th scope="col">주소</th>
				<th scope="col">회원관리</th>
			</tr>
		</thead>
		 <tbody align="center">
		 
			<c:forEach items="${memberlist}" var="MemberDto">
			<c:choose>
				<c:when test="${MemberDto.mgrade eq '0'}">
					<tr class="table-danger">
						<th scope="row">${MemberDto.id}</th>
							<td>${MemberDto.name}</td>				
							<td>${MemberDto.eMail}</td>
							<td>${MemberDto.rDate}</td>
							<td>${MemberDto.address}</td>
							<td><a href="member_content_view.do?id=${MemberDto.id}">${MemberDto.id}</a></td>
					</tr>				
				</c:when>
				<c:otherwise>
					<tr>
						<th scope="row">${MemberDto.id}</th>
						<td>${MemberDto.name}</td>				
							<td>${MemberDto.eMail}</td>
							<td>${MemberDto.rDate}</td>
							<td>${MemberDto.address}</td>
							<td><a href="member_content_view.do?id=${MemberDto.id}">${MemberDto.id}</a></td>
					</tr>								
				</c:otherwise>
			</c:choose>
			</c:forEach>
			

		 </tbody>
		<tr>
			
			<td colspan="6">
			
			<form action="admin_search.do" method="post">
			<select name="option" title="검색구분">
					<option value="5" selected>선택하세요</option>
					<option value="1">이름</option>
					<option value="2">아이디</option>
					<option value="3">이메일</option>					
			</select>
			<input type="text" name="search">
			<input type="submit" value="검색">
			</form>	
					
			</td>			
		</tr>
		<tr>
			<td colspan="6"><input type="button" value="메인으로"	onclick="javascript:window.location='./admin/adminmenu.jsp'"></td>			
		</tr>
	<tr>	
		<td colspan ="6">
		
		<nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		    	      
		      	<!-- 처음 -->
				<c:choose>
					<c:when test="${(page.curPage -1)< 1}">
				<li class="page-item disabled">	<a class="page-link" href="#" tabindex="-1" aria-disabled="false">First</a> </li>
					</c:when>
					<c:otherwise>
				<li class="page-item"><a class="page-link" href ="memberlist.do?page=1"  tabindex="-1" aria-disabled="true">First</a>
					</c:otherwise>
				</c:choose>			  
		   		<!-- 이전 -->
				<c:choose>
					<c:when test="${(page.curPage -1)< 1}">
						<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="memberlist.do?page=${page.curPage - 1}">Previous</a></li>
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
						<li class="page-item"><a class="page-link" href="memberlist.do?page=${fEach}">[${fEach}]&nbsp; </a></li>					
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
						<li class="page-item"><a class="page-link" href="memberlist.do?page=${page.curPage + 1}">Next</a></li>
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
						<li class="page-item"><a class="page-link" href="memberlist.do?page=${page.totalPage}">End</a></li>
					</c:otherwise>
				</c:choose>		    
		  </ul>
		</nav>
		
		</td>
	</tr>
	</table>
</div>	
	
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