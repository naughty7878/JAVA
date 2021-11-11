package com.test.jooq.pojo;



public class UserMessagePojo {

    private Integer uid;
    private String name;
    private String msg;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "UserMessagePojo{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}