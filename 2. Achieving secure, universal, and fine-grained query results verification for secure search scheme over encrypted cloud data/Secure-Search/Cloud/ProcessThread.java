package com;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.FileInputStream;
import java.io.File;
import javax.swing.JTextArea;
import java.io.FileOutputStream;
import java.util.ArrayList;
public class ProcessThread extends Thread{
    Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;
	JTextArea area;
	ArrayList<VerificationObject> vlist;
public ProcessThread(Socket soc,JTextArea area){
    socket=soc;
	this.area=area;
    try{
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }catch(Exception e){
        e.printStackTrace();
    }
}
@Override
public void run(){
    try{
		Object input[]=(Object[])in.readObject();
        String type=(String)input[0];
		if(type.equals("register")){
			String user = (String)input[1];
			String pass = (String)input[2];
			String contact = (String)input[3];
			String mail = (String)input[4];
			String address = (String)input[5];
			String input_data[]={user,pass,contact,mail,address};
			String msg = DBCon.register(input_data);
			if(msg.equals("Registration process completed")){
				File file = new File("Users/"+user);
				if(!file.exists())
					file.mkdir();
			}
			Object res[] = {msg};
			out.writeObject(res);
			area.append(user+" "+msg+"\n");
		}
		if(type.equals("login")){
			String user = (String)input[1];
			String pass = (String)input[2];
			String input_data[]={user,pass};
			String msg = DBCon.login(input_data);
			Object res[] = {msg};
			out.writeObject(res);
			String res1="";
			if(msg.equals("pass")){
				res1 = user+" login successfully";
			}
			else
				res1 = user+" login failed";
			area.append(res1+"\n");
		}
		if(type.equals("logout")){
			String user = (String)input[1];
			Object res[] = {user+" logout successfully"};
			out.writeObject(res);
			area.append(user+" logout successfully from server\n");
		}
		if(type.equals("download")){
			String file = (String)input[1];
			System.out.println(file);
			FileInputStream fin = new FileInputStream("Users/"+file);
			byte b[] = new byte[fin.available()];
			fin.read(b,0,b.length);
			fin.close();
			Object res[] = {b};
			out.writeObject(res);
			out.flush();
			area.append("File sent to user\n");
		}
		if(type.equals("query")){
			String user = (String)input[1];
			ArrayList<byte[]> trapdoor = (ArrayList<byte[]>)input[2];
			String response = DBCon.search(trapdoor);
			File vfile = new File("VerifyObject.txt");
			if(!vfile.exists()) {
				vlist = new ArrayList<VerificationObject>();
			} else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(vfile));
				Object object = (Object)ois.readObject();
				vlist = (ArrayList<VerificationObject>)object;
				ois.close();
			}
			area.append("Query result sent to user "+user+"\n");
			Object res[] = {response,vlist};
			out.writeObject(res);
			out.flush();
		}
		if(type.equals("upload")){
			String user = (String)input[1];
			String file = (String)input[2];
			byte file_data[] = (byte[])input[3];
			ArrayList<byte[]> encrypted_keywords = (ArrayList<byte[]>)input[4];
			ArrayList<String> bloom = (ArrayList<String>)input[5];
			ArrayList<String> hash = (ArrayList<String>)input[6];
			FileOutputStream fout = new FileOutputStream("Users/"+user+"/"+file);
			fout.write(file_data,0,file_data.length);
			fout.close();

			DBCon.saveKeywords(user+"/"+file,encrypted_keywords);
			
			File vfile = new File("VerifyObject.txt");
			if(!vfile.exists()) {
				vlist = new ArrayList<VerificationObject>();
			} else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(vfile));
				Object object = (Object)ois.readObject();
				vlist = (ArrayList<VerificationObject>)object;
				ois.close();
			}
			VerificationObject vo = new VerificationObject();
			vo.setKeyword(encrypted_keywords);
			vo.setBloom(bloom);
			vo.setHash(hash);
			vo.setPath("Users/"+user+"/"+file);
			vlist.add(vo);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(vfile));
			oos.writeObject(vlist);
			oos.flush();
			oos.close();
			String msg = "File "+file+" saved at cloud server";
			area.append(msg+"\n");
			Object res[] = {msg};
			out.writeObject(res);
			out.flush();
		}
    }catch(Exception e){
        e.printStackTrace();
    }
}
}
