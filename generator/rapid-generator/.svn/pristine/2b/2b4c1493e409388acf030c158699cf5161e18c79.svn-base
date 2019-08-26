<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign hasDateType = false> 
package ${domainpackage}.vo;

<#list table.columns as column>
<#if column.isDateTimeColumn>
<#assign hasDateType = true>
</#if>
</#list>
<#if hasDateType>
import java.util.Date;
</#if>
import ${baseDomainPackage}.BaseVo;
import com.fasterxml.jackson.annotation.JsonProperty;

<@classComment value="Vo 对象"/>
public class ${className}Vo extends BaseVo {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    <#list table.notPkColumns as column>
    /**
     * ${column.columnAlias!} 
     */
    @JsonProperty("${column.sqlName}")
    private ${column.javaType} ${column.columnNameLower};

    </#list>

<@generateJavaColumns/>
<@generateJavaManyToOne/>
}

<#macro generateJavaColumns>
    <#list table.notPkColumns as column>
    public void set${column.columnName}(${column.javaType} ${column.columnName?uncap_first}) {
        this.${column.columnNameLower} = ${column.columnName?uncap_first};
    }

    public ${column.javaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }

    </#list>
</#macro>

<#macro generateJavaOneToMany>
    <#list table.exportedKeys.associatedTables?values as foreignKey>
    <#assign fkSqlTable = foreignKey.sqlTable>
    <#assign fkTable    = fkSqlTable.className>
    <#assign fkPojoClass = fkSqlTable.className + "Vo">
    <#assign fkPojoClassVar = fkPojoClass?uncap_first>

    private List<${fkPojoClass}> ${fkPojoClassVar}s = new ArrayList<${fkPojoClass}>(0);
    public void set${fkPojoClass}s(List<${fkPojoClass}> ${fkPojoClassVar}){
        this.${fkPojoClassVar}s = ${fkPojoClassVar};
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
    <#assign fkPojoClass = fkSqlTable.className + "Vo">
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