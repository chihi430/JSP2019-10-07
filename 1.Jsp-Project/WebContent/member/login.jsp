<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("ValidMem") != null) {
%>
	<jsp:forward page="main.jsp"></jsp:forward>
<%
	}

	String ctx = request.getContextPath();	//콘텍스트명 얻어오기.	
	response.setHeader("Pragma-directive", "no-cache");
	response.setHeader("Cache-directive", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires",0); 
%>

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
        <a class="nav-link" href="#"><span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">      
        <a class="nav-link" href="./login.jsp">로그인</a>
      </li>
      <li class="nav-item active">      
        <a class="nav-link" href="../list.do">자유게시판</a>
      </li>
      <li class="nav-item active">      
        <a class="nav-link" href="../comehere.html">오시는길</a>
      </li>
      <li class="nav-item active">      
        <a class="nav-link" href="../admin/adminlogin.jsp">관리자 로그인</a>
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

	<form class="form-signin" action="../loginOk.to" method="post">
	<br><br>
		
	  <div class="text-center mb-4">
	    <img class="mb-4" src="https://getbootstrap.com/docs/4.3/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
	    <h1 class="h3 mb-3 font-weight-normal">Login</h1>
	    <p>Build form controls with floating labels via the <code>:placeholder-shown</code> pseudo-element. <a href="https://caniuse.com/#feat=css-placeholder-shown">Works in latest Chrome, Safari, and Firefox.</a></p>
	  </div>
	  <div class="form-label-group">
	    <input type="text" name="id" id="inputID" class="form-control" placeholder="Email address" required="" autofocus="" 
	    value="<%if (session.getAttribute("id") != null) out.println(session.getAttribute("id"));%>">
	    <label for="inputEmail">ID</label>
	  </div>	  


	  <div class="form-label-group">
	    <input type="password" name="pw" id="inputPassword" class="form-control" placeholder="Password" required="">
	    <label for="inputPassword">Password</label>
	  </div>
	    <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
	    <button class="btn btn-lg btn-primary btn-block" onclick="javascript:window.location='join.jsp'">Sign in</button>
	   	<br>		
	   						
	 <!--  문자 캡차 -->
	 <div class="form-label-group" align="center">						
			<div id="catpcha"></div>
			<div id="audiocatpch" style="display: none;"></div><br>
			<button type="button" class="btn btn-outline-primary" id="reLoad">새로고침</button>
			<button type="button" class="btn btn-outline-primary" id="soundOn">음성듣기</button>
			<button type="button" class="btn btn-outline-primary" id="soundOnKor">한글음성</button>				
			<input type="password" name="answer" id="answer" class="form-control" placeholder="Password" required="">
			
			
	 </div>
	 <!-- 카카오 로그인 -->
	 <div class="form-label-group" align="center">	
			<div id="login" style="display: block">			   
			    <a id="custom-login-btn"  href="javascript:loginWithKakao()">
			    <img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/>
			    </a>			   
			</div>
			
			<div id="logout" style="display: none;">
			    <input type="button" class="btn btn-success" onclick="signOut();" value="로그아웃" /><br>
			</div>
			    <img id="upic" src=""><br>
			    <span id="uname"></span>
			   	<span id="uname"></span>
	</div>				
			<p class="mt-5 mb-3 text-muted text-center">© ChangHwan</p>					
		</form>	

	<!-- 카카오로그인 ajax -->
	<script type='text/javascript'>
    Kakao.init('e820e3f1ab9cb28c526d4388a4a2ab44');
    function loginWithKakao() {
      // 로그인 창을 띄웁니다.
      Kakao.Auth.login({
        success: function(authObj) {
          //alert(JSON.stringify(authObj));
          signIn(authObj);
          console.log(authObj);          
          //window.location.replace("main.jsp");          
        },
        fail: function(err) {
          alert(JSON.stringify(err));
        }
      });
    };

    function signIn(authObj) {
        //console.log(authObj);
        Kakao.API.request({
            url: '/v2/user/me',
            success: function(res) {
                //console.log(res);                                              

               console.log(res.properties.nickname);
                console.log(res.id);
                $('#login').css('display', 'none');
               	$('#logout').css('display', 'block');
                $('#upic').attr('src', res.properties.thumbnail_image );
               	var uname = $('#uname').html(res.properties.nickname);
               	<% // session.setAttribute( "name" , uname ); %>
               	
             }        
         })
	}

    function signOut() {
	    Kakao.Auth.logout(function () {
	    	$('#login').css('display', 'block');
	    	$('#logout').css('display', 'none');
	    	$('#upic').attr('src', '');
	    	$('#uname').html('');
	    });
	}
    
    
</script>	
</body>

</html>