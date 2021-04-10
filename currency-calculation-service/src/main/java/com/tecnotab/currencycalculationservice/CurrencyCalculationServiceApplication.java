package com.tecnotab.currencycalculationservice;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

/* note*/
@EnableFeignClients

@EnableDiscoveryClient


public class CurrencyCalculationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyCalculationServiceApplication.class, args);
    }

    /* note: this bean created to work with sleuth. sleuth tags each http request with a unique trackable id*/
    @Bean
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }

}
