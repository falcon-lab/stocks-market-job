package com.stocksmarket.job.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class HealthIndicatorController implements HealthIndicator {

	@Override
	public Health health() {
		return Health.up().build();
	}

}
