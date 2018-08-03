package com.lh.order.service.impl;

import com.lh.order.dataobject.OrderDetail;
import com.lh.order.dataobject.OrderMaster;
import com.lh.order.dto.OrderDTO;
import com.lh.order.repository.OrderDetailRepository;
import com.lh.order.repository.OrderMasterRepository;
import com.lh.order.service.OrderService;
import com.lh.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        //查询商品信息(调用商品服务)
        //计算总价
        //扣库存(调用商品服务)
        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderStatus(1);
        orderDTO.setPayStatus(1);
        orderDTO.setBuyerAddress("address");
        orderDTO.setBuyerName("name");
        orderDTO.setBuyerOpenid("openid");
        orderDTO.setBuyerPhone("153");
        orderDTO.setOrderAmount(new BigDecimal(1));
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setProductName("product name");
            orderDetail.setProductPrice(new BigDecimal(1));
            orderDetail.setProductIcon("");
            orderDetail.setProductQuantity(1);
            orderDetailRepository.save(orderDetail);
        }
        return orderDTO;
    }

}
