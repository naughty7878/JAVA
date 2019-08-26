<#include "/java_copyright.include">
<#assign className = table.className>
package ${basepackage}.dao;

import ${basepackage}.domain.${className};
import ${baseDaoPackage}.EntityDao;

<@classComment value="Dao"/>
public interface I${className}Dao extends EntityDao<${className}, ${table.idColumn.javaType}> {

}
