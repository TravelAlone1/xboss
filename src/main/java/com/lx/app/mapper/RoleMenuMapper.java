package com.lx.app.mapper;

import com.lx.app.model.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
*@Author: lx
*@Date: 2019/9/2 21:59
*/
@Mapper
public interface RoleMenuMapper {
    int deleteByPrimaryKey(@Param("roleId") String roleId, @Param("menuId") String menuId);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);
}