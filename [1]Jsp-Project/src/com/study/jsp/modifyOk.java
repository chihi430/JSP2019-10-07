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
	//�����ϱ�
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("modifyOk");

		HttpSession session;

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		System.out.println("���� ����Ʈ ����");

		session = request.getSession();

		PrintWriter writer = response.getWriter();

		// text�� �Ǿ��ִ°��� session ���� �θ���
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");

		// input type �� request�� �ҷ����ؾ��Ѵ�.
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
				writer.println(" alert(\"�����Ϸ�!\");");
				writer.println(" window.location.replace(\"main.jsp\");");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
				System.out.println("���� �Ϸ�");
			}
		} else {
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println(" alert(\"�н����尡 ��ġ���� �ʽ��ϴ�..\");");
			writer.println(" window.location.replace(\"modify.jsp\");");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
			System.out.println("�н����尡 ��ġ���� �ʽ��ϴ�.");
		}
	}

}
