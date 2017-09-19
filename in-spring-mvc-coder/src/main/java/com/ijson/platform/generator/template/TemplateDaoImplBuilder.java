package com.ijson.platform.generator.template;

import com.ijson.platform.api.model.ParamsVo;
import com.ijson.platform.generator.model.TableEntity;
import com.ijson.platform.generator.util.FileOperate;
import com.ijson.platform.common.util.Validator;

import java.util.List;
import java.util.Map;


public class TemplateDaoImplBuilder implements TemplateHanlder {

    public void execute(ParamsVo<TableEntity> vo,Map<String, String> config) {
        List<TableEntity> tables = vo.getObjs();
        String prefix = Validator.getDefaultStr(String.valueOf(vo.getParams("prefix")), "src/main/");
        createdDaoImpl(prefix, tables,config);
    }

    public void createdDaoImpl(String prefix, List<TableEntity> tables, Map<String, String> config) {
        String daoPath = config.get("fs_path") + "/" + prefix + "java/"
                + config.get("package_name").replace(".", "/") + "/dao/";
        FileOperate.getInstance().newCreateFolder(daoPath);
        getTemplateStr(tables, daoPath,config);
    }

    public void getTemplateStr(List<TableEntity> tables, String daoPath,Map<String, String> config) {
        if (!Validator.isEmpty(tables)) {
            int count = tables.size();
            for (int i = 0; i < count; i++) {
                StringBuffer result = new StringBuffer("");
                TableEntity table = tables.get(i);
                String tableName = table.getTableAttName();
                result.append(getImports(config));
                result.append("public class " + tableName + "DaoImpl extends DaoImpl { \n\n");
                result.append(getClassMethods(table));
                result.append("} \n");
                FileOperate.getInstance().newCreateFile(daoPath + tableName + "DaoImpl.java", result.toString());
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
                + ".dao;\n\n");
        result.append("import com.ijson.platform.database.db.DaoImpl;\n");
        result.append("\n \n");
        return result.toString();
    }

    /**
     * 生成类中的方法
     *
     * @return
     */
    private String getClassMethods(TableEntity table) {
        StringBuffer result = new StringBuffer("   public String getSql(int type) { \n");
        String sql = "from " + table.getTableAttName() + " where 1=1";
        result.append("      String sql = \"\";\n");
        result.append("      switch (type) {\n");
        result.append("      case 1:\n");
        result.append("           sql=\"select count(*) " + sql + " \";\n           break;\n");
        result.append("      case 2:\n");
        result.append("           sql=\" " + sql + " \";\n           break;\n");
        result.append("      default:\n");
        result.append("          sql=\"select count(*) " + sql + " \";\n    }\n");
        result.append("    return sql;\n }\n");
        result.append("\n \n");
        result.append("   public void initSystemCache() { \n");
        result.append("   }\n");
        return result.toString();
    }

}
