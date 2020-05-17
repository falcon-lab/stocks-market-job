package com.stocksmarket.job.wrapper;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Component;

import com.stocksmarket.job.exception.JobSchedulerException;

@Component
public class SchedulerWrapper {

	/**
	 * @param scheduler
	 * @param jobKey
	 * @return
	 * @throws JobSchedulerException
	 */
	public boolean checkExists(Scheduler scheduler, JobKey jobKey) throws JobSchedulerException {
		try {
			return scheduler.checkExists(jobKey);
		}
		catch (SchedulerException e) {
			throw new JobSchedulerException(e.getLocalizedMessage());
		}
	}

	/**
	 * @param scheduler
	 * @param triggerKey
	 * @return
	 * @throws JobSchedulerException
	 */
	public boolean checkExists(Scheduler scheduler, TriggerKey triggerKey) throws JobSchedulerException {
		try {
			return scheduler.checkExists(triggerKey);
		}
		catch (SchedulerException e) {
			throw new JobSchedulerException(e.getLocalizedMessage());
		}
	}

	/**
	 * @param scheduler
	 * @param jobKey
	 * @return
	 * @throws JobSchedulerException
	 */
	public JobDetail getJobDetail(Scheduler scheduler, JobKey jobKey) throws JobSchedulerException {
		try {
			return scheduler.getJobDetail(jobKey);
		}
		catch (SchedulerException e) {
			throw new JobSchedulerException(e.getLocalizedMessage());
		}
	}

	/**
	 * @param scheduler
	 * @param trigger
	 * @throws JobSchedulerException
	 */
	public void scheduleJob(Scheduler scheduler, SimpleTrigger trigger) throws JobSchedulerException {
		try {
			scheduler.scheduleJob(trigger);
		}
		catch (SchedulerException e) {
			throw new JobSchedulerException(e.getLocalizedMessage());
		}
	}

}
