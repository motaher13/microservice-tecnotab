package com.tecnotab.currencycalculationservice.facade;

import com.tecnotab.currencycalculationservice.model.CalculatedAmount;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/* note: */
//@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000/")
//@FeignClient(name = "currency-exchange-service")
//@LoadBalancerClient(name="currency-exchange-service")

@FeignClient(name = "netflix-zuul-api-gateway-server")
@LoadBalancerClient(name="currency-exchange-service")

public interface CurrencyExchangeProxy {


//    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    public CalculatedAmount retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
