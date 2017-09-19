
package com.ijson.platform.generator.template;

import com.ijson.platform.api.model.ParamsVo;
import com.ijson.platform.common.util.Validator;
import com.ijson.platform.generator.model.TableEntity;
import com.ijson.platform.generator.util.FileOperate;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;


/**
 * description:  生成bat文件
 *
 * @author cuiyongxu 创建时间：Nov 13, 2015
 */
public class BatFileBuilder implements TemplateHanlder {

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     */
    public void execute(ParamsVo<TableEntity> vo, Map<String, String> config) {
        String fsPath = config.get("fs_path");
        String jarPath = config.get("package_name");
        String mvnContent = getMvnContent(jarPath);
        Map<String, String> map = System.getenv();
        Set<Entry<String, String>> entries = map.entrySet();
        Map<String, String> mapAll = new HashMap<String, String>();
        for (Entry<String, String> entry : entries) {
            mapAll.put(entry.getKey(), entry.getValue());
        }
        String value = mapAll.get("M2_HOME");
        if (Validator.isNull(value)) {
            value = mapAll.get("m2_home");
        }
        //仍然为空
        Map<Object, Object> mapAllObj = new HashMap<Object, Object>();
        if (Validator.isNull(value)) {
            //全局变量
            Properties properties = System.getProperties();
            Set<Entry<Object, Object>> set = properties.entrySet();
            for (Entry<Object, Object> objectObjectEntry : set) {
                mapAllObj.put(objectObjectEntry.getKey(), objectObjectEntry.getValue());
            }
            value = (String) mapAllObj.get("M2_HOME");
            if (Validator.isNull(value)) {
                value = mapAll.get("m2_home");
            }
        }

        if (Validator.isNull(value)) {
            mvnContent = getNoMvn(jarPath);
        }

        FileOperate.getInstance().newCreateFile(fsPath + "/generation.bat", mvnContent, "GBK");
    }

    /**
     * description:  elipse执行文件
     *
     * @return
     * @author cuiyongxu
     * @update Nov 13, 2015
     */
    private String getMvnContent(String jarPath) {
        StringBuffer sb = new StringBuffer();
        sb.append("");
        sb.append("@echo off\r\n");
        sb.append("title " + jarPath.substring(jarPath.lastIndexOf(".") + 1) + " generation\r\n");
        sb.append("echo [INFO] 正在生成maven项目\r\n");
        sb.append("call mvn eclipse:clean eclipse:eclipse\r\n");
        sb.append("pause\r\n");
        sb.append(":end\r\n");
        return sb.toString();
    }

    /**
     * description:  elipse执行文件
     *
     * @return
     * @author cuiyongxu
     * @update Nov 13, 2015
     */
    private String getNoMvn(String jarPath) {
        StringBuffer sb = new StringBuffer();
        sb.append("");
        sb.append("@echo off\r\n");
        sb.append("title " + jarPath.substring(jarPath.lastIndexOf(".") + 1) + " generation\r\n");
        sb.append("echo [INFO] 确保系统中已经安装配置好Maven.并已配置环境变量\r\n");
        sb.append("echo [INFO] 环境变量中添加:M2_HOME=/home/maven-3.0.4\r\n");
        sb.append("PAUSE\r\n");
        sb.append(":end\r\n");
        return sb.toString();
    }

}
