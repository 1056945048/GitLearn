package com.yunqu.yq.engine.entity;

import lombok.Builder;

import java.io.Serializable;

/**
 * (ZyTask)实体类
 *
 * @author Bianhl
 * @since 2020-05-22 00:09:22
 */
@Builder
public class ZyTask implements Serializable {
    private static final long serialVersionUID = 607335841933709591L;

    private String id;

    private String taskName;

    private String taskState;

    private String createTime;

    private String dateId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDateId() {
        return dateId;
    }

    public void setDateId(String dateId) {
        this.dateId = dateId;
    }

}
