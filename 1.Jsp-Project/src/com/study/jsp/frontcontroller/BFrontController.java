package com.study.jsp.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.bcommand.BCommand;
import com.study.jsp.bcommand.BContentCommand;
import com.study.jsp.bcommand.BDeleteCommand;
import com.study.jsp.bcommand.BLikeCommand;
import com.study.jsp.bcommand.BListCommand;
import com.study.jsp.bcommand.BMemberContentCommand;
import com.study.jsp.bcommand.BMemberListCommand;
import com.study.jsp.bcommand.BModifyCommand;
import com.study.jsp.bcommand.BModifyReplyCommand;
import com.study.jsp.bcommand.BRank;
import com.study.jsp.bcommand.BReplyCommand;
import com.study.jsp.bcommand.BReplyDeleteCommand;
import com.study.jsp.bcommand.BWriteCheckIdCommand;
import com.study.jsp.bcommand.BWriteCommand;



@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BFrontController() {
        super();
    }
	private void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		BCommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		System.out.println("com : "+com);
		HttpSession session = null;
		session = request.getSession();
		
		//¸Þ´º ¼±ÅÃ°ª ¹Þ±â
		String menuchoice = "";
		try {
			menuchoice = (String) request.getParameter("option");			
			session.setAttribute("cmenuchoice", menuchoice);
		} catch (Exception e) {
		}
		
		int curPage = 1;
		
		if(session.getAttribute("cpage") !=null) {
			curPage = (int)session.getAttribute("cpage");
		}		
		
		if(com.equals("/write_view.do")) {
			command = new BWriteCheckIdCommand();
			command.execute(request, response);
			viewPage = "/board/write_view.jsp";			
		}else if(com.equals("/write.do")) {
			command = new BWriteCommand();
			command.execute(request, response);
			viewPage = "list.do";
			
		}else if(com.equals("/list.do")) {
			command = new BListCommand();
			command.execute(request, response);		
			viewPage = "/board/list2.jsp";
			System.out.println("--------------list");
			
		}else if(com.equals("/content_view.do")) {
			System.out.println("ÄÁÅÙÆ®ºä");
			
			
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "/board/content_view.jsp";
			
		}else if(com.equals("/modify_view.do")) {
			System.out.println("¼öÁ¤ ºä");
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "/board/modify_view.jsp";
			
		}else if(com.equals("/modify.do")) {
			System.out.println("¸ðµðÆÄÀÌºä");
			command = new BModifyCommand();
			command.execute(request, response);
			
			command = new BContentCommand();
			command.execute(request, response);					
			viewPage = "/board/content_view.jsp";
			
		}else if(com.equals("/delete.do")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "/list.do?page="+curPage;
			
		}else if(com.equals("/reply.do")) {
			command = new BReplyCommand();
			command.execute(request, response);			
			viewPage = "content_view.do";
		
		}else if(com.equals("/replydelete.do")) {
			command = new BReplyDeleteCommand();
			command.execute(request, response);			
			viewPage = "content_view.do";		
		}
		
		else if(com.equals("/replymodify.do")) {
			command = new BModifyReplyCommand();
			command.execute(request, response);			
			viewPage = "content_view.do";		
		}
		
		else if(com.equals("/search.do")) {
			if("0".equals(menuchoice)) {
				command = new BListCommand();
				command.execute(request, response);
				viewPage = "list.do";			
			}else {
				command = new BListCommand();
				command.execute(request, response);
				viewPage = "/board/listsearch.jsp";
				System.out.println("---------------search");
			}
		}	
		else if(com.equals("/memberlist.do")) {
			command = new BMemberListCommand();
			command.execute(request, response);
			viewPage ="/admin/manager_member_list.jsp";
		}
		else if(com.equals("/member_content_view.do")) {
			command = new BMemberContentCommand();
			command.execute(request, response);
			viewPage ="/admin/member_content_view.jsp";
		}
		else if(com.equals("/admin_search.do")) {
			if("5".equals(menuchoice)) {
				command = new BMemberListCommand();
				command.execute(request, response);
				viewPage ="/admin/manager_member_list.do";
			}
			command = new BMemberListCommand();
			command.execute(request, response);
			viewPage ="/admin/manager_member_listsearch.jsp";
		}
		else if(com.equals("/like.do")) {
			command = new BLikeCommand();
			command.execute(request, response);
			viewPage = "content_view.do";
		}
		else if(com.equals("/admin/rank.do")) {
			command = new BRank();
			command.execute(request, response);
			viewPage = "/admin/adminmenu.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		actionDo(request, response);
	}
	

}
