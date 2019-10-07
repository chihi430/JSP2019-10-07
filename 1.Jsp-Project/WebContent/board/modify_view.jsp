<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<% String id = (String)session.getAttribute("id"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>회원가입 · Join</title>
    <script language="JavaScript" src="member.js"></script>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/checkout/">

    <!-- Bootstrap core CSS -->
<link href="https://getbootstrap.com/docs/4.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- include summernote css/js -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>

    <script>
	    $(document).ready(function (){
	        $('#summernote').summernote({
	           options : {disableDragAndDrop : false},
	           lang: 'ko-KR',
	           height : 500,
	           toolbar : [
	              ['style', ['style']],
	              ['style', ['bold','italic','underline','strikethrough','clear']],
	              ['fontface',['fontname']],
	              ['textsize',['textsize']],
	              ['color',['color']],
	              ['alignment',['ul','ol','paragraph','lineheight']],
	              ['height',['height']],
	              ['table',['table']],
	              ['link',['link']]
	           ]
	        });
	   });
    </script>

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
    <link href="https://getbootstrap.com/docs/4.3/examples/checkout/form-validation.css" rel="stylesheet">
  </head>
<body class="bg-light">

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
  </div>
</nav>

	<div class="container">
	  <div class="py-5 text-center">
	    <img class="d-block mx-auto mb-4" src="https://getbootstrap.com/docs/4.3/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
	    <h2><%= id %>님의 게시글 수정</h2>	   
	  </div>	
	  
		   <div class="col-md-11 order-md-2">	
			<table class="table" width="1200">
			<input type= "hidden" name="bId" value="${content_view.bId}">
				<form action = "modify.do" method="post">
						<tr class="table-primary"  align="center">					
							<th scope="col"></th>
							<th scope="col"></th>						
						</tr>										
					<tr>
						<td align="center" class="table-primary">번호</td>
						<td>${content_view.bId}</td>
					</tr>
					<tr>
						<td align="center" class="table-primary">조회수</td>
						<td>${content_view.bHit}</td>
					</tr>
					<tr>
						<td align="center" class="table-primary">이름</td>
						<td>${content_view.bName}</td>
					</tr>
					<tr>
						<td align="center" class="table-primary">제목</td>
						<td><input type="text" class="form-control" name="bTitle" id="exampleInputEmail1" aria-describedby="emailHelp" value="${content_view.bTitle}"></td>
										
					</tr>
					<tr>
						<td align="center" class="table-primary">내용</td>
						<td><textarea name="bContent" id="summernote" rows="10" cols="100" ></textarea>
						<!--  에디터 -->			
						</td>
						
					</tr>
					<tr>	
						<td colspan="2"><input type="submit" value="수정"> &nbsp;&nbsp;
						<a href="content_view.do?bId=${content_view.bId}">취소</a>&nbsp;&nbsp;
						<a href="list.do?page=<%= session.getAttribute("cpage") %>">목록보기</a>&nbsp;&nbsp;
						</td>
					</tr>
				</form>		
			</table>
			</div>

	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="https://getbootstrap.com/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="https://getbootstrap.com/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
    <script src="https://getbootstrap.com/docs/4.3/examples/checkout/form-validation.js"></script>
</body>

</html>