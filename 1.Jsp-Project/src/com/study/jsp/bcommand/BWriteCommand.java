package com.study.jsp.bcommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.MemberDto;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session;
					
		session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		String bName = request.getParameter("bName");
		String bPw = request.getParameter("bPw");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String selectInfo = request.getParameter("selectInfo");
		BDao dao = new BDao();		
		
		dao.write(bName, bPw, bTitle, bContent, selectInfo);
	}	
}
