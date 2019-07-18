package com.test.mybatis.dao;

import com.test.mybatis.pojo.GodUserRole;
import com.test.mybatis.pojo.GodUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GodUserRoleMapper {
    long countByExample(GodUserRoleExample example);

    int deleteByExample(GodUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GodUserRole record);

    int insertSelective(GodUserRole record);

    List<GodUserRole> selectByExample(GodUserRoleExample example);

    GodUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GodUserRole record, @Param("example") GodUserRoleExample example);

    int updateByExample(@Param("record") GodUserRole record, @Param("example") GodUserRoleExample example);

    int updateByPrimaryKeySelective(GodUserRole record);

    int updateByPrimaryKey(GodUserRole record);
}