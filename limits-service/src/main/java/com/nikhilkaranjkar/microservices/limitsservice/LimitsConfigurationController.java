package com.nikhilkaranjkar.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nikhilkaranjkar.microservices.limitsservice.bean.LimitsConfiguration;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private Configuration configuraiton;
	
	@GetMapping("/limits")
	public LimitsConfiguration reteiveLimitsFromConfigurations()
	{
		return new LimitsConfiguration(configuraiton.getMaximum(),configuraiton.getMinimum());
	}
	
	
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallbackRetrieveConfiguration")
	public LimitsConfiguration retrieveConfiguration() {
		throw new RuntimeException("Not available");
	}

	public LimitsConfiguration fallbackRetrieveConfiguration() {
		return new LimitsConfiguration(6666, 6);
	}
	
	

}
