package com.ijson.platform.generator.manager;


import com.ijson.platform.generator.model.ParamsVo;
import com.ijson.platform.generator.model.TableEntity;

/**
 * description: 代码
 *
 * @author cuiyongxu 创建时间：Nov 17, 2015
 */
public interface CodeGeneratorManager {

    /**
     * 代码统一生成器入口方法
     *
     * @param vo 方法参数
     */
    public void execute(ParamsVo<TableEntity> vo);
}
