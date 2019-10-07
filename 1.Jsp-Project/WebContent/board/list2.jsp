<%@page import="com.study.jsp.dto.BDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ page import="java.sql.*,java.text.SimpleDateFormat,java.util.Date"%>
    <c:set var="now" value="<%= new java.util.Date() %>" />
	<% String id = (String)session.getAttribute("id"); %>
	<% String bId = (String)session.getAttribute("bId"); %>	
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>list</title>

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
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
  <a class="navbar-brand" href="index.jsp">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#"><span class="sr-only">(current)</span></a>
      </li>

      <% if(id==null){%>
      	  <li class="nav-item active">      
	        <a class="nav-link" href="./member/login.jsp">로그인</a>
	      </li>      	
	  <%} else{ %>      
	      <li class="nav-item active">      
	        <a class="nav-link"><%= id %></a>
	      </li>
	  <%} %>      
      <li class="nav-item active">      
        <a class="nav-link" href="../1.Jsp-Project/list.do">자유게시판</a>
      </li>
      <li class="nav-item active">      
        <a class="nav-link" href="comehere.html">오시는길</a>
      </li>
      <!-- 
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Disabled</a>
      </li>
       -->
    </ul>
    <form class="form-inline my-2 my-lg-0">
     <ul class="navbar-nav mr-auto">    
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      </ul>
    </form>
  </div>
</nav>
<br>

<div class="container">
	<table class="table table-hover" width="600" cellpadding="0" cellspacing="0" >
		<thead class="thead-light">			
			<tr align="center">					
				<th scope="col">번호</th>
				<th scope="col">이름</th>
				<th scope="col">제목</th>
				<th scope="col">작성날짜</th>
				<th scope="col">조회수</th>
				<th scope="col">좋아요</th>
			</tr>
						
		</thead>
		 <tbody align="center">
			<c:forEach items="${list}" var="dto">
			<c:choose>
				<c:when test="${dto.bGrade eq 1}">
					<tr class="table-info">
						<th scope="row">${dto.bId}</th>
						<td>${dto.bName}</td>
						<td>
							<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>					
							<fmt:parseNumber value="${now.time / (1000*60*60*24)}" integerOnly="true" var="today"></fmt:parseNumber>
							<fmt:parseDate value="${dto.bDate}" var="chg_dttm" pattern="yyyy-MM-dd"/>
							<fmt:parseNumber value="${chg_dttm.time / (1000*60*60*24)}" integerOnly="true" var="chgDttm"></fmt:parseNumber>
							<c:choose>
							<c:when test="${today - chgDttm le 1}">
								<a href="content_view.do?bId=${dto.bId}&bName=${dto.bName}">${dto.bTitle}</a>&nbsp;<img src="./images/new.jpg" alt="gdgd" />						
							</c:when>
							<c:otherwise>
								<a href="content_view.do?bId=${dto.bId}&bName=${dto.bName}">${dto.bTitle}</a>
							</c:otherwise>
							</c:choose>
						</td>														
							<td>${dto.bDate}</td>
							<td>${dto.bHit}</td>
							<td>${dto.bLike}</td>
					</tr>					
				</c:when>
				<c:otherwise>
					<tr>
						<th scope="row">${dto.bId}</th>
						<td>${dto.bName}</td>
						<td>
							<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>					
							<fmt:parseNumber value="${now.time / (1000*60*60*24)}" integerOnly="true" var="today"></fmt:parseNumber>
							<fmt:parseDate value="${dto.bDate}" var="chg_dttm" pattern="yyyy-MM-dd"/>
							<fmt:parseNumber value="${chg_dttm.time / (1000*60*60*24)}" integerOnly="true" var="chgDttm"></fmt:parseNumber>
							<c:choose>
							<c:when test="${today - chgDttm le 1}">
								<a href="content_view.do?bId=${dto.bId}&bName=${dto.bName}">${dto.bTitle}</a>&nbsp;<img src="./images/new.jpg" alt="gdgd" />						
							</c:when>
							<c:otherwise>
								<a href="content_view.do?bId=${dto.bId}&bName=${dto.bName}">${dto.bTitle}</a>
							</c:otherwise>
							</c:choose>
						</td>														
							<td>${dto.bDate}</td>
							<td>${dto.bHit}</td>
							<td>${dto.bLike}</td>
					</tr>					
				</c:otherwise>
			</c:choose>
			</c:forEach>
		 </tbody>
		<tr>
			<td colspan="6">
			<div align="right">
			<button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="javascript:window.location='write_view.do'">글작성</button>
			</div>				    
			<form class="form-inline my-3 my-lg-0" action="../1.Jsp-Project/search.do" method="post">
			<select class="custom-select custom-select-sm" style="width:80px;height:35px;" name="option" title="검색구분">
					<option value="0" selected>선택하세요</option>
					<option value="1">제목</option>
					<option value="2">내용</option>
					<option value="3">작성자</option>					
			</select>&nbsp;&nbsp;
			
			<input class="form-control mr-sm-2" name="search" type="text" placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검 색</button>
			</form></td>			
			
		</tr>
		<tr>
			<td colspan="6"><input type="button" value="메인으로"	onclick="javascript:window.location='./member/main.jsp'"></td>			
		</tr>
	<tr>	
		<td colspan ="6">
		
		<nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		    	      
		      	<!-- 처음 -->
				<c:choose>
					<c:when test="${(page.curPage -1)< 1}">
						<li class="page-item disabled">	
						<a class="page-link" tabindex="-1" aria-disabled="false">First</a> </li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href ="list.do?page=1"  tabindex="-1" aria-disabled="true" style="color:black">First</a>						
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
							<li class="page-item active"><a class="page-link" href="#">[${fEach}]&nbsp; </a></li>
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
<script src="http://code.jquery.com/jquery.js"></script>
</html>