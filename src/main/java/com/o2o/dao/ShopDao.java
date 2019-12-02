package com.o2o.dao;

import com.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /**
     * 分页查询店铺，可查询的条件有：店铺名（模糊），店铺状态，店铺类别，区域Id，owner
     * @param shopCondition
     * @param rowIndex 从第几行开始取数据，返回的条数是多少条
     * @param pageSize
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,
                             @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 返回queryShopList总数
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);

    /**
     * 增加店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺
     * @param shop
     * @return
     */
     int updateShop(Shop shop);

    /**
     * 通过shopId查询店铺
     * @param shopId
     * @return
     */
    Shop queryByShopId(long shopId);
}
