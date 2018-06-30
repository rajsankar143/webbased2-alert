package com;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.File;

public class CreateProfile extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	response.setContentType("text/html");
	boolean flag=false;
	HttpSession session = request.getSession();
	PrintWriter out = response.getWriter();
	String name=request.getParameter("t1").trim();
	String dob=request.getParameter("t2").trim();
	String age=request.getParameter("t3").trim();
	String gender=request.getParameter("t4").trim();
	String ssno=request.getParameter("t5").trim();
	String problem=request.getParameter("t6").trim();
	try{
		String user = session.getAttribute("user").toString();
		String input[]={name,dob,age,gender,ssno,user,problem};
		String res[] = DBConnection.createProfile(input).split(",");
    if(res[0].equals("success")){
		session.setAttribute("pid",res[1]);
		RequestDispatcher rd=request.getRequestDispatcher("OwnerScreen.jsp?t1=Profile Created with Profile ID "+res[1]);
		rd.forward(request, response);
	}else{
		RequestDispatcher rd=request.getRequestDispatcher("OwnerScreen.jsp?t1=Error in creating profile");
		rd.forward(request, response);
	}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	}

}
