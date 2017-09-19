package com.ijson.platform.database.db.mybatis.dialect;

import com.zbx.database.constant.Validator;

/**
 * description: DB2分页插件
 * 
 * @author Hou DaYu 
 */
public class DB2Dialect implements Dialect {

	/* (non-Javadoc)
	 * @see com.kimbanx.database.db.ibatis3.dialect.Dialect#supportsLimit()
	 */
	public boolean supportsLimit() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.kimbanx.database.db.ibatis3.dialect.Dialect#supportsLimitOffset()
	 */
	public boolean supportsLimitOffset() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.kimbanx.database.db.ibatis3.dialect.Dialect#getLimitString(java.lang.String, int, int)
	 */
	public String getLimitString(String sql, int offset, int limit, String orderby) {
		if (sql == null)
			return "";
		sql = trim(sql);
		String rowNumSql = getRowNumSql(orderby);
		StringBuffer sb = new StringBuffer(sql.length() + 20);
		sb.append("SELECT * FROM (SELECT A.*, " + rowNumSql + "as ROWNUM from (");
		sb.append(sql);
		sb.append(" )A ) WHERE ROWNUM <");
		sb.append(limit + 1);
		sb.append(" and ROWNUM >");
		if (offset - 1 >= 0) {
			sb.append(offset - 1);
		}
		return sb.toString();
	}

	/**
	 * 为sql添加order by语句。
	 * 
	 * @return 如果有排序条件的话则返回添加了order by语句的sql，否则直接返回sql
	 */
	private String getRowNumSql(String orderby) {
		if (Validator.isNull(orderby))
			return "";
		StringBuffer strBuf = new StringBuffer("ROW_NUMBER() OVER(ORDER BY  " + orderby + " )");
		return strBuf.toString();
	}

	/**
	 * 去除空格，如果该字符串以";"结尾则去掉。
	 * 
	 * @param sql 源串
	 * @return
	 */
	private String trim(String sql) {
		if (Validator.isNull(sql)) {
			return sql;
		}
		sql = sql.trim();
		if (sql.endsWith(";")) {
			sql = sql.substring(0, sql.length() - 1);
		}
		return sql;
	}
}
