<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String id = (String)session.getAttribute("id");  %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Jekyll v3.8.5">
<title>Insert title here</title>

<link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/checkout/">


   <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 

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
    
    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/4.3/examples/checkout/form-validation.css" rel="stylesheet">    
    
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
        <a class="nav-link" href="/1.Jsp-Project/list.do">자유게시판</a>
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
<br><br><br>
<div align="center">
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="write.do" method="post">
			<tr>
				<td>이름</td>
				<td><input type="text" name="bName" size="50" value=<%= id %> readonly></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" name="bPw" size="50"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle" size="50"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea id="summernote" name="bContent" rows="10"></textarea>				
				</td>
			</tr>
				<td colspan="2">
				
					
					<input type="submit" value="입력">&nbsp;&nbsp;
					<a href="../1.Jsp-Project/list.do">목록보기</a>&nbsp;&nbsp;
					<c:choose>
						<c:when test="${write_content_view.mgrade eq 2}">						
							<input type="checkbox" name="selectInfo" value="Info">
							<label>공지작성</label>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
						
					
				</td>
		</form>
		
	</table>
</div>

<script>window.jQuery || document.write('<script src="https://getbootstrap.com/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://getbootstrap.com/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
    <script src="https://getbootstrap.com/docs/4.3/examples/checkout/form-validation.js"></script>	
</body>
</html>