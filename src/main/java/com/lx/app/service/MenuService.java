package com.lx.app.service;

import com.lx.app.model.Menu;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author: lx
 * @Date: 2019/9/3 8:54
 */

public interface MenuService {

    int deleteByPrimaryKey(String menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    Set<String> findMenuCodeByUserId(String userId);

    Set<String> getALLMenuCode();
}
