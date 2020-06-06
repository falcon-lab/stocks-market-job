package com.stocksmarket.job.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Health.class)
public class HealthIndicatorControllerTest {

	@InjectMocks
	private HealthIndicatorController healthIndicatorController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void healthIndicatorShouldReturnOkWhenAppIsUpAndRunning() {
		Health health = healthIndicatorController.health();
		Assert.assertEquals(Status.UP, health.getStatus());
	}

}
