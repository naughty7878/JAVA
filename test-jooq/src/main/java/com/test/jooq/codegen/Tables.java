/*
 * This file is generated by jOOQ.
 */
package com.test.jooq.codegen;


import com.test.jooq.codegen.tables.User;
import com.test.jooq.codegen.tables.UserMsg;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in test
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * 用户表
     */
    public static final User USER = User.USER;

    /**
     * The table <code>test.user_msg</code>.
     */
    public static final UserMsg USER_MSG = UserMsg.USER_MSG;
}