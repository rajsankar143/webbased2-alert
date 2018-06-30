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
            <li><a href="ViewPPersonalProfile.jsp">View Personal Profile</a></li>
            <li><a href="ViewPMedicalProfile.jsp">View Medical Profile</a></li>
			
           	<li><a href="Logout.jsp">Logout</a></li>
          </ul>
        </div>
        <div class="hbg"><img src="images/header_images.jpg" width="915" height="286" alt="" /></div>
      				<center>

   <h2><b>View User Profile Screen</b></h2>
   
	<center>
<form name="f1" method="post" action="PProfile.jsp" onsubmit="return validate(this);"><br/>
   
   
	<%
	String res = request.getParameter("t1");
	if(res != null){
		out.println("<center><font face=verdana color=red>"+res+"</center></font>");
	}%>
						
						<table align="center" width="40" >
			 <tr><td><b>Username</b></td><td><select name="t1">
			
	<%
	Connection con = DBConnection.getCon();
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("select username from newuser where usertype='Data Owner'");
	while(rs.next()){
		String s1 = rs.getString(1);%>
		<option value="<%=s1%>"><%=s1%></option>
	<%}%>
	</select></td></tr>
	<tr><td></td><td><input type="submit" value="Submit"></td>
	</table>
	</body>
</html>