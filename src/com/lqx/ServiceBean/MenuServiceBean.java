package com.lqx.ServiceBean;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.lqx.Service.MenuService;
import com.lqx.bean.Menu;
import com.lqx.utils.SqlText;
@Service

public class MenuServiceBean extends BaseServiceBean<Menu> implements MenuService{
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Map<String, Object>> getFirstMenu(){
		return this.jdbcTemplate.queryForList("select * from table_menu where col_parent_id is null order by col_index asc");
	}
	
	@Override
	public List<Map<String, Object>> getAllMenu(){
		return this.jdbcTemplate.queryForList("select *  from table_menu a  left join (select col_menu_id as me,col_menu_name as col_top_name from table_menu ) b on a.col_parent_id=b.me order by a.col_index asc");
	}
	
	@Override
	public void menu_save(Menu menu){
		save(menu);
	}
	@Override
	public List<Menu> hibernateGetFistMenu(){
		return getList(new SqlText("where o.parent is null"), "order by o.index asc");
	}
	@Override
	public List<Menu> hibernateGetSecondMenu(){
		return getList(new SqlText("where o.parent is not null"), "order by o.index asc");
	}
	@Override
	public void flush(Menu menu){
		getSession().refresh(menu);
	}
}
