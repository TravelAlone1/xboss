package com.lx.app.service;

import com.lx.app.model.ItemCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lx
 * @Date: 2019/9/3 8:49
 */

public interface ItemCategoryService {

    ItemCategory findById(ItemCategory itemCategory);

    List<ItemCategory> list(ItemCategory itemCategory);

    List<ItemCategory> list1();

    int count(ItemCategory itemCategory);

    int insert(ItemCategory itemCategory);

    int update(ItemCategory itemCategory);

    void delete(ItemCategory itemCategory);

    int updateStatus(ItemCategory itemCategory);
}
