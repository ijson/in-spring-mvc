package com.ijson.platform.generator.dao;


import com.ijson.platform.generator.model.TableEntity;

import java.util.List;

public interface IDao {

	List<TableEntity> getTables(String[] tableNames);
}
