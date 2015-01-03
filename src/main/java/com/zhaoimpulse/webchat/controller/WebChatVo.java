package com.zhaoimpulse.webchat.controller;

/**
 * @author zhaohaifeng
 * @since 2014-12-06
 */
public class WebChatVo {

    private Integer fromUserId;

    private String message;

    private Integer toUserId;

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }
}
