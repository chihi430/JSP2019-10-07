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
		System.out.println("���ø����̼�����");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("���ø����̼� ����");
		
		
		ServletContext sc = event.getServletContext();
		sc.setAttribute("schdeule", 1000);
	}
    
    
   /*
		�ٸ� �ۿ��� ������ ���� ��밡��
		
    */
    
}
