package com.joker.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joker.board.db.BoardDAO;
import com.joker.board.db.BoardDTO;
import com.joker.member.service.Action;
import com.joker.member.service.ActionForward;

public class BoardContent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardContent");
		// get 방식으로 넘어온 값 저장
		String section = request.getParameter("section");
		String num = "1";
		if(request.getParameter("num") != null){
			num = request.getParameter("num");
		}
		int no = Integer.parseInt(request.getParameter("no"));
		String sel = request.getParameter("sel");
		String find = request.getParameter("find");
		
		BoardDAO manager = BoardDAO.getInstance();
		// 조회수 업데이트
		manager.readcountUpdate(no);
		// 해당 게시글의 데이터 검색
		BoardDTO content = manager.boardContent(no);
		
		// boardContent.jsp로 데이터 공유
		request.setAttribute("content", content);
		request.setAttribute("section", section);
		request.setAttribute("num", num);
		
		// 검색하였을 경우 공유
		if(sel != null && find != null){
			request.setAttribute("sel", sel);
			request.setAttribute("find", find);
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./board/boardContent.jsp");

		return forward;
	}
	
}
