package com;
import java.io.Serializable;
import java.util.ArrayList;
public class VerificationObject implements Serializable{
	ArrayList<byte[]> encrypted_keyword;
	ArrayList<String> bloom_code;
	ArrayList<String> hash;
	String file_path;

public void setKeyword(ArrayList<byte[]> encrypted_keyword){
	this.encrypted_keyword = encrypted_keyword;
}
public ArrayList<byte[]> getKeyword(){
	return encrypted_keyword;
}

public void setBloom(ArrayList<String> bloom_code){
	this.bloom_code = bloom_code;
}
public ArrayList<String> getBloom(){
	return bloom_code;
}

public void setHash(ArrayList<String> hash){
	this.hash = hash;
}
public ArrayList<String> getHash(){
	return hash;
}
public void setPath(String file_path){
	this.file_path = file_path;
}
public String getPath(){
	return file_path;
}
}