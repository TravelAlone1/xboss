package com.lx.app.mapper;

import com.lx.app.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
*@Author: lx
*@Date: 2019/9/2 21:58
*/
@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(@Param("userId") String userId, @Param("roleId") String roleId);

    int insert(UserRole record);

    int insertSelective(UserRole record);
}