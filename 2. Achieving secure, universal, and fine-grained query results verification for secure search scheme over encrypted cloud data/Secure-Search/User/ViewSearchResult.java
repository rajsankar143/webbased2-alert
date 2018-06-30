package com;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.File;
import javax.swing.JOptionPane;
public class ViewSearchResult extends JFrame{
	Font f1;
	JPanel p1,p2;
	DefaultTableModel dtm;
	JScrollPane jsp;
	JTable table;
	JButton b1;
public ViewSearchResult(){
	setTitle("Query Found In Below Files At Cloud Server");
	getContentPane().setLayout(new BorderLayout());
	
    f1 = new Font("Courier New",Font.BOLD,14);
    p1 = new JPanel();
	p1.setLayout(new BorderLayout());
    dtm = new DefaultTableModel(){
		public boolean isCellEditable(){
			return false;
		}
	};
	dtm.addColumn("File Names");
	table = new JTable(dtm);
	table.setFont(f1);
	table.setRowHeight(30);
	jsp = new JScrollPane(table);
	p1.add(jsp,BorderLayout.CENTER);

	p2 = new JPanel();
	b1 = new JButton("Download & Decrypt File");
	b1.setFont(f1);
	p2.add(b1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			download();
		}
	});
	getContentPane().add(p2, BorderLayout.SOUTH);
	getContentPane().add(p1, BorderLayout.CENTER);
}
public void download(){
	try{
		int row = table.getSelectedRow();
		String file = dtm.getValueAt(row,0).toString().trim();
		File fname = new File(file);
		Socket socket = new Socket("localhost",3333);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		Object req[]={"download",file};
		out.writeObject(req);
		out.flush();
		Object res[]=(Object[])in.readObject();
		byte b[] = (byte[])res[0];
		byte decrypt[] = AES.decrypt(b);
		FileOutputStream fout = new FileOutputStream("D:/"+fname.getName());
		fout.write(decrypt,0,decrypt.length);
		fout.close();
		JOptionPane.showMessageDialog(this,file+" File saved in D directory");
	}catch(Exception e){
		e.printStackTrace();
	}
}
}