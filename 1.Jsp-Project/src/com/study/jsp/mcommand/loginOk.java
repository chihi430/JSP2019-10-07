package com.study.jsp.mcommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.MemberDao;
import com.study.jsp.dto.MemberDto;

import nl.captcha.Captcha;


public class loginOk implements Service {
	public loginOk() {
		
	}
	//로그인확인
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		
		System.out.println("loginOk");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = "";
		
		Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
		String answer = request.getParameter("answer");


		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		
		System.out.println("내가적은 답 : "+answer);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		if ( answer != null && !"".equals(answer) ) {
			if (captcha.isCorrect(answer) && dao.userCheck(id, pw) == MemberDao.MEMBER_LOGIN_SUCCESS) {
				session.removeAttribute(Captcha.NAME);
				
				session = request.getSession();
				dto = dao.getMember(id);
				name= dto.getName();
				session.setAttribute("ValidMem", "yes");
				session.setAttribute("id", id);
				session.setAttribute("pw", pw);
				session.setAttribute("name", name);
				
				
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"환영합니다.\");");
				writer.println(" window.location.replace(\"./member/main.jsp\");");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
				
			} else {
				if(dao.userCheck(id, pw) == MemberDao.MEMBER_LOGIN_IS_NOT) 
				{
					writer.println("<html><head></head><body>");
					writer.println("<script language=\"javascript\">");
					writer.println(" alert(\"해당 아이디가 존재하지 않습니다.\");");
					writer.println(" window.location.replace(\"./member/login.jsp\");");
					writer.println("</script>");
					writer.println("</body></html>");
					writer.close();
				}
				else if(dao.userCheck(id, pw)== MemberDao.BLACKLIST) {
					writer.println("<html><head></head><body>");
					writer.println("<script language=\"javascript\">");
					writer.println(" alert(\"해당 아이디는 블랙리스트입니다.\");");
					writer.println(" window.location.replace(\"./member/login.jsp\");");
					writer.println("</script>");
					writer.println("</body></html>");
					writer.close();
				}
				else if (!captcha.isCorrect(answer)) 
				{
					writer.println("<html><head></head><body>");
					writer.println("<script language=\"javascript\">");
					writer.println(" alert(\"문자가 일치하지 않습니다..\");");
					writer.println(" window.location.replace(\"./member/login.jsp\");");
					writer.println("</script>");
					writer.println("</body></html>");
					writer.close();
				}
				else 
				{
					writer.println("<html><head></head><body>");
					writer.println("<script language=\"javascript\">");
					writer.println(" alert(\"비밀번호가 틀렸습니다.\");");
					writer.println(" window.location.replace(\"./member/login.jsp\");");
					writer.println("</script>");
					writer.println("</body></html>");
					writer.close();
				}
			}		
		}
	}
}
