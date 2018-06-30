package com;
import java.util.ArrayList;
public class BloomFilter{
	static byte encrypt_data[];
	static int input[] = new int[50];
	static ArrayList<Integer> list = new ArrayList<Integer>();
public static void generateBloom(int key,byte enc[]){
	encrypt_data = enc;
	list.clear();
	for(int i=0;i<encrypt_data.length;i++){
		String data = new String(Byte.toString(encrypt_data[i]));
		int bloom = data.hashCode()%key;
		list.add(bloom);
	}

	for(int i=0;i<50;i++){
		if(list.contains(i))
			input[i] = 1;
		else
			input[i] = 0;
	}
}
}