package com.lx.app.service.impl;

import com.lx.app.mapper.ItemMapper;
import com.lx.app.model.Item;
import com.lx.app.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lx
 * @Date: 2019/9/3 8:45
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Item findById(Item item) {
        return itemMapper.findById(item);
    }

    @Override
    public void delete(Item item) {
        itemMapper.delete(item);
    }

    @Override
    public List<Item> list(Item item) {
        return itemMapper.list(item);
    }

    @Override
    public List<Item> listS(Item item) {
        return itemMapper.listS(item);
    }

    @Override
    public int count(Item item) {
        return itemMapper.count(item);
    }

    @Override
    public int insert(Item item) {
        return itemMapper.insert(item);
    }

    @Override
    public int update(Item item) {
        return itemMapper.update(item);
    }

    @Override
    public List<Item> selectAll() {
        return itemMapper.selectAll();
    }
}
