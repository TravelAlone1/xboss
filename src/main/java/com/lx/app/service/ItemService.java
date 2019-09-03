package com.lx.app.service;

import com.lx.app.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lx
 * @Date: 2019/9/3 8:45
 */

public interface ItemService {

    Item findById(Item item);

    void delete(Item item);

    List<Item> list(Item item);

    List<Item> listS(Item item);

    int count(Item item);

    int insert(Item item);

    int update (Item item);


    List<Item> selectAll();
}
