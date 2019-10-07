package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginOk implements Service {
	
	public loginOk() {
		
	}
	//�α���
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
	{
		System.out.println("loginOk");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = "";

		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		if(dao.userCheck(id, pw) == MemberDao.MEMBER_LOGIN_SUCCESS) 
		{
			HttpSession session = request.getSession();
			dto = dao.getMember(id);
			name= dto.getName();
			session.setAttribute("ValidMem", "yes");
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			session.setAttribute("name", name);
			
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println(" alert(\"ȯ���մϴ�.\");");
			writer.println(" window.location.replace(\"main.jsp\");");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
			
		}
		else  
		{
			if(dao.userCheck(id, pw) == MemberDao.MEMBER_LOGIN_IS_NOT) 
			{
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"���̵� �������� �ʽ��ϴ�.\");");
				writer.println(" window.location.replace(\"login.jsp\");");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}
			else 
			{
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"��й�ȣ�� Ʋ�Ƚ��ϴ�.\");");
				writer.println(" window.location.replace(\"login.jsp\");");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}
		}
	}
}
