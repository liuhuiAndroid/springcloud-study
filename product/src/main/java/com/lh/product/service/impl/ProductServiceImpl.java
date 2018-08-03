package com.lh.product.service.impl;

import com.lh.product.dataobject.ProductInfo;
import com.lh.product.enums.ProductStatusEnum;
import com.lh.product.repository.ProductInfoRepository;
import com.lh.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

}
