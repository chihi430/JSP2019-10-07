<%@page import="com.study.jsp.dao.BDao"%>
<%@page import="com.study.jsp.dao.BLikeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.study.jsp.dto.BDto"%> 
    <%@page import="com.study.jsp.dto.BLikeDto" %>
    <%@page import="com.study.jsp.dto.BReplyDto" %>    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
     <% String id = (String)session.getAttribute("id"); %> 
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="">
    <!-- Bootstrap CSS -->
    
<title>Insert title here</title>
    <style>
   	  .boxReply { overflow:hidden; border:1px solid #b3bcc4; box-sizing:border-box; color:#707070; }
      /* 헤더 */
      .reply-head { overflow:hidden; padding:20px; color:#444; }
      .reply-head .info { overflow:hidden; }
      .reply-head .info span { float:left; display:block; font-size:0.9em; }
      .reply-head .info strong { margin-left:3px; color:#e60000; line-height:160%; }
      .reply-head .info span.warning { float:right; }
      .reply-head .write { clear:both; overflow:hidden; position:relative; }
      .reply-head .write textarea { float:left; width:86%; margin-right:10px; height:70px;  }
      .reply-head .write button { position:absolute; right:50px; top:0; width:93px; height:70px; overflow:hidden; margin-left:10px; text-align:center;  text-indent:-1000px; color:#fff; font-size:1.2em; background:url('/1.Jsp-Project/images/btnReply.gif') 0 0 no-repeat #e60013; }
      .reply-head p { margin-top:10px; }
      .reply-head  p strong { margin:0 3px 0 0; color:#e60000;  }   
      .reply_file { width:75% }   
      .boxReply textarea { padding:8px; border:1px solid #abadb3; background:#fff; box-sizing:border-box; }
    
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
      #tablescroll{
      height: 500px;
  	  overflow-y: scroll;
      }
     #likescroll{
      height: 200px;
  	  overflow-y: scroll;
      }
    </style>


</head>
<body>
<div class="container">
  <!-- Content here -->

	<table class="table" width="500" cellpadding="0" cellspacing="0" >
	 <thead class="thead-dark">
		<tr>
			<th scope="col">제목</th>
			<th scope="col">${content_view.bTitle}</th>
		</tr>
	 </thead>
	   <tbody>
	<tr>
		<th scope="row">번호</th>
		<td>${content_view.bId}</td>
	</tr>
	<tr>
		<th scope="row">조회수</th>
		<td>${content_view.bHit}</td>
	</tr>
	<tr>
		<th scope="row">이름</th>
		<td>${content_view.bName}</td>
	</tr>
	<tr>
		<th scope="row">내용</th>
		<td>${content_view.bContent}</td>
	</tr>
		</tbody>
	<tr>
		<td colspan="2">		
			<!-- 좋아요,안좋아요 -->
			<c:choose>			
				<c:when test="${like_content_view.likeid eq id}">				
					<a href="like.do?bId=${content_view.bId}&check=unlike"><img src="/1.Jsp-Project/images/like.PNG"/></a>
					
						<!-- 좋아요누른사람 조회 하기  버튼  -->
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong">
						  ${content_view.bLike}
						</button>
						
						<!-- 좋아요한사람 조회하는 창 생성 -->
						<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLongTitle">좋아요누른 회원</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
							      <div id="likescroll">
							      <c:forEach items="${findmember}" var="dto">
							      ${dto.likeid}
							      <hr>
							      </c:forEach>
							       </div> 						       
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>						        
						      </div>
						    </div>
						  </div>
						</div>
										
				</c:when>
				<c:otherwise>
					<a href="like.do?bId=${content_view.bId}&check=like"><img src="/1.Jsp-Project/images/unlike1.PNG"/></a>
					
						<!-- 좋아요누른사람 조회 하기  버튼  -->
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong">
						  ${content_view.bLike}
						</button>
						
						<!-- 좋아요한사람 조회하는 창 생성 -->						
						<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLongTitle">좋아요누른 회원들</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
							      <div id="likescroll">
							      <c:forEach items="${findmember}" var="dto">
							      ${dto.likeid}
							      <hr>
							      </c:forEach>
							       </div> 
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>						        
						      </div>
						    </div>
						  </div>
						</div>
															
				</c:otherwise>
			</c:choose>	
					 
			<button type="button" class="btn btn-outline-secondary" onclick="location.href='list.do?page=<%= session.getAttribute("cpage") %>'">목록보기</button>
						
			<c:choose>
				<c:when test="${id eq content_view.bName}">
					<button type="button" class="btn btn-outline-primary" onclick="location.href='modify_view.do?bId=${content_view.bId}'">수정</button>
				</c:when>
				<c:when test="${member_view.mgrade eq 2}">
					<button type="button" class="btn btn-outline-primary" onclick="location.href='modify_view.do?bId=${content_view.bId}'">수정</button>
				</c:when>				
				<c:otherwise>
					<button type="button" class="btn btn-outline-primary" onclick="location.href='modify_view.do?bId=${content_view.bId}'" disabled>수정</button>
				</c:otherwise>
			</c:choose>			
			<c:choose>
				<c:when test="${id eq content_view.bName}">
					<button type="button" class="btn btn-outline-danger" onclick="location.href='delete.do?bId=${content_view.bId}'">삭제</button>
				</c:when>
				<c:when test="${member_view.mgrade eq 2}">
					<button type="button" class="btn btn-outline-danger" onclick="location.href='delete.do?bId=${content_view.bId}'">삭제</button>
				</c:when>				
				<c:otherwise>
					<button type="button" class="btn btn-outline-danger" disabled>삭제</button>
				</c:otherwise>
			</c:choose>	
			
			<div align="left" class="custom-control custom-checkbox"></div>						
			
		</td>
	</tr>
	
	</table>
		<!-- 댓글 쓰는 곳 -->
		<div class="container">
		<table class="table" width="500" cellpadding="0" cellspacing="0">
		
		 	<form action ="reply.do?bId=${content_view.bId}&bName=${dto.bName}" method="post">	 		 				
			<tr>
				<div class="boxReply">					
				<div class="reply-head">
			        <p class="info">
			            <span><%=id %><strong id="_countComment">총${reply_totalcount.totalreplyCount}개</strong></span>
			            <span class="warning">욕설, 상업적인 내용, 특정인이나 특정사안을 비방하는 내용 등은 예고 없이 삭제될 수 있습니다.</span>
			         </p>
			        <p class="write">
			           <textarea name="replybContent" title="댓글 입력" class="txtArea" placeholder="댓글을 입력해주세요."></textarea>
			            <button type="submit">댓글 달기</button>
			        </p>
					</div>
				</div>
			</tr>			
			</form>	
			
		</table>	
		</div>
		
	
	<!-- 댓글 달리는곳  -->
	<div id="tablescroll">
	<table class="table" width="500" height="200" cellpadding="0" cellspacing="0">
		
		<h4>댓글목록</h4>
		<c:forEach items="${reply_view}" var="reply">		
		<tr>
			<td>ID/작성일 <br>
				내용  </td>
			<td>${reply.replyid} / ${reply.replyDate}<br>
				${reply.replybcontent}				
			</td>
			<td>
			<input type="hidden" name="replynum" value="${reply.replynum}">
			<c:choose>
				<c:when test="${reply.replyid eq id || member_view.mgrade eq 2}">
					<div class="btn-group" width="20px">
					  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					  </button>
					  <div class="dropdown-menu">
					    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#exampleModal" data-whatever="@fat">수정하기</a>
					    <!--  수정  -->							    
					    <div class="dropdown-divider"></div>
					    <a class="dropdown-item" href="replydelete.do?replynum=${reply.replynum}&bId=${content_view.bId}">삭제하기</a>					    					    					   
					  </div>
					</div>
					
						<!-- 댓글 수정 -->
						<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">New message</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      
						      <form action="replymodify.do?replynum=${reply.replynum}&bId=${content_view.bId}" method="post">
						      <div class="modal-body">						        
						          <div class="form-group">
						            <label for="recipient-name" class="col-form-label">아이디</label>
						            <input type="text" class="form-control" id="recipient-name" value="${reply.replyid}" readonly>
						          </div>
						          <div class="form-group">
						            <label for="message-text"  class="col-form-label">내용</label>
						            <textarea class="form-control" name="replybcontent" id="modifycontent">${reply.replybcontent}</textarea>
						          </div>						        
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
						        <button type="submit" class="btn btn-primary">수정</button>		            
						      </div>
						      </form>
						    </div>
						  </div>
						</div>											
				</c:when>				
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>		
		</c:forEach>
	</table>	
	</div>
	<br>

	</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</html>