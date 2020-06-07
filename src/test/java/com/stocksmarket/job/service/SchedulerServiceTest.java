package com.stocksmarket.job.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stocksmarket.job.exception.FireTriggerException;
import com.stocksmarket.job.exception.JobSchedulerException;
import com.stocksmarket.job.model.ScheduleRequest;
import com.stocksmarket.job.model.ScheduleResponse;
import com.stocksmarket.job.model.dto.Schedule;
import com.stocksmarket.job.scheduler.SchedulerMediator;
import com.stocksmarket.job.storage.FiredTriggersStorage;

public class SchedulerServiceTest {

	private static final UUID TEST_UUID = UUID.fromString("5490695f-67f5-3b92-81e4-79d367e46c8a");

	@InjectMocks
	private SchedulerService schedulerService;

	@Mock
	private SchedulerMediator schedulerMediator;

	@Mock
	private FiredTriggersStorage firedTriggersStorage;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void handleJobScheduleShouldTriggerJobWhenRequestIsValid() throws FireTriggerException {
		Mockito.when(schedulerMediator.fireTrigger(Mockito.any(), Mockito.any())).thenReturn(TEST_UUID);
		try {
			UUID uuid = schedulerService.handleJobSchedule(Mockito.mock(ScheduleRequest.class));
			Assert.assertEquals(TEST_UUID, uuid);
		}
		catch (JobSchedulerException e) {
			Assert.fail(this.getClass().getEnclosingMethod().getName() + " " + e.getLocalizedMessage());
		}
	}

	@Test
	public void handleRetrieveSchedulesShouldRetrieveActiveJobWhenRequested() throws JobSchedulerException {
		Mockito.when(firedTriggersStorage.getAllFiredTriggers()).thenReturn(mockScheduleList());
		try {
			ScheduleResponse scheduleResponse = schedulerService.handleRetrieveSchedules();
			Assert.assertTrue(!scheduleResponse.getData().isEmpty());
		}
		catch (JobSchedulerException e) {
			Assert.fail(this.getClass().getEnclosingMethod().getName() + " " + e.getLocalizedMessage());
		}
	}

	private Schedule mockSchedule() {
		Schedule schedule = new Schedule();
		schedule.setUuid(TEST_UUID);
		return schedule;
	}

	private List<Schedule> mockScheduleList() {
		List<Schedule> scheduleList = new ArrayList();
		scheduleList.add(mockSchedule());
		return scheduleList;
	}

}
