package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginProcess")
public class LoginProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = "";
		String eMail = "";
		String address = "";

		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		
		
		String json_data="";
		if(dao.userCheck(id, pw) == MemberDao.MEMBER_LOGIN_SUCCESS) 
		{
			HttpSession session = request.getSession();
			dto = dao.getMember(id);
			name= dto.getName();
			session.setAttribute("ValidMem", "yes");
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			session.setAttribute("name", name);
			json_data = "{\"code\":\"success\",\"desc\":\"환영합니다!\"}";	
			
		}
		else  
		{
			if(dao.userCheck(id, pw) == MemberDao.MEMBER_LOGIN_IS_NOT) 
			{
				json_data = "{\"code\":\"fail\",\"desc\":\"아이디가 존재하지 않습니다.!\"}";
			}
			else 
			{
				json_data = "{\"code\":\"fail\",\"desc\":\"비밀번호가 틀렸습니다. \"}";	
			}
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("doGet");
	}

}
