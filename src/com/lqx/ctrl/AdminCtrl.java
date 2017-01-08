package com.lqx.ctrl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminCtrl {

	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//System.out.println("login..............");
		return new ModelAndView("admin/login");
	}
	@RequestMapping("To403")
	public ModelAndView To403(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("403..............");
		return new ModelAndView("admin/403");
	}
}
