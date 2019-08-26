<#include "/java_copyright.include">
<#assign className = table.className>
package ${basepackage}.dao${table.ownerSynonymName}.impl;

import ${basepackage}.domain${table.ownerSynonymName}.${className};
import ${basepackage}.dao${table.ownerSynonymName}.I${className}Dao;
import ${baseDaoPackage}.EntityDao4Mybatis;

import org.springframework.stereotype.Repository;

<@classComment value="Dao实现"/>
@Repository
public class ${className}DaoImpl extends EntityDao4Mybatis<${className}, ${table.idColumn.javaType}> implements I${className}Dao {

}
