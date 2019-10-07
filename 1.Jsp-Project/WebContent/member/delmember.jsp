<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%!
   		Connection con;
    	Statement stmt;
    	ResultSet resultSet;
    	
    	String driver = "oracle.jdbc.driver.OracleDriver";
    	String url = "jdbc:oracle:thin:@localhost:1521:xe";
    	String uid = "scott";
    	String upw = "tiger";
    	String query = "select * from members";
    	
    	String name, id,pw;
    
    %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>회원탈퇴 · Delete</title>
    <script language="JavaScript" src="member.js"></script>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/checkout/">

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
    </style>
    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/4.3/examples/checkout/form-validation.css" rel="stylesheet">

</head>
<body>
	<%
		id =(String)session.getAttribute("id");
		String query = "select * from members where id = '"+id+"'";
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,uid,upw);
		stmt = con.createStatement();
		resultSet = stmt.executeQuery(query);
		
		
		while(resultSet.next()){
			name = resultSet.getString("name");
			pw = resultSet.getString("pw");
		}
	
	%>
<div class="container">
		  <div class="py-5 text-center">
		    <img class="d-block mx-auto mb-4" src="https://getbootstrap.com/docs/4.3/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
		    <h2>회원탈퇴 · Delete</h2>
		  </div>
		  
		  <div class="row">
		    <div class="col-md-12 order-md-10">
		      <h4 class="mb-3">정보를 입력해 주세요!</h4>
		      <form class="needs-validation" novalidate="" action ="../deleteOk.to" method="post">
		      	
		      	<!-- 아이디 -->
		        <div class="row">
		          <div class="col-md-12 mb-3">
		            <label for="firstName">Your ID</label>
		            <input style="width:300px;" type="text" class="form-control" id="firstName" name="id" placeholder="아이디" value="<%= id %>" required="" readonly>
		          </div>
		        </div>
		      
		        <!-- 비밀번호 -->
		        <div class="mb-3">
		          <label for="address">Password</label>
		          <input style="width:300px;" type="password" class="form-control" id="address" name="pw" placeholder="비밀번호" required="">
		          <div class="invalid-feedback">
		             	비밀번호 입력은 필수입니다!
		          </div>
		        </div>
		        <div class="mb-3">
		          <label for="address">Password Check</label>
		          <input style="width:300px;" type="password" class="form-control" id="address" name="pw_check" placeholder="비밀번호 확인" required="">
		          <div class="invalid-feedback">
		             	비밀번호 확인을위해 다시 입력해주세요
		          </div>
		        </div>
		        
		        <hr class="mb-4">
		        <div align ="center">			
		        <button style="width:300px;" class="btn btn-primary btn-lg btn-block" type="submit" value="회원탈퇴">회원탈퇴</button>
		        <button style="width:300px;" class="btn btn-primary btn-lg btn-block" onclick="javascript:window.location='main.jsp'">취 소</button>		        
		        </div>		        
		        
		       </form>
		      </div>
		   </div>
		   		  	
</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="https://getbootstrap.com/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script><script src="https://getbootstrap.com/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
<script src="https://getbootstrap.com/docs/4.3/examples/checkout/form-validation.js"></script>
</body>
</html>