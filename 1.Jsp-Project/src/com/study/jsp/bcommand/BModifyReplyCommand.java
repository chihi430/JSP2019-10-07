package com.study.jsp.bcommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.BDao;
import com.study.jsp.dao.BReplyDao;

public class BModifyReplyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String replybcontent = request.getParameter("replybcontent");		
		String replynum = request.getParameter("replynum");
		
		System.out.println(replybcontent);
		System.out.println(replynum);
		
		BReplyDao dao = new BReplyDao();
		dao.replymodify(replybcontent, replynum);
	}
	
}
