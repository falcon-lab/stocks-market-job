package com.stocksmarket.job.scheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.stocksmarket.job.exception.JobSchedulerException;
import com.stocksmarket.job.job.ExecutionJob;
import com.stocksmarket.job.wrapper.SchedulerWrapper;

@Component
public class ScheduleConfiguration {

	@Autowired
	private SchedulerFactoryBean schedulerFactory;

	@Autowired
	private SchedulerWrapper schedulerWrapper;

	/**
	 * @param jobKey
	 * @param stockId
	 * @return
	 * @throws JobSchedulerException
	 */
	public JobDetail configureJob(JobKey jobKey, String stockId) throws JobSchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();

		if (!schedulerWrapper.checkExists(scheduler, jobKey)) {
			register(jobKey.getName(), jobKey.getGroup(), createJobDataMap(stockId));
		}
		return schedulerWrapper.getJobDetail(scheduler, jobKey);
	}

	/**
	 * @param jobName
	 * @param jobGroup
	 * @param map
	 * @throws JobSchedulerException
	 */
	private void register(String jobName, String jobGroup, JobDataMap map) throws JobSchedulerException {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class).withIdentity(jobName, jobGroup)
					.usingJobData(map).storeDurably().build();
			scheduler.addJob(jobDetail, true);
		}
		catch (SchedulerException e) {
			throw new JobSchedulerException(e.getLocalizedMessage());
		}
	}

	/**
	 * @param stock
	 * @return
	 */
	private JobDataMap createJobDataMap(String stock) {
		JobDataMap map = new JobDataMap();
		map.put("stockName", stock);
		return map;
	}

}
