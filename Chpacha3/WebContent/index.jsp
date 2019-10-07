<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	
	response.setHeader("Pragma-directive", "no-cache");
	response.setHeader("Cache-directive", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires",0); 
%>
<!DOCTYPE  html>
<html>
<head>
<title>CaptCha 예제3</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Imagetoolbar" content="no" />

<script type="text/javascript"	src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript" src="chpacha.js">
</script>
</head>
<body>
	<div id="catpcha"></div>
	<div id="audiocatpch" style="display: none;"></div>

	<input id="reLoad" type="button" value="새로고침" />
	<input id="soundOn" type="button" value="음성듣기" />
	<input id="soundOnKor" type="button" value="한글음성" />
	<br />
	<input type="text" id="answer" name="answer" value="" />
	<input type="button" id="frmSubmit" value="확인" />
</body>
</html>