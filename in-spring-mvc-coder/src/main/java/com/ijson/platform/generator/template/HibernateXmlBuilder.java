package com.ijson.platform.generator.template;

import com.ijson.platform.common.util.SystemUtil;
import com.ijson.platform.generator.model.ColumnEntity;
import com.ijson.platform.api.model.ParamsVo;
import com.ijson.platform.generator.model.TableEntity;
import com.ijson.platform.generator.util.DataType;
import com.ijson.platform.generator.util.FileOperate;
import com.ijson.platform.common.util.Validator;

import java.util.List;


public class HibernateXmlBuilder implements TemplateHanlder {

    public void execute(ParamsVo<TableEntity> vo) {
        List<TableEntity> tables = vo.getObjs();
        String prefix = Validator.getDefaultStr(String.valueOf(vo.getParams("prefix")), "src/main/");
        createdHibernateXml(prefix, tables);
    }

    public void createdHibernateXml(String prefix, List<TableEntity> tables) {
        String jarPath = SystemUtil.getInstance().getConstant("package_name");
        String xmlPath = SystemUtil.getInstance().getConstant("fs_path") + "/" + prefix + "resources/hibernate/";
        FileOperate.getInstance().newCreateFolder(xmlPath);
        if (!Validator.isEmpty(tables)) {
            int count = tables.size();
            for (int i = 0; i < count; i++) {
                TableEntity table = tables.get(i);
                StringBuffer result = new StringBuffer("");
                result.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
                result.append("<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\" \n");
                result.append("\"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">\n\n");
                result.append("<hibernate-mapping>\n");
                result.append("    <class name=\"" + jarPath + ".entity." + table.getTableAttName() + "\" table=\""
                        + table.getTableName() + "\" >\n");

                result.append(getResultMap(table));

                result.append("    </class>\n");
                result.append("</hibernate-mapping>\n");
                FileOperate.getInstance().newCreateFile(xmlPath + table.getTableAttName() + ".hbm.xml",
                        result.toString());
            }
        }
    }

    public String getResultMap(TableEntity table) {
        StringBuffer result = new StringBuffer("");
        int count = table.getColumns().size();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                ColumnEntity col = table.getColumns().get(i);
                String colType = DataType.getDataType(col.getColumnTypeName(), true, col.getPrecision());

                if (col.getAttrName().equalsIgnoreCase(table.getPKColumn())) {
                    result.append("    <id name=\"" + col.getAttrName() + "\" type=\"" + colType + "\">\n");
                    result.append("        <column name=\"" + col.getColumnName() + "\" length=\"" + col.getPrecision()
                            + "\" />\n");
                    result.append("        <generator class=\"assigned\" />\n");
                    result.append("    </id>\n");
                } else {
                    result.append("    <property name=\"" + col.getAttrName() + "\" type=\"" + colType + "\">\n");
                    result.append("        <column name=\"" + col.getColumnName() + "\" length=\"" + col.getPrecision()
                            + "\" />\n");
                    result.append("    </property>\n");
                }
            }
        }
        return result.toString();
    }
}
