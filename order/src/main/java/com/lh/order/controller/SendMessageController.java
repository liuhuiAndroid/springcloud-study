package com.lh.order.controller;

import com.lh.order.dto.OrderDTO;
import com.lh.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendMessageController {

    @Autowired
    StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process(){
        String message = "now" + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }

    @GetMapping("/sendOrderDTO")
    public void processOrderDTO(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("11111111");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
