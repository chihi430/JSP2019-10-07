package com.joker.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.joker.member.db.MemberDAO;

public class Withdrawal implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Withdrawal");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		String passwd = request.getParameter("passwd");
		
		MemberDAO manager = MemberDAO.getInstance();
		
		int result = 0;
		
		if(!passwd.equals(manager.pwCheck(id))){ // 입력한 비밀번호와 저장된 비밀번호가 다를경우
			out.print("<script>");
			out.print("alert('비밀번호가 다릅니다.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
			return null;
		}else{ // 비밀번호가 동일하면
			// 회원 탈퇴
			result = manager.memDelete(id);
			request.setAttribute("result", result);
			session.invalidate(); // 세션 해제
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./WithdrawalForm.do");
		
		return forward;
	}
	
}
