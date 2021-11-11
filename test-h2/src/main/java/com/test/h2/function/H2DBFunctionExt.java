package com.test.h2.function;

import java.util.UUID;

/**
 * 对H2数据库函数的扩展
 * @author h__d
 * @date 2019-05-28 18:46:07
 *
 */
public class H2DBFunctionExt {

	/**
	 * 用法：SELECT myid();
	 * @return
	 */
    public static String myid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
	
}
