<#include "/java_copyright.include">
<#assign className = table.className>
<#assign remarks = table.remarks>
<#assign classNameLower = className?uncap_first> 
package ${basepackage}${modulepackage}.service${table.ownerSynonymName};

import java.util.List;

import ${basepackage}${modulepackage}.domain${table.ownerSynonymName}.${className};

<@classComment value="Service"/>
public interface I${className}Service {
    
    /**
     * 保存${remarks}
     * 
     * @param ${classNameLower} ${remarks}
     * @return ${remarks}对象
     */
    ${className} save(${className} ${classNameLower});
    
    /**
     * 根据id删除${remarks}
     * 
     * @param id 主键编号
     */
    void delete(String id);

    /**
     * 根据id查询${remarks}
     * 
     * @param id 主键编号
     * @return ${remarks}
     */
    ${className} get(String id);
    
    /**
     * 查询${remarks}
     * 
     * @return ${remarks}集合
     */
    List<${className}> findAll();

    /**
     * 统计${remarks}数量
     * 
     * @return 统计数量
     */
    long count();


}
