package com.ijson.platform.generator.dao;

import com.ijson.platform.generator.model.ColumnEntity;
import com.ijson.platform.generator.model.TableEntity;
import com.ijson.platform.generator.util.ConnctionData;
import com.ijson.platform.generator.util.SystemUtil;
import com.ijson.platform.generator.util.ToolsUtil;
import com.ijson.platform.generator.util.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MysqlDaoImpl implements IDao {

    private ConnctionData conn;

    public MysqlDaoImpl() {
        conn = ConnctionData.getInstance(SystemUtil.getInstance().getConstant("jdbc.url"), SystemUtil.getInstance()
                .getConstant("jdbc.driver"), SystemUtil.getInstance().getConstant("jdbc.user"), SystemUtil
                .getInstance().getConstant("jdbc.password"));
    }

    public List<TableEntity> getTables(String[] tableNames) {
        List<TableEntity> result = new ArrayList<TableEntity>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = conn.getConnection();
            if (Validator.isEmpty(connection)) {
                System.out.println("连接数据库成功...");
            }
            int count = tableNames.length;
            String tabPrefix = SystemUtil.getInstance().getConstant("table_prefix").toLowerCase();
            for (int q = 0; q < count; q++) {
                String sql = "select * from " + tableNames[q].toLowerCase();
                stmt = connection.prepareStatement(sql);
                rs = stmt.executeQuery(sql);
                ResultSetMetaData data = rs.getMetaData();
                String pkColumn = getPKColumn(tableNames[q], connection);
                TableEntity table = new TableEntity();
                String tableName = tableNames[q].toLowerCase();
                System.out.println("当前表名:" + tableName);
                //if (rs.next()) {
                int ccnum = data.getColumnCount();
                for (int i = 1; i <= ccnum; i++) {
                    ColumnEntity column = new ColumnEntity();
                    //获得所有列的数目及实际列数
                    //	int columnCount = data.getColumnCount();
                    //获得指定列的列名
                    String columnName = data.getColumnName(i).toLowerCase();/////
                    //获得指定列的列值
                    //	String columnValue = rs.getString(i);
                    //获得指定列的数据类型
                    //	int columnType = data.getColumnType(i);
                    //获得指定列的数据类型名
                    String columnTypeName = data.getColumnTypeName(i);////
                    //所在的Catalog名字
                    //	String catalogName = data.getCatalogName(i);
                    //对应数据类型的类
                    //	String columnClassName = data.getColumnClassName(i);
                    //在数据库中类型的最大字符个数
                    int columnDisplaySize = data.getColumnDisplaySize(i);////
                    //默认的列的标题
                    //	String columnLabel = data.getColumnLabel(i);
                    //获得列的模式
                    //	String schemaName = data.getSchemaName(i);
                    //某列类型的精确度(类型的长度)
                    //	int precision = data.getPrecision(i);
                    //小数点后的位数
                    //	int scale = data.getScale(i);
                    //获取某列对应的表名
                    //tableName = data.getTableName(i);
                    // 是否自动递增
                    //boolean isAutoInctement = data.isAutoIncrement(i);
                    //在数据库中是否为货币型
                    //	boolean isCurrency = data.isCurrency(i);
                    //是否为空
                    int isNullable = data.isNullable(i);/////
                    column.setColumnName(columnName);
                    column.setIsNullable(isNullable);
                    column.setColumnTypeName(columnTypeName);
                    column.setPrecision(columnDisplaySize);
                    column.setAttrName(ToolsUtil.toCamelNamed(columnName));
                    //column.set
                    //是否为只读
                    //	boolean isReadOnly = data.isReadOnly(i);
                    //能否出现在where中
                    //boolean isSearchable = data.isSearchable(i);
                    //System.out.println(columnCount);
                    System.out.println("----->获得列" + i + "的字段名称:" + columnName);
                    //System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
                    //System.out.println("获得列" + i + "在数据库中类型的最大字符个数:" + columnDisplaySize);
                    //System.out.println("获得列" + i + "对应的表名:" + tableName);
                    //System.out.println("获得列" + i + "是否为空:" + isNullable);
                    table.setColumns(column);
                }
                //}
                if (Validator.isNotNull(pkColumn)) {
                    table.setpKColumn(ToolsUtil.toCamelNamed(pkColumn));
                }
                table.setTableAttName(ToolsUtil.toUpperFirst(tableName.substring(tabPrefix.length())));
                table.setTableName(tableName);
                result.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        } finally {
            conn.close(connection, stmt, rs);
        }
        return result;
    }

    /**
     * 获取主键列名称,推荐string
     */
    private String getPKColumn(String tableName, Connection conn) {
        String pkColumn = "";
        DatabaseMetaData dbMeta;
        ResultSet pkRSet = null;
        try {
            dbMeta = conn.getMetaData();
            pkRSet = dbMeta.getPrimaryKeys(null, null, tableName.toLowerCase());
            if (pkRSet.next()) {
                pkColumn = pkRSet.getString("column_name").toLowerCase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != pkRSet) {
                    pkRSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pkColumn;
    }
}
