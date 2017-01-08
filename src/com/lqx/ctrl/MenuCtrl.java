package com.lqx.ctrl;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.lqx.Service.MenuService;
import com.lqx.bean.Menu;
import com.lqx.utils.UserUtil;

@Controller
@RequestMapping("/menu")
public class MenuCtrl {

	@Resource
	private MenuService menuService;
	/**
	 * 
	 * TODO 初始化菜單
	 * lqx
	 * 2017年1月2日
	 * @param request
	 * @param reponse
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("init")
	public ModelAndView initMenu(HttpServletRequest request,HttpServletResponse reponse){
		/**
		 * 取得所有菜單列表，在頁面區分目錄等級然後列出到導航欄
		 */
		System.out.println(UserUtil.getUserName());
		ModelAndView mv = new ModelAndView("test/test");
		List<Menu> firstList = menuService.hibernateGetFistMenu();
		List<Menu> SecondList = menuService.hibernateGetSecondMenu();
		
		mv.addObject("firstList", firstList);
		mv.addObject("SecondList", SecondList);
		return mv;
	}
	@RequestMapping("list111")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse reponse){
		ModelAndView mv = new ModelAndView("/menu/menuShow");
		return mv;
	}
	@RequestMapping("getMenuJson")
	public void getMenuJson(HttpServletRequest request ,HttpServletResponse reponse) throws IOException{
		OutputStream out = reponse.getOutputStream();
		List<Map<String, Object>> menu = menuService.getAllMenu();
		Gson gson=new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", menu.size());
		map.put("rows", menu);
		reponse.setContentType("text/UTF-8");
		out.write(gson.toJson(map).getBytes("UTF-8"));
	}
	@RequestMapping("getFirstJson")
	public void getFirstJson(HttpServletRequest request ,HttpServletResponse reponse) throws IOException{
		reponse.getOutputStream().write(new Gson().toJson(menuService.getFirstMenu()).getBytes("UTF-8"));
	}
	@RequestMapping("menu_save")
	public void menu_save(HttpServletRequest request ,HttpServletResponse reponse,Menu menu){
		try {
			if(menu!=null&&menu.getParent().getId()!=null){
				menu.setParent(menuService.find(menu.getParent().getId()));
			}else{
				menu.setParent(null);
			}
			menuService.saveOrUpdate(menu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("menu_delete")
	public void menu_delete(HttpServletRequest request ,HttpServletResponse reponse,Integer id){
		Menu menu = menuService.find(id);
		if (menu!=null&&menu.getParent()!=null) {
			menu.setParent(null);
		}
		menuService.saveOrUpdate(menu);
		//menuService.flush(menu);
		menuService.delete(id);
	}
}
