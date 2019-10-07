 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<% String id = (String)session.getAttribute("id"); %>
	<% String pw = request.getParameter("pw"); %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Jumbotron Template · Bootstrap</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/jumbotron/">
	<script src="http://code.jquery.com/jquery.js"></script>

	
	
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
      #messages{
      height: 100%;
  	  overflow-y: scroll;
      }
      
    </style>
    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/4.3/examples/jumbotron/jumbotron.css" rel="stylesheet">
    
  </head>

<body>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
  <a class="navbar-brand" href="./main.jsp">Home</a>
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
     <ul class="navbar-nav mr-auto">    
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">마이페이지</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="../logout.to">로그아웃</a>
          <a class="dropdown-item" href="./modify.jsp">정보수정</a>
          <a class="dropdown-item" href="./delmember.jsp">회원탈퇴</a>
        </div>
      </li>
      </ul>
    </form>
  </div>
</nav>
<!--  수정전 -->




<div class="container">
	<div class="mx-auto" style="width:500px;">
	  Centered element
	
	<br>
		<div class="row">
			<table width="600" height="500" border="1">
			<td>
			<textarea id="send_text" class="form-control" name="content" cols="200"
				rows="20" placeholder="보낼값" readonly></textarea>				

			</td>
			</table>
		</div>
	  <div class="row">
		    <div class="col"></div>
			    <div class="col">
				<div>
					사용자 아이디 : <%=id%>
				</div>
				<div>	
					<input type="text" id=messageinput />
				</div>
				<div>
					<button type="button" onclick="openSocket();">Open</button>
					<button type="button" onclick="send();">Send</button>
					<button type="button" onclick="closeSocket();">Close</button>		
				</div>
			    </div>
		    <div class="col">
			
		    </div>
	  </div>
	<br>
	</div>	
	<button type="button" id="jsonConvertStringSend">번역하기 </button>

	<textarea id="result_text" class="form-control" name="content"
		cols="40" rows="4" placeholder="결과값" readonly></textarea>
	
</div>

	<!-- Script to utilise the WebSocket -->
	<script type="text/javascript">
		var webSocket;
		var messages = document.getElementById("send_text");
		
		function openSocket() {
			if(webSocket != undefined && webSocket.readyState != WebSocket.CLOSED){
				writeResponse("WebSocket is already opened.");
				return;
			}
			// Create a new instance of the websocket
			// webSocket = new WebSocket("ws://localhost:8081/*projectname*/echo");
			webSocket = new WebSocket("ws://localhost:8081/1.Jsp-Project/websocketendpoint");
			
			/*
			Binds functions to the listener for the websocket.
			*/
			webSocket.onopen = function(event){
				
				if (event.data==undefined)
					return;			
				
				writeResponse(event.data);				
			};
			
			webSocket.onmessage = function(event){
				writeResponse(event.data);
			};
			
			webSocket.onclose= function(event){
				writeResponse("Connection closed");
			};
		}
			/*
				Sends the value of the text input to the server
			*/
			function send() { 
				var id = "<%=id%>";
				var text = document.getElementById("messageinput").value;
				webSocket.send(id+ " | " + text);
			}
			function closeSocket() {
				webSocket.close();				
			}
			function writeResponse(text){
				messages.innerHTML +="\n"+text;
			}
		
			
			//파파고 번역부분
			//번역을 위해서 button 이벤트를 위해서 사용하는 것
			$('#jsonConvertStringSend').click(function() {
				//번역할 object를 생성
				var test = {
					"original_str" : $("#send_text").val()
				};
				jsonSend(test);
			});
			function jsonSend(test) {
			$.ajax({
							type : "POST",
							url : "/1.Jsp-Project/NMTTestServlet",
							data : test, //json을 보내는 방법
							success : function(data) { //서블렛을 통한 결과 값을 받을 수 있습니다.
								console.log(data);
								//alert(data);
			
								//string의 값을 object 형식으로 변환합니다.
								var resulut_obj = JSON.parse(data);
								//결과값을 textarea에 넣기 위해서
								$("#result_text").val(
										resulut_obj.message.result.translatedText);
							},
							error : function(e) {
								console.log(e);
								alert('실패했습니다.');
							}
						});
					}
	</script>
	 
	
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</html>