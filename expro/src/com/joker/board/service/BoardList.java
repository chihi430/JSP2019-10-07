package com.joker.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joker.board.db.BoardDAO;
import com.joker.board.db.BoardDTO;
import com.joker.member.service.Action;
import com.joker.member.service.ActionForward;

public class BoardList implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardList");
		// get 방식으로 넘어오는 section 값 변수에 저장
		String section = request.getParameter("section");

		// 검색했을 때 사용할 변수
		String sel = request.getParameter("sel");
		String find = request.getParameter("find");

		// 현재 페이지 번호
		String num = request.getParameter("num");
		if (num == null)
			num = "1";
		int currentPage = Integer.parseInt(num);

		// 한 페이지에 출력할 게시물 수
		int pageSize = 10;
		// 해당 페이지에서 시작할 레코드 / 마지막 레코드
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;

		BoardDAO manager = BoardDAO.getInstance();
		// 해당 section의 게시물 수
		int count = manager.getCount(section);

		// section의 검색 게시물
		Map<String, Object> fMap = new HashMap<>();

		int fcount = 0;
		if (sel != null && find != null) {
			fMap.put("section", section);
			fMap.put("sel", sel);
			fMap.put("find", "%" + find + "%");

			fcount = manager.getfCount(fMap);
			System.out.println("fcount : " + fcount);
		}
		
		List<BoardDTO> list = null;
		List<BoardDTO> flist = null;
		
		// 검색하지 않았을 때
		if (count > 0 && sel == null && find == null) {
			Map<String, Object> map = new HashMap<>();
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("section", section);
			list = manager.getList(map);
		} 
		// 검색했을 때
		else if (count > 0 && fcount > 0 && sel != null && find != null) {
			fMap.put("startRow", startRow);
			fMap.put("endRow", endRow);
			flist = manager.getfList(fMap);
		}

		// 총 페이지 수
		int pageCount = 0;
		if (count > 0 && sel == null && find == null) {
			pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		} else if (sel != null && find != null) { // 검색했을 경우
			pageCount = fcount / pageSize + ((fcount % pageSize == 0) ? 0 : 1);
		}

		// pageBlock 수
		int pageBlock = 10;
		// page 시작번호 및 끝 번호
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;

		if (endPage > pageCount) {
			endPage = pageCount;
		}
		
		// 뷰페이지로 공유할 값 설정
		request.setAttribute("section", section);
		request.setAttribute("sel", sel);
		request.setAttribute("find", find);
		request.setAttribute("num", currentPage);
		request.setAttribute("count", count);
		request.setAttribute("fcount", fcount);
		request.setAttribute("list", list);
		request.setAttribute("flist", flist);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./board/boardlist.jsp");

		return forward;
	}
}
