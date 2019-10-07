package com.study.jsp.bcommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.MemberDto;


public class BWriteCheckIdCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session;
		session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			String id = (String) session.getAttribute("id");
			if(id == null) {
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"회원만 사용가능합니다.\");");
				writer.println(" window.location='list.do'");				
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}else {
				BDao dao = new BDao();
				MemberDto memberDto = dao.membercontentView(id);
				request.setAttribute("write_content_view", memberDto);
				System.out.println(id);				
			}
			
			

							
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
