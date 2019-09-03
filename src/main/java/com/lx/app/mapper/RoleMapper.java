package com.lx.app.mapper;

import com.lx.app.model.Role;
import org.apache.ibatis.annotations.Mapper;

/**
*@Author: lx
*@Date: 2019/9/2 21:58
*/
@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}