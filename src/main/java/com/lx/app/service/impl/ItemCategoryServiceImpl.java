package com.lx.app.service.impl;

import com.lx.app.mapper.ItemCategoryMapper;
import com.lx.app.model.ItemCategory;
import com.lx.app.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lx
 * @Date: 2019/9/3 8:50
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryMapper itemCategoryMapper;
    @Override
    public ItemCategory findById(ItemCategory itemCategory) {
        return itemCategoryMapper.findById(itemCategory);
    }

    @Override
    public List<ItemCategory> list(ItemCategory itemCategory) {
        return itemCategoryMapper.list(itemCategory);
    }

    @Override
    public List<ItemCategory> list1() {
        return itemCategoryMapper.list1();
    }

    @Override
    public int count(ItemCategory itemCategory) {
        return itemCategoryMapper.count(itemCategory);
    }

    @Override
    public int insert(ItemCategory itemCategory) {
        return itemCategoryMapper.insert(itemCategory);
    }

    @Override
    public int update(ItemCategory itemCategory) {
        return itemCategoryMapper.update(itemCategory);
    }

    @Override
    public void delete(ItemCategory itemCategory) {
        itemCategoryMapper.delete(itemCategory);
    }

    @Override
    public int updateStatus(ItemCategory itemCategory) {
        return itemCategoryMapper.updateStatus(itemCategory);
    }
}
