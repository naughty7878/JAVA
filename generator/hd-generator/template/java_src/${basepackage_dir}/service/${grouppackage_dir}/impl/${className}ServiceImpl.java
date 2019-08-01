<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign remarks = table.remarks>
package ${basepackage}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${basepackage}.domain.${className};
import ${basepackage}.service.I${className}Service;
import ${basepackage}.dao.I${className}Dao;
import ${basepackage}.domain.query.${className}Query;
import ${basePagePackage}.Pagination;

<@classComment value="Service实现"/>
@Service
@Transactional
public class ${className}ServiceImpl implements I${className}Service {

    /**
     * ${remarks} Dao
     */
    @Autowired
    private I${className}Dao ${classNameLower}Dao;

    @Override
    @Transactional(readOnly = true)
    public ${className} get${className}ById(Integer id) {
        return ${classNameLower}Dao.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(${className}Query query) {
        return ${classNameLower}Dao.count(query);
    }

    @Override
    @Transactional(readOnly = true)
    public List<${className}> search${className}(${className}Query query) {
        return ${classNameLower}Dao.search(query);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<${className}> page${className}(${className}Query query) {
        return ${classNameLower}Dao.page(query);
    }

    @Override
    public int save${className}(${className} ${classNameLower}) {
        return ${classNameLower}Dao.save(${classNameLower});
    }

    @Override
    public int save${className}s(List<${className}> ${classNameLower}s) {
        return ${classNameLower}Dao.save(${classNameLower}s);
    }

    @Override
    public int update${className}(${className} ${classNameLower}) {
        return ${classNameLower}Dao.update(${classNameLower});
    }
    
    @Override
    public int updatePart${className}(${className} ${classNameLower}) {
        return ${classNameLower}Dao.updatePart(${classNameLower});
    }

    @Override
    public int delete${className}(Integer id) {
        return ${classNameLower}Dao.delete(id);
    }

    @Override
    public int delete${className}s(List<Integer> ids) {
        return ${classNameLower}Dao.delete(ids);
    }

}
