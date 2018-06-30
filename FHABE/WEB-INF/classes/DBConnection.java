package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.FileWriter;
public class DBConnection{
    private static Connection con;
	
public static Connection getCon()throws Exception {
   try{
	   Class.forName("com.mysql.jdbc.Driver");
	   con = DriverManager.getConnection("jdbc:mysql://localhost/fhabe","root","root");
    }catch(Exception e){
		e.printStackTrace();
	}
	return con;
}

public static String uploadHistory(String user,byte enc[],String file)throws Exception{
	String msg="no";
	java.util.Date d1 = new java.util.Date();
	java.sql.Date date = new java.sql.Date(d1.getTime());
	con = getCon();
	PreparedStatement stat=con.prepareStatement("insert into history values(?,?,?,?)");
    stat.setString(1,user);
    stat.setBytes(2,enc);
    stat.setString(3,file);
    stat.setDate(4,date);
    int i=stat.executeUpdate();
    if(i > 0){
        msg = "success";
	}
	 return msg;
}
public static String createUser(String[] input)throws Exception{
    String msg="no";
    boolean flag=false;
    con = getCon();
	Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select username from newuser where username='"+input[0]+"'");
    if(rs.next()){
        flag=true;
        msg = "Username already exist";
    }
    if(!flag){
    PreparedStatement stat=con.prepareStatement("insert into newuser values(?,?,?,?,?)");
    stat.setString(1,input[0]);
    stat.setString(2,input[1]);
    stat.setString(3,input[2]);
    stat.setString(4,input[3]);
    stat.setString(5,input[4].trim());
	int i=stat.executeUpdate();
    if(i > 0){
        msg = "success";
	}
    }
    return msg;
}
public static String createProfile(String[] input)throws Exception{
    String msg="no";
    int pid = 53210;
    con = getCon();
	Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select count(*) from createprofile");
    if(rs.next()){
        pid = pid + rs.getInt(1);
    }
	java.util.Date d1 = new java.util.Date(input[1].trim());
	java.sql.Date d2 = new java.sql.Date(d1.getTime());
    PreparedStatement stat=con.prepareStatement("insert into createprofile values(?,?,?,?,?,?,?,?)");
    stat.setString(1,input[0]);
    stat.setDate(2,d2);
    stat.setString(3,input[2]);
    stat.setString(4,input[3]);
    stat.setString(5,input[4].trim());
	stat.setInt(6,pid);
	stat.setString(7,input[5].trim());
	stat.setString(8,input[6]);
	int i=stat.executeUpdate();
    if(i > 0){
        msg = "success,"+pid;
	}
    
    return msg;
}


public static int getStorage()throws Exception{
	int storage = 0;
	con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select count(*) from access_structure");
    if(rs.next()){
        storage = rs.getInt(1);
    }
    return storage;
}


public static String accessPolicy(String user,String profile,String medical,byte[] pprv,byte[] ppub,byte[] mprv,byte[] mpub,String transport)throws Exception{
    String msg="no";
	con = getCon();
	PreparedStatement stat=con.prepareStatement("insert into access_structure values(?,?,?,?,?,?,?,?)");
    stat.setString(1,user);
    stat.setString(2,profile);
    stat.setString(3,medical);
    stat.setBytes(4,pprv);
    stat.setBytes(5,ppub);
	stat.setBytes(6,mprv);
	stat.setBytes(7,mpub);
	stat.setString(8,transport);
	int i = stat.executeUpdate();
    if(i > 0){
        msg = "success";
	}
    
    return msg;
}

public static String getType(String user)throws Exception{
	String type = "none";
	con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select usertype from newuser where username='"+user+"'");
    if(rs.next()){
        type = rs.getString(1);
    }
    System.out.println(type);
    return type;
}
public static String login(String input[])throws Exception{
    String msg="invalid login";
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select usertype from newuser where username='"+input[0]+"' and password='"+input[1]+"'");
    if(rs.next()){
        msg = rs.getString(1);
    }
    System.out.println(msg);
    return msg;
}

}
