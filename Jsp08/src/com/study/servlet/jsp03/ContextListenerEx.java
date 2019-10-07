package com.study.servlet.jsp03;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ContextListenerEx implements ServletContextListener{
       

    public ContextListenerEx() {
    	
    }

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("어플리케이션종료");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("어플리케이션 시작");
		
		
		ServletContext sc = event.getServletContext();
		sc.setAttribute("schdeule", 1000);
	}
    
    
   /*
		다른 앱에서 다음과 같이 사용가능
		
    */
    
}
