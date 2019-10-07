package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/JoinProcess")
public class JoinProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("doPost");
		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		dto.setId(id);
		dto.setName(name);
		dto.seteMail(eMail);
		dto.setAddress(address);
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		
		String json_data = "";
		if(dao.confirmId(dto.getId()) == MemberDao.MEMBER_EXISTENT) {
			json_data = "{\"code\":\"fail\",\"desc\":\"���̵� �̹� ���� �մϴ�.\"}";
						
		}else {
			int ri = dao.insertMember(dto);
			if(ri == MemberDao.MEMBER_EXISTENT) {
				HttpSession session = request.getSession();
				session.setAttribute("id", dto.getId());
				
				json_data = "{\"code\":\"success\",\"desc\":\"ȸ�������� ���� �մϴ�.\"}";
			}else {
				json_data = "{\"code\":\"fail\",\"desc\":\"������ �߻��Ͽ� ȸ�����Կ� ���� �߽��ϴ�.\"}";
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
