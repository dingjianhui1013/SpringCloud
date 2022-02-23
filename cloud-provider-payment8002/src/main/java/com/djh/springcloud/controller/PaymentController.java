package com.djh.springcloud.controller;

import com.djh.springcloud.entities.CommonResult;
import com.djh.springcloud.entities.Payment;
import com.djh.springcloud.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String serverPort;
    @Resource
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;
    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        Long id = paymentService.create(payment);
        if(id>0){
            return new CommonResult<>(200,"插入成功,serverPort"+serverPort,payment);
        }else{
            return new CommonResult<>(404,"插入失败,serverPort"+serverPort);
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment !=null){
            return new CommonResult<>(200,"查询成功,serverPort"+serverPort,payment);
        }else{
            return new CommonResult<>(404,"查询失败，查询id为"+id+",serverPort"+serverPort);
        }
    }


    @GetMapping(value = "/payment/discovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*****element: "+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout()
    {
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
