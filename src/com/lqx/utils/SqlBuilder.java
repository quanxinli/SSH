package com.lqx.utils;


public class SqlBuilder {
	private final static String SELECT = " SELECT ";
	private final static String FROM = " FROM ";
	private final static String UPDATE = " UPDATE ";
	private final static String o = " o ";
	private final static String COUNT = " COUNT(o) ";
	
	
	public static String createUpdateSql(String entityName, SqlText setSql, SqlText whereSql) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(UPDATE).append(entityName).append(o);
		sb.append(setSql.getSql());
		if (whereSql != null) {
			sb.append(" ");
			sb.append(whereSql.getSql());
		}
		return sb.toString();
	}
	

	
	
	/**
	 * 创建select查询语句
	 * @param whereJPQL
	 * @param type
	 * @return
	 */
	public static String createQuerySql(String entityName, SqlText whereSql, String order) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(o).append(FROM);
		sql.append(entityName).append(o);
		if (whereSql != null) {
			sql.append(whereSql.getSql());
		}
		if (order != null && !"".equals(order)) {
			sql.append(" ").append(order);
		}
		
		return sql.toString();
	}

	/**
	 * 创建 Count统计语句
	 * @param whereSql
	 * @return
	 */
	public static String createCountSql(String entityName, SqlText whereSql) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(COUNT).append(FROM);
		sql.append(entityName).append(o);
		if (whereSql != null) {
			sql.append(whereSql.getSql());
		}
		return sql.toString();
	}

}