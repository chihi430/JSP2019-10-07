package com.study.jsp.mcommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.*;
import com.study.jsp.dto.*;

public class deletememberOk implements Service {


	private String id, pw;
	HttpSession session;
	public deletememberOk() {
	
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		
		id = (String)session.getAttribute("id");	
		pw = request.getParameter("pw");
		// 관리자 회원 관리 리퀘스트값 가져오기
		String bid = request.getParameter("bId");
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		

		
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();
		
		System.out.println("딜리트 커맨드진입");
		// ceo 체크
		if(dao.gradecheck(id) == true) {
			System.out.println("ceo입니다.");
			dto = dao.getMember(bid);
			if(dao.deleteMember(dto)==1) {
				System.out.println("admin delete success");
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"회원 강제 탈퇴에 성공하였습니다.\");");
				writer.println(" window.location='memberlist.do'");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}
		}
		
		else if(pwConfirm()) {
			System.out.println("DeleteOK");
				dto.setId(id);
				if(dao.deleteMember(dto) == 1) {
					System.out.println("admin delete success");
					writer.println("<html><head></head><body>");
					writer.println("<script language=\"javascript\">");
					writer.println(" alert(\"회원탈퇴에 성공하였습니다.\");");
					writer.println(" window.location.replace(\"./member/main.jsp\");");					
					writer.println("</script>");
					writer.println("</body></html>");
					writer.close();
					session.invalidate();

				} else {
					writer.println("<html><head></head><body>");
					writer.println("<script language=\"javascript\">");
					writer.println(" alert(\"회원탈퇴에 실패하였습니다.\");");
					writer.println(" window.location.replace(\"./member/login.jsp\");");
					writer.println("</script>");
					writer.println("</body></html>");
					writer.close();
				}
				
			}

		else {
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println(" alert(\"비밀번호가 틀렸습니다.\");");
			writer.println(" window.location='memberlist.do'");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
		}
	}
	
	private boolean pwConfirm() {
		boolean rs = false;
		String sessionPw = (String)session.getAttribute("pw");
		
		if(sessionPw.equals(pw)) {
			rs = true;
		}else {
			rs = false;
		}
		return rs;
	}
	
	
	
}
