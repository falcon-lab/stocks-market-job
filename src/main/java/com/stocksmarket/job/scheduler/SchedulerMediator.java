package com.stocksmarket.job.scheduler;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.UUID;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stocksmarket.job.exception.FireTriggerException;
import com.stocksmarket.job.exception.JobSchedulerException;

@Component
public class SchedulerMediator {

	@Autowired
	private ScheduleConfiguration jobScheduler;

	@Autowired
	private TriggerConfiguration triggerConfiguration;

	/**
	 * @param group
	 * @param stock
	 * @return
	 * @throws FireTriggerException
	 */
	public UUID fireTrigger(String group, String stock) throws FireTriggerException {
		UUID jobUuid = buildUUID(group, stock);
		JobDetail jobDetail;

		try {
			JobKey jobKey = JobKey.jobKey(jobUuid.toString());
			jobDetail = jobScheduler.configureJob(jobKey, stock);
		}
		catch (JobSchedulerException e) {
			throw new FireTriggerException(JobSchedulerException.JOB_WRONG_CALL, group, stock);
		}

		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobUuid.toString());
			triggerConfiguration.configureTrigger(triggerKey, jobDetail);
		}
		catch (JobSchedulerException e) {
			throw new FireTriggerException(JobSchedulerException.JOB_ALREADY_RUNNING);
		}
		return jobUuid;
	}

	/**
	 * @param group
	 * @param stock
	 * @return
	 */
	private UUID buildUUID(String group, String stock) {
		String source = LocalDate.now().toString() + group + stock;
		byte[] bytes = source.getBytes(StandardCharsets.UTF_8);
		return UUID.nameUUIDFromBytes(bytes);
	}

}
