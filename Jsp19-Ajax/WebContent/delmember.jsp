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
    	String query = "select * from member";
    	
    	String name, id,pw,phone1,phone2,phone3,gender;
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery.js"></script>
<script>

function submit_ajax(){
	var queryString = $("#DelMemberProcess").serialize();
	$.ajax({
		url:'/Jsp19-Ajax/DelMemberProcess',
		type: 'post',		
		data: queryString,
		dataType:'json',
		success: function (json){
			var results = eval(json);
			if(results[0].result=="ok"){
				alert("회원탈퇴가 정상적으로 처리되었습니다.!")
				window.location.replace("delmemberResult.jsp");
			}else{
				alert(results[0].desc);
			}				
		}
	});
}

</script>

<title>Insert title here</title>
</head>
<body>
	<%
		id =(String)session.getAttribute("id");
		String query = "select * from member where id = '"+id+"'";
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,uid,upw);
		stmt = con.createStatement();
		resultSet = stmt.executeQuery(query);
		
		String phone ="";
		
		while(resultSet.next()){
			name = resultSet.getString("name");
			pw = resultSet.getString("pw");
		}
	
	%>
<form id=DelMemberProcess>
		아이디 : <%= id %><br>
		비밀번호 : <input type="text" name="pw" size="10"><br>
		이름 : <input type="text" name="name" size="10" value=<%=name %>><br>
		<input type="button" value="회원탈퇴" onclick="submit_ajax()" />

</form>
</body>
</html>