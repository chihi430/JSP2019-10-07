package com.study.jsp.mcommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.MemberDao;
import com.study.jsp.dto.MemberDto;

public class logoutOk implements Service {
	
	public logoutOk() {
		
	}
	//�α׾ƿ�
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
	{
		System.out.println("logoutOk");
		//�α׾ƿ�
		request.setCharacterEncoding("UTF-8");
				
		HttpSession session = request.getSession();
				
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		session.invalidate();
				
		writer.println("<html><head></head><body>");
		writer.println("<script language=\"javascript\">");
		writer.println(" alert(\"�α׾ƿ� �Ǿ����ϴ�.\");");
		writer.println(" window.location.replace(\"index.jsp\");");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();

		
	}
}
