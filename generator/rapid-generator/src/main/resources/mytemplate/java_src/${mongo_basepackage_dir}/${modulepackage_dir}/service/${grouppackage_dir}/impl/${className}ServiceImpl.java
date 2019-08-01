<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign remarks = table.remarks>
package ${basepackage}${modulepackage}.service${table.ownerSynonymName}.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basepackage}${modulepackage}.domain${table.ownerSynonymName}.${className};
import ${basepackage}${modulepackage}.service${table.ownerSynonymName}.I${className}Service;
import ${basepackage}${modulepackage}.dao${table.ownerSynonymName}.I${className}Dao;

<@classComment value="Service实现"/>
@Service
public class ${className}ServiceImpl implements I${className}Service {

    /**
     * ${remarks} Dao
     */
    @Autowired
    private I${className}Dao ${classNameLower}Dao;

    @Override
    public ${className} save(${className} ${classNameLower}) {
        return ${classNameLower}Dao.save(${classNameLower});
    }

    @Override
    public void delete(String id) {
        ${classNameLower}Dao.delete(id);
    }
    
    @Override
    public ${className} get(String id) {
        return ${classNameLower}Dao.get(id);
    }
    
    @Override
    public List<${className}> findAll() {
        return ${classNameLower}Dao.findAll();
    }

    @Override
    public long count() {
        return ${classNameLower}Dao.count();
    }

}
