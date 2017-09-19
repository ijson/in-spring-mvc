package com.ijson.platform.generator.model;

public class ColumnEntity {

	private String columnName;//列名
	//获得指定列的数据类型名 
	private String columnTypeName = "";
	//对应数据类型的类 
	private String columnClassName;
	//在数据库中类型的最大字符个数
	private int precision;
	//是否为空 
	private int isNullable;//0为不可空,1为可空

	private String attrName;//属性名
	private String jdbcType;//数据类型

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnTypeName() {
		return columnTypeName;
	}

	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}

	public String getColumnClassName() {
		return columnClassName;
	}

	public void setColumnClassName(String columnClassName) {
		this.columnClassName = columnClassName;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(int isNullable) {
		this.isNullable = isNullable;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

}
