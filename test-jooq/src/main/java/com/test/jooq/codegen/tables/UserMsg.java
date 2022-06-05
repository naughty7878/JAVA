/*
 * This file is generated by jOOQ.
 */
package com.test.jooq.codegen.tables;


import com.test.jooq.codegen.AhjhTest;
import com.test.jooq.codegen.Indexes;
import com.test.jooq.codegen.Keys;
import com.test.jooq.codegen.tables.records.UserMsgRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class UserMsg extends TableImpl<UserMsgRecord> {

    private static final long serialVersionUID = -278807101;

    /**
     * The reference instance of <code>test.user_msg</code>
     */
    public static final UserMsg USER_MSG = new UserMsg();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserMsgRecord> getRecordType() {
        return UserMsgRecord.class;
    }

    /**
     * The column <code>test.user_msg.id</code>. ID
     */
    public final TableField<UserMsgRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "ID");

    /**
     * The column <code>test.user_msg.uid</code>. 用户id
     */
    public final TableField<UserMsgRecord, Integer> UID = createField(DSL.name("uid"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "用户id");

    /**
     * The column <code>test.user_msg.msg</code>. 信息
     */
    public final TableField<UserMsgRecord, String> MSG = createField(DSL.name("msg"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "信息");

    /**
     * Create a <code>test.user_msg</code> table reference
     */
    public UserMsg() {
        this(DSL.name("user_msg"), null);
    }

    /**
     * Create an aliased <code>test.user_msg</code> table reference
     */
    public UserMsg(String alias) {
        this(DSL.name(alias), USER_MSG);
    }

    /**
     * Create an aliased <code>test.user_msg</code> table reference
     */
    public UserMsg(Name alias) {
        this(alias, USER_MSG);
    }

    private UserMsg(Name alias, Table<UserMsgRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserMsg(Name alias, Table<UserMsgRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> UserMsg(Table<O> child, ForeignKey<O, UserMsgRecord> key) {
        super(child, key, USER_MSG);
    }

    @Override
    public Schema getSchema() {
        return AhjhTest.test;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.USER_MSG_PRIMARY);
    }

    @Override
    public UniqueKey<UserMsgRecord> getPrimaryKey() {
        return Keys.KEY_USER_MSG_PRIMARY;
    }

    @Override
    public List<UniqueKey<UserMsgRecord>> getKeys() {
        return Arrays.<UniqueKey<UserMsgRecord>>asList(Keys.KEY_USER_MSG_PRIMARY);
    }

    @Override
    public UserMsg as(String alias) {
        return new UserMsg(DSL.name(alias), this);
    }

    @Override
    public UserMsg as(Name alias) {
        return new UserMsg(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserMsg rename(String name) {
        return new UserMsg(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserMsg rename(Name name) {
        return new UserMsg(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}