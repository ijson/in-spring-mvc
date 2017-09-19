package com.ijson.platform.generator.template;

import com.ijson.platform.common.util.SystemUtil;
import com.ijson.platform.api.model.ParamsVo;
import com.ijson.platform.generator.model.TableEntity;
import com.ijson.platform.generator.util.FileOperate;
import com.ijson.platform.generator.util.ToolsUtil;
import com.ijson.platform.common.util.Validator;

import java.util.List;


public class SpringXmlBuilder implements TemplateHanlder {

    public void execute(ParamsVo<TableEntity> vo) {
        List<TableEntity> tables = vo.getObjs();
        String prefix = Validator.getDefaultStr(String.valueOf(vo.getParams("prefix")), "src/main/");
        createSpringXml(prefix, tables);
    }

    public void createSpringXml(String prefix, List<TableEntity> tables) {
        String jarPath = SystemUtil.getInstance().getConstant("package_name");
        String xmlPath = SystemUtil.getInstance().getConstant("fs_path") + "/" + prefix + "resources/spring/";
        FileOperate.getInstance().newCreateFolder(xmlPath);
        if (!Validator.isEmpty(tables)) {
            int count = tables.size();
            StringBuffer result = new StringBuffer("");
            StringBuffer resultDao = new StringBuffer("");
            StringBuffer resultManager = new StringBuffer("");
            StringBuffer resultService = new StringBuffer("");
            result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            result
                    .append("<beans xmlns=\"http://www.springframework.org/schema/beans\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
            result
                    .append("    xmlns:jee=\"http://www.springframework.org/schema/jee\"  xmlns:context=\"http://www.springframework.org/schema/context\"\n");
            result
                    .append("    xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee-3.0.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd\"\n");
            result.append("    default-autowire=\"byName\"> \n\n");
            for (int i = 0; i < count; i++) {
                TableEntity table = tables.get(i);
                resultDao.append(getDaoConfig(jarPath, table.getTableName()));
                resultManager.append(getManagerConfig(jarPath, table.getTableName()));
                resultService.append(getServiceConfig(jarPath, table.getTableName()));
            }
            result.append("   <!-- dao config start -->\n" + resultDao.toString() + "   <!-- dao config stop -->\n\n");
            result.append("   <!-- manager config start -->\n" + resultManager.toString()
                    + "   <!-- manager config stop -->\n\n");
            //			result.append("   <!-- service config start -->\n" + resultService.toString()
            //					+ "   <!-- service config stop -->\n\n");
            result.append("</beans>\n");
            FileOperate.getInstance().newCreateFile(
                    xmlPath + "applicationContext-" + jarPath.substring(jarPath.lastIndexOf(".") + 1) + ".xml",
                    result.toString());
        }
    }

    private String getDaoConfig(String jarPath, String tableName) {
        StringBuffer result = new StringBuffer("");
        String tabPrefix = SystemUtil.getInstance().getConstant("table_prefix").toLowerCase();
        String id = ToolsUtil.toCamelNamed(tableName.replaceAll(tabPrefix, "")) + "Dao";
        String name = ".dao." + ToolsUtil.toUpperFirst(tableName.replaceAll(tabPrefix, "")) + "DaoImpl";
        result.append("   <bean id=\"" + id + "\" class=\"" + jarPath + name + "\" parent=\"abstractDao\"/>\n");
        return result.toString();
    }

    private String getManagerConfig(String jarPath, String tableName) {
        StringBuffer result = new StringBuffer("");
        String tabPrefix = SystemUtil.getInstance().getConstant("table_prefix").toLowerCase();
        String id = ToolsUtil.toCamelNamed(tableName.replaceAll(tabPrefix, "")) + "Manager";
        String name = ToolsUtil.toUpperFirst(tableName.replaceAll(tabPrefix, "")) + "ManagerImpl";
        result.append("   <bean id=\"" + id + "\" class=\"" + jarPath + ".manager.impl." + name + "\"></bean>\n");
        return result.toString();
    }

    private String getServiceConfig(String jarPath, String tableName) {
        StringBuffer result = new StringBuffer("");
        String tabPrefix = SystemUtil.getInstance().getConstant("table_prefix").toLowerCase();
        String id = ToolsUtil.toCamelNamed(tableName.replaceAll(tabPrefix, "")) + "Service";
        String name = ToolsUtil.toUpperFirst(tableName.replaceAll(tabPrefix, "")) + "ServiceImpl";
        result.append("   <bean id=\"" + id + "\" class=\"" + jarPath + ".manager." + name + "\"></bean>\n");
        return result.toString();
    }
}
