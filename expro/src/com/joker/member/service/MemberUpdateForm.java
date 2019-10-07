package com.joker.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.joker.member.db.MemberDAO;
import com.joker.member.db.MemberDTO;

public class MemberUpdateForm implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdateForm");
		
		HttpSession session = request.getSession();
		// session으로 공유되어 있는 id를 가져옴
		String id = (String) session.getAttribute("id");

		MemberDAO manager = MemberDAO.getInstance();
		// id에 해당하는 정보를 구해옴
		MemberDTO member = manager.getMember(id);
		// 구해온 데이터를 request 영역으로 공유
		request.setAttribute("member", member);
		
		ActionForward forward = new ActionForward();
		// 공유 객체가 있으므로 dispatcher 방식 사용(false) / 이동 경로 설정
		forward.setRedirect(false);
		forward.setPath("./member/memberUpdateForm.jsp");
		
		return forward;
	}
}
