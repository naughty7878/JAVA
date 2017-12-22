<#include "/java_copyright.include">
<#assign className = table.className>
<#assign remarks = table.remarks>
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service;

import java.util.List;

import ${basepackage}.domain.${className};
import ${basepackage}.domain.query.${className}Query;
import ${basePagePackage}.Pagination;

<@classComment value="Service"/>
public interface I${className}Service {

    /**
     * 根据id查询${remarks}
     * 
     * @param id 主键编号
     * @return ${remarks}
     */
    ${className} get${className}ById(Integer id);

    /**
     * 统计${remarks}数量
     * 
     * @param query ${remarks}查询条件对象
     * @return 统计数量
     */
    long count(${className}Query query);

    /**
     * 根据组合条件查询${remarks}
     * 
     * @param query ${remarks}查询对象
     * @return ${remarks}集合,如果不存在,返回Empty List
     */
    List<${className}> search${className}(${className}Query query);

    /**
     * 分页查询${remarks}
     * 
     * @param query ${remarks}查询对象
     * @return 分页结果,如果不存在,结果集为Empty List
     */
    Pagination<${className}> page${className}(${className}Query query);

    /**
     * 保存${remarks}
     * 
     * @param ${classNameLower} ${remarks}
     * @return 操作影响行数
     */
    int save${className}(${className} ${classNameLower});

    /**
     * 批量保存${remarks}
     * 
     * @param ${classNameLower}s ${remarks}
     * @return 操作影响行数
     */
    int save${className}s(List<${className}> ${classNameLower}s);

    /**
     * 更新${remarks}
     * 
     * @param ${classNameLower} ${remarks}
     * @return 操作影响行数
     */
    int update${className}(${className} ${classNameLower});
    
    /**
     * 局部更新${remarks}
     * 
     * @param ${classNameLower} ${remarks}
     * @return 操作影响行数
     */
    int updatePart${className}(${className} ${classNameLower});

    /**
     * 根据编号删除${remarks}
     * 
     * @param id 编号
     * @return 操作影响行数
     */
    int delete${className}(Integer id);

    /**
     * 根据id集合批量删除${remarks}
     * 
     * @param ids id主键集合
     * @return 操作影响行数
     */
    int delete${className}s(List<Integer> ids);
}
