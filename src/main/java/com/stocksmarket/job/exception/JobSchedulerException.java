package com.stocksmarket.job.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobSchedulerException extends Exception {

	public static final String JOB_WRONG_CALL = "Not able to create a job";
	public static final String JOB_ALREADY_RUNNING = "Job is still running";
	public static final String NO_DATA = "No data";

	protected Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public JobSchedulerException(String message) {
		super(message);
	}
}
