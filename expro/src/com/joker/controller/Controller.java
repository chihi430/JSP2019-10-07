package com.joker.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joker.board.service.BoardContent;
import com.joker.board.service.BoardList;
import com.joker.board.service.BoardWrite;
import com.joker.board.service.RecCount;
import com.joker.board.service.RecUpdate;
import com.joker.member.service.Action;
import com.joker.member.service.ActionForward;
import com.joker.member.service.LogOut;
import com.joker.member.service.LoginAction;
import com.joker.member.service.MemberJoin;
import com.joker.member.service.MemberUpdate;
import com.joker.member.service.MemberUpdateForm;
import com.joker.member.service.NewLoginAction;
import com.joker.member.service.PasswdChange;
import com.joker.member.service.Withdrawal;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println("requestURI : " + requestURI);
		System.out.println("contextPath : " + contextPath);
		System.out.println("command : " + command);

		Action action = null;
		ActionForward forward = null;

		// 회원가입 폼
		if (command.equals("/MemberJoinForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./member/memberJoinForm.jsp");
		}
		// 회원가입
		else if (command.equals("/MemberJoin.do")) {
			try {
				action = new MemberJoin();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// index 페이지로 이동
		else if (command.equals("/Index.do")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./index.jsp");
		}
		// 로그인 폼으로 이동
		else if (command.equals("/LoginForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./member/loginForm.jsp");
		}
		// 로그인
		else if (command.equals("/LoginAction.do")) {
			try {
				action = new LoginAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 새창 로그인폼
		else if (command.equals("/NewLoginForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./member/newLoginForm.jsp");
		}
		// 새창 로그인
		else if (command.equals("/NewLoginAction.do")) {
			try {
				action = new NewLoginAction();
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 로그아웃
		else if (command.equals("/LogOut.do")) {
			try {
				action = new LogOut();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 정보수정폼 form
		else if(command.equals("/MemberUpdateForm.do")){
			try {
				action = new MemberUpdateForm();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 정보 수정
		else if(command.equals("/MemberUpdate.do")){
			try {
				action = new MemberUpdate();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 비밀번호 변경 폼
		else if(command.equals("/PasswdChangeForm.do")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/passwdChangeForm.jsp");
		}
		// 비밀번호 변경
		else if(command.equals("/PasswdChange.do")){
			try {
				action = new PasswdChange();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원 탈퇴form
		else if (command.equals("/WithdrawalForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/withdrawalForm.jsp");
		}
		// 회원 탈퇴form
		else if (command.equals("/Withdrawal.do")) {
			try {
				action = new Withdrawal();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 게시판 목록
		else if (command.equals("/BoardList.do")) {
			try {
				action = new BoardList();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 게시판 글작성 form
		else if (command.equals("/BoardWriteForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./board/boardWriteForm.jsp?section="+request.getParameter("section"));
		}
		// 게시판 글작성
		else if (command.equals("/BoardWrite.do")) {
			try {
				action = new BoardWrite();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 게시판 상세 페이지
		else if (command.equals("/BoardContent.do")) {
			try {
				action = new BoardContent();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 추천수 업데이트
		else if (command.equals("/RecUpdate.do")) {
			try {
				action = new RecUpdate();
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 추천수 검색
		else if (command.equals("/RecCount.do")) {
			try {
				action = new RecCount();
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		

		// 페이지 이동
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get");
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		System.out.println("post");
		doProcess(request, response);
	}
}
