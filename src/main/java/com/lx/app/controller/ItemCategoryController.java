package com.lx.app.controller;

import com.lx.app.model.ItemCategory;
import com.lx.app.model.ResObject;
import com.lx.app.service.ItemCategoryService;
import com.lx.app.util.Constant;
import com.lx.app.util.DateUtil;
import com.lx.app.util.PageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 商品分类管理
 */
@Controller
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequiresPermissions("role:save")
    @RequestMapping("/user/itemCategoryManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String itemCategoryManage(ItemCategory itemCategory,
                                     @PathVariable Integer pageCurrent,
                                     @PathVariable Integer pageSize,
                                     @PathVariable Integer pageCount,
                                     Model model){
        //判断
        if(pageSize == 0){
            pageSize = 20;
        }
        if(pageCurrent == 0){
            pageCurrent = 1;
        }
        //商品种类总数
        int rows = itemCategoryService.count(itemCategory);
        //确定需要多少页
        if(pageCount == 0) {
            pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
        }
        //设置每页的数量
        itemCategory.setStart((pageCurrent - 1)*pageSize);
        itemCategory.setEnd(pageSize);
        //取出商品种类，并打印在前端
        List<ItemCategory> list = itemCategoryService.list(itemCategory);
        for (ItemCategory i : list){
            i.setCreatedStr(DateUtil.getDateStr(i.getCreated()));
        }
        //展示数据
        model.addAttribute("list", list);
        String pageHTML = PageUtil.getPageContent("itemCategoryManage_{pageCurrent}_{pageSize}_{pageCount}?name="+itemCategory.getName(), pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("itemCategory", itemCategory);
        return "item/itemCategoryManage";
    }

    @GetMapping("/user/itemCategoryEdit")
    public String itemCategoryEditGet(Model model,ItemCategory itemCategory) {
        //前端传过来带id
        // 判断是否存在
        if(itemCategory.getId() != 0){
            //获取商品类别
            ItemCategory itemCategory0 = itemCategoryService.findById(itemCategory);
            model.addAttribute("itemCategory",itemCategory0);
        }
        //跳转到商品类别编辑界面
        return "item/itemCategoryEdit";
    }
    @PostMapping("/user/itemCategoryEdit")
    public String newsCategoryEditPost(Model model, ItemCategory itemCategory, @RequestParam MultipartFile[] imageFile, HttpSession httpSession) {
        //根据时间和随机数生成id
        Date date = new Date();
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (999 - 100 + 1)) + 10;// 获取3位随机数
        itemCategory.setCreated(date);
        itemCategory.setUpdated(date);
        List<ItemCategory> list = itemCategoryService.list1();
        String name = itemCategory.getName();
        for(ItemCategory i : list){
            if(i.getName().equals(name)){
                return "redirect:itemCategoryManage_0_0_0";
            }
        }
        //判断是否存在
        if(itemCategory.getId() != 0){
            itemCategoryService.update(itemCategory);
        } else {
            itemCategory.setId(rannum);
            itemCategoryService.insert(itemCategory);
        }
        return "redirect:itemCategoryManage_0_0_0";
    }

    @ResponseBody
    @PostMapping("/user/itemCategoryEditState")
    public ResObject<Object> itemCategoryEditState(ItemCategory itemCategory){
        //根据id进行删除
        itemCategoryService.delete(itemCategory);
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }
}
