package com.study.jsp.bcommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.MemberDto;

public class BMemberContentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		String id = request.getParameter("id");
		BDao dao = new BDao();
		MemberDto memberDto = dao.membercontentView(id);
		
		request.setAttribute("member_content_view", memberDto);
	}
}
