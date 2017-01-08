package com.lqx.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class EncryptUtil {
	private static final String SITE_WIDE_SECRET = "my-secret-key";  
	   private static final PasswordEncoder encoder = new StandardPasswordEncoder(  
	      SITE_WIDE_SECRET);  
	  
	   public static String encrypt(String rawPassword) {  
	        return encoder.encode(rawPassword);  
	   }  
	  
	   public static boolean match(String rawPassword, String password) {  
	        return encoder.matches(rawPassword, password);  
	   }  
	   

}
