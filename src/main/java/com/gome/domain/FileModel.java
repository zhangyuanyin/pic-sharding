package com.gome.domain;

import java.util.List;

/**
 * <dt>解析文件服务系统报文需要对象信息</dt>
 * @author yyzhang
 * @since 2018年2月5日11:04:00
 */
public class FileModel {
    private String status;

    private String state;

    private String showMessage;

    private List<BodyModel> body;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    public List<BodyModel> getBody() {
        return body;
    }

    public void setBody(List<BodyModel> body) {
        this.body = body;
    }
}
