<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>글 작성 - ${ param.section }</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%-- summernote 라이브러리 추가 --%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.4/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.4/summernote.js"></script>
<script>
	<%-- form의 textarea에 summernote 적용 --%>
	$(document).ready(function() {
		$('#content').summernote({
			height: 700,
			fontNames : [ '맑은고딕', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', ],
			fontNamesIgnoreCheck : [ '맑은고딕' ],
			focus: true
		});
	});
</script>
</head>
<body>
	<div>
		<div>
			<%@ include file="/home/sidebar.jsp"%>
		</div>
		<div class="w3-margin-top w3-main" style="margin-left: 340px; margin-right: 40px; width: 60%;">
			<form action="/expro/BoardWrite.do" method="post">
				<div class="w3-center w3-text-white w3-round">
					<h3 style="text-transform: uppercase">${ param.section }</h3>
				</div>
				<div class="w3-margin-bottom w3-border">
					<input type="text" name="board_subject" class="w3-input w3-border w3-white" autofocus placeholder="subject">
				</div>
				<div>
					<textarea name="board_content" id="content"></textarea>
				</div>
				<div align="center">
					<input type="hidden" name="section" value="${ param.section }">
					<input type="hidden" name="id" value="${ id }">
					<input type="submit" value="확인" class="w3-button w3-white w3-round-small"> &nbsp;
					<input type="button" value="취소" class="w3-button w3-white w3-round-small" onclick="history.go(-1)">
				</div>
			</form>
		</div>
	</div>
</body>
</html>