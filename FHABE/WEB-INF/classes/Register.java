package com;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Register extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	response.setContentType("text/html");
	boolean flag=false;
	PrintWriter out = response.getWriter();
	String uname=request.getParameter("t1").trim();
	String pass=request.getParameter("t2").trim();
	String type=request.getParameter("t3").trim();
	String contact=request.getParameter("t4").trim();
	String address=request.getParameter("t5").trim();
	try{
		String input[]={uname,pass,type,contact,address};
		String res = DBConnection.createUser(input);
    if(res.equals("success")){
		RequestDispatcher rd=request.getRequestDispatcher("Login.jsp?t1=You can login now");
		rd.forward(request, response);
	}else{
		RequestDispatcher rd=request.getRequestDispatcher("Login.jsp?t1=Error in registration process");
		rd.forward(request, response);
	}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	}

}
