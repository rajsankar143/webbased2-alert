package com;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bswabe.SerializeUtils;
import bswabe.BswabePrv;
import bswabe.BswabePub;
import bswabe.BswabeCph;

public class AccessPolicy extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	response.setContentType("text/html");
	HttpSession session = request.getSession();
	PrintWriter out = response.getWriter();
	
	String personal = request.getParameter("t1").trim();
	String personal_access[] = request.getParameterValues("t2");

	String medical = request.getParameter("t3").trim();
	String medical_access[] = request.getParameterValues("t4");

	StringBuilder p = new StringBuilder();
	StringBuilder m = new StringBuilder();
	for(int i=0;i<personal_access.length;i++){
		p.append(personal_access[i].trim()+",");
	}
	p.deleteCharAt(p.length()-1);
	for(int i=0;i<medical_access.length;i++){
		m.append(medical_access[i].trim()+",");
	}
	m.deleteCharAt(m.length()-1);
	String per[] = p.toString().trim().split(",");
	String med[] = m.toString().trim().split(",");
	boolean flag = false;
	for(int i=0;i<med.length;i++){
		boolean status = false;
		for(int j=0;j<per.length;j++){
			if(med[i].equals(per[j])){
				status = true;
			}
		}
		if(status)
			flag = true;
		else
			flag = false;
	}
	try{
		String transport = "none";
		byte pprv[] = SerializeUtils.serializeBswabePrv(GenerateKey.generateKey(per));
		byte ppub[] = SerializeUtils.serializeBswabePub(GenerateKey.public_key);

		byte mprv[] = SerializeUtils.serializeBswabePrv(GenerateKey.generateKey(med));
		byte mpub[] = SerializeUtils.serializeBswabePub(GenerateKey.public_key);
			
		if(true){
			mprv = pprv;
			mpub = ppub;
			transport = "true";
		}
		String user = session.getAttribute("user").toString();
		String res = DBConnection.accessPolicy(user,p.toString().trim(),m.toString().trim(),pprv,ppub,mprv,mpub,transport);
		if(res.equals("success")){
			RequestDispatcher rd=request.getRequestDispatcher("OwnerScreen.jsp?t1=Access policy file created");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd=request.getRequestDispatcher("OwnerScreen.jsp?t1=Error in creating access policy");
			rd.forward(request, response);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
}

}
