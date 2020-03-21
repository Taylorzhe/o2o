package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.Product;
import com.o2o.entity.ProductCategory;
import com.o2o.entity.ProductImg;
import com.o2o.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testAInsertProduct() throws Exception{
        Shop shop1 = new Shop();
        shop1.setShopId(2L);
        ProductCategory pc1 = new ProductCategory();
        pc1.setProductCategoryId(21L);
        //初始化三个商品实例并添加进shopId为2的店铺里，
        //同时商品类别Id为21
        Product product1 = new Product();
        product1.setProductName("半糖布雷");
        product1.setProductDesc("糖度五分");
        product1.setImgAddr("111");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setPoint(10);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop1);
        product1.setProductCategory(pc1);
        //---------------------------------------------------------
        Product product2 = new Product();
        product2.setProductName("三分糖布雷");
        product2.setProductDesc("糖度三分");
        product2.setImgAddr("222");
        product2.setPriority(2);
        product2.setPoint(13);
        product2.setEnableStatus(0);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop1);
        product2.setProductCategory(pc1);
        //---------------------------------------------------
        Product product3 = new Product();
        product3.setProductName("全糖布雷");
        product3.setProductDesc("糖度十分");
        product3.setImgAddr("333");
        product3.setPriority(3);
        product3.setEnableStatus(3);
        product3.setPoint(12);
        product3.setCreateTime(new Date());
        product3.setLastEditTime(new Date());
        product3.setShop(shop1);
        product3.setProductCategory(pc1);
        //判断是否成功
        int effectedNum1 = productDao.insertProduct(product1);
        assertEquals(1, effectedNum1);
        int effectedNum2 = productDao.insertProduct(product2);
        assertEquals(1, effectedNum2);
        int effectedNum3 = productDao.insertProduct(product3);
        assertEquals(1, effectedNum3);
    }

    @Test
    public void testBQueryProductList() throws Exception {
        Product productCondition = new Product();
        // 分页查询，预期返回三条结果
        List<Product> productList = productDao.queryProductList(productCondition, 0, 3);
        assertEquals(3, productList.size());
        // 查询名称为测试的商品总数
        int count = productDao.queryProductCount(productCondition);
        assertEquals(5, count);
        // 使用商品名称模糊查询，预期返回两条结果
        productCondition.setProductName("布雷");
        productList = productDao.queryProductList(productCondition, 0, 4);
        assertEquals(3, productList.size());
        count = productDao.queryProductCount(productCondition);
        assertEquals(3, count);
    }

    @Test
    public void testCQueryProductByProductId(){
        long productId = 2;
        //初始化两个商品实例并添加进shopId为2的店铺里
        //批量插入到商品详情图表中
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片1");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(2L);

        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(2L);

        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2,effectedNum);
        //查询productId为2的商品信息并校验返回的详情图实例列表size是否为2
        Product product = productDao.queryProductById(productId);
        assertEquals(2, product.getProductImgList().size());
        //删除新增的两个商品详情图实例
        effectedNum = productImgDao.deleteProductImgByProductId(productId);
        assertEquals(2, effectedNum);
    }


    @Test

    public void testDUpdateProduct() throws Exception {
        Product product = new Product();
        ProductCategory pc = new ProductCategory();
        Shop shop = new Shop();
        shop.setShopId(2L);
        pc.setProductCategoryId(27L);
        product.setProductId(2L);
        product.setShop(shop);
        product.setProductName("五分糖布雷");
        product.setProductCategory(pc);
        // 修改productId为2的商品的名称
        // 以及商品类别并校验影响的行数是否为1
        int effectedNum = productDao.updateProduct(product);
        assertEquals(1, effectedNum);
    }


    @Test
    public void testEUpdateProductCategoryToNull() {
        // 将productCategoryId为21的商品类别下面的商品的商品类别置为空
        int effectedNum = productDao.updateProductCategoryToNull(27L);
        assertEquals(2, effectedNum);
    }


    @Test
    public void testFDeleteShopAuthMap() throws Exception {
        // 清除掉insert方法添加的商品
        Product productCondition = new Product();
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(21L);
        productCondition.setProductCategory(pc);
        // 通过输入productCategoryId为21去商品表中查出新增的三条测试数据
        List<Product> productList = productDao.queryProductList(productCondition, 0, 3);
        assertEquals(3, productList.size());
        // 循环删除这三条数据
        for (Product p : productList) {
            int effectedNum = productDao.deleteProduct(p.getProductId(), 2);
            assertEquals(1, effectedNum);
        }
    }
}
