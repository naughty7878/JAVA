<#include "/java_copyright.include">
<#assign className = table.className>
package ${basepackage}${modulepackage}.dao${table.ownerSynonymName}.impl;

import ${basepackage}${modulepackage}.domain${table.ownerSynonymName}.${className};
import ${basepackage}${modulepackage}.dao${table.ownerSynonymName}.mongodb.I${className}Repository;
import ${basepackage}${modulepackage}.dao${table.ownerSynonymName}.I${className}Dao;
import ${basepackage}.dao.base.impl.BaseMongoDaoImpl;

import org.springframework.stereotype.Repository;

<@classComment value="Dao实现"/>
@Repository
<#if table.idColumn.isStringColumn>
public class ${className}DaoImpl extends BaseMongoDaoImpl<${className}, I${className}Repository> implements I${className}Dao {

}
</#if>