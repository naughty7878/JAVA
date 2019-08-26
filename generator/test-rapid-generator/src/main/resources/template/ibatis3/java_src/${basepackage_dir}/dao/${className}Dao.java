<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao;

<#include "/java_imports.include">

import java.util.List;
import org.apache.ibatis.annotations.Param;

import ${basepackage}.model.${className};
import ${basepackage}.model.${className}Example;


/**
 * ${table.remarks } DAO层
 * 
 * @author ${author}
 * @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
 */
public interface ${className}Dao {
	
	/**
	 * 统计记录-根据示例条件
	 */
    long countByExample(${className}Example example);

    /**
     * 删除记录-根据示例条件
     */
    int deleteByExample(${className}Example example);

    /**
     * 删除记录-根据主键
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入记录-完全插入
     */
    int insert(${className} record);

    /**
     * 插入记录-选择性插入
     */
    int insertSelective(${className} record);

    /**
     * 查询记录-根据示例条件
     */
    List<${className}> selectByExample(${className}Example example);

    /**
     * 查询记录-根据主键
     */
    ${className} selectByPrimaryKey(Integer id);

    /**
     * 更新记录-根据示例条件选择性更新
     */
    int updateByExampleSelective(@Param("record") ${className} record, @Param("example") ${className}Example example);

    /**
     * 更新记录-根据示例条件更新
     */
    int updateByExample(@Param("record") ${className} record, @Param("example") ${className}Example example);

    /**
     * 更新记录-根据主键选择性更新
     * @return
     */
    int updateByPrimaryKeySelective(${className} record);

    /**
     * 更新记录-根据主键更新
     * @return
     */
    int updateByPrimaryKey(${className} record);
    
}
