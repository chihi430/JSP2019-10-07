<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>게시판 - ${ section }</title>
</head>
<body>
	<div>
		<div>
			<%@ include file="/home/sidebar.jsp"%>
		</div>
		<div class="w3-main w3-margin-bottom" style="margin-left: 340px; margin-right: 40px; width: 60%;">
			<div class="w3-center w3-text-white w3-round">
				<h3 style="text-transform: uppercase">${ section }</h3>
			</div>
			<div>
				<c:if test="${ id != null }">
					<button type="button" class="w3-button w3-white w3-round-small" onclick="location.href='./BoardWriteForm.do?section=${ section }'">
						<i class="fa fa-pencil"></i> 글 작성
					</button>
				</c:if>
			</div>
			<div class="w3-pannel w3-bar">
				<%-- 검색창 --%>
				<form action="/expro/BoardList.do" method="get" class="w3-bar-item">
					<div>
						<input type="hidden" name="section" value="${ section }">
						<select name="sel">
							<option value="id">작성자</option>
							<option value="board_subject">제목</option>
						</select>
						<input type="text" name="find" required="">
						<button type="submit">
							<i class="fa fa-search w3-large"></i>
						</button>
					</div>
				</form>
				<%-- 정렬 방법(추후 구현) --%>
				<div class="w3-bar-item w3-right">
					<c:choose>
						<c:when test="${ sel == null && find == null }">
							<small> <a href="#">최신순</a> <a href="#">조회순</a> <a href="#">추천순</a> <a href="#">댓글순</a>
							</small>
						</c:when>
						<c:otherwise>
							<small> <a href="#">최신순</a> <a href="#">조회순</a> <a href="#">추천순</a> <a href="#">댓글순</a>
							</small>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<%-- 게시물 목록 --%>
			<div>
				<%-- 게시물이 없으면 --%>
				<c:if test="${ count == 0 }">
					<div class="w3-container w3-border">게시물이 없습니다.</div>
				</c:if>
				<%-- 게시물이 있으면 --%>
				<c:if test="${ count > 0 && sel == null && find == null}">
					<c:forEach var="li" items="${ list }">
						<div class="w3-container w3-border w3-hover-border-red" style="margin: 0px">
							#${ li.board_no } <i class="fa fa-user"></i> <a href="#">${ li.id }</a> <br /> 
							<span class="w3-xlarge w3-text-indigo w3-hover-text-white"> 
							<a href="/expro/BoardContent.do?section=${ section }&num=${ num }&no=${ li.board_no }" style="text-decoration: none;">${ li.board_subject }</a>
							</span>
							<div class="w3-right">
								<span><i class="fa fa-eye"></i>&nbsp; ${ li.readcount }</span>&nbsp; <span><i class="fa fa-calendar"></i>&nbsp; <fmt:formatDate value="${li.write_date}" pattern="yy-MM-dd" /></span>
							</div>
						</div>
					</c:forEach>
				</c:if>
				<%-- 검색하였을 경우 일치하는 조건이 없다면 --%>
				<c:if test="${ fcount == 0 && sel != null && find != null }">
					검색어와 일치하는 게시물이 없습니다.
				</c:if>
				<%-- 검색어와 일치하는 게시글이 있으면 --%>
				<c:if test="${ fcount > 0 && sel != null && find != null}">
					<c:forEach var="li" items="${ flist }">
						<div class="w3-container w3-border w3-hover-border-red" style="margin: 0px">
							#${ li.board_no } <i class="fa fa-user"></i> <a href="#">${ li.id }</a> <br /> 
							<span class="w3-xlarge w3-text-indigo w3-hover-text-white"> 
							<a href="/expro/BoardContent.do?section=${ section }&num=${ num }&no=${ li.board_no }&sel=${ sel }&find=${ find }" style="text-decoration: none;">${ li.board_subject }</a>
							</span>
							<div class="w3-right">
								<span><i class="fa fa-eye"></i>&nbsp;  ${ li.readcount }</span>&nbsp;  <span><i class="fa fa-calendar"></i>&nbsp;  <fmt:formatDate value="${li.write_date}" pattern="yy-MM-dd" /></span>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
			
			<div class="w3-center w3-border">
				<div class="w3-bar">
					<%-- 검색하지 않았을 경우 페이징 처리 --%>
					<c:if test="${ pageCount !=0 && sel == null && find == null }">
						<c:if test="${ startPage > 10 }">
							<a href="/expro/BoardList.do?section=${ section }&num=${ startPage - 10 }" class="w3-bar-item w3-button w3-small">이전</a>
						</c:if>
						<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
							<c:choose>
								<c:when test="${ i == num }">
									<span class="w3-bar-item w3-button w3-small w3-text-blue"><b>${ i }</b></span>
								</c:when>
								<c:otherwise>
									<a href="/expro/BoardList.do?section=${ section }&num=${ i }" class="w3-bar-item w3-button w3-small">${ i }</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${ pageCount > endPage }">
							<a href="/expro/BoardList.rcd?section=${ section }&num=${ startPage + 10 }" class="w3-bar-item w3-button w3-small">다음</a>
						</c:if>
					</c:if>
					
					<%-- 검색어가 있을 경우 페이징 처리 --%>
					<c:if test="${ pageCount !=0 && sel != null && find != null }">
						<c:if test="${ startPage > 10 }">
							<a href="/expro/BoardList.do?section=${ section }&num=${ startPage - 10 }&sel=${ sel }&find=${ find }" class="w3-bar-item w3-button w3-small">이전</a>
						</c:if>
						<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
							<c:choose>
								<c:when test="${ i == num }">
									<span class="w3-bar-item w3-button w3-small w3-text-blue"><b>${ i }</b></span>
								</c:when>
								<c:otherwise>
									<a href="/expro/BoardList.do?section=${ section }&num=${ i }&sel=${ sel }&find=${ find }" class="w3-bar-item w3-button w3-small">${ i }</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${ pageCount > endPage }">
							<a href="/expro/BoardList.do?section=${ section }&num=${ startPage + 10 }&sel=${ sel }&find=${ find }" class="w3-bar-item w3-button w3-small">다음</a>
						</c:if>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>