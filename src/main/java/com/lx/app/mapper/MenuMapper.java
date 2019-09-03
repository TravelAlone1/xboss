package com.lx.app.mapper;

import com.lx.app.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
*@Author: lx
*@Date: 2019/9/2 21:59
*/
@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    Set<String> findMenuCodeByUserId(String userId);

    Set<String> getALLMenuCode();
}