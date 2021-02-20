package com.yunqu.yq.engine.entity;

import lombok.Builder;

import java.io.Serializable;

/**
 * (ZyTaskObj)实体类
 *
 * @author Bianhl
 * @since 2020-05-22 01:01:28
 */
@Builder
public class ZyTaskObj implements Serializable {
    private static final long serialVersionUID = -55763303559004737L;

    private String id;

    private String taskId;

    private Integer markNum;

    private Integer isSearched;

    private String optTime;

    private String phoneNum;

    private String memo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getMarkNum() {
        return markNum;
    }

    public void setMarkNum(Integer markNum) {
        this.markNum = markNum;
    }

    public Integer getIsSearched() {
        return isSearched;
    }

    public void setIsSearched(Integer isSearched) {
        this.isSearched = isSearched;
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
