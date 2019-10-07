package com.joker.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joker.member.db.MemberDAO;
import com.joker.member.db.MemberDTO;

public class MemberJoin implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoin");
		// out 객체에서 사용할 타입
		response.setContentType("text/html;charset=utf-8");
		// out 객체 생성
		PrintWriter out=response.getWriter();
		// post 방식으로 넘어오는 데이터의 인코딩 타입 설정
		request.setCharacterEncoding("utf-8");
		// 폼에서 넘어온 id값 저장
		String id = request.getParameter("id");
		// DAO 객체 생성
		MemberDAO manager = MemberDAO.getInstance();
		// 아이디 중복검사
		int result = manager.idCheck(id);
		
		if(result != 0){
			out.println("<script>");
			out.println("alert('동일한 아이디가 있습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}else{
			// 동일한 id가 없으면 DTO 객체에 값을 저장하여 DAO로 전달
			MemberDTO member = new MemberDTO();
			member.setId(id);
			member.setPasswd(request.getParameter("passwd"));
			member.setName(request.getParameter("name"));
			member.setEmail(request.getParameter("email"));
			member.setNickname(request.getParameter("nickname"));
			manager.memberJoin(member);
		}
		// 포워딩 방식 및 경로 설정
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./LoginForm.do");
		
		return forward;
	}
}
