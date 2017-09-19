package com.ijson.platform.generator.model;

import java.util.ArrayList;
import java.util.List;

public class TableEntity {

	private String tableName;//表名

	private String tableAttName;//类名

	private String pKColumn;//主键列名

	private List<ColumnEntity> columns = new ArrayList<ColumnEntity>();

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<ColumnEntity> getColumns() {
		return columns;
	}

	public void setColumns(ColumnEntity column) {
		this.columns.add(column);
	}

	public String getpKColumn() {
		return pKColumn;
	}

	public void setpKColumn(String pKColumn) {
		this.pKColumn = pKColumn;
	}

	public String getTableAttName() {
		return tableAttName;
	}

	public void setTableAttName(String tableAttName) {
		this.tableAttName = tableAttName;
	}

}
