/*
 * This file is generated by jOOQ.
 */
package com.test.jooq.codegen.tables.interfaces;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface IUserMsg extends Serializable {

    /**
     * Setter for <code>test.user_msg.id</code>. ID
     */
    public void setId(Integer value);

    /**
     * Getter for <code>test.user_msg.id</code>. ID
     */
    public Integer getId();

    /**
     * Setter for <code>test.user_msg.uid</code>. 用户id
     */
    public void setUid(Integer value);

    /**
     * Getter for <code>test.user_msg.uid</code>. 用户id
     */
    public Integer getUid();

    /**
     * Setter for <code>test.user_msg.msg</code>. 信息
     */
    public void setMsg(String value);

    /**
     * Getter for <code>test.user_msg.msg</code>. 信息
     */
    public String getMsg();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IUserMsg
     */
    public void from(com.test.jooq.codegen.tables.interfaces.IUserMsg from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IUserMsg
     */
    public <E extends com.test.jooq.codegen.tables.interfaces.IUserMsg> E into(E into);
}