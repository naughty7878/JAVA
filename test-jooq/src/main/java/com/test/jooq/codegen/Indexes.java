/*
 * This file is generated by jOOQ.
 */
package com.test.jooq.codegen;


import com.test.jooq.codegen.tables.User;
import com.test.jooq.codegen.tables.UserMsg;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>test</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index USER_PRIMARY = Indexes0.USER_PRIMARY;
    public static final Index USER_MSG_PRIMARY = Indexes0.USER_MSG_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index USER_PRIMARY = Internal.createIndex("PRIMARY", User.USER, new OrderField[] { User.USER.ID }, true);
        public static Index USER_MSG_PRIMARY = Internal.createIndex("PRIMARY", UserMsg.USER_MSG, new OrderField[] { UserMsg.USER_MSG.ID }, true);
    }
}
