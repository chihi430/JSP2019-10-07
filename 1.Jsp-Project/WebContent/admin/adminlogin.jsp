<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP/Servlet 21-1</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">    
    <meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Imagetoolbar" content="no" />	
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Floating labels example · Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/floating-labels/">

    <!-- Bootstrap core CSS -->
<link href="https://getbootstrap.com/docs/4.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


    <style>
      .bd-placeholder-img {
        font-size: 1 .125rem;
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
    <link href="https://getbootstrap.com/docs/4.3/examples/floating-labels/floating-labels.css" rel="stylesheet">
	
	
	<script type="text/javascript"	src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	
	<script type="text/javascript" src="chpacha.js"></script>
</head>
  
  
<body>
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	
	  <a class="navbar-brand" href="../index.jsp">Home</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
	    <ul class="navbar-nav mr-auto">
	      
	      <li class="nav-item active">      
	        <a class="nav-link" href="../list.do">자유게시판</a>
	      </li>
	      <li class="nav-item active">      
	        <a class="nav-link" href="../comehere.html">오시는길</a>
	      </li>
	      <!-- 
	      <li class="nav-item">
	        <a class="nav-link disabled" href="#">Disabled</a>
	      </li>
	       -->
	    </ul>
	    <form class="form-inline my-2 my-lg-0">
	      
	    </form>
	  </div>
	</nav>
	
	
	<div class="container">
	<form class="form-signin" action="adminloginOk.to" method="post">
		<br><br>		
		  <div class="text-center mb-4">
		    <img class="mb-4" src="https://getbootstrap.com/docs/4.3/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
		    <h1 class="h3 mb-3 font-weight-normal">Admin Login</h1>
		    
		  </div>
		  <div class="form-label-group">
		    <input type="text" name="adminid" id="inputID" class="form-control" placeholder="Admin ID" required="" autofocus="" 
		    value="">
		    <label for="inputEmail">Admin ID</label>
		  </div>	  
	
	
		  <div class="form-label-group">
		    <input type="password" name="adminpw" id="inputPassword" class="form-control" placeholder="Admin Password" required="">
		    <label for="inputPassword">Admin Password</label>
		  </div>
		    <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>	    
		   	<br>
	</form>
	</div>
</body>
</html>