package com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

public class GeneratorTest {

    private GeneratorFacade generator;

    private static final String TEMPLATE = "src/main/resources/mytemplate";

    @Before
    public void setUp() throws Exception {
        generator = new GeneratorFacade();
        generator.deleteOutRootDir();
    }

    @After
    public void tearDown() throws Exception {
        generator = null;
        String outRoot = GeneratorProperties.getRequiredProperty("outRoot");
        outRoot += "/java_src/";
        String basePackage = GeneratorProperties.getRequiredProperty("basepackage").replace(".", "/");
        Runtime.getRuntime().exec("cmd.exe /c start " + outRoot + basePackage);
    }

    /**
     * 生成单表, 参数为表名
     * 
     * @throws Exception 异常
     */
    @Test
    public void testSingleTableGenerator() throws Exception {
        String tables = "employee";
        String moduleName = "foundation";
        generator.generateByTable(tables, moduleName, TEMPLATE);
    }

    /**
     * 生成多表, 参数为多个表名
     * 
     * @throws Exception 异常
     */
    @Test
    public void testMultipleTableGenerator() throws Exception {
        String[] tables = new String[] {"province","area","city"};
        generator.generateByTables(tables, null, TEMPLATE);
    }

    /**
     * 生成全表
     * 
     * @throws Exception 异常
     */
    @Test
    public void testFullTableGenerator() throws Exception {
        generator.generateByAllTable(TEMPLATE);
    }

}
