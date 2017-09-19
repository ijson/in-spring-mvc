package com.ijson.platform.generator.test;

import com.ijson.platform.generator.dao.LoadDaoFactory;
import com.ijson.platform.generator.manager.CodeGeneratorManager;
import com.ijson.platform.generator.manager.LoadManagerFactory;
import com.ijson.platform.generator.model.ParamsVo;
import com.ijson.platform.generator.model.TableEntity;
import com.ijson.platform.generator.util.SystemUtil;

import java.util.List;

public class CoderTest {
    public static void main(String[] args) {
        try {
            //获取要生成的模板
            CodeGeneratorManager codeGeneratorManager = LoadManagerFactory.getInstance().getCodeGeneratorManager();
            String tableNames[] = SystemUtil.getInstance().getConstant("builder_tables").split(",");
            //对表进行操作
            List<TableEntity> result = LoadDaoFactory.getInstance().getDao("Mysql").getTables(tableNames);
            ParamsVo<TableEntity> vo = new ParamsVo<TableEntity>();
            vo.setObjs(result);
            codeGeneratorManager.execute(vo);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
