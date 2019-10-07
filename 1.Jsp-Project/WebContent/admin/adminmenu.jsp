<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    String name = (String)session.getAttribute("name");
    String id = (String)session.getAttribute("id");
    String uname = (String)session.getAttribute("uname");
    %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Admin 메뉴</title>

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
  <a class="navbar-brand" href="#">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#"><%= id %>님 안녕하세요</a>
      </li>
      <li class="nav-item active">      	      
        <a class="nav-link" href="../logout.to">로그아웃</a>
      </li>      
      <li class="nav-item active">      
        <a class="nav-link" href="../list.do">게시판 관리</a>
      </li>
      <li class="nav-item active">      
        <a class="nav-link" href="../memberlist.do">회원관리</a>
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
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">마이페이지</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">          
          <a class="dropdown-item" href="../logout.to">로그아웃</a>          
          <a class="dropdown-item" href="../comehere.html">오시는길</a>
        </div>
      </li>
      </ul>
    </form>    
  </div>
</nav>

	
	<main role="main">

  <!-- Main jumbotron for a primary marketing message or call to action -->
  <div class="jumbotron">
    <div class="container">
      <h1 class="display-3">Hello, world!</h1>
      <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
      <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more »</a></p>
    </div>
  </div>
    <div class="container">
    <h3>금주의 댓글왕 / 보드왕 </h3>
    <br><br>
    	<!-- 댓글왕 -->    
	    <div class="row">	
	    <div align="center" class="col-md-3">
		<h4>댓글왕</h4>
		 </div>		   
			<c:forEach items="${rank}" var="dto">		
			      <div class="col-md-3">
			        <h2>${dto.ranknum} 등</h2>
			        <p>${dto.replyid}</p>
			        <p>${dto.replycount}</p>
			        <p><a class="btn btn-secondary" href="#" role="button">View details »</a></p>
			      </div>
		
		    </c:forEach>
	    </div>
	    
	    <!-- 보드왕 -->
		 <div class="row">
		 <div align="center" class="col-md-3">
		 <h4>보드왕</h4>
		 </div>	    
			<c:forEach items="${board}" var="dto">
		
			      <div class="col-md-3">
			        <h2>${dto.ranknum} 등</h2>
			        <p>${dto.bname}</p>
			        <p>${dto.boardcount}</p>
			        <p><a class="btn btn-secondary" href="#" role="button">View details »</a></p>
			      </div>
		
		    </c:forEach>
	    </div>    
	    		       
    	<hr>
  </div> <!-- /container -->

</main>

<footer class="container">
  <p>© ChangHwan</p>
</footer>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script><script src="https://getbootstrap.com/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
	
	
</body>
</html>