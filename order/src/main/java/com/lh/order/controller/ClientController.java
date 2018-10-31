package com.lh.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate3;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
//        // 1.第一种方式(直接使用RestTemplate，缺点:url写死)
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);
//        log.warn("response={}",response);

        // 2.第二种方式(利用loadBalancerClient通过应用名获取url，然后再使用restTemplate)
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
        String response = restTemplate.getForObject(url, String.class);
        log.warn("response={}", response);

//        // 3.第三种方式(利用@LoadBalanced，可在RestTemplate里使用应用名字)
//        String response = restTemplate3.getForObject("http://PRODUCT/msg", String.class);
//        log.warn("response={}", response);
        return "client:" + response;
    }
}
