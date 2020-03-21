package com.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin", method = {RequestMethod.GET})
public class ShopAdminController {
    // 转发至注册/编辑界面
    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
       return "shop/shopoperation";
    }

    // 转发至店铺列表页面
    @RequestMapping(value = "/shoplist")
    public String shopList(){
        return "shop/shoplist";
    }

    // 转发至店铺管理页面
    @RequestMapping(value = "/shopmanagement")
    public String shopManagement(){
        return "shop/shopmanagement";
    }

    // 转发至商品类别管理页面
    @RequestMapping(value = "/productcategorymanagement", method = RequestMethod.GET)
    private String productCategoryManage() {
        return "shop/productcategorymanagement";
    }


    @RequestMapping(value = "/productoperation")
    public String productOperation() {
        // 转发至商品添加/编辑页面
        return "shop/productoperation";
    }

    @RequestMapping(value = "/productmanagement")
    public String productManagement() {
        // 转发至商品管理页面
        return "shop/productmanagement";
    }


}
