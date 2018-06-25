package com.gome.domain;

/**
 * <dt>解析文件服务系统报文体需要对象信息</dt>
 * @author yyzhang
 * @since 2018年2月5日11:02:43
 */
public class BodyModel {
    private String path;
    private String fieldName;
    private String group;
    private String key;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
