<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.DBConnection"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>FH-ABE</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main">
  <div class="main_resize">
    <div class="header">
      <div class="logo">
        <h1><span>An Efficient File Hierarchy Attribute-Based
Encryption Scheme in Cloud Computing </span><small></small></h1>
      </div>
    </div>
    <div class="content">
      <div class="content_bg">
        <div class="menu_nav">
         <ul>
            <li><a href="CreateProfile.jsp">Create Profile</a></li>
            <li><a href="ViewProfile.jsp">View Profile</a></li>
			<li><a href="AccessPolicy.jsp">Access Structure</a></li>
			<li><a href="Upload.jsp">Upload Medical History</a></li>
			<li><a href="StorageChart.jsp">Storage Cost</a></li>
			<li><a href="Logout.jsp">Logout</a></li>
          </ul>
        </div>
        <div class="hbg"><img src="images/header_images.jpg" width="915" height="286" alt="" /></div>
      				<center>

   <h2><b>View User Profile Screen</b></h2>
   
	<%
	String res = request.getParameter("t1");
	if(res != null){
		out.println("<center><font face=verdana color=red>"+res+"</center></font>");
	}%>
			<table border="1" align="center" width="100%">
			<tr><th>Person Name</th><th>Birth Date</th><th>Age</th>
			<th>Gender</th><th>S.S.No</th><th>Problem Description</th><tr>
	<%
	Connection con = DBConnection.getCon();
	String pid = session.getAttribute("user").toString();
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("select * from createprofile where username='"+pid+"'");
	while(rs.next()){%>
	<tr><td><font size="3" color="black"><%=rs.getString(1)%></td>
	<td><font size="3" color="black"><%=rs.getString(2)%></td>
	<td><font size="3" color="black"><%=rs.getString(3)%></td>
	<td><font size="3" color="black"><%=rs.getString(4)%></td>
	<td><font size="3" color="black"><%=rs.getString(5)%></td>
	<td><font size="3" color="black"><%=rs.getString(8)%></td>
	<%}%>
	</tr>
	</table>
	</body>
</html>