package com.study.servlet.jsp01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ser")
public class ServletInitParam extends HttpServlet {
	


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet");
		
		//UTF-8로 나온다.
		request.setCharacterEncoding("UTF-8");

		String id = getInitParameter("id");
		String pw = getInitParameter("pw");
		String path = getInitParameter("path");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		writer.println("<html><head></head><body>");
		writer.println("아이디 : " + id + "<br>");
		writer.println("비밀번호 : " + pw + "<br>");
		writer.println("path : " + path + "<br>");
		writer.println("</body></html>");
		
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		
	}

}
