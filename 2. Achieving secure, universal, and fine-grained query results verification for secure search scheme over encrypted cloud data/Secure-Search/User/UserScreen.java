package com;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.math.BigInteger;
import org.jfree.ui.RefineryUtilities;
import java.util.Random;
public class UserScreen extends JFrame{
	JButton b1,b2,b3,b4,b5;
	JPanel p1,p2;
	Font f1;
	JTextArea area;
	JScrollPane jsp;
	Login login;
	String user;
	JFileChooser chooser;
	ArrayList<byte[]> trapdoor = new ArrayList<byte[]>();
	ArrayList<VerificationObject> vlist;
	long time;
	int keyword_size;
public UserScreen(Login log,String usr){
	super("User Screen");
	login = log;
	user = usr;
	p1 = new JPanel();
	f1 = new Font("Monospaced",Font.BOLD,16);
	chooser = new JFileChooser();
	b1 = new JButton("Upload File");
	b1.setFont(f1);
	p1.add(b1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			int option = chooser.showOpenDialog(UserScreen.this);
			if(option == JFileChooser.APPROVE_OPTION){
				File file = chooser.getSelectedFile();
				upload(file);
			}
		}
	});
	
	b2 = new JButton("Search Query");
	b2.setFont(f1);
	p1.add(b2);
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			search();
		}
	});

	b5 = new JButton("Verification");
	b5.setFont(f1);
	p1.add(b5);
	b5.addActionListener(new ActionListener(){
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			verification();
		}
	});

	b3 = new JButton("Keywords Vs Verification");
	b3.setFont(f1);
	p1.add(b3);
	b3.addActionListener(new ActionListener(){
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			Chart chart1 = new Chart("Keywords Vs Verification Chart",time,keyword_size);
			chart1.pack();
			RefineryUtilities.centerFrameOnScreen(chart1);
			chart1.setVisible(true);
		}
	});

	b4 = new JButton("Logout");
	b4.setFont(f1);
	p1.add(b4);
	b4.addActionListener(new ActionListener(){
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			setVisible(false);
			login.setVisible(true);
		}
	});

	p2 = new JPanel();
	p2.setLayout(new BorderLayout());
	area = new JTextArea();
	area.setFont(f1);
	area.setEditable(false);
	jsp = new JScrollPane(area);
	p2.add(jsp,BorderLayout.CENTER);

	getContentPane().add(p1,BorderLayout.NORTH);
	getContentPane().add(p2,BorderLayout.CENTER);

	PaillierEnc.KeyGeneration();
}

public void verification(){
	try{
		long start = System.currentTimeMillis();
		ArrayList<String> hash = new ArrayList<String>();
		ArrayList<String> bloom = new ArrayList<String>();
		for(int i=0;i<trapdoor.size();i++){
			BloomFilter.generateBloom(50,trapdoor.get(i));
			StringBuilder sb = new StringBuilder();
			for(int j=0;j<BloomFilter.input.length;j++){
				sb.append(BloomFilter.input[j]);
			}
			System.out.println(sb.toString()+" "+new String(AES.decrypt(trapdoor.get(i))));
			bloom.add(sb.toString());
		}

		for(int i=0;i<bloom.size();i++){
			BigInteger input = new BigInteger(bloom.get(i).getBytes());
			hash.add(PaillierEnc.Encryption(input).toString());
		}

		StringBuilder sb = new StringBuilder();
		for(int i=0;i<hash.size();i++){
			for(int j=0;j<vlist.size();j++){
				VerificationObject vo = vlist.get(j);
				keyword_size = keyword_size + vo.getKeyword().size();
				for(int k=0;k<vo.getHash().size();k++){
					String h = vo.getHash().get(k);
					if(h.equals(hash.get(i))){
						File ff = new File(vo.getPath());
						File ff1 = new File(ff.getParent());
						sb.append(ff1.getName()+"/"+ff.getName()+",");
					}
				}
			}
		}
		System.out.println("verify "+sb.toString());
		if(sb.length() > 0){
			sb.deleteCharAt(sb.length()-1);
			ViewSearchResult vsr = new ViewSearchResult();
			String arr1[] = sb.toString().trim().split(",");
			for(int i=0;i<arr1.length;i++){
				String row[] = {arr1[i]};
				vsr.dtm.addRow(row);
			}
			vsr.setVisible(true);
			vsr.setSize(600,400);
			vsr.setTitle("Files obtains from verification object for same query");
		}else{
			JOptionPane.showMessageDialog(this,"No record found");
		}
		long end = System.currentTimeMillis();
		time = end - start;
	}catch(Exception e){
		e.printStackTrace();
	}
}
public void upload(File file){
	try{
		FileInputStream fin = new FileInputStream(file);
		byte file_data[] = new byte[fin.available()];
		fin.read(file_data,0,file_data.length);
		fin.close();
		byte enc[] = AES.encrypt(file_data);

		String keywords = new String(file_data);
		String arr[] = keywords.trim().toLowerCase().split("\\s+");
		ArrayList<byte[]> encrypted_keywords = new ArrayList<byte[]>();
		ArrayList<String> bloom = new ArrayList<String>();
		ArrayList<String> hash = new ArrayList<String>();
		ArrayList<String> dup = new ArrayList<String>();
		for(int i=0;i<arr.length;i++){
			if(!dup.contains(arr[i])){
				dup.add(arr[i]);
				byte data[] = AES.encrypt(arr[i].getBytes());
				encrypted_keywords.add(data);
			}
		}

		for(int i=0;i<encrypted_keywords.size();i++){
			BloomFilter.generateBloom(50,encrypted_keywords.get(i));
			StringBuilder sb = new StringBuilder();
			for(int j=0;j<BloomFilter.input.length;j++){
				sb.append(BloomFilter.input[j]);
			}
			System.out.println(sb.toString()+" "+new String(AES.decrypt(encrypted_keywords.get(i))));
			bloom.add(sb.toString());
		}

		for(int i=0;i<bloom.size();i++){
			BigInteger input = new BigInteger(bloom.get(i).getBytes());
			hash.add(PaillierEnc.Encryption(input).toString());
		}

		Socket socket = new Socket("localhost",3333);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		Object req[]={"upload",user,file.getName(),enc,encrypted_keywords,bloom,hash};
		out.writeObject(req);
		out.flush();
		Object res[]=(Object[])in.readObject();
		String response = (String)res[0];
		
		area.append(response+"\n");
		out.close();
		in.close();
		socket.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}

public void search(){
	try{
		String input = JOptionPane.showInputDialog(this,"Enter input query");
		if(input != null){
			input = input.trim().toLowerCase();
			String arr[] = input.split("\\s+");
			trapdoor.clear();
			for(int i=0;i<arr.length;i++){
				byte enc[] = AES.encrypt(arr[i].trim().getBytes());
				trapdoor.add(enc);
				area.append("Query = "+arr[i]+" Encrypted trapdoor = "+new String(enc)+"\n");
			}

			Socket socket = new Socket("localhost",3333);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			Object req[]={"query",user,trapdoor};
			out.writeObject(req);
			out.flush();
			Object res[]=(Object[])in.readObject();
			String response = (String)res[0];
			System.out.println("==="+response);
			vlist = (ArrayList<VerificationObject>)res[1];
			if(!response.equals("No record found for given querys")){
				ViewSearchResult vsr = new ViewSearchResult();
				String arr1[] = response.split(",");
				int random = getRandom();
				int size = arr1.length;
				if(random == 1)
					size = size - 1;
				for(int i=0;i<size;i++){
					String row[] = {arr1[i]};
					vsr.dtm.addRow(row);
				}
				vsr.setVisible(true);
				vsr.setSize(600,400);
			}else{
				JOptionPane.showMessageDialog(this,response);
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
public int getRandom(){
	Random r = new Random();
	return r.nextInt(2);
}
}