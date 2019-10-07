<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />


<title>AirLook</title>
    <link rel="stylesheet" type="text/css" href="../css/board.css">
    <link rel="stylesheet" href="../css/reset.css" type="text/css" media="screen">
    <link rel="stylesheet" href="../css/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="../css/grid.css" type="text/css" media="screen"> 
    <script src="js/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script src="js/jquery.galleriffic.js" type="text/javascript"></script>
    <script src="js/jquery.opacityrollover.js" type="text/javascript"></script>      
<script src="../js/script.js"></script>
<script type="text/javascript">

</script>
</head>
<%
	String memid = (String) session.getAttribute("idKey"); //loginproc.jsp의 값(idKey)이 일치해야 됨

	String log = "";
	if (memid == null) {
		log = "<a href='login.jsp'>로그인</a>";
	} else {
		log = "<a href='logout.jsp'>로그아웃</a>";
	}

	String mem = "";
	if (memid == null) {
		mem = "<a href='../member/register.jsp'>회원가입</a>";
	} else {
		mem = "<a href='../member/memberupdate.jsp'>회원정보 수정</a>";
	}
%>
<div class="row-1">
	<div class="main">
		<div class="container_12">
			<div class="grid_12">
				<nav>
					<ul class="menu">
						<li><%=log%></li>
						<li><%=mem%></li>
						<li><a class="active" href="productlist.jsp">상품목록</a></li>
						<li><a href="cartlist.jsp">장바구니</a></li>
						<li><a href="orderlist.jsp">구매목록</a></li>
						<li><a href="../board/boardlist.jsp">게시판(Q&A)</a></li>	
					</ul>
				</nav>
			</div>
		</div>
	</div>
</div>