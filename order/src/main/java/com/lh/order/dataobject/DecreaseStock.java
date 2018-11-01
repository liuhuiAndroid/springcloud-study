package com.lh.order.dataobject;

import lombok.Data;

@Data
public class DecreaseStock {

    private String productId;

    private Integer productQuantity;

    public DecreaseStock() {
    }

    public DecreaseStock(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

}
