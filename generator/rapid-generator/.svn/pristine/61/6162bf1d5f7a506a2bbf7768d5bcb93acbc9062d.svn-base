<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign hasDateType = false> 
package ${domainpackage}.query;

<#list table.columns as column>
<#if column.isDateTimeColumn>
    <#assign hasDateType = true>
</#if>
</#list>
<#if hasDateType>
import java.util.Date;
</#if>
import ${baseDomainPackage}.BaseQuery;

<@classComment value="查询对象"/>
public class ${className}Query extends BaseQuery implements java.io.Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    <@generateFields/>
    <@generateProperties/>

}
<#macro generateFields>
    <#list table.notPkColumns as column>
    <#if column.isDateTimeColumn && !column.contains("begin,start,end")>
    /**
     * ${column.columnAlias!} 
     */
    private ${column.javaType} ${column.columnNameLower}Begin;

    /**
     * ${column.columnAlias!} 
     */
    private ${column.javaType} ${column.columnNameLower}End;

    <#else>
    /**
     * ${column.columnAlias!} 
     */
    private ${column.javaType} ${column.columnNameLower};

    </#if>
    </#list>

</#macro>

<#macro generateProperties>
    <#list table.columns as column>
    <#if column.isDateTimeColumn && !column.contains("begin,start,end")>
    public ${column.javaType} get${column.columnName}Begin() {
        return this.${column.columnNameLower}Begin;
    }
    
    public void set${column.columnName}Begin(${column.javaType} ${column.columnNameLower}Begin) {
        this.${column.columnNameLower}Begin = ${column.columnNameLower}Begin;
    }    
    
    public ${column.javaType} get${column.columnName}End() {
        return this.${column.columnNameLower}End;
    }
    
    public void set${column.columnName}End(${column.javaType} ${column.columnNameLower}End) {
        this.${column.columnNameLower}End = ${column.columnNameLower}End;
    }
    
    <#elseif column.columnNameLower!="id">
    public ${column.javaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }
    
    public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }

    </#if>
    </#list>
</#macro>