package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class DBCon{
    private static Connection con;
	
public static Connection getCon()throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost/securesearch","root","root");
     return con;
}
public static String register(String[] input)throws Exception{
    String msg="fail";
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select user from newuser where user='"+input[0]+"'");
    if(rs.next()){
		msg = "Username already exist";
    }else{
		PreparedStatement stat=con.prepareStatement("insert into newuser values(?,?,?,?,?)");
		stat.setString(1,input[0]);
		stat.setString(2,input[1]);
		stat.setString(3,input[2]);
		stat.setString(4,input[3]);
		stat.setString(5,input[4]);
		int i=stat.executeUpdate();
		if(i > 0){
			msg = "Registration process completed";
		}
    }
    return msg;
}

public static String search(ArrayList<byte[]> trapdoor)throws Exception{
	StringBuilder sb = new StringBuilder();
	con = getCon();
	ArrayList<String> dup = new ArrayList<String>();
	for(int i=0;i<trapdoor.size();i++){
		PreparedStatement stat = con.prepareStatement("select file from keywords where keyword=?");
		stat.setBytes(1,trapdoor.get(i));
		ResultSet rs = stat.executeQuery();
		while(rs.next()){
			String value = rs.getString(1);
			if(!dup.contains(value)){
				dup.add(value);
				sb.append(value+",");
			}
		}
		stat.close();
	}
	//System.out.println("result "+sb.toString());
	if(sb.length() == 0)
		sb.append("No record found for given querys");
	else
		sb.deleteCharAt(sb.length()-1);
	return sb.toString().trim();
}
public static String login(String input[])throws Exception{
    String msg="fail";
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select user,pass from newuser where user='"+input[0]+"' && pass='"+input[1]+"'");
    if(rs.next()){
        msg = "pass";
    }
    return msg;
}

public static void saveKeywords(String file,ArrayList<byte[]> encrypted_keywords)throws Exception{
	con = getCon();
	for(int i=0;i<encrypted_keywords.size();i++){
		PreparedStatement stat = con.prepareStatement("insert into keywords values(?,?)");
		stat.setString(1,file);
		stat.setBytes(2,encrypted_keywords.get(i));
		stat.executeUpdate();
		stat.close();
	}
	con.close();
}
}
