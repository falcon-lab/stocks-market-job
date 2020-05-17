package com.stocksmarket.job.storage;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stocksmarket.job.exception.JobSchedulerException;
import com.stocksmarket.job.model.dto.Schedule;
import com.stocksmarket.job.model.dto.ScheduleTransformation;
import com.stocksmarket.job.model.entity.QrtzFiredTriggers;
import com.stocksmarket.job.repository.QuartzRepository;

@Component
public class FiredTriggersStorage {

	private static Logger logger = LogManager.getLogger(FiredTriggersStorage.class);

	@Autowired
	private QuartzRepository quartzRepository;

	@Autowired
	private ScheduleTransformation scheduleTransformation;

	public List<Schedule> getAllFiredTriggers() throws JobSchedulerException {
		List<QrtzFiredTriggers> qrtzFiredTriggersList = quartzRepository.findAll();

		if (qrtzFiredTriggersList.isEmpty()) {
			throw new JobSchedulerException(JobSchedulerException.NO_DATA);
		}
		logger.info("retrieved {} active jobs", qrtzFiredTriggersList.size());
		return scheduleTransformation.transformToScheduleList(qrtzFiredTriggersList);
	}
}
