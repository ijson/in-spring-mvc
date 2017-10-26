package com.ijson.platform.i18n.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Resource implements Serializable {

    //唯一标识
    private String id;
    //键名
    private String ename;
    //值
    private String cname;
    //对应的语言
    private String language;

}
