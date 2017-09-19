package com.ijson.platform.generator.template;

import com.ijson.platform.generator.model.ParamsVo;
import com.ijson.platform.generator.model.TableEntity;
import com.ijson.platform.generator.util.FileOperate;
import com.ijson.platform.generator.util.SystemUtil;
import com.ijson.platform.generator.util.Validator;

import java.util.List;


public class TemplateDaoImplBuilder implements TemplateHanlder {

    public void execute(ParamsVo<TableEntity> vo) {
        List<TableEntity> tables = vo.getObjs();
        String prefix = Validator.getDefaultStr(String.valueOf(vo.getParams("prefix")), "src/main/");
        createdDaoImpl(prefix, tables);
    }

    public void createdDaoImpl(String prefix, List<TableEntity> tables) {
        String daoPath = SystemUtil.getInstance().getConstant("fs_path") + "/" + prefix + "java/"
                + SystemUtil.getInstance().getConstant("package_name").replace(".", "/") + "/dao/";
        FileOperate.getInstance().newCreateFolder(daoPath);
        getTemplateStr(tables, daoPath);
    }

    public void getTemplateStr(List<TableEntity> tables, String daoPath) {
        if (!Validator.isEmpty(tables)) {
            int count = tables.size();
            for (int i = 0; i < count; i++) {
                StringBuffer result = new StringBuffer("");
                TableEntity table = tables.get(i);
                String tableName = table.getTableAttName();
                result.append(getImports());
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
    private String getImports() {
        StringBuffer result = new StringBuffer("package " + SystemUtil.getInstance().getConstant("package_name")
                + ".dao;\n\n");
        result.append("import cn.datamining.database.db.DaoImpl;\n");
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
