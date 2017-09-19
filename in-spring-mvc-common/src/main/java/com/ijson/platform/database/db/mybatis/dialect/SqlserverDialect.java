/**
  * <br>JAVACC DEMO 1.0<br>
  * @copy right zbx company All rights reserved.<br>
  * <br>
  * @Package com.zbx.database.db.ibatis.dialect
*/
package com.ijson.platform.database.db.mybatis.dialect;

import com.zbx.database.constant.Validator;

/**
  * description: 
  * @author Hou DaYu 创建时间：上午11:34:48
  */
public class SqlserverDialect implements Dialect {

	/* (non-Javadoc)
	 * @see com.zbx.database.db.ibatis.dialect.Dialect#supportsLimit()
	 */
	@Override
	public boolean supportsLimit() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.zbx.database.db.ibatis.dialect.Dialect#supportsLimitOffset()
	 */
	@Override
	public boolean supportsLimitOffset() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.zbx.database.db.ibatis.dialect.Dialect#getLimitString(java.lang.String, int, int, java.lang.String)
	 */
	@Override
	public String getLimitString(String sql, int offset, int limit, String orderby) {
		sql = sql.trim();
		String orderByStr = "";
		if (Validator.isNotNull(orderByStr)) {
			orderByStr = " order by " + orderby;
		} else {
			orderByStr = "";
		}
		StringBuffer pageSql = new StringBuffer(sql.length() + 15);
		pageSql.append(sql).insert(getAfterSelectInsertPoint(sql), " top " + (offset + limit));

		// 其实这里还是有一点问题的，就是排序问题，指定死了，有解决的提供一下，等复习到Hibernate看看Hibernat内部是如何实现的。  
		//		pageSql.append("select top " + (offset + limit) + " * from( ");
		//		pageSql.append(sql);
		//		pageSql.append(" ) a " + orderByStr);
		return pageSql.toString();
	}

	private int getAfterSelectInsertPoint(String sql) {
		int selectIndex = sql.toLowerCase().indexOf("select");
		final int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
		return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
	}
}
