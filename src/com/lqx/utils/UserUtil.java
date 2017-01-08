package com.lqx.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtil {
	
	public static String getUserName(){
		String username="None";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			System.out.println(((UserDetails) principal).getAuthorities());
		 username = ((UserDetails)principal).getUsername();
		} 
		return username;
	}

}
