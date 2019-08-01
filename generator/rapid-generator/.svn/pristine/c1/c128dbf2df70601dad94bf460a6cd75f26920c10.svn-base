<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign hasDateType = false> 
package ${basepackage}${modulepackage}.domain${table.ownerSynonymName};

<#list table.columns as column>
<#if column.isDateTimeColumn>
    <#assign hasDateType = true>
</#if>
</#list>
<#if hasDateType>
import java.util.Date;
</#if>

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import ${domainpackage}.base.BaseStrEntity;

<@classComment value="实体类"/>
@Document(collection = "${table.sqlName}")
public class ${className} extends BaseStrEntity implements java.io.Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    <#list table.notPkColumns as column>
    /**
     * ${column.columnAlias!} 
     */
    <#if column.indexed>
    @Indexed
    </#if>
    @Field(value="${column.sqlName}")
    private ${column.javaType} ${column.columnNameLower};

    </#list>

<@generateJavaColumns/>
}
<#macro generateJavaColumns>
    <#list table.columns as column>
    <#if column.columnNameLower!="id">
    public void set${column.columnName}(${column.javaType} ${column.columnName?uncap_first}) {
        this.${column.columnNameLower} = ${column.columnName?uncap_first};
    }
    
    public ${column.javaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }
    
    </#if>
    </#list>
</#macro>
<#macro generateJavaOneToMany>
    <#list table.exportedKeys.associatedTables?values as foreignKey>
    <#assign fkSqlTable = foreignKey.sqlTable>
    <#assign fkTable    = fkSqlTable.className>
    <#assign fkPojoClass = fkSqlTable.className>
    <#assign fkPojoClassVar = fkPojoClass?uncap_first>
    
    private List<${fkPojoClass}> ${fkPojoClassVar}s = new ArrayList<${fkPojoClass}>();
    public void set${fkPojoClass}s(List<${fkPojoClass}> ${fkPojoClassVar}s){
        this.${fkPojoClassVar}s = ${fkPojoClassVar}s;
    }
    
    public List<${fkPojoClass}> get${fkPojoClass}s() {
        return ${fkPojoClassVar}s;
    }
    </#list>
</#macro>

<#macro generateJavaManyToOne>
    <#list table.importedKeys.associatedTables?values as foreignKey>
    <#assign fkSqlTable = foreignKey.sqlTable>
    <#assign fkTable    = fkSqlTable.className>
    <#assign fkPojoClass = fkSqlTable.className>
    <#assign fkPojoClassVar = fkPojoClass?uncap_first>
    
    private ${fkPojoClass} ${fkPojoClassVar};
    
    public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
        this.${fkPojoClassVar} = ${fkPojoClassVar};
    }
    
    public ${fkPojoClass} get${fkPojoClass}() {
        return ${fkPojoClassVar};
    }
    </#list>
</#macro>