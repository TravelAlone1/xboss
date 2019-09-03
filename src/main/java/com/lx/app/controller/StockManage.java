package com.lx.app.controller;

import com.lx.app.mapper.ItemCategoryMapper;
import com.lx.app.model.Item;
import com.lx.app.model.ItemCategory;
import com.lx.app.service.ItemService;
import com.lx.app.util.DateUtil;
import com.lx.app.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 库存管理
 */
@Controller
public class StockManage {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryMapper itemCategoryMapper;

    /**
     *
     * @param item 商品
     * @param pageCurrent 当前页
     * @param pageSize 每页展示总数
     * @param pageCount 总页数
     * @param model
     * @return
     */
    @RequestMapping("/user/stockManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String stockManage(Item item, @PathVariable Integer pageCurrent,
                              @PathVariable Integer pageSize,
                              @PathVariable Integer pageCount,
                              Model model) {

        if (pageSize == 0){
            pageSize = 50;
        }
        if (pageCurrent == 0){
            pageCurrent = 1;
        }
        int rows = itemService.count(item);
        if (pageCount == 0){
            pageCount=rows%pageSize==0?(rows/pageSize):(rows/pageSize)+1;
        }
        //item.setStart((pageCurrent-1)*pageSize+1);
        item.setStart((pageCurrent - 1) * pageSize);
        item.setEnd(pageSize);
        //获取商品列表
        List<Item> itemList = itemService.listS(item);
        for (Item i : itemList){
            i.setUpdatedStr(DateUtil.getDateStr(i.getUpdated()));
        }
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setStart(0);
        itemCategory.setEnd(Integer.MAX_VALUE);
        //获取所有类别
        List<ItemCategory> itemCategoryList = itemCategoryMapper.list(itemCategory);
        Integer minNum = item.getMinNum();
        Integer maxNum = item.getMaxNum();
        model.addAttribute("itemCategoryList", itemCategoryList);
        model.addAttribute("itemList", itemList);
        String pageHTML = PageUtil.getPageContent("stockManage_{pageCurrent}_{pageSize}_{pageCount}?title=" + item.getTitle() + "&cid=" + item.getCid() + "&minNum" + minNum + "&maxNum" + maxNum, pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("item", item);
        return "item/stockManage";
    }
}
