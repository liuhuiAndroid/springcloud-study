package com.lh.product.service.impl;

import com.lh.product.ProductApplicationTests;
import com.lh.product.dataobject.DecreaseStock;
import com.lh.product.dataobject.ProductInfo;
import com.lh.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductServiceImplTest extends ProductApplicationTests {

    @Autowired
    ProductService productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productService.findUpAll();
        System.err.println(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void decreaseStock() throws Exception {
        DecreaseStock decreaseStockInput = new DecreaseStock("157875196366160022", 2);
        productService.decreaseStock(Arrays.asList(decreaseStockInput));
    }

}