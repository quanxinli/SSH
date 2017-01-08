package com.lqx.ctrl;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("init")
public class InitCtrl {
	@RequestMapping("404")
	public ModelAndView To404(){
		return new ModelAndView("init/404");
	}
	@RequestMapping("500")
	public ModelAndView To500(){
		
		return new ModelAndView("init/404");
	}
	

}
