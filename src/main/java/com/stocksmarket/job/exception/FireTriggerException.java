package com.stocksmarket.job.exception;

public class FireTriggerException extends JobSchedulerException {

	public FireTriggerException(String message) {
		super(message);
		logger.error(message);
	}

	public FireTriggerException(String message, String group, String name) {
		super(message);
		logger.error(message, group, name);
	}
}
