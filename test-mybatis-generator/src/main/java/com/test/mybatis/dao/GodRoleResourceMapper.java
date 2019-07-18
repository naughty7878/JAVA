package com.test.mybatis.dao;

import com.test.mybatis.pojo.GodRoleResource;
import com.test.mybatis.pojo.GodRoleResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GodRoleResourceMapper {
    long countByExample(GodRoleResourceExample example);

    int deleteByExample(GodRoleResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GodRoleResource record);

    int insertSelective(GodRoleResource record);

    List<GodRoleResource> selectByExample(GodRoleResourceExample example);

    GodRoleResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GodRoleResource record, @Param("example") GodRoleResourceExample example);

    int updateByExample(@Param("record") GodRoleResource record, @Param("example") GodRoleResourceExample example);

    int updateByPrimaryKeySelective(GodRoleResource record);

    int updateByPrimaryKey(GodRoleResource record);
}