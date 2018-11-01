package com.lh.product.service.impl;

import com.lh.product.dataobject.DecreaseStock;
import com.lh.product.dataobject.ProductInfo;
import com.lh.product.enums.ProductStatusEnum;
import com.lh.product.enums.ResultEnum;
import com.lh.product.exception.ProductException;
import com.lh.product.repository.ProductInfoRepository;
import com.lh.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Transactional
    public void decreaseStock(List<DecreaseStock> DecreaseStockList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStock DecreaseStock: DecreaseStockList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(DecreaseStock.getProductId());
            //判断商品是否存在
            if (!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();
            //库存是否足够
            Integer result = productInfo.getProductStock() - DecreaseStock.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
    }

}
