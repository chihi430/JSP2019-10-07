<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<title>AirLook</title>
	<link rel="stylesheet" type="text/css" href="../css/board.css">
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/grid.css" type="text/css" media="screen"> 
    <script src="js/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script src="js/jquery.galleriffic.js" type="text/javascript"></script>
    <script src="js/jquery.opacityrollover.js" type="text/javascript"></script>      
<script src="../js/script.js"></script>
<script type="text/javascript">

</script>
</head>
<body id="page1">
<table style="width: 90%">
	<tr>
		<td style ="font-size: 24px">AirLook</td>
		</tr>
</table>

<%@ include file="guest_top.jsp" %>
<table style="width: 90%">
<% if(memid != null){%>
		<tr style="text-align:center;">
		<td style="background-color:red">
		<b><font color="white"><b><%= memid %>님! 방문을 환영합니다.</font><br>
			<img src="../images/AirLookMain.png" width="100%"/>
		</td>
	<tr>
<%}else{%>
		<tr style="text-align:center;">
		<td style="background-color:blue">
		<!-- <td style="background-image: url(../images/pic.jpg); background-size: 100%;
					background-size:100% font-size:20px;">  // 셀 배경으로 이미지 사용 가능-->
			<b><font color="white">환영합니다 고객님! 로그인 이후 사용하세요!</font></b>
			<img src="../images/AirLookMain.png" width="100%"/>
		</td>
	<tr>
<%}%>
</table>
<%@ include file="guest_bottom.jsp" %>		
</body>
</html>

