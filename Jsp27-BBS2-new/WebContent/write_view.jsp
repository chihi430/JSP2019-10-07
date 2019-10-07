<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
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
</head>
<body>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="write.do" method="post">
			<tr>
				<td>이름</td>
				<td><input type="text" name="bName" size="50"></td>
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
					<a href="list.do">목록보기</a>
				</td>
		</form>
	</table>
	
</body>
</html>