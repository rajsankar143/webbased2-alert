<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>FH-ABE</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<script language="javascript">
	function validate(formObj)
	{
	if(formObj.t1.value.length==0)
	{
	alert("Please Enter Username");
	formObj.t1.focus();
	return false;
	}
	if(formObj.t2.value.length==0)
	{
	alert("Please Enter DOB");
	formObj.t2.focus();
	return false;
	}
	if(formObj.t3.value.length==0)
	{
	alert("Please Enter Age");
	formObj.t3.focus();
	return false;
	}
	if(isNaN(formObj.t3.value)){
		alert("Age  must be numeric");
		formObj.t3.focus();
		return false;
	}
	
	if(formObj.t5.value.length==0)
	{
	alert("Please Enter Social Security No");
	formObj.t5.focus();
	return false;
	}
	if(formObj.t6.value.length==0)
	{
	alert("Please Enter Problem Description");
	formObj.t6.focus();
	return false;
	}
	formObj.actionUpdateData.value="update";
	return true;
	}
	</script>
	 <script language="javascript" type="text/javascript" src="datetimepicker.js">
</script>
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
<form name="f1" method="post" action="CreateProfile" onsubmit="return validate(this);"><br/>
   <h2><b>User Profile Creation Screen</b></h2>
   
	<%
	String res = request.getParameter("t1");
	if(res != null){
		out.println("<center><font face=verdana color=red>"+res+"</center></font>");
	}%>
						
						<table align="center" width="30%" >
			 <tr><td><b>Name</b></td><td><input type="text" name="t1" style="font-family: Comic Sans MS" size=20 value="<%=session.getAttribute("user").toString()%>"></td></tr>
         
		  <tr><td><b>DOB</b></td><td><input name="t2" type="Text" id="demo1" maxlength="25" size="20" class="c2" ><a href="javascript:NewCal('demo1','ddmmmyyyy',false,24)"><img src="cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
	  		<span class="descriptions"></span></td></tr>

			<tr><td><b>Age</b></td><td><input type="text" name="t3" style="font-family: Comic Sans MS" size=20/></td></tr>

		   <tr><td><b>Gender</b></td><td><select name="t4" style="font-family: Comic Sans MS">
		   <option value="Male">Male</option>
		   <option value="Female">Female</option>
		   </select>
		   </td></tr>

		   <tr><td><b>S.S.No</b></td><td><input type="text" name="t5" style="font-family: Comic Sans MS" size=20/></td></tr>

          <tr><td><b>Problem Description</b></td><td><textarea name="t6" style="font-family: Comic Sans MS" rows=6 cols="20"></textarea></td></tr>
			<tr><td></td><td><input type="submit" value="Create"></td>
			</table>
				</div>	
					
				</div>
				
					
	</body>
</html>