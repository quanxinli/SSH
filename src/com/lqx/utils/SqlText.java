package com.lqx.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *  
 *
 */
public class SqlText{
	/* sql 文本*/
	private String sql;
	/*参数列表*/
	private List<Object> paramenters ;
	
	/**
	 * 带参数的构�?方法
	 * @param whereSql where语句
	 * @param values 参数数组
	 */
	public SqlText(String sql, Object... values) {
		this.sql = sql;
		add(values);
	}
	/**
	 * 添加参数方法
	 * @param values
	 */
	public void add(Object... values){
		if (paramenters==null) {
			paramenters = new ArrayList<Object>();
		}
		for (Object value : values) {
			this.paramenters.add(value);
		}
	}
		
	/**
	 * 设置 where 语句
	 * @param whereJPQL
	 */
	public void setSql(String sql) {
		this.sql = sql;					
	}
	

	/* (non-Javadoc)
	 * @see cn.zhu.bean.database.JPQLText#getWhereJPQL()
	 */
	
	public String getSql() {
		return sql;
	}

	public List<Object> getParameters(){
		return this.paramenters;
	}

	
}
