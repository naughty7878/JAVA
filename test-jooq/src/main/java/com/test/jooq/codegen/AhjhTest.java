/*
 * This file is generated by jOOQ.
 */
package com.test.jooq.codegen;


import com.test.jooq.codegen.tables.User;
import com.test.jooq.codegen.tables.UserMsg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class AhjhTest extends SchemaImpl {

    private static final long serialVersionUID = 1852824272;

    /**
     * The reference instance of <code>test</code>
     */
    public static final AhjhTest test = new AhjhTest();

    /**
     * 用户表
     */
    public final User USER = com.test.jooq.codegen.tables.User.USER;

    /**
     * The table <code>test.user_msg</code>.
     */
    public final UserMsg USER_MSG = com.test.jooq.codegen.tables.UserMsg.USER_MSG;

    /**
     * No further instances allowed
     */
    private AhjhTest() {
        super("test", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            User.USER,
            UserMsg.USER_MSG);
    }
}
