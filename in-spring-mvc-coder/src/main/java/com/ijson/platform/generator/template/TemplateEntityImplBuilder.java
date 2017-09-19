package com.ijson.platform.generator.template;

import com.ijson.platform.generator.model.ColumnEntity;
import com.ijson.platform.api.model.ParamsVo;
import com.ijson.platform.generator.model.TableEntity;
import com.ijson.platform.generator.util.DataType;
import com.ijson.platform.generator.util.FileOperate;
import com.ijson.platform.generator.util.ToolsUtil;
import com.ijson.platform.common.util.Validator;

import java.util.List;
import java.util.Map;


public class TemplateEntityImplBuilder implements TemplateHanlder {

    public void execute(ParamsVo<TableEntity> vo,Map<String, String> config) {
        List<TableEntity> tables = vo.getObjs();
        String prefix = Validator.getDefaultStr(String.valueOf(vo.getParams("prefix")), "src/main/");
        getTemplateStr(prefix, tables,config);
    }

    public void getTemplateStr(String prefix, List<TableEntity> tables,Map<String, String> config) {
        String classPath = config.get("fs_path") + "/" + prefix + "java/"
                + config.get("package_name").replace(".", "/") + "/entity/";
        FileOperate.getInstance().newCreateFolder(classPath);
        if (!Validator.isEmpty(tables)) {
            int count = tables.size();
            for (int i = 0; i < count; i++) {
                StringBuffer result = new StringBuffer("");
                TableEntity table = tables.get(i);
                String tableName = table.getTableAttName();
                result.append(getImports(config));
                result.append("@SuppressWarnings(\"serial\")\n");
                result.append("public class " + tableName + " extends BaseEntity { \n\n");
                result.append(getClassMethods(table.getColumns()));
                result.append("} \n");
                FileOperate.getInstance().newCreateFile(classPath + tableName + ".java", result.toString());
            }
        }
    }

    /**
     * 返回类的头引入内容
     *
     * @return
     */
    private String getImports(Map<String, String> config) {
        StringBuffer result = new StringBuffer("package " + config.get("package_name")
                + ".entity;\n\n");
        result.append("import com.ijson.platform.api.model.BaseEntity;\n");
        result.append("\n \n");
        return result.toString();
    }

    /**
     * 生成类中的方法
     *
     * @return
     */
    private String getClassMethods(List<ColumnEntity> columns) {
        int count = columns.size();
        StringBuffer result = new StringBuffer("");
        String cols[] = new String[count];
        StringBuffer sb = new StringBuffer("      StringBuffer sb = new StringBuffer();\n");
        for (int i = 0; i < count; i++) {
            ColumnEntity column = columns.get(i);
            String colType = DataType.getDataType(column.getColumnTypeName(), false, column.getPrecision());
            String str = colType + " " + column.getAttrName();
            cols[i] = str;
            sb.append("      sb.append(\"" + column.getAttrName() + "=\"+this." + column.getAttrName() + "+\";\");\n");
            result.append("   public " + colType + " get" + ToolsUtil.toUpperFirst(column.getAttrName()) + "() {\n");
            result.append("      return this. " + column.getAttrName() + ";\n   } \n");
            result.append("   public void set" + ToolsUtil.toUpperFirst(column.getAttrName()) + "(" + str + " ) {\n");
            result.append("      this. " + column.getAttrName() + "=" + column.getAttrName() + ";\n   } \n");
        }
        result.append("\n   ");
        for (int i = 0; i < count; i++) {
            result.append("private " + cols[i] + ";\n   ");
        }
        result.append("public String toString(){\n");
        result.append(sb.toString() + "\n");
        result.append("      return sb.toString();\n");
        result.append("   } \n");
        result.append("\n");
        return result.toString();
    }
}
