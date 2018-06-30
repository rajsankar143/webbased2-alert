
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
<form name="f1" method="post" action="AccessPolicy" onsubmit="return validate(this);"><br/>
   <h2><b>Access Structure Screen</b></h2>
   
	<%
	String res = request.getParameter("t1");
	if(res != null){
		out.println("<center><font face=verdana color=red>"+res+"</center></font>");
	}%>
	
						<table align="center" width="30%" >
			 

	 <tr><td><b>Access File</b></td><td><select name="t1" style="font-family: Comic Sans MS">
	 <option value="Personal Profile">Personal Profile</option>
     </select>
	</td></tr>


	 <tr><td><b>Access Structure</b></td><td><select name="t2" style="font-family: Comic Sans MS" multiple>
	 <option value="Physician">Physician</option>
	 <option value="Researcher">Researcher</option>
	 <option value="Cardiology">Cardiology</option>
	</select>
	</td></tr>

	<tr><td><b>Access File</b></td><td><select name="t3" style="font-family: Comic Sans MS">
	 <option value="Medical History">Medical History</option>
     </select>
	</td></tr>


	 <tr><td><b>Access Structure</b></td><td><select name="t4" style="font-family: Comic Sans MS" multiple>
	 <option value="Physician">Physician</option>
	 <option value="Researcher">Researcher</option>
	 <option value="Cardiology">Cardiology</option>
</select>
	</td></tr>
 

	 

			<tr><td></td><td><input type="submit" value="Submit"></td>
			</table>
				</div>	
					
				</div>
				
					
	</body>
</html>