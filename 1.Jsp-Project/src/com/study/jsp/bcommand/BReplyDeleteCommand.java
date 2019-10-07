package com.study.jsp.bcommand;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.BDao;
import com.study.jsp.dao.BReplyDao;
import com.study.jsp.dto.BReplyDto;

public class BReplyDeleteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{		
		String replynum = request.getParameter("replynum");
		
		System.out.println("bId : "+replynum);
		
		BReplyDao dao = new BReplyDao();
		dao.reply_delete(replynum);
		
	}
	
}
