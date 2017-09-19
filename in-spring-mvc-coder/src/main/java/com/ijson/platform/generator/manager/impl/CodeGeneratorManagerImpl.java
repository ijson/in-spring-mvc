package com.ijson.platform.generator.manager.impl;

import com.ijson.platform.api.model.ParamsVo;
import com.ijson.platform.common.util.Validator;
import com.ijson.platform.generator.manager.CodeGeneratorManager;
import com.ijson.platform.generator.model.TableEntity;
import com.ijson.platform.generator.template.TemplateHanlder;

import java.util.List;
import java.util.Map;


public class CodeGeneratorManagerImpl implements CodeGeneratorManager {

    private List<TemplateHanlder> hanlders;

    public void execute(ParamsVo<TableEntity> vo,Map<String,String> config) {
        String prefix = "src/main/";
        vo.setParams("prefix", prefix);
        if (!Validator.isEmpty(hanlders)) {
            int count = hanlders.size();
            for (int i = 0; i < count; i++) {
                hanlders.get(i).execute(vo,config);
            }
        } else {
            System.out.println("生成器模板接口没有被注入成功!");
        }
    }

    public void setHanlders(List<TemplateHanlder> hanlders) {
        this.hanlders = hanlders;
    }

}
