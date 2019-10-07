package com.study.jsp.mcommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginkakaoOk implements Service {

	public loginkakaoOk() {
	
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("cacao");
		
		String uname = request.getParameter("uname");
		
		HttpSession session = request.getSession();
		
		session.setAttribute("ValidMem", "yes");
		session.setAttribute("uname", uname);
		response.sendRedirect("../member/main.jsp");
	}
	
	
	
}
