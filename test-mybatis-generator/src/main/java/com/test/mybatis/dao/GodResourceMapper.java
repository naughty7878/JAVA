package com.test.mybatis.dao;

import com.test.mybatis.pojo.GodResource;
import com.test.mybatis.pojo.GodResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GodResourceMapper {
    long countByExample(GodResourceExample example);

    int deleteByExample(GodResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GodResource record);

    int insertSelective(GodResource record);

    List<GodResource> selectByExample(GodResourceExample example);

    GodResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GodResource record, @Param("example") GodResourceExample example);

    int updateByExample(@Param("record") GodResource record, @Param("example") GodResourceExample example);

    int updateByPrimaryKeySelective(GodResource record);

    int updateByPrimaryKey(GodResource record);
}