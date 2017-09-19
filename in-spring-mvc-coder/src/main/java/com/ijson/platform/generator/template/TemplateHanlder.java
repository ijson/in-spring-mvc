package com.ijson.platform.generator.template;


import com.ijson.platform.api.model.ParamsVo;
import com.ijson.platform.generator.model.TableEntity;

public interface TemplateHanlder {

    /**
     * 代码生成器模板接口
     *
     * @param vo 方法参数
     */
    public void execute(ParamsVo<TableEntity> vo);
}
