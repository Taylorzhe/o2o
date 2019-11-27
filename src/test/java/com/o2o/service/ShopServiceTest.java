package com.o2o.service;

import com.o2o.BaseTest;
import com.o2o.dto.ImageHolder;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Area;
import com.o2o.entity.PersonInfo;
import com.o2o.entity.Shop;
import com.o2o.entity.ShopCategory;
import com.o2o.enums.ShopStateEnum;
import com.o2o.exceptions.ShopOperationException;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;


@Service
@Transactional
public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test

    public void testQueryShopListAndCount(){
        Shop shopCondition = new Shop();
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(1L);
        shopCondition.setShopCategory(sc);
        ShopExecution se = shopService.getShopList(shopCondition, 2,2);
        System.out.println("店铺列表数为：" + se.getShopList().size());
        System.out.println("店铺总数为：" + se.getCount());
    }

    @Test
    @Ignore
    @Rollback(false)
    public void testModifyShop() throws ShopOperationException, FileNotFoundException{
        Shop shop = new Shop();
        shop.setShopId(27L);
        shop.setShopName("修改后的店铺名称");
        File shopImage = new File("C:/pic/dabai.jpg");
        InputStream is = new FileInputStream(shopImage);
        ImageHolder imageHolder = new ImageHolder("dabai.jpg", is);
        ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
        System.out.println("新的图片地址" + shopExecution.getShop().getShopImg());
    }

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("王哲的铺子");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImage = new File("C:/pic/timg.jpg");
        InputStream is = new FileInputStream(shopImage);
        ImageHolder imageHolder = new ImageHolder(shopImage.getName(),is);
        ShopExecution se = shopService.modifyShop(shop, imageHolder);
        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
    }

}
