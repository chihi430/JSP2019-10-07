package com.study.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/DelMemberProcess")
public class DelMemberProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection con;
	private PreparedStatement pstmt;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "scott";
	String upw = "tiger";

	private String id, pw;
	HttpSession session;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		actionDo(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		
		id = (String)session.getAttribute("id");	
		pw = request.getParameter("pw");
		
		if(pwConfirm()) {
			System.out.println("OK");
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,uid,upw);
				
				String query = "delete from member where id=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);
				int updateCount = pstmt.executeUpdate();
				
				if(updateCount ==1) {
					System.out.println("delete success");					
					response.sendRedirect("delmemberResult.jsp");
				}else {
					System.out.println("update fail");
					response.sendRedirect("loginResult.jsp");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				} catch (Exception e2) {}
			}
		}else {
			System.out.println("패스워드가 일치하지 않습니다.");
			response.sendRedirect("loginResult.jsp");
		}
		
		
	}
	private boolean pwConfirm() {
		boolean rs = false;
		String sessionPw = (String)session.getAttribute("pw");
		
		if(sessionPw.equals(pw)) {
			rs = true;
		}else {
			rs = false;
		}
		return rs;
	}

}
