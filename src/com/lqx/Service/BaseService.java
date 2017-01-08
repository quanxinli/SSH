package com.lqx.Service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lqx.utils.PageView;
import com.lqx.utils.SqlText;


public interface BaseService<T> {

	//public Session openSession();
	/**
	 * 取得當前線程的Session
	 * @return
	 */
	public Session getSession();
	/**
	 * 取得Spring JdbcTemplate操作模版
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate();
	/**
	 * 保存实体
	 * @param entity
	 */
	public void save(T entity);
	
	/**
	 * 更新实体
	 * @param entity
	 */
	public void update(T entity);
	/**
	 * 更新
	 * @param sql
	 * @param params
	 */
	public void update(String sql, Object... params);
	/**
	 * 删除实体
	 * @param ids
	 */
	public void delete(Serializable... ids);
	/**
	 * 保存或者更新
	 * @param entity
	 */
	public void saveOrUpdate(T entity);
	/**
	 * 查找实体
	 * @param id
	 * @return
	 */
	public T find(Serializable id);
	/**
	 * 获取怕有数据
	 * @return 查询结果集

	/**
	 * 取得所有用户信息
	 * @param pageNumber 当前页码
	 * @return
	 */
	public PageView<T> getPageView(Integer currentPage);
	/**
	 * 根据查询参数取得数据
	 */
	public PageView<T> getPageView(Integer currentPage,
			SqlText whereSql);
	/**
	 * 根据查询参数取得数据
	 */
	public PageView<T> getPageView(Integer currentPage,
			SqlText whereSql, String order);
	
	
	
	/**
	 * 根据where 条件语句获取记录数
	 * @param whereJPQL
	 * @return
	 */
	public long getCount(SqlText whereSql);
	
	/**
	 * 根据 where 语句查询数据并按 order by 语句排序
	 */
	public List<T> getList(SqlText whereSql, String order);
	
	/**
	 * 获取数据集
	 * @param whereSql
	 * @param order
	 * @param limit 返回数量
	 * @return
	 */
	public List<T> getList(SqlText whereSql, String order, int limit);
	/**
	 * 根据 where 语句返回结果集
	 */
	public List<T> getList(SqlText whereSql);
	
	/**
	 * 根据 where 语句返回结果集
	 */
	public List<T> getList();
	
	public List<T> getList(String orderBy);
	
	public T get(SqlText whereSql);
	
	public List<T> getList(String orderBy,int limit);
	
}