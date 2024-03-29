package com.nikhilkaranjkar.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
	//	ExchangeValue exchangeValue= new ExchangeValue(1000L,"from","to",BigDecimal.valueOf(65));
		
		logger.info("{}","currency exchange :");
		
		ExchangeValue exchangeValue=exchangeValueRepository.findByFromAndTo(from, to);
		
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

		return exchangeValue;
		
	}

}
