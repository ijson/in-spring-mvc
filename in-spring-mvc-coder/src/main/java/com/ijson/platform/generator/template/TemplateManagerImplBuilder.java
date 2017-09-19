package com.ijson.platform.generator.template;

import com.ijson.platform.common.util.SystemUtil;
import com.ijson.platform.common.util.Validator;
import com.ijson.platform.generator.model.ColumnEntity;
import com.ijson.platform.generator.model.ParamsVo;
import com.ijson.platform.generator.model.TableEntity;
import com.ijson.platform.generator.util.DataType;
import com.ijson.platform.generator.util.FileOperate;
import com.ijson.platform.generator.util.ToolsUtil;

import java.util.List;


public class TemplateManagerImplBuilder implements TemplateHanlder {

    private String tabPrefix = SystemUtil.getInstance().getConstant("table_prefix").toLowerCase();
    private String useCache = SystemUtil.getInstance().getConstant("use_cache").toUpperCase();

    public void execute(ParamsVo<TableEntity> vo) {
        List<TableEntity> tables = vo.getObjs();
        String prefix = Validator.getDefaultStr(String.valueOf(vo.getParams("prefix")), "src/main/");
        createdManager(prefix, tables);
        createdManagerImpl(prefix, tables);

    }

    /**
     * 生成manager接口类
     */
    public void createdManager(String prefix, List<TableEntity> tables) {
        String managerPath = SystemUtil.getInstance().getConstant("fs_path") + "/" + prefix + "java/"
                + SystemUtil.getInstance().getConstant("package_name").replace(".", "/") + "/manager/";
        FileOperate.getInstance().newCreateFolder(managerPath);
        FileOperate.getInstance().newCreateFile(managerPath + "UnityBaseManager.java", getUnityManager());
        if (!Validator.isEmpty(tables)) {
            int count = tables.size();
            for (int i = 0; i < count; i++) {
                StringBuffer result = new StringBuffer("");
                TableEntity table = tables.get(i);
                String tableName = table.getTableAttName();
                result.append(getManagerImports(tableName));
                result.append("public interface " + tableName + "Manager extends UnityBaseManager<" + tableName
                        + "> { \n\n");
                //result.append(getManagerClassMethods(tableName));
                result.append("} \n");
                FileOperate.getInstance().newCreateFile(managerPath + tableName + "Manager.java", result.toString());
            }
        }
    }

    /**
     * description: 生成模块公共接口类
     */
    private String getUnityManager() {
        StringBuffer result = new StringBuffer("package " + SystemUtil.getInstance().getConstant("package_name")
                + ".manager;\n\n");
        result.append("import cn.datamining.database.model.Page;\n");
        result.append("import cn.datamining.api.manager.BaseManager;\n");
        result.append("import cn.datamining.api.obj.ParamsVo;\n");
        result.append("\n \n     /**\n       * description: 统一manager接口\n       */\n");
        result.append("public interface UnityBaseManager<E> extends BaseManager<E> { \n\n");
        result.append("    /**\n      * 获取信息列表（带分页）\n      * @param vo 方法参数\n      * @return 返回信息列表\n    */\n");
        result.append("   Page getPageInfo(ParamsVo<E> vo); \n");
        result.append("} \n");
        return result.toString();
    }

    /**
     * 返回类的头引入内容
     *
     * @return
     */
    private String getManagerImports(String tableName) {
        StringBuffer result = new StringBuffer("package " + SystemUtil.getInstance().getConstant("package_name")
                + ".manager;\n\n");
        result
                .append("import " + SystemUtil.getInstance().getConstant("package_name") + ".entity." + tableName
                        + ";\n");
        result.append("\n \n");
        return result.toString();
    }

    /**
     * 生成manager接口的实现类
     */
    public void createdManagerImpl(String prefix, List<TableEntity> tables) {
        String managerPath = SystemUtil.getInstance().getConstant("fs_path") + "/" + prefix + "java/"
                + SystemUtil.getInstance().getConstant("package_name").replace(".", "/") + "/manager/impl/";

        String pluginPath = SystemUtil.getInstance().getConstant("fs_path") + "/" + prefix + "java/"
                + SystemUtil.getInstance().getConstant("package_name").replace(".", "/") + "/manager/plugins/";
        FileOperate.getInstance().newCreateFolder(managerPath);
        FileOperate.getInstance().newCreateFolder(pluginPath);
        if (!Validator.isEmpty(tables)) {
            int count = tables.size();
            for (int i = 0; i < count; i++) {
                StringBuffer result = new StringBuffer("");
                TableEntity table = tables.get(i);
                String tableName = table.getTableAttName();
                String beanIdName = ToolsUtil.toCamelNamed(table.getTableName().replaceAll(tabPrefix, ""));
                result.append(getManagerImplImports(tableName));
                result.append("public class " + tableName + "ManagerImpl implements " + tableName + "Manager { \n\n");
                result.append(getManagerImplClassMethods(tableName, beanIdName, table.getpKColumn(), table));
                result.append("} \n");
                FileOperate.getInstance()
                        .newCreateFile(managerPath + tableName + "ManagerImpl.java", result.toString());
            }
        }
    }

    /**
     * 返回类的头引入内容
     *
     * @return
     */
    private String getManagerImplImports(String tableName) {
        StringBuffer result = new StringBuffer("package " + SystemUtil.getInstance().getConstant("package_name")
                + ".manager.impl;\n\n");
        result.append("import java.util.List;\n");
        result.append("import java.util.Map;\n");

        result.append("import cn.datamining.database.model.MethodParam;\n");
        result.append("import cn.datamining.database.model.Page;\n");
        result.append("import cn.datamining.database.db.IDao;\n");
        result.append("import cn.datamining.api.obj.ParamsVo;\n");
        result.append("import cn.datamining.tools.Validator;\n");
        result.append("import cn.datamining.api.manager.PluginConnector;\n");

        result
                .append("import " + SystemUtil.getInstance().getConstant("package_name") + ".entity." + tableName
                        + ";\n");
        result.append("import " + SystemUtil.getInstance().getConstant("package_name") + ".manager." + tableName
                + "Manager;\n");
        result.append("import cn.datamining.api.exception.BusinessException;\n");
        result.append("\n \n");
        return result.toString();
    }

    /**
     * 生成类中的方法
     *
     * @return
     */
    private String getManagerImplClassMethods(String tableName, String beanIdName, String pkCol, TableEntity table) {
        StringBuffer result = new StringBuffer();
        String dao = beanIdName + "Dao";
        String jarPath = SystemUtil.getInstance().getConstant("package_name");
        result.append("    protected IDao " + dao + ";\n");
        result.append("    protected Map<String, PluginConnector" + "> plugins;\n");

        result.append("    protected final String entityName = \"" + jarPath + ".entity." + tableName + "\";\n\n");
        String pkCols = ToolsUtil.toUpperFirst(pkCol);
        ///添加spring注入方法
        result.append("    public void set" + tableName + "Dao(IDao " + dao + ") { \n");
        result.append("      this." + dao + "=" + dao + ";\n   }\n");

        result.append("	   public void setPlugins(Map<String, PluginConnector> plugins) {\n");
        result.append("		 this.plugins = plugins;\n");
        result.append("	   }\n");

        ///新增方法的实现
        result.append(saveInfo(dao, tableName, beanIdName, pkCols));
        ///修改方法的实现
        result.append(editInfo(dao, tableName, beanIdName, pkCols));
        //删除方法的实现
        result.append(deleteInfo(dao, tableName, beanIdName, pkCol));
        //按ID获取信息的实现
        result.append(infoById(dao, tableName, beanIdName, pkCols));

        result.append("    public Object execute(ParamsVo<" + tableName + "> vo){ \n");
        result.append("		  String key = vo.getMethodKey();\n");
        result.append("		  try {\n");
        result.append("			if (!Validator.isEmpty(plugins)) {\n");
        result.append("				if (!Validator.isEmpty(plugins.get(key))) {\n");
        result.append("					return plugins.get(key).execute(vo);\n");
        result.append("				}\n");
        result.append("	  		  }\n");
        result.append("		  } catch (Exception e) {\n");
        result.append("			e.printStackTrace();\n");
        result.append("		  }\n");
        result.append(" 	return null; \n   }\n");

        result.append("    public Page getPageInfo(ParamsVo<" + tableName + "> vo){ \n");
        result.append("      MethodParam param = setMethodParams(vo, 2);\n");
        result
                .append("      int pageSize = Integer.valueOf(Validator.getDefaultStr(String.valueOf(vo.getParams(\"pageSize\")), \"10\"));\n");
        result
                .append("      int pageIndex = Integer.valueOf(Validator.getDefaultStr(String.valueOf(vo.getParams(\"pageIndex\")), \"1\"));\n");
        result.append("      param.setPageIndex(pageIndex);\n");
        result.append("      param.setPageSize(pageSize);\n");
        result.append("      Page page = " + dao + ".pageSelect(param);\n      return page; \n   }\n");

        result.append("    public List<" + tableName + "> getList(ParamsVo<" + tableName + "> vo){ \n");
        result.append("      MethodParam param = setMethodParams(vo, 2);\n");
        result.append("      return " + dao + ".select(param);\n   }\n");

        result.append("    public long countInfo(ParamsVo<" + tableName + "> vo){ \n");
        result.append("      MethodParam param = setMethodParams(vo, 1);\n");
        result.append("      return " + dao + ".count(param); \n   }\n");

        result.append(setMethodParams(tableName, dao, table, beanIdName, pkCol));

        return result.toString();
    }

    private String setMethodParams(String tableName, String dao, TableEntity table, String beanIdName, String pkCol) {
        StringBuffer result = new StringBuffer();
        StringBuffer params = new StringBuffer();
        result.append("    private MethodParam setMethodParams(ParamsVo<" + tableName + "> vo, int type) { \n");
        result.append("      String methodKey = Validator.getDefaultStr(vo.getMethodKey(), \"ByProperty\");\n");
        result.append("      StringBuffer sb = new StringBuffer(" + dao + ".getSql(type));\n");
        result.append("      MethodParam param = new MethodParam(methodKey, \"\", \"\", entityName);\n");
        if (!Validator.isEmpty(table.getColumns())) {
            int count = table.getColumns().size();
            params.append("      " + tableName + " " + beanIdName + " = vo.getObj();\n");
            for (int i = 0; i < count; i++) {
                ColumnEntity column = table.getColumns().get(i);
                if (column.getAttrName().equals(pkCol)) {
                    continue;
                }
                String getMethod = beanIdName + ".get" + ToolsUtil.toUpperFirst(column.getAttrName()) + "()";
                if (DataType.isStringType(column.getColumnTypeName())) {
                    params.append("      if(Validator.isNotNull(" + getMethod + ")) { \n");
                    params.append("           sb.append(\" and " + column.getAttrName() + " = :" + column.getAttrName()
                            + "\");\n");
                    params
                            .append("           param.setParams(\"" + column.getAttrName() + "\", " + getMethod
                                    + "); \n");
                    params.append("      }\n");
                }
            }
        }
        result.append(params.toString());
        result.append("      \n      param.setSqlStr(sb.toString());\n");
        result.append("      return param; \n   }\n");
        return result.toString();
    }

    /**
     * 新增方法的实现
     */
    private String saveInfo(String daoStr, String tableName, String beanIdName, String pkCol) {
        StringBuffer result = new StringBuffer();
        result.append("    public String saveInfo(ParamsVo<" + tableName + "> vo) throws BusinessException { \n");
        result.append("      " + tableName + " " + beanIdName + " = vo.getObj();\n");
        //		result
        //				.append("      String infoId = Validator.generate();\n      //定义对象缓存KEY,如果不需要缓存对象请不要对变量赋值，如果要缓存对象建议使用infoId\n");
        result.append("      String infoId = Validator.generate();\n");

        if ("T".equals(useCache)) {
            result.append("      String cacheKey=\"" + beanIdName + "_\"+infoId;\n");
        } else {
            result.append("      String cacheKey=\"\";\n");
        }

        result.append("      " + beanIdName + ".set" + pkCol + "(infoId);\n");
        result
                .append("      MethodParam param = new MethodParam(\"" + tableName
                        + "\", cacheKey, \"\", entityName);\n");
        result.append("      param.setVaule(" + beanIdName + ");\n");
        result.append("      if (" + daoStr + ".insert(param)) { \n         return infoId;\n      }\n");
        result.append("      return \"\"; \n   }\n");
        return result.toString();
    }

    /**
     * 修改方法的实现
     */
    private String editInfo(String daoStr, String tableName, String beanIdName, String pkCol) {
        StringBuffer result = new StringBuffer();
        result.append("    public boolean editInfo(ParamsVo<" + tableName + "> vo) throws BusinessException { \n");
        result.append("      " + tableName + " " + beanIdName + " = vo.getObj();\n");
        result.append("      if (Validator.isNotNull(" + beanIdName + ".get" + pkCol + "())) {\n");

        //		result.append("         String cacheKey=\"\";\n");
        //		result.append("      //String cacheKey=\"" + beanIdName + "_\"+" + beanIdName + ".get" + pkCol + "();\n");

        if ("T".equals(useCache)) {
            result.append("      String cacheKey=\"" + beanIdName + "_\"+" + beanIdName + ".get" + pkCol + "();\n");
        } else {
            result.append("         String cacheKey=\"\";\n");
        }

        result.append("         MethodParam param = new MethodParam(\"" + tableName
                + "\", cacheKey, \"\", entityName);\n");
        result.append("         param.setVaule(" + beanIdName + ");\n         return " + daoStr
                + ".edit(param);\n      }\n");
        result.append("      return false; \n   }\n");
        return result.toString();
    }

    /**
     * 按ID获取信息的实现
     */
    private String deleteInfo(String daoStr, String tableName, String beanIdName, String pkCol) {
        StringBuffer result = new StringBuffer();
        result.append("    public boolean deleteInfo(ParamsVo<" + tableName + "> vo) throws BusinessException { \n");
        result.append("      String infoId = vo.getInfoId();\n");
        result.append("      if (Validator.isNull(infoId)){\n          return false; \n      }\n");
        //		result.append("      String cacheKey=\"\";\n");
        //		result.append("      //String cacheKey=\"" + beanIdName + "_\"+infoId;\n");

        if ("T".equals(useCache)) {
            result.append("      String cacheKey=\"" + beanIdName + "_\"+infoId;\n");
        } else {
            result.append("      String cacheKey=\"\";\n");
        }

        result
                .append("      String mark = Validator.getDefaultStr(String.valueOf(vo.getParams(\"isDelete\")), \"true\");\n");
        result.append("      MethodParam param = new MethodParam(\"ById\", cacheKey, \"\", entityName);\n");
        result.append("      param.setInfoId(infoId);\n");
        result.append("      " + tableName + " info = (" + tableName + ") " + daoStr + ".selectById(param);\n");
        result.append("      if (Validator.isEmpty(info)) {\n          return false; \n      }\n");
        result.append("      param.setVaule(info);//此处需要先将状态值赋值为删除状态\n");
        result.append("      if(\"false\".equals(mark)){//逻辑删除\n         param.setKey(\"" + tableName + "\");\n");
        result.append("         return " + daoStr + ".edit(param);\n      } else{\n");
        result.append("         param.setParams(\"" + pkCol + "\",infoId);\n");
        result.append("         param.setDelete(true);\n");
        result.append("         return " + daoStr + ".delete(param);\n      }\n   }\n");
        return result.toString();
    }

    /**
     * 按ID获取信息的实现
     */
    private String infoById(String daoStr, String tableName, String beanIdName, String pkCol) {
        StringBuffer result = new StringBuffer();
        result.append("    public Object getInfoById(ParamsVo<" + tableName + "> vo){ \n");
        result.append("      String infoId = vo.getInfoId();\n");
        //		result.append("      String cacheKey=\"\";\n");
        //		result.append("      //String cacheKey=\"" + beanIdName + "_\"+infoId;\n");

        if ("T".equals(useCache)) {
            result.append("      String cacheKey=\"" + beanIdName + "_\"+infoId;\n");
        } else {
            result.append("      String cacheKey=\"\";\n");
        }

        result.append("      if (Validator.isNull(infoId)) {\n         return null;\n      }\n");
        result.append("      MethodParam param = new MethodParam(\"ById\", cacheKey, \"\", entityName);\n");
        result.append("      param.setInfoId(infoId);\n");
        result.append("      return " + daoStr + ".selectById(param); \n   }\n");
        return result.toString();
    }

}
