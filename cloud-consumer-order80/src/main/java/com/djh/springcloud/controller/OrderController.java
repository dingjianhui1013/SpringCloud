package com.djh.springcloud.controller;

import com.djh.springcloud.entities.CommonResult;
import com.djh.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author djh
 * @version 1.0
 * @description: TODO
 * @date 2021/1/20 17:14
 */

@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENY_URL="http://localhost:8001";

    @Autowired
    private RestTemplate restTemplate;

   
    /**
     * @description:
     * @param: 
     * @param payment
     * @return: com.djh.springcloud.entities.CommonResult<com.djh.springcloud.entities.Payment>
     * @author djh
     * @date: 2021/1/20 17:27
     */
    @GetMapping("consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){

        return  restTemplate.postForObject(PAYMENY_URL+"/payment/create",payment,CommonResult.class);
    }

    /** 
     * @description: 80端口发起请求，调用8001服务，根据id查询数据
     * @author djh
     * @date: 2021/1/20 17:24
     * @param: 
     * @param id
     * @return: com.djh.springcloud.entities.CommonResult<com.djh.springcloud.entities.Payment>
     */
    @GetMapping("/consumer/payment/get/{id}")
    public  CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return  restTemplate.getForObject(PAYMENY_URL+"/payment/get/"+id,CommonResult.class);
    }


}
