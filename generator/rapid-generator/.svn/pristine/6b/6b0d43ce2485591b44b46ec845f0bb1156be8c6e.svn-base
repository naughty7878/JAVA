<#include "/java_copyright.include">
<#assign className = table.className>
package ${basepackage}${modulepackage}.dao${table.ownerSynonymName};

import ${basepackage}${modulepackage}.domain${table.ownerSynonymName}.${className};
import ${basepackage}.dao.base.IBaseMongoDao;

<@classComment value="Dao"/>
<#if table.idColumn.isStringColumn>
public interface I${className}Dao extends IBaseMongoDao<${className}> {

}
</#if>