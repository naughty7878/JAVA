package com.test.mybatis.dao;

import com.test.mybatis.pojo.GodRole;
import com.test.mybatis.pojo.GodRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GodRoleMapper {
    long countByExample(GodRoleExample example);

    int deleteByExample(GodRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GodRole record);

    int insertSelective(GodRole record);

    List<GodRole> selectByExample(GodRoleExample example);

    GodRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GodRole record, @Param("example") GodRoleExample example);

    int updateByExample(@Param("record") GodRole record, @Param("example") GodRoleExample example);

    int updateByPrimaryKeySelective(GodRole record);

    int updateByPrimaryKey(GodRole record);
}