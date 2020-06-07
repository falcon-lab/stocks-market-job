package com.stocksmarket.job.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocksmarket.job.exception.JobSchedulerException;
import com.stocksmarket.job.model.ScheduleRequest;
import com.stocksmarket.job.model.ScheduleResponse;
import com.stocksmarket.job.model.dto.Schedule;
import com.stocksmarket.job.scheduler.SchedulerMediator;
import com.stocksmarket.job.storage.FiredTriggersStorage;

@Service
public class SchedulerService {

	@Autowired
	private SchedulerMediator schedulerMediator;

	@Autowired
	private FiredTriggersStorage firedTriggersStorage;

	/**
	 * @param schedule
	 * @throws JobSchedulerException
	 */
	public UUID handleJobSchedule(ScheduleRequest schedule) throws JobSchedulerException {
		return schedulerMediator.fireTrigger(schedule.getGroup(), schedule.getStock());
	}

	/**
	 * @return
	 * @throws JobSchedulerException
	 */
	public ScheduleResponse handleRetrieveSchedules() throws JobSchedulerException {
		List<Schedule> scheduleList = firedTriggersStorage.getAllFiredTriggers();
		return new ScheduleResponse(scheduleList);
	}

}
