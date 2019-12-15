package com.nikhilkaranjkar.microservices.limitsservice.bean;

import org.springframework.beans.factory.annotation.Value;

public class LimitsConfiguration {

	public LimitsConfiguration(int maximum, int minimum) {
		super();
		this.maximum = maximum;
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public int getMinimum() {
		return minimum;
	}

	private int maximum;
	private int minimum;

}
