package com.djh.springcloud.dao;

import com.djh.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public Long create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
