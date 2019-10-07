package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class modifyOk implements Service {

	public modifyOk() {

	}
	//수정하기
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("modifyOk");

		HttpSession session;

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		System.out.println("수정 포스트 실행");

		session = request.getSession();

		PrintWriter writer = response.getWriter();

		// text로 되어있는것은 session 으로 부르고
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");

		// input type 은 request로 불러서해야한다.
		String pw = request.getParameter("pw");
		String sessionPw = request.getParameter("pw_check");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");

		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = dao.getMember(id);

		boolean rs = false;
		if (sessionPw.equals(pw)) {
			rs = true;
		} else {
			rs = false;
		}

		if (rs) {
			
			dto.seteMail(eMail);
			dto.setAddress(address);
			if (dao.updateMember(dto) == 1) { 
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"수정완료!\");");
				writer.println(" window.location.replace(\"main.jsp\");");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
				System.out.println("수정 완료");
			}
		} else {
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println(" alert(\"패스워드가 일치하지 않습니다..\");");
			writer.println(" window.location.replace(\"modify.jsp\");");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
			System.out.println("패스워드가 일치하지 않습니다.");
		}
	}

}
