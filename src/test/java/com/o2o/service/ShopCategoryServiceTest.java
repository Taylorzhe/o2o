package com.o2o.service;

import com.o2o.BaseTest;
import com.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



public class ShopCategoryServiceTest extends BaseTest {
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Test
    public void testGetShopCategoryList() {
        List<ShopCategory> categoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
        System.out.println(categoryList.get(0).getShopCategoryName());
        ShopCategory shopCategoryCondition = new ShopCategory();
        ShopCategory parent = new ShopCategory();
        parent.setShopCategoryId(1L);
        shopCategoryCondition.setParent(parent);
        categoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
        System.out.println(categoryList.get(0).getShopCategoryName());
    }
}

