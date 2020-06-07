package com.stocksmarket.job.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.stocksmarket.job.model.entity.QrtzFiredTriggers;

public class ScheduleTransformationTest {

	private static final String TEST_SCHEDULE_NAME = "schedule_001";
	private static final String TEST_ENTRY_ID = "id_001";
	private static final String TEST_TRIGGER_NAME = "452ca76d-977e-4233-a300-ad610fb5e924";
	private static final String TEST_FIRED_TIME = "1234567890";
	private static final String TEST_PRIORITY = "5";
	private static final String TEST_STATE = "Running";
	private static final String TEST_JOB_NAME = "schedule_id_001";

	@InjectMocks
	private ScheduleTransformation scheduleTransformation;
	private Object Schedule;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void transformToScheduleListShouldTransformWhenRequested() {
		List<Schedule> scheduleList = scheduleTransformation.transformToScheduleList(mockQuartzTriggersList());
		for (Schedule schedule : scheduleList) {
			assertSchedule(schedule);
		}
	}

	@Test
	public void transformToScheduleShouldTransformWhenRequested() {
		Schedule schedule = scheduleTransformation.transformToSchedule(mockQuartzTriggers());
		assertSchedule(schedule);
	}

	private List<QrtzFiredTriggers> mockQuartzTriggersList() {
		List<QrtzFiredTriggers> triggers = new ArrayList();
		triggers.add(mockQuartzTriggers());
		return triggers;
	}

	private QrtzFiredTriggers mockQuartzTriggers() {
		QrtzFiredTriggers qrtzFiredTriggers = new QrtzFiredTriggers();
		qrtzFiredTriggers.setSchedName(TEST_SCHEDULE_NAME);
		qrtzFiredTriggers.setEntryId(TEST_ENTRY_ID);
		qrtzFiredTriggers.setTriggerName(TEST_TRIGGER_NAME);
		qrtzFiredTriggers.setFiredTime(TEST_FIRED_TIME);
		qrtzFiredTriggers.setPriority(TEST_PRIORITY);
		qrtzFiredTriggers.setState(TEST_STATE);
		qrtzFiredTriggers.setJobName(TEST_JOB_NAME);
		return qrtzFiredTriggers;
	}

	private void assertSchedule(Schedule schedule) {
		Assert.assertTrue(Objects.nonNull(schedule.getUuid()));
		Assert.assertTrue(Objects.nonNull(schedule.getStartedAt()));
		Assert.assertTrue(Objects.nonNull(schedule.getDuration()));
		Assert.assertTrue(Objects.nonNull(schedule.getState()));
		Assert.assertTrue((Objects.nonNull(schedule.getPriority())));
	}

}
