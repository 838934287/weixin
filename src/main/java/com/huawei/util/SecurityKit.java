package com.huawei.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityKit {
	
	public static String sha1(String str){
		
		StringBuffer sb = new StringBuffer();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("sha1");
			md.update(str.getBytes());
			byte[] msg = md.digest();
			for (byte b:msg) {
				sb.append(String.format("%02x", b));
//				System.out.println(b);
			}
//			System.out.println("sb: "+sb.toString());
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}


