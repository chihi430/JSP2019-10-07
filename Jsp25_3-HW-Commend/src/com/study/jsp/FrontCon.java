package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontCon() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("actionDo");
		
		String uri=request.getRequestURI();
		System.out.println("uri : "+uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : "+conPath);
		String command = uri.substring(conPath.length());
		System.out.println("command : "+command);
		
		if(command.equals("/joinOk.do")) {
			System.out.println("joinOk.do");
			Service service = new joinOk();
			service.execute(request, response);
			
		}else if(command.equals("/loginOk.do")) {
			System.out.println("login.do");
			Service service = new loginOk();
			service.execute(request, response);
			
		}else if(command.equals("/modifyOk.do")) {
			System.out.println("modifyOk.do");
			Service service = new modifyOk();
			service.execute(request, response);
			
		}else if(command.equals("/logout.do")) {
			System.out.println("logout.do");
			logout(request, response);
			System.out.println("----------");
		}
	}
	
	//로그아웃
	public void logout (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
			request.setCharacterEncoding("UTF-8");
			
			HttpSession session = request.getSession();
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();

			session.invalidate();
			
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println(" alert(\"로그아웃 되었습니다.\");");
			writer.println(" window.location.replace(\"login.jsp\");");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();

	}
	
}
