package com.study.jsp.mcommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.MemberDao;
import com.study.jsp.dto.MemberDto;

public class adminfreeblacklistOk implements Service {

	public adminfreeblacklistOk() {

	}
	//수정하기
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("blacklistOk");

		HttpSession session;

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		session = request.getSession();

		PrintWriter writer = response.getWriter();

		// text로 되어있는것은 session 으로 부르고
		String id = request.getParameter("bId");
		System.out.println(id);
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = dao.getMember(id);

			if (dao.BlacklistfreeMember(dto) == 1) { 
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"블랙리스트 해제완료\");");
				writer.println(" window.location='memberlist.do'");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
				System.out.println("블랙리스트 해제 완료");
			}
		 else {
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println(" alert(\"블랙리스트 해제 실패\");");
			writer.println(" window.location='memberlist.do'");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
			System.out.println("블랙리스트 해제 실패");
		 }
	}
}
