package com.study.jsp.bcommand;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.BDao;
import com.study.jsp.dao.BLikeDao;
import com.study.jsp.dto.MemberDto;

public class BLikeCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session;
		session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer;
		
		String bId = request.getParameter("bId");
		String id = (String) session.getAttribute("id");
		session.getAttribute("bId");

		try {
			writer = response.getWriter();
			if(id == null) {
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"회원만 사용가능합니다.\");");
				writer.println(" window.location='list.do'");				
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}else {
				String check = "";
				try {
					check = (String) request.getParameter("check");			
					session.setAttribute("like", check);
				} catch (Exception e) {
					
				}
				BDao dao = new BDao();
				BLikeDao bLikeDao = new BLikeDao();			
				bLikeDao.likeboard(bId, id, check);
				
				
				MemberDto memberDto = dao.membercontentView(id);		
				request.setAttribute("member_content_view", memberDto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}			
	}
}
