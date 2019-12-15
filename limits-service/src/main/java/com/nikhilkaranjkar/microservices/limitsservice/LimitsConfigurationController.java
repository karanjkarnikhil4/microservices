package com.nikhilkaranjkar.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	

}
