<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("ValidMem") != null) {
%>
<jsp:forward page="main.jsp"></jsp:forward>
<%
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <script src="http://code.jquery.com/jquery.js"></script>

	<meta name="google-signin-client_id" content="1028157568180-30dt0t5r3stskgjktvanprljoi51sb6f.apps.googleusercontent.com">

	<script>
	
	//구글로그인
    function onSignIn(googleUser) {
    	var profile = googleUser.getBasicProfile();
		$('#my-signin2').css('display', 'none');
    	$('#logout').css('display', 'block');
    	$('#upic').attr('src', profile.getImageUrl());
    	$('#uname').html('[ ' +profile.getName() + ' ]');
    }
    function onFailure(error) {
    }
	function signOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	    	$('#my-signin2').css('display', 'block');
	    	$('#logout').css('display', 'none');
	    	$('#upic').attr('src', '');
	    	$('#uname').html('');
	    });
	}
    function renderButton() {
        gapi.signin2.render('my-signin2', {
	        'scope': 'profile email',
	        'width': 240,
	        'height': 50,
	        'longtitle': true,
	        'theme': 'dark',
	        'onsuccess': onSignIn,
	        'onfailure': onFailure
        });

    }
    $(document).ready(function() {
    	
    });
    ///////////////////////////페이스북 로그인///////////////////////////////////////////
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '376352213292410',
      cookie     : true,
      xfbml      : true,
      version    : 'v4.0'
    });

    FB.getLoginStatus(function(response) {
    	console.log(response);
      statusChangeCallback(response);
    });
  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  function statusChangeCallback(response) {
    if (response.status === 'connected') {
      getINFO();
    } else {
      $('#login1').css('display', 'block');
      $('#logout1').css('display', 'none');
      $('#upic1').attr('src', '');
      $('#uname1').html('');
    }
  }
	  
  function fbLogin () {
    FB.login(function(response){
      statusChangeCallback(response);
    }, {scope: 'public_profile, email'});
  }

  function fbLogout () {
    FB.logout(function(response) {
      statusChangeCallback(response);
    });
  }

  function getINFO() {
    FB.api('/me?fields=id,name,picture.width(100).height(100).as(picture_small)', function(response) {
      console.log(response);
      $('#login1').css('display', 'none');
      $('#logout1').css('display', 'block');
      $('#upic1').attr('src', response.picture_small.data.url );
      $('#uname1').html('[ ' + response.name + ' ]');
    });
  }
   
	</script>



<title>JSP/Servlet 21-1</title>
</head>
<body>
	
	<!-- 구글로그인  -->
    <div id="my-signin2"></div>
    <div id="logout" style="display: none;">
    <input type="button" onclick="signOut();" value="로그아웃" /><br>

    <img id="upic" src=""><br>
    <span id="uname"></span>
    </div>
  
    <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script><br>
    
    <!-- 페이스북로그인  -->
    
	<div id="login1" style="display: none;">
	    <input type="button" onclick="fbLogin();" value="로그인" /><br>
	</div>

	<div id="logout1" style="display: none;">
	    <img id="upic1" src=""><br>
	    <span id="uname1"></span>
	    <input type="button" onclick="fbLogout();" value="로그아웃" /><br>
	</div>
	
</body>
</html>