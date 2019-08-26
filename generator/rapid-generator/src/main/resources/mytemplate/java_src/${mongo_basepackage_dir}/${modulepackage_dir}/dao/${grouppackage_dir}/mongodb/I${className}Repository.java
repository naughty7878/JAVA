<#include "/java_copyright.include">
<#assign className = table.className>
package ${basepackage}${modulepackage}.dao${table.moduleName}${table.ownerSynonymName}.mongodb;

import ${basepackage}${modulepackage}.domain${table.ownerSynonymName}.${className};

import org.springframework.data.mongodb.repository.MongoRepository;

<@classComment value="Dao"/>
<#if table.idColumn.isStringColumn>
public interface I${className}Repository extends MongoRepository<${className},String> {

}
</#if>