package com.lh.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lh.order.dataobject.ProductInfo;
import com.lh.order.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        List<ProductInfo> productInfos = (List<ProductInfo>) JsonUtil.fromJson(message,
                new TypeReference<List<ProductInfo>>() {
                });
        log.info("接收到消息:{}", productInfos);

        // 存储到redis中
        for (ProductInfo productInfo : productInfos) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfo.getProductId()),
                    String.valueOf(productInfo.getProductStock()));
        }
    }
}
