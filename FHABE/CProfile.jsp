<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.DBConnection"%>
<%@page import="bswabe.SerializeUtils"%>
<%@page import="com.GenerateKey"%>
<%@page import="bswabe.BswabeCph"%>
<%@page import="bswabe.BswabePrv"%>
<%@page import="bswabe.BswabePub"%>
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
            <li><a href="ViewCPersonalProfile.jsp">View Personal Profile</a></li>
            <li><a href="ViewCMedicalProfile.jsp">View Medical Profile</a></li>
			
           	<li><a href="Logout.jsp">Logout</a></li>
          </ul>
        </div>
        <div class="hbg"><img src="images/header_images.jpg" width="915" height="286" alt="" /></div>
      				<center>

   <h2><b>View User Profile Screen</b></h2>
   
	
			
	<%
	String patient = request.getParameter("t1");
	String user = session.getAttribute("user").toString();
	String usertype = DBConnection.getType(user);
	Connection con = DBConnection.getCon();
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("select personal_structure,pprivate_key,ppublic_key from access_structure where username='"+patient+"'");
	while(rs.next()){
		String att = rs.getString(1);
		byte prv[] = rs.getBytes(2);
		byte pub[] = rs.getBytes(3);
		if(att.indexOf(usertype) != -1){
			BswabePub public_key = SerializeUtils.unserializeBswabePub(pub);
			BswabePrv private_key = SerializeUtils.unserializeBswabePrv(public_key,prv);
			String policy = usertype+" 100 1of2";
			BswabeCph cph = GenerateKey.encrypt(policy,public_key);
			boolean flag = GenerateKey.decrypt(public_key,private_key,cph);
			if(flag){%>

			<table border="1" align="center" width="100%">
			<tr><th>Person Name</th><th>Birth Date</th><th>Age</th>
			<th>Gender</th><th>S.S.No</th><th>Problem Description</th>
			<%
			Statement stmt1 = con.createStatement();
	ResultSet rs1 = stmt1.executeQuery("select * from createprofile where username='"+patient+"'");
	while(rs1.next()){
		%>
	<tr><td><font size="3" color="black"><%=rs1.getString(1)%></td>
	<td><font size="3" color="black"><%=rs1.getString(2)%></td>
	<td><font size="3" color="black"><%=rs1.getString(3)%></td>
	<td><font size="3" color="black"><%=rs1.getString(4)%></td>
	<td><font size="3" color="black"><%=rs1.getString(5)%></td>
	<td><font size="3" color="black"><%=rs1.getString(8)%></td>
	
	</td>
<%}}}
	}%>
	</tr>
	</table>
	</body>
</html>