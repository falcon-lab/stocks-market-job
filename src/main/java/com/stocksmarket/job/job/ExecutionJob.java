package com.stocksmarket.job.job;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class ExecutionJob implements Job {

	private static Logger logger = LogManager.getLogger(ExecutionJob.class);

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
		logger.info("Start job execution", map.get("stockName"));

		try {
			TimeUnit.MINUTES.sleep(1);
		}
		catch (InterruptedException e) {
			logger.info(e.getLocalizedMessage());
		}
	}

}
