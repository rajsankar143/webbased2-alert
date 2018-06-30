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
Encryption Scheme in Cloud Computing</span><small></small></h1>
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
		<br/>
		
	<%
	String res = request.getParameter("t1");
	if(res != null){
		out.println("<center><font face=verdana color=red>"+res+"</center></font>");
	}%>
        <p align="justify"><font size="3" style="font-family: Comic Sans MS" color="black">Abstract-Ciphertext-policy attribute-based encryption
(CP-ABE) has been a preferred encryption technology to
solve the challenging problem of secure data sharing in
cloud computing. The shared data files generally have the
characteristic of multilevel hierarchy, particularly in the area of
healthcare and the military. However, the hierarchy structure
of shared files has not been explored in CP-ABE. </p>
<p align="justify"><font size="3" style="font-family: Comic Sans MS" color="black">
 In this paper,
an efficient file hierarchy attribute-based encryption scheme
is proposed in cloud computing. The layered access structures
are integrated into a single access structure, and then, the
hierarchical files are encrypted with the integrated access
structure. The ciphertext components related to attributes could
be shared by the files.</p>
<p align="justify"><font size="3" style="font-family: Comic Sans MS" color="black">
Therefore, both ciphertext storage and
time cost of encryption are saved. Moreover, the proposed
scheme is proved to be secure under the standard assumption.
Experimental simulation shows that the proposed scheme is
highly efficient in terms of encryption and decryption. With the
number of the files increasing, the advantages of our scheme
become more and more conspicuous. </p>
 
 
  </body>
</html>
