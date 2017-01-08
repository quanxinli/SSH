package com.lqx.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lqx.bean.Admin;
import com.lqx.utils.SqlBuilder;
import com.lqx.utils.SqlText;

@Controller
public class testCtrl {

	@RequestMapping("test")
	public ModelAndView toTest(){
		SqlBuilder.createQuerySql("com.lqx.Admin", new SqlText("where o.name=? and o.password=?", "lqx","221"),null);
		return new ModelAndView("test/test");
		
	}
}
