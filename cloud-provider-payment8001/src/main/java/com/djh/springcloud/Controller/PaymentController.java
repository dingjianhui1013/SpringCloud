package com.djh.springcloud.Controller;

import com.djh.springcloud.entities.CommonResult;
import com.djh.springcloud.entities.Payment;
import com.djh.springcloud.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> Create(@RequestBody Payment payment){
        int  result = paymentService.create(payment);
        if (result!=0){
            return  new CommonResult<Payment>(200,"success",payment);
        }else {
            return  new CommonResult<Payment>(500,"error",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        int age = 10/2;
        if (payment!=null){
            return  new CommonResult<Payment>(200,"success",payment);
        }else {
            return  new CommonResult<Payment>(500,"error",null);
        }
    }


}
