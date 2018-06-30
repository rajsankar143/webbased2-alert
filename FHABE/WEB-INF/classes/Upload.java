package com;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Iterator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
public class Upload extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	response.setContentType("text/html");
	boolean flag=false;
	byte b[]=null;
	String file = null;
	PrintWriter out = response.getWriter();
	DiskFileItemFactory factory = new DiskFileItemFactory();
	factory.setSizeThreshold(10*1024*1024);
	factory.setRepository(new File("C:/usr"));
    ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setSizeMax(10*1024*1024);
	try{
		List fileItems = upload.parseRequest(request);
		Iterator itr = fileItems.iterator();
		while(itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			file = item.getName();
			InputStream in=item.getInputStream();
			b = new byte[in.available()];
			in.read(b,0,b.length);
		}
		HttpSession session=request.getSession();
		String user = (String)session.getAttribute("user");
		byte enc[] = AES.encrypt(b);
		String str = DBConnection.uploadHistory(user,enc,file);
		if(str.equals("success")){
			RequestDispatcher rd=request.getRequestDispatcher("Upload.jsp?t1=File uploaded");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd=request.getRequestDispatcher("Upload.jsp?t1=Error in uploading");
			rd.forward(request, response);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}

}
