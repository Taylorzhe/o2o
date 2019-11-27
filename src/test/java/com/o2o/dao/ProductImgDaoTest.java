package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.ProductImg;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest {
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testABatchInsertProductImg() throws Exception{
        //初始化三个商品实例并添加进shopId为1的店铺里
        //同时商品类别的Id也为1
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片1");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(1L);
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(1L);
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2,effectedNum);
    }

    @Test
    public void testBQueryProductImgList(){
        //检查productId为1的商品是否有且仅有两张商品详情图片
        List<ProductImg> productImgList = productImgDao.queryProductImgList(1L);
        assertEquals(2, productImgList.size());
    }

    @Test
    public void testCDeleteProductImgByProductId() throws Exception{
        //删除新增的两条商品详情图片记录
        long productId = 1;
        int effectedNum = productImgDao.deleteProductImgByProductId(productId);
        assertEquals(2, effectedNum);
    }
}
