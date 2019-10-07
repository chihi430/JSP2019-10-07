package com.study.jsp.mcommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.MemberDao;
import com.study.jsp.dto.MemberDto;

import nl.captcha.Captcha;


public class adminloginOk implements Service {
	public adminloginOk() {
		
	}
	//로그인확인
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		System.out.println("adminloginOk");
		String id = request.getParameter("adminid");
		String pw = request.getParameter("adminpw");
		String name = "";
		
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
				
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		if(dao.adminlogin(id, pw) == MemberDao.MEMBER_LOGIN_SUCCESS) 
		{
			
			session = request.getSession();
			dto = dao.getMember(id);
			name= dto.getName();
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			session.setAttribute("name", name);
			
			
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println(" alert(\"환영합니다.\");");
			writer.println(" window.location.replace(\"./rank.do\");");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
			
		}
		else  
		{
			if(dao.adminlogin(id, pw) == MemberDao.MEMBER_LOGIN_IS_NOT) 
			{
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"해당 아이디가 존재하지 않습니다.\");");
				writer.println(" window.location.replace(\"./adminlogin.jsp\");");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}
			else 
			{
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"비밀번호가 틀렸습니다.\");");
				writer.println(" window.location.replace(\"./adminlogin.jsp\");");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}
		}
	}
}
