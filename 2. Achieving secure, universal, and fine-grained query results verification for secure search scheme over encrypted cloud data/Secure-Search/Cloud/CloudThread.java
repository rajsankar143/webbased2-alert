package com;
public class CloudThread extends Thread{
	Cloud server;
public CloudThread(Cloud server){
	this.server=server;
	start();
}
public void run(){
	server.start();
}
}