<%@page import="org.jfree.ui.RefineryUtilities"%>
<%@page import="com.DBConnection"%>
<%@page import="com.Chart"%>
<%
int storage = DBConnection.getStorage();
Chart chart1 = new Chart("Storage Cost Chart",(storage*2*200),(storage*1*200));
chart1.pack();
RefineryUtilities.centerFrameOnScreen(chart1);
chart1.setVisible(true);
response.sendRedirect("OwnerScreen.jsp");
%>