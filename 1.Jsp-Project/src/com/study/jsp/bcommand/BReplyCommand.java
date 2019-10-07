package com.study.jsp.bcommand;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.BReplyDao;

public class BReplyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session;
		session = request.getSession();
		
		String bId = request.getParameter("bId");
		String replybContent = request.getParameter("replybContent");
		
		String id = (String) session.getAttribute("id");
		System.out.println("bId : "+bId);
		
		PrintWriter writer;
		try {
			writer = response.getWriter();
		if(id != null) {			
			BReplyDao dao = new BReplyDao();
			dao.reply(bId, id, replybContent);
		}else {
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println(" alert(\"회원만 사용가능합니다.\");");
			writer.println(" window.location.replace(\"./member/login.jsp\");");				
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
		

		
	}
	
}
