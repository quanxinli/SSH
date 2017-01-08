package com.lqx.ServiceBean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Entity;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.lqx.Service.BaseService;
import com.lqx.utils.PageView;
import com.lqx.utils.SqlBuilder;
import com.lqx.utils.SqlText;


@Transactional(value="transactionManager")
public abstract  class BaseServiceBean<T> implements BaseService<T> {
	
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/*取得当前对象的泛型父类*/	
	private Class<?> entityClass = getSuperClassGenricType();
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	
	/*public Session openSession(){
		return sessionFactory.openSession();
	}*/
	
	public JdbcTemplate getJdbcTemplate(){
		return this.jdbcTemplate;
	}
	
	/*
	 * 删除实体
	 */
	public void delete(Serializable... ids) {
		for (Serializable id : ids) {
			getSession().delete(getSession().load(this.entityClass, id));
		}
		
	}
	/*
	 * 查找实体
	 */
	@SuppressWarnings("unchecked")
	public T find(Serializable id) {
		return (T)getSession().get(entityClass, id);
	}
	/*
	 * 保存实体
	 */
	public void save(T entity) {
		getSession().persist(entity);
	}
	/*
	 * 更新实体
	 */
	public void update(T entity) {
		getSession().merge(entity);
	}
	
	public void update(String sql, Object... params){
		Query query = getSession().createQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		query.executeUpdate();
	}
	
	public void saveOrUpdate(T entity){
		getSession().saveOrUpdate(entity);
	}
	
	
	/*
	 * 分頁方法
	 * 取得當前實體對就應的表的分頁數據
	 * (non-Javadoc)
	 * @see com.foxlink.pipr.service.IBaseService#getPageView(java.lang.Integer, com.foxlink.pipr.dao.sql.SqlText, java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageView<T> getPageView(Integer currentPage,SqlText whereSql, String order) {
		currentPage = currentPage==null ? 1 : currentPage;
		PageView<T> pageView = new PageView<T>(currentPage);
		List records = createQuery(whereSql,order)
						.setFirstResult(pageView.getFirstResult())
						.setMaxResults(pageView.getMaxResult())
						.list();
		pageView.setRecords(records);
		pageView.setTotalRecords(getCount(whereSql));
		return pageView;
	}
	/*
	 * 获取分页数据
	 * @param currentPage 当前页码
	 * @param whereSQLs where语句
	 */
	public PageView<T> getPageView(Integer currentPage,
			SqlText whereSql) {		
		return getPageView(currentPage, whereSql, null);
	}
	/*
	 * 获取分页数据
	 * @param currentPage 当前页码
	 */
	public PageView<T> getPageView(Integer currentPage) {
		return getPageView(currentPage, null, null);
	}
	
//	public int update(SqlText setSql, SqlText whereSql){
//		String updateSql = SqlBuilder.createUpdateSql(getEntityName(), setSql, whereSql);
//		setSql.getParameters().addAll(whereSql.getParameters());
//		return jdbcTemplate.update(updateSql,setSql.getParameters().toArray());
//	}
	public long getCount(SqlText whereSql) {
		String countSql = SqlBuilder.createCountSql(getEntityName(), whereSql);
		Query query = getSession().createQuery(countSql);
		setParameter(query, whereSql);
		return (Long)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(SqlText whereSql, String order, int limit) {
		if (limit > 0) {
			return createQuery(whereSql, order).setMaxResults(limit).list();
		}
		return createQuery(whereSql, order).list();
	}
	
	@Override
	public List<T> getList(SqlText whereSql, String order) {
		return getList(whereSql, order, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(SqlText whereSql) {
		return createQuery(whereSql, null).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(String orderBy) {
		return createQuery(null, orderBy).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(String orderBy,int limit) {
		return createQuery(null, orderBy).setMaxResults(limit).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList() {
		return createQuery(null, null).list();
	}
	
	
	public T get(SqlText whereSql){
		List<T> list = getList(whereSql);
		if (list != null && list.size()> 0) {
			return list.get(0);
		}
		return null;
	}
	/*  
     * 通过反射,获得指定类的父类的泛型参数的实际类型.
     *  如BuyerServiceBean extends DaoSupport<Buyer>  
     * @param clazz clazz 需要反射的类,该类必须继承范型父类
     * @param index 泛型参数所在索引,从0开始
     * @return 范型参数的实际类型, 如果没有实现ParameterizedType接口，
     * 即不支持泛型，所以直接返回<code>Object.class</code>
     */  
	private Class<?> getSuperClassGenricType() {    
        Type genType = this.getClass().getGenericSuperclass();//得到帶泛型父类  
        //如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class   
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;   
        }  
        //返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class, 如BuyerServiceBean extends DaoSupport<Buyer,Contact>就返回Buyer和Contact类型   
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();                   
            
        if (!(params[0] instanceof Class<?>)) {
            return Object.class;   
        }   
        return (Class<?>) params[0];
    }
	
	/*
	 * 取得当前对象的简单类名
	 * @param <E>
	 * @param entityClass
	 * @return
	 */
	private String getEntityName() {
		String entityName = entityClass.getSimpleName();
		Entity entity =  entityClass.getAnnotation(Entity.class);
		if (entity!=null && !"".equals(entity.name().trim())) {
			entityName = entity.name().trim();
		}
		return entityName;
	}
	

	/*
	 * 創建一個查詢對象
	 */
	private Query createQuery(SqlText whereSql, String order) {
		String querySql = SqlBuilder.createQuerySql(getEntityName(), whereSql, order);
		Query query = getSession().createQuery(querySql);
		setParameter(query,whereSql);
		return query;
	}

	/*
	 * 設置查詢參數
	 * @param query
	 * @param whereSql
	 */
	private void setParameter(Query query, SqlText whereSql) {
		if (whereSql != null) {
			for (int i = 0; i < whereSql.getParameters().size(); i++) {
				query.setParameter(i, whereSql.getParameters().get(i));
			}
		}
	}
}
