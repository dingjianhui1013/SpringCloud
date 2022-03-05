package com.djh.springcloud.controller;

import com.djh.springcloud.entities.CommonResult;
import com.djh.springcloud.entities.Payment;
import com.djh.springcloud.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        Long id = paymentService.create(payment);
        if (id > 0) {
            return new CommonResult<>(200, "插入成功,serverPort" + serverPort, payment);
        } else {
            return new CommonResult<>(404, "插入失败,serverPort" + serverPort);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功,serverPort" + serverPort, payment);
        } else {
            return new CommonResult<>(404, "查询失败，查询id为" + id + ",serverPort" + serverPort);
        }
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi ,i'am paymentzipkin server fall back，welcome to here, O(∩_∩)O哈哈~";
    }

}
