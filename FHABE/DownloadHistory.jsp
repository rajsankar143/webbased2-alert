<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.DBConnection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.AES"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PHR</title>
</head>
<body>
<%!
byte b[];
%>
<%
String user = request.getParameter("t1").toString();
String file = request.getParameter("t2").toString();
byte[] filedata = null;
Connection con = DBConnection.getCon();
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select history_data from history where username='"+user+"' and file_name='"+file+"'");
while(rs.next()){
	filedata = rs.getBytes(1);
}
rs.close();stmt.close();con.close();
filedata = AES.decrypt(filedata);
response.setHeader("Content-Disposition", "attachment;filename=\"" +file + "\"");
response.setHeader("Content-Type", "application/octet-stream;");
java.io.OutputStream os=response.getOutputStream();
os.write(filedata,0,filedata.length);
os.flush();
os.close();
out.println("<center><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
out.println("<center><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
out.println("<br><br><br><a href=ViewFile.jsp>back</a>");
%>
</body>
</html>
	
	
	