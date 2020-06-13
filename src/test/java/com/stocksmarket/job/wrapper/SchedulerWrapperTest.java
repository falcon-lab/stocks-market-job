package com.stocksmarket.job.wrapper;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerKey;

import com.stocksmarket.job.exception.JobSchedulerException;

public class SchedulerWrapperTest {

	public static final String TEST_JOB_KEY = "JOB_KEY";
	public static final String TEST_TRIGGER_KEY = "TRIGGER_KEY";

	@Mock
	private Scheduler scheduler;

	@InjectMocks
	private SchedulerWrapper schedulerWrapper;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void checkExistsShouldReturnTrueWhenTriggerExists() throws SchedulerException {
		JobKey jobKey = mockJobKey();
		Mockito.when(scheduler.checkExists(jobKey)).thenReturn(true);
		try {
			boolean exists = schedulerWrapper.checkExists(scheduler, jobKey);
			Assert.assertEquals(true, exists);
		}
		catch (JobSchedulerException e) {
			Assert.fail("ended unexpectedly");
		}
	}

	@Test(expected = JobSchedulerException.class)
	public void checkExistsShouldThrowJobSchedulerExceptionWhenJobNotFound()
			throws JobSchedulerException, SchedulerException {
		JobKey jobKey = mockJobKey();
		Mockito.doThrow(SchedulerException.class).when(scheduler).checkExists(jobKey);
		schedulerWrapper.checkExists(scheduler, jobKey);
	}

	@Test
	public void checkExistsShouldReturnTrueWhenJobExists() throws SchedulerException {
		TriggerKey triggerKey = mockTriggerKey();
		Mockito.when(scheduler.checkExists(triggerKey)).thenReturn(true);
		try {
			boolean exists = schedulerWrapper.checkExists(scheduler, triggerKey);
			Assert.assertEquals(true, exists);
		}
		catch (JobSchedulerException e) {
			Assert.fail("ended unexpectedly");
		}
	}

	@Test(expected = JobSchedulerException.class)
	public void checkExistsShouldThrowJobSchedulerExceptionWhenTriggerNotFound()
			throws JobSchedulerException, SchedulerException {
		TriggerKey triggerKey = mockTriggerKey();
		Mockito.doThrow(SchedulerException.class).when(scheduler).checkExists(triggerKey);
		schedulerWrapper.checkExists(scheduler, triggerKey);
	}

	@Test
	public void getJobDetailShouldRetrieveDetailsWhenJobKeyExists() throws SchedulerException {
		JobKey jobKey = mockJobKey();
		Mockito.when(scheduler.getJobDetail(jobKey)).thenReturn(Mockito.mock(JobDetail.class));
		try {
			schedulerWrapper.getJobDetail(scheduler, jobKey);
		}
		catch (JobSchedulerException e) {
			Assert.fail("ended unexpectedly");
		}
	}

	@Test(expected = JobSchedulerException.class)
	public void getJobDetailShouldThrowJobSchedulerExceptionWhenJobKeyNotFound()
			throws JobSchedulerException, SchedulerException {
		JobKey JobKey = mockJobKey();
		Mockito.doThrow(SchedulerException.class).when(scheduler).getJobDetail(JobKey);
		schedulerWrapper.getJobDetail(scheduler, JobKey);
	}

	@Test
	public void scheduleJobShouldCreateScheduleWhenRequested() throws SchedulerException {
		SimpleTrigger trigger = mockSimpleTrigger();
		Mockito.when(scheduler.scheduleJob(trigger)).thenReturn(Mockito.mock(Date.class));
		try {
			schedulerWrapper.scheduleJob(scheduler, trigger);
		}
		catch (JobSchedulerException e) {
			Assert.fail("ended unexpectedly");
		}
	}

	@Test(expected = JobSchedulerException.class)
	public void scheduleJobShouldThrowJobSchedulerExceptionWhenRequestNotValid()
			throws SchedulerException, JobSchedulerException {
		SimpleTrigger trigger = mockSimpleTrigger();
		Mockito.doThrow(SchedulerException.class).when(scheduler).scheduleJob(trigger);
		schedulerWrapper.scheduleJob(scheduler, trigger);
	}

	private TriggerKey mockTriggerKey() {
		return new TriggerKey(TEST_TRIGGER_KEY);
	}

	private JobKey mockJobKey() {
		return new JobKey(TEST_JOB_KEY);
	}

	private SimpleTrigger mockSimpleTrigger() {
		return Mockito.mock(SimpleTrigger.class);
	}

}
