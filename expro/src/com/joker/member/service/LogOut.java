package com.joker.member.service;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOut implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LogOut");
		
		response.setContentType("text/html;charset=utf-8");			
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession();
		session.invalidate();
		out.println("<script>");
		out.println("location.href = document.referrer;");
		out.println("</script>");
		out.close();
		
		return null;
	}
}