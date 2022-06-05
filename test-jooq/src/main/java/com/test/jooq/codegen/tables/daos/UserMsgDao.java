/*
 * This file is generated by jOOQ.
 */
package com.test.jooq.codegen.tables.daos;


import com.test.jooq.codegen.tables.UserMsg;
import com.test.jooq.codegen.tables.records.UserMsgRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


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
@Repository
public class UserMsgDao extends DAOImpl<UserMsgRecord, com.test.jooq.codegen.tables.pojos.UserMsg, Integer> {

    /**
     * Create a new UserMsgDao without any configuration
     */
    public UserMsgDao() {
        super(UserMsg.USER_MSG, com.test.jooq.codegen.tables.pojos.UserMsg.class);
    }

    /**
     * Create a new UserMsgDao with an attached configuration
     */
    @Autowired
    public UserMsgDao(Configuration configuration) {
        super(UserMsg.USER_MSG, com.test.jooq.codegen.tables.pojos.UserMsg.class, configuration);
    }

    @Override
    public Integer getId(com.test.jooq.codegen.tables.pojos.UserMsg object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.test.jooq.codegen.tables.pojos.UserMsg> fetchRangeOfId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(UserMsg.USER_MSG.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.test.jooq.codegen.tables.pojos.UserMsg> fetchById(Integer... values) {
        return fetch(UserMsg.USER_MSG.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.test.jooq.codegen.tables.pojos.UserMsg fetchOneById(Integer value) {
        return fetchOne(UserMsg.USER_MSG.ID, value);
    }

    /**
     * Fetch records that have <code>uid BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.test.jooq.codegen.tables.pojos.UserMsg> fetchRangeOfUid(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(UserMsg.USER_MSG.UID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>uid IN (values)</code>
     */
    public List<com.test.jooq.codegen.tables.pojos.UserMsg> fetchByUid(Integer... values) {
        return fetch(UserMsg.USER_MSG.UID, values);
    }

    /**
     * Fetch records that have <code>msg BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.test.jooq.codegen.tables.pojos.UserMsg> fetchRangeOfMsg(String lowerInclusive, String upperInclusive) {
        return fetchRange(UserMsg.USER_MSG.MSG, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>msg IN (values)</code>
     */
    public List<com.test.jooq.codegen.tables.pojos.UserMsg> fetchByMsg(String... values) {
        return fetch(UserMsg.USER_MSG.MSG, values);
    }
}