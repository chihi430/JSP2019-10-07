package com.study.jsp.bcommand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.BDao;
import com.study.jsp.dao.BReplyDao;
import com.study.jsp.dto.BReplyRankDto;
import com.study.jsp.dto.BboardRankDto;

public class BRank implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
						
		BReplyDao bReplyDao = BReplyDao.getInstance();
		BDao bDao = BDao.getInstance();
		ArrayList<BReplyRankDto> replyrank = bReplyDao.reply_rank();
		ArrayList<BboardRankDto> boardrank = bDao.board_rank();
		
		request.setAttribute("board", boardrank);
		request.setAttribute("rank", replyrank);	
					
	}
	
}
