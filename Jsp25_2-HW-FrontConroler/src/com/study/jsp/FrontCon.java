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
			joinOk(request, response);
			System.out.println("----------");
		}else if(command.equals("/logout.do")) {
			System.out.println("update");
			logout(request, response);
			System.out.println("----------");
		}else if(command.equals("/loginOk.do")) {
			System.out.println("login.do");
			loginOk(request, response);
			System.out.println("----------");
		}else if(command.equals("/modifyOk.do")) {
			System.out.println("modifyOk.do");
			modifyOk(request, response);
			System.out.println("----------");
		}
	}
	
	//ȸ������
	public void joinOk (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		System.out.println("joinOk.do");
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		
		MemberDto dto = new MemberDto();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.seteMail(eMail);
		dto.setAddress(address);
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		MemberDao dao = MemberDao.getInstance();
		if(dao.confirmId(dto.getId()) == MemberDao.MEMBER_EXISTENT) {
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println(" alert(\"���̵� �̹� ���� �մϴ�.\");");
			writer.println(" history.back();");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
		}else {
			int ri = dao.insertMember(dto);
			if(ri ==MemberDao.MEMBER_JOINSUCCESS) {
				HttpSession session = request.getSession();
				session.setAttribute("id", dto.getId());
				
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"ȸ�������� ���� �մϴ�.\");");
				writer.println(" window.location.replace(\"login.jsp\");");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
				
				
			}else {
				
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"ȸ�����Կ� ���� �߽��ϴ�.\");");
				writer.println(" history.back();");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}
		}
	}
	
	//�α���
	public void loginOk (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = "";
		String eMail = "";
		String address = "";

		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		if(dao.userCheck(id, pw) == MemberDao.MEMBER_LOGIN_SUCCESS) 
		{
			HttpSession session = request.getSession();
			dto = dao.getMember(id);
			name= dto.getName();
			session.setAttribute("ValidMem", "yes");
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			session.setAttribute("name", name);
			
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println(" alert(\"ȯ���մϴ�.\");");
			writer.println(" window.location.replace(\"main.jsp\");");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
			
		}
		else  
		{
			if(dao.userCheck(id, pw) == MemberDao.MEMBER_LOGIN_IS_NOT) 
			{
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"���̵� �������� �ʽ��ϴ�.\");");
				writer.println(" window.location.replace(\"login.jsp\");");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}
			else 
			{
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"��й�ȣ�� Ʋ�Ƚ��ϴ�.\");");
				writer.println(" window.location.replace(\"login.jsp\");");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}
		}
			
		
	}
	
	//����
	public void modifyOk (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		{
			HttpSession session;

			
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			System.out.println("���� ����Ʈ ����");						
			
			session = request.getSession();
			
			PrintWriter writer = response.getWriter();
			
			
			//text�� �Ǿ��ִ°��� session ���� �θ���
			String id = (String) session.getAttribute("id");
			String name = (String) session.getAttribute("name");
			
			//input type �� request�� �ҷ����ؾ��Ѵ�.
			String pw = request.getParameter("pw");
			String sessionPw = request.getParameter("pw_check");
			String eMail = request.getParameter("eMail");
			String address = request.getParameter("address");
			
			MemberDao dao = MemberDao.getInstance();
			MemberDto dto = dao.getMember(id);
			
			boolean rs = false;
			if (sessionPw.equals(pw)) {
				rs = true;
			} else {
				rs = false;
			}
			
			if (rs) {
				System.out.println(dao.updateMember(dto));
				dto.seteMail(eMail);
				dto.setAddress(address);
				if(dao.updateMember(dto)==1) {
					writer.println("<html><head></head><body>");
					writer.println("<script language=\"javascript\">");
					writer.println(" alert(\"�����Ϸ�!\");");
					writer.println(" window.location.replace(\"main.jsp\");");
					writer.println("</script>");
					writer.println("</body></html>");
					writer.close();
					System.out.println("���� �Ϸ�");
				}				
			}
			else {
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println(" alert(\"�н����尡 ��ġ���� �ʽ��ϴ�..\");");
				writer.println(" window.location.replace(\"modify.jsp\");");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
				System.out.println("�н����尡 ��ġ���� �ʽ��ϴ�.");
			}
		}	
	}
	
	//�α׾ƿ�
	public void logout (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
			request.setCharacterEncoding("UTF-8");
			
			HttpSession session = request.getSession();
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();

			session.invalidate();
			
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println(" alert(\"�α׾ƿ� �Ǿ����ϴ�.\");");
			writer.println(" window.location.replace(\"login.jsp\");");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();

	}
	
}
