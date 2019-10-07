package com.study.jsp.bcommand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.BPageInfo;
import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

public class BMemberSearchListCommand implements BCommand{

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
		
		System.out.println("서치값 : "+search);
		System.out.println("메뉴초이스 : "+menuchoice);

		BDao dao = BDao.getInstance();
		BPageInfo pinfo = dao.articlePage(nPage, menuchoice, search);

		request.setAttribute("page", pinfo);
		nPage = pinfo.getCurPage();				
		session.setAttribute("cpage", nPage);
		
		ArrayList<BDto> dtos = dao.serach(search, menuchoice, nPage);		
		request.setAttribute("searchlist", dtos);
	}

}
