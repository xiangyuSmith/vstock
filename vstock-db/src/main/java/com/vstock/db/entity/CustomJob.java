package com.vstock.db.entity;

/**
 * Created by administor on 2016/5/30.
 */
public class CustomJob {
    private String jobId; // 任务的Id，一般为所定义Bean的ID
    private String jobName; // 任务的描述
    private String jobGroup; // 任务所属组的名称
    private int jobStatus; // 任务的状态，0：启用；1：禁用；2：已删除
    private String cronExpression; // 定时任务运行时间表达式
    private String memos; // 任务描述
    private String createTime; //创建时间
    private String createUser; //创建人
    private String upateTime; //更新时间
    private String updateUser; //更新人
//    private Class<?> stateFulljobExecuteClass;//同步的执行类，需要从StatefulMethodInvokingJob继承
//    private Class<?> jobExecuteClass;//异步的执行类，需要从MethodInvokingJob继承
    /**
     * 得到该job的Trigger名字
     * @return
     */
    public String getTriggerName() {
        return this.getJobId() + "Trigger";
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public int getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(int jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getMemos() {
        return memos;
    }

    public void setMemos(String memos) {
        this.memos = memos;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpateTime() {
        return upateTime;
    }

    public void setUpateTime(String upateTime) {
        this.upateTime = upateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

//    public Class<?> getStateFulljobExecuteClass() {
//        return stateFulljobExecuteClass;
//    }
//
//    public void setStateFulljobExecuteClass(Class<?> stateFulljobExecuteClass) {
//        this.stateFulljobExecuteClass = stateFulljobExecuteClass;
//    }
//
//    public Class<?> getJobExecuteClass() {
//        return jobExecuteClass;
//    }
//
//    public void setJobExecuteClass(Class<?> jobExecuteClass) {
//        this.jobExecuteClass = jobExecuteClass;
//    }
}
