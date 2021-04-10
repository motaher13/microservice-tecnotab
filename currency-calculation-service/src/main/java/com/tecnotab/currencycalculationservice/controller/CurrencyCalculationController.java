package com.tecnotab.currencycalculationservice.controller;

import com.tecnotab.currencycalculationservice.facade.CurrencyExchangeProxy;
import com.tecnotab.currencycalculationservice.model.CalculatedAmount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyCalculationController {

    Logger logger= LoggerFactory.getLogger(CurrencyCalculationController.class);

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CalculatedAmount calculatedAmount(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);


        /* note: */
        String url="http://localhost:8000/currency-exchange/from/{from}/to/{to}";

        ResponseEntity<CalculatedAmount> responseEntity = new RestTemplate().getForEntity(url, CalculatedAmount.class, uriVariables);
        CalculatedAmount calculatedAmount=responseEntity.getBody();

        return new CalculatedAmount(calculatedAmount.getId(), calculatedAmount.getFrom(), calculatedAmount.getTo(),
                calculatedAmount.getConversionMultiple(), quantity, quantity.multiply(calculatedAmount.getConversionMultiple()),
                calculatedAmount.getPort());
    }





    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CalculatedAmount calculatedAmountFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){


        CalculatedAmount calculatedAmount= currencyExchangeProxy.retrieveExchangeValue(from,to);

        logger.info("Calculated Amount: {}", calculatedAmount);

        return new CalculatedAmount(calculatedAmount.getId(), calculatedAmount.getFrom(), calculatedAmount.getTo(),
                calculatedAmount.getConversionMultiple(), quantity, quantity.multiply(calculatedAmount.getConversionMultiple()),
                calculatedAmount.getPort());
    }

}
