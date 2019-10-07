package com.study.jsp.bcommand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.BPageInfo;
import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.MemberDto;

public class BMemberListCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nPage =1;
		HttpSession session = null;
		session = request.getSession();	
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
		} catch (Exception e) {
			
		}
		//검색어 값 받기
		String search = "";
		try {
			request.setCharacterEncoding("UTF-8");
			search = request.getParameter("search");	
			session.setAttribute("csearch", search);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//메뉴 선택값 받기
		String menuchoice = "";
		try {
			menuchoice = (String) request.getParameter("option");			
			session.setAttribute("cmenuchoice", menuchoice);

		} catch (Exception e) {
		}
		
		BDao dao = BDao.getInstance();
		BPageInfo pinfo = dao.memberPage(nPage, menuchoice, search);
		request.setAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		if("0".equals(menuchoice) || menuchoice == null) {
			//전체 리스트
			ArrayList<MemberDto> memberDtos = dao.memberlist(nPage, menuchoice, search);
			request.setAttribute("memberlist", memberDtos);
		}else {
			//검색
			ArrayList<MemberDto> memberDtos = dao.memberlist(nPage, menuchoice, search);
			request.setAttribute("searchmemberlist", memberDtos);
		}
		
		
	}
	

}
