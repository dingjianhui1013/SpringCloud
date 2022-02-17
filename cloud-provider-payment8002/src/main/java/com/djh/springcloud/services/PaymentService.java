package com.djh.springcloud.services;

import com.djh.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public Long create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
