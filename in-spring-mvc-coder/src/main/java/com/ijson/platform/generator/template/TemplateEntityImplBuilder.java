package com.ijson.platform.generator.template;

import com.ijson.platform.generator.model.ColumnEntity;
import com.ijson.platform.generator.model.ParamsVo;
import com.ijson.platform.generator.model.TableEntity;
import com.ijson.platform.generator.util.DataType;
import com.ijson.platform.generator.util.FileOperate;
import com.ijson.platform.generator.util.ToolsUtil;
import com.ijson.platform.generator.util.Validator;

import java.util.List;
import java.util.Map;


public class TemplateEntityImplBuilder implements TemplateHanlder {

    public void execute(ParamsVo<TableEntity> vo, Map<String, String> config) {
        List<TableEntity> tables = vo.getObjs();
        String prefix = Validator.getDefaultStr(String.valueOf(vo.getParams("prefix")), "src/main/");
        getTemplateStr(prefix, tables, config);
    }


    public void getTemplateStr(String prefix, List<TableEntity> tables, Map<String, String> config) {
        String classPath = config.get("fs_path") + "/" + prefix + "java/"
                + config.get("package_name").replace(".", "/") + "/entity/";
        FileOperate.getInstance().newCreateFolder(classPath);
        if (!Validator.isEmpty(tables)) {
            for (TableEntity table1 : tables) {
                StringBuilder result = new StringBuilder("");
                String tableName = table1.getTableAttName();
                result.append(getImports(config));
                result.append("@SuppressWarnings(\"serial\")\n");
                result.append("@Setter()\n");
                result.append("@Getter()\n");
                result.append("public class ").append(tableName).append(" extends BaseEntity { \n\n");
                result.append(getClassMethods(table1.getColumns()));
                result.append("} \n");
                FileOperate.getInstance().newCreateFile(classPath + tableName + ".java", result.toString());
            }
        }
    }


    public void getTemplateStr2(String prefix, List<TableEntity> tables, Map<String, String> config) {
        String classPath = config.get("fs_path") + "/" + prefix + "java/"
                + config.get("package_name").replace(".", "/") + "/entity/";
        FileOperate.getInstance().newCreateFolder(classPath);
        if (!Validator.isEmpty(tables)) {
            for (TableEntity table1 : tables) {
                StringBuilder result = new StringBuilder("");
                String tableName = table1.getTableAttName();
                result.append(getImports(config));
                result.append("@SuppressWarnings(\"serial\")\n");
                result.append("public class ").append(tableName).append(" extends BaseEntity { \n\n");
                result.append(getClassMethods(table1.getColumns()));
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
        return "package " + config.get("package_name")
                + ".entity;\n\n" + "import com.ijson.platform.api.model.BaseEntity;\n" +
                "import lombok.Getter;\n" +
                "import lombok.Setter;\n" +
                "\n \n";
    }

    /**
     * 生成类中的方法
     *
     * @return
     */
    private String getClassMethods(List<ColumnEntity> columns) {
        int count = columns.size();
        StringBuilder result = new StringBuilder("");
        String cols[] = new String[columns.size()];
        for (int i = 0; i < count; i++) {
            ColumnEntity column = columns.get(i);
            String colType = DataType.getDataType(column.getColumnTypeName(), false, column.getPrecision());
            String str = colType + " " + column.getAttrName();
            cols[i] = str;
        }
        result.append("\n   ");
        for (int i = 0; i < count; i++) {
            result.append("private ").append(cols[i]).append(";\n   ");
        }
        result.append("\n");
        return result.toString();
    }

    private String getClassMethods2(List<ColumnEntity> columns) {
        int count = columns.size();
        StringBuilder result = new StringBuilder("");
        String cols[] = new String[count];
        StringBuilder sb = new StringBuilder("      StringBuffer sb = new StringBuffer();\n");
        for (int i = 0; i < count; i++) {
            ColumnEntity column = columns.get(i);
            String colType = DataType.getDataType(column.getColumnTypeName(), false, column.getPrecision());
            String str = colType + " " + column.getAttrName();
            cols[i] = str;
            sb.append("      sb.append(\"").append(column.getAttrName()).append("=\"+this.").append(column.getAttrName()).append("+\";\");\n");
            result.append("   public ").append(colType).append(" get").append(ToolsUtil.toUpperFirst(column.getAttrName())).append("() {\n");
            result.append("      return this. ").append(column.getAttrName()).append(";\n   } \n");
            result.append("   public void set").append(ToolsUtil.toUpperFirst(column.getAttrName())).append("(").append(str).append(" ) {\n");
            result.append("      this. ").append(column.getAttrName()).append("=").append(column.getAttrName()).append(";\n   } \n");
        }
        result.append("\n   ");
        for (int i = 0; i < count; i++) {
            result.append("private ").append(cols[i]).append(";\n   ");
        }
        result.append("public String toString(){\n");
        result.append(sb.toString()).append("\n");
        result.append("      return sb.toString();\n");
        result.append("   } \n");
        result.append("\n");
        return result.toString();
    }
}
