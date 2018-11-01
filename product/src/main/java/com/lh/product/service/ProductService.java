package com.lh.product.service;

import com.lh.product.dataobject.DecreaseStock;
import com.lh.product.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStock> decreaseStockInputList);

}
