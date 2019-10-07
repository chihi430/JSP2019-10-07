<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>${ section }-${ content.board_subject }</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(function(){
		// 추천버튼 클릭시(추천 추가 또는 추천 제거)
		$("#rec_update").click(function(){
			$.ajax({
				url: "/expro/RecUpdate.do",
                type: "POST",
                data: {
                    no: ${content.board_no},
                    id: '${id}'
                },
                success: function () {
			        recCount();
                },
			})
		})
	
		// 게시글 추천수
	    function recCount() {
			$.ajax({
				url: "/expro/RecCount.do",
                type: "POST",
                data: {
                    no: ${content.board_no}
                },
                success: function (count) {
                	$(".rec_count").html(count);
                },
			})
	    };
	    recCount(); // 처음 시작했을 때 실행되도록 해당 함수 호출
	    
		 // 로그인 클릭
	    $("#newLogin").click(function(){
			var ww=400;    //띄울 창의 넓이
			var wh=250;    //띄울 창의 높이
			
			// 중앙 좌표
			var top=(screen.availHeight-wh)/2;
			var left=(screen.availWidth-ww)/2;
			// 새창 띄움
			window.open("/expro/NewLoginForm.do", "window", "width="+ww+", height="+wh+", top="+top+", left="+left+", toolbar=no, menubar=no, scrollbars=no, resizable=no");
	    });
	})
</script>
</head>
<body>
	<div>
		<%@ include file="/home/sidebar.jsp"%>
	</div>
	<div class="w3-main w3-margin-bottom" style="margin-left: 340px; margin-right: 40px; width: 60%;">
		<div class="w3-center w3-text-white w3-round">
			<h3 style="text-transform: uppercase">${ section } </h3>
		</div>
		<div class="w3-bar">
			<c:if test="${ sel == null && find == null }">
				<button type="button" class="w3-bar-item w3-button w3-border" onclick="location.href='/expro/BoardList.do?section=${ section }&num=${ num }'">
					<i class="fa fa-bars"></i> 글 목록
				</button>
			</c:if>
			<c:if test="${ sel != null && find != null }">
				<button type="button" class="w3-bar-item w3-button w3-border" onclick="location.href='/expro/BoardList.do?section=${ section }&num=${ num }&sel=${ sel }&find=${ find }'">
					<i class="fa fa-bars"></i> 글 목록
				</button>
			</c:if>
			<!-- 로그인 하였을때 -->
			<c:if test="${ id != null }">
				<button type="button" class="w3-bar-item w3-button w3-border" onClick="location.href='/expro/BoardWriteForm.do?section=${ section }'">
					<i class="fa fa-pencil-square-o"></i> 새 글 쓰기
				</button>
			</c:if>
			<!--작성자 일때  -->
			<c:if test="${ content.id == id }">
				<button type="button" class="w3-bar-item w3-button w3-border" onClick="">
					<i class="fa fa-exchange"></i> 글 수정
				</button>
				<button type="button" class="w3-bar-item w3-button w3-border">
					<i class="fa fa-remove"></i> 글 삭제
				</button>
			</c:if>
		</div>
		<!-- 게시글 내용(작성자, 작성일, 조회수, 번호, 제목, 내용) -->
		<div class="w3-article">
			<div class="w3-border w3-padding">
				<i class="fa fa-user"></i> <a href="#">${ content.id }</a><br /> 
				<i class="fa fa-calendar"></i> <fmt:formatDate value="${ content.write_date }" pattern="yy-MM-dd" />
				<div class="w3-right">
					<span><i class="fa fa-eye"></i> ${ content.readcount }</span>&nbsp;
					<i class="fa fa-heart" style="color:red"></i>&nbsp;<span class="rec_count"></span>
				</div>
			</div>
			<div class="w3-border w3-padding">
				#${ content.board_no } <span class="w3-center w3-xlarge w3-text-blue">${ content.board_subject }</span>
			</div>
			<article class="w3-border w3-large w3-padding">${ content.board_content }</article>
		</div>
		
		<!-- 추천 기능 -->
		<div>
			<div class="w3-border w3-center w3-padding">
				<c:if test="${ id == null }">
					추천 기능은 <button type="button" id="newLogin"><b class="w3-text-blue">로그인</b></button> 후 사용 가능합니다.<br />
					<i class="fa fa-heart" style="font-size:16px;color:red"></i>
					&nbsp;<span class="rec_count"></span>					
				</c:if>
				<c:if test="${ id != null }">
					<button class="w3-button w3-black w3-round" id="rec_update">
						<i class="fa fa-heart" style="font-size:16px;color:red"></i>
						&nbsp;<span class="rec_count"></span>
					</button> 
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>