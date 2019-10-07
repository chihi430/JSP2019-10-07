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

@WebServlet("/ModifyProcess")
public class ModifyProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String pw ;
	private HttpSession session;
	private String sessionPw; 

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("���� ����Ʈ ����");
		
		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		
		
		String id = (String) session.getAttribute("id");
		pw = request.getParameter("pw");
		sessionPw = (String) session.getAttribute("pw");
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		String json_data = "";
		if (pwConfirm()) {
			System.out.println(dao.updateMember(dto));
			if(dao.updateMember(dto)==1) {
			System.out.println("���� �Ϸ�");
			json_data = "{\"code\":\"success\",\"desc\":\"������ �Ϸ�Ǿ����ϴ�.\"}";
			System.out.println("OK");
			}
			
		}
		else {
			json_data = "{\"code\":\"fail\",\"desc\":\"�н����� ��ġ�����ʽ��ϴ�.\"}";
			System.out.println("�н����尡 ��ġ���� �ʽ��ϴ�.");
			response.sendRedirect("modify.jsp");
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();
	}
	public boolean pwConfirm() {
		boolean rs = false;

		if (sessionPw.equals(pw)) {
			rs = true;
		} else {
			rs = false;
		}
		return rs;
	}

}
