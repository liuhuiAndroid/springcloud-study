package com.lh.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqReceiver {

//    @RabbitListener(queues = "myQueue")
//    public void process(String message) {
//        log.info("MqReceiver:{}", message);
//    }

    // 自动创建队列
//    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
//    public void process(String message) {
//        log.info("MqReceiver:{}", message);
//    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        log.info("MqReceiver:{}", message);
    }

    /**
     * 水果供应商服务
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fruitOrder"),
            key = "fruit",
            exchange = @Exchange("myOrder")
    ))
    public void processFruit(String message) {
        log.info("fruit MqReceiver:{}", message);
    }

    /**
     * 水果供应商服务
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("computerOrder"),
            key = "computer",
            exchange = @Exchange("myOrder")
    ))
    public void processComputer(String message) {
        log.info("computer MqReceiver:{}", message);
    }
}
