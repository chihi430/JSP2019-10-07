<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!String num1, num2, protocol;%>

	<%
	if(request.getMethod().equals("GET")){		
		response.sendRedirect("error41A2.jsp");
	}
	request.setCharacterEncoding("UTF-8");
	
	num1 =request.getParameter("num1");
	num2 = request.getParameter("num2");	
	protocol = request.getParameter("protocol");
	
	%>
	
	첫번째 숫자 : <%= num1 %><br> 
	두번째 숫자 : <%= num2 %><br>	
	연산자 : <%= protocol %><br> 
	
	<%
	int sum;
	Integer pnum1 = Integer.parseInt(num1);
	Integer pnum2 = Integer.parseInt(num2);
	
		if(protocol.equals("+")){		
			out.println(pnum1+"+"+pnum2+"="+(pnum1+pnum2)); 
		}
		else if(protocol.equals("-")){		
			out.println(pnum1+"-"+pnum2+"="+(pnum1-pnum2)); 
		}
		else if(protocol.equals("*")){		
			out.println(pnum1+"*"+pnum2+"="+(pnum1*pnum2)); 
		}
		else if(protocol.equals("/")){		
			out.println(pnum1+"/"+pnum2+"="+(pnum1/pnum2)); 
		}
		else if(protocol.equals("제곱")){		
			out.println("제곱 될 숫자 : "+pnum1+" 제곱 수 : "+pnum2+" 결과 : "+(Math.pow(pnum1, pnum2))); 
		}
	
	%>
	

</body>
</html>