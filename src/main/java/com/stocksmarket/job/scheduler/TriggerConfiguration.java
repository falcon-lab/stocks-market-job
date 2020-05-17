package com.stocksmarket.job.scheduler;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.stocksmarket.job.exception.JobSchedulerException;
import com.stocksmarket.job.wrapper.SchedulerWrapper;

@Component
public class TriggerConfiguration {

	@Autowired
	private SchedulerFactoryBean schedulerFactory;

	@Autowired
	private SchedulerWrapper schedulerWrapper;

	public void configureTrigger(TriggerKey triggerKey, JobDetail jobDetail) throws JobSchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity(triggerKey).startNow()
				.forJob(jobDetail).build();

		if (!schedulerWrapper.checkExists(scheduler, triggerKey)) {
			schedulerWrapper.scheduleJob(scheduler, trigger);
		}
		else {
			throw new JobSchedulerException(JobSchedulerException.JOB_ALREADY_RUNNING);
		}
	}
}
