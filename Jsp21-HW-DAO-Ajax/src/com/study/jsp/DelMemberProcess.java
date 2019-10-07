package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
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

	private String id, pw;
	HttpSession session;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		
		id = (String)session.getAttribute("id");	
		pw = request.getParameter("pw");
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		dto.setId(id);
		
		String json_data = "";	
		if(pwConfirm()) {
			System.out.println("OK");
				
											
				if(dao.deleteMember(dto) == 1) {
					System.out.println("delete success");
					json_data = "{\"code\":\"success\",\"desc\":\"ȸ��Ż�� �����Ͽ����ϴ�.\"}" ;
					response.sendRedirect("login.jsp");
				} else {
					json_data = "{\"code\":\"fail\",\"desc\":\"ȸ��Ż�� �����Ͽ����ϴ�.\"}" ;
					response.sendRedirect("login.jsp");
				}
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println(json_data);
				writer.close();
				
			}
		else {
			System.out.println("�н����尡 ��ġ���� �ʽ��ϴ�.");
			response.sendRedirect("loginResult.jsp");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
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
