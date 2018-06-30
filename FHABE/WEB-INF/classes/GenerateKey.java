package com;
import bswabe.Bswabe;
import bswabe.BswabeCph;
import bswabe.BswabeCphKey;
import bswabe.BswabeElementBoolean;
import bswabe.BswabeMsk;
import bswabe.BswabePrv;
import bswabe.BswabePub;
import bswabe.BswabeCph;
public class GenerateKey {
	
	static BswabePub public_key;
public static BswabePrv generateKey(String attr[])throws Exception{
	public_key = new BswabePub();
	BswabeMsk master_key = new BswabeMsk();
	Bswabe.setup(public_key,master_key);
	BswabePrv private_key = Bswabe.keygen(public_key,master_key,attr);
	return private_key;
}
public static BswabeCph encrypt(String policy,BswabePub pu)throws Exception{
	public_key = pu;
	BswabeCphKey crypted = Bswabe.enc(public_key,policy);
	BswabeCph cph = crypted.cph;
	return cph;
}
public static boolean decrypt(BswabePub pub,BswabePrv prv,BswabeCph cph)throws Exception{
	boolean flag=false;
	BswabeElementBoolean result = Bswabe.dec(pub,prv,cph);
	if(result.b == true)
		flag = true;
	return flag;
}
}
