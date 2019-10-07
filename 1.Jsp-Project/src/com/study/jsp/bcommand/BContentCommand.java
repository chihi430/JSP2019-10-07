package com.study.jsp.bcommand;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Remove;

import com.study.jsp.BPageInfo;
import com.study.jsp.dao.BDao;
import com.study.jsp.dao.BLikeDao;
import com.study.jsp.dao.BReplyDao;
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.BLikeDto;
import com.study.jsp.dto.BReplyDto;
import com.study.jsp.dto.MemberDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session;
		session = request.getSession();
		
		//String replynum =(String) request.getParameter("replynum");			
		String bId = (String)request.getParameter("bId");
		String id = (String) session.getAttribute("id");
		
		session.setAttribute(bId, "bId");
		System.out.println("bID : "+bId);		
		BDao dao = new BDao();
		BLikeDao bLikeDao = new BLikeDao();
		BReplyDao replydao = new BReplyDao();
		
		BDto dto = dao.contentView(bId);
		BLikeDto bLikeDto = bLikeDao.likecontentView(id, bId); 
		MemberDto memberDto = dao.membercontentView(id);
		ArrayList<BReplyDto> replydtos = replydao.reply_list(bId);		
		BPageInfo replytotalcount = replydao.replycount(bId);
		ArrayList<BLikeDto> findlikemember = bLikeDao.findlikemember(bId);
		
		request.setAttribute("findmember", findlikemember); // 해당 게시물에 좋아요 누른사람 회원 조회
		request.setAttribute("reply_totalcount", replytotalcount); // 댓글갯수 카운트
		request.setAttribute("reply_view", replydtos);	// 댓글 내용					
		request.setAttribute("like_content_view", bLikeDto); // 좋아요 누를 회원들을 담는 공간				
		request.setAttribute("member_view", memberDto); // 회원전체		
		request.setAttribute("content_view", dto); // 보드
		
		session.removeAttribute(bId);
	}
	
}
