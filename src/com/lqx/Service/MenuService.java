package com.lqx.Service;

import java.util.List;
import java.util.Map;

import com.lqx.bean.Menu;

public interface MenuService  extends BaseService<Menu>{

	

	List<Map<String, Object>> getFirstMenu();

	List<Map<String, Object>> getAllMenu();

	void menu_save(Menu menu);

	List<Menu> hibernateGetFistMenu();

	List<Menu> hibernateGetSecondMenu();

	void flush(Menu menu);

	

}
