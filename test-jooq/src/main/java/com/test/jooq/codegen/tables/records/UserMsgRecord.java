/*
 * This file is generated by jOOQ.
 */
package com.test.jooq.codegen.tables.records;


import com.test.jooq.codegen.tables.UserMsg;
import com.test.jooq.codegen.tables.interfaces.IUserMsg;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


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
public class UserMsgRecord extends UpdatableRecordImpl<UserMsgRecord> implements Record3<Integer, Integer, String>, IUserMsg {

    private static final long serialVersionUID = -752022443;

    /**
     * Setter for <code>test.user_msg.id</code>. ID
     */
    @Override
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>test.user_msg.id</code>. ID
     */
    @Override
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>test.user_msg.uid</code>. 用户id
     */
    @Override
    public void setUid(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>test.user_msg.uid</code>. 用户id
     */
    @Override
    public Integer getUid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>test.user_msg.msg</code>. 信息
     */
    @Override
    public void setMsg(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>test.user_msg.msg</code>. 信息
     */
    @Override
    public String getMsg() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, Integer, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return UserMsg.USER_MSG.ID;
    }

    @Override
    public Field<Integer> field2() {
        return UserMsg.USER_MSG.UID;
    }

    @Override
    public Field<String> field3() {
        return UserMsg.USER_MSG.MSG;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getUid();
    }

    @Override
    public String component3() {
        return getMsg();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getUid();
    }

    @Override
    public String value3() {
        return getMsg();
    }

    @Override
    public UserMsgRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public UserMsgRecord value2(Integer value) {
        setUid(value);
        return this;
    }

    @Override
    public UserMsgRecord value3(String value) {
        setMsg(value);
        return this;
    }

    @Override
    public UserMsgRecord values(Integer value1, Integer value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IUserMsg from) {
        setId(from.getId());
        setUid(from.getUid());
        setMsg(from.getMsg());
    }

    @Override
    public <E extends IUserMsg> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserMsgRecord
     */
    public UserMsgRecord() {
        super(UserMsg.USER_MSG);
    }

    /**
     * Create a detached, initialised UserMsgRecord
     */
    public UserMsgRecord(Integer id, Integer uid, String msg) {
        super(UserMsg.USER_MSG);

        set(0, id);
        set(1, uid);
        set(2, msg);
    }
}
