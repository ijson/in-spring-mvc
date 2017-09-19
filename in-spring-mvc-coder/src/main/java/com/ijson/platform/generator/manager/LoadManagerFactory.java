package com.ijson.platform.generator.manager;

import com.ijson.platform.generator.manager.impl.CodeGeneratorManagerImpl;
import com.ijson.platform.generator.template.*;

import java.util.ArrayList;
import java.util.List;


public class LoadManagerFactory {

    private static LoadManagerFactory instance;

    private CodeGeneratorManager codeGeneratorManager;

    private LoadManagerFactory() {

    }

    public static LoadManagerFactory getInstance() {
        if (null == instance) {
            instance = new LoadManagerFactory();
        }
        return instance;
    }

    /**
     * description:  根据模板生成
     *
     * @return
     * @author cuiyongxu
     * @update Nov 17, 2015
     */
    public CodeGeneratorManager getCodeGeneratorManager() {
        if (null == codeGeneratorManager) {
            CodeGeneratorManagerImpl codegener = new CodeGeneratorManagerImpl();
            List<TemplateHanlder> hanlders = new ArrayList<TemplateHanlder>();
            hanlders.add(new PomXMlBuilder());//生成pom
            hanlders.add(new SpringXmlBuilder());//生成spring
            hanlders.add(new TemplateDaoImplBuilder());//生成dao
            hanlders.add(new TemplateEntityImplBuilder());//生成entity
            hanlders.add(new TemplateManagerImplBuilder());//生成manager
            hanlders.add(new HibernateXmlBuilder());//生成.hbm.xml
            hanlders.add(new BatFileBuilder());//生成bat文件
            codegener.setHanlders(hanlders);
            codeGeneratorManager = codegener;
        }
        return codeGeneratorManager;
    }

}
