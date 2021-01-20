package com.djh.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author djh
 * @version 1.0
 * @description: TODO
 * @date 2021/1/20 17:17
 */

@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
}
