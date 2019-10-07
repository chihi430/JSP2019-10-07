package com.study.jsp.frontcontroller;

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

import com.study.jsp.mcommand.*;


@WebServlet("*.to")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontCon() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("actionDo");
		
		String uri=request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		System.out.println("command : "+command);
		
		if(command.equals("/joinOk.to")) {
			System.out.println("joinOk.to");
			Service service = new joinOk();
			service.execute(request, response);
			
		}else if(command.equals("/loginkakaoOk.to")) {
			Service service = new loginkakaoOk();
			service.execute(request, response);
		}
		else if(command.equals("/loginOk.to")) {
			System.out.println("login.to");
			Service service = new loginOk();
			service.execute(request, response);
			
		}else if(command.equals("/modifyOk.to")) {
			System.out.println("modifyOk.to");
			Service service = new modifyOk();
			service.execute(request, response);
			
		}else if(command.equals("/logout.to")) {
			System.out.println("logout.to");
			Service service = new logoutOk();
			service.execute(request, response);
			System.out.println("----------");
			
		}else if(command.equals("/deleteOk.to")) {
			System.out.println("deleteOk");
			Service service = new deletememberOk();
			service.execute(request, response);
			System.out.println("----------");

			
		}else if(command.equals("/admin/adminloginOk.to")) {
			System.out.println("adminlogin");
			Service service = new adminloginOk();
			service.execute(request, response);
			System.out.println("----------");

			
		}else if(command.equals("/blacklist.to")) {
			System.out.println("blacklist.to");
			Service service = new adminblacklistOk();
			service.execute(request, response);
			System.out.println("----------");

			
		}else if(command.equals("/free.to")) {
			System.out.println("free.to");
			Service service = new adminfreeblacklistOk();
			service.execute(request, response);	
			System.out.println("----------");

			
		}
	}
	
	
}
