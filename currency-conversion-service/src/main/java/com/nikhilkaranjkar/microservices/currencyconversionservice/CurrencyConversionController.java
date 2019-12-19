package com.nikhilkaranjkar.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity)
	{
		
		logger.info("{}","currency conversion : non feign");
		//Feign - Problem 1
		Map<String ,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity= new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,uriVariables);
		
		CurrencyConversionBean currencyConversionBean = responseEntity.getBody();
		
		return new CurrencyConversionBean(currencyConversionBean.getId(),from, to,currencyConversionBean.getConversionMultiple(),quantity,currencyConversionBean.getConversionMultiple().multiply(quantity),currencyConversionBean.getPort());
	}
	
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity)
	{
		
		CurrencyConversionBean currencyConversionBean= currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
		
		logger.info("{}","currency conversion : feign");
//		//Feign - Problem 1
//		Map<String ,String> uriVariables = new HashMap<>();
//		uriVariables.put("from", from);
//		uriVariables.put("to", to);
//
//		ResponseEntity<CurrencyConversionBean> responseEntity= new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,uriVariables);
		
		//CurrencyConversionBean currencyConversionBean = responseEntity.getBody();
		
		return new CurrencyConversionBean(currencyConversionBean.getId(),from, to,currencyConversionBean.getConversionMultiple(),quantity,currencyConversionBean.getConversionMultiple().multiply(quantity),currencyConversionBean.getPort());
	}

}
