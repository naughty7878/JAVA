package com.test.mybatis.dao;

import com.test.mybatis.pojo.GodUser;
import com.test.mybatis.pojo.GodUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GodUserMapper {
    long countByExample(GodUserExample example);

    int deleteByExample(GodUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GodUser record);

    int insertSelective(GodUser record);

    List<GodUser> selectByExample(GodUserExample example);

    GodUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GodUser record, @Param("example") GodUserExample example);

    int updateByExample(@Param("record") GodUser record, @Param("example") GodUserExample example);

    int updateByPrimaryKeySelective(GodUser record);

    int updateByPrimaryKey(GodUser record);
}