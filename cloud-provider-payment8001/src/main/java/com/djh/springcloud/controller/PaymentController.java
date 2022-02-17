package com.djh.springcloud.controller;

import com.djh.springcloud.entities.CommonResult;
import com.djh.springcloud.entities.Payment;
import com.djh.springcloud.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        Long id = paymentService.create(payment);
        if(id>0){
            return new CommonResult<>(200,"插入成功",payment);
        }else{
            return new CommonResult<>(404,"插入失败");
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment !=null){
            return new CommonResult<>(200,"查询成功",payment);
        }else{
            return new CommonResult<>(404,"查询失败，查询id为"+id);
        }
    }
}
