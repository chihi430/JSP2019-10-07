package com.joker.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joker.member.db.MemberDAO;
import com.joker.member.db.MemberDTO;

public class PasswdChange implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String oldPasswd = request.getParameter("oldPasswd");
		String id = request.getParameter("id");
		
		MemberDAO manager = MemberDAO.getInstance();
		// db에 저장된 기존 비밀번호를 가져옴
		String checkPasswd = manager.pwCheck(id);
		
		// 입력한 비밀번호와 일치하는지 검사
		if(!oldPasswd.equals(checkPasswd)){
			out.print("<script>");
			out.print("alert('기존 비밀번호가 다릅니다.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
		}else{ // 비밀번호가 일치하면
			MemberDTO member = new MemberDTO();
			member.setId(id);
			member.setPasswd(request.getParameter("passwd"));
			int result = manager.pwChange(member);
			request.setAttribute("result", result);
		}
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./PasswdChangeForm.do");
		
		return forward;
	}
}
