package com.stocksmarket.job.model.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.stocksmarket.job.model.entity.QrtzFiredTriggers;

@Component
public class ScheduleTransformation {

	public List<Schedule> transformToScheduleList(List<QrtzFiredTriggers> qrtzFiredTriggersList) {
		List<Schedule> scheduleList = new ArrayList();

		for (QrtzFiredTriggers firedTriggers : qrtzFiredTriggersList) {
			scheduleList.add(transformToSchedule(firedTriggers));
		}

		return scheduleList;
	}

	public Schedule transformToSchedule(QrtzFiredTriggers qrtzFiredTriggers) {
		Schedule schedule = new Schedule();
		schedule.setUuid(transformStringToUUID(qrtzFiredTriggers.getTriggerName()));
		schedule.setStartedAt(transformStringToLong(qrtzFiredTriggers.getFiredTime()));
		schedule.setDuration(convertDurationToLong(qrtzFiredTriggers.getFiredTime()));
		schedule.setPriority(transformStringToInt(qrtzFiredTriggers.getPriority()));
		schedule.setState(qrtzFiredTriggers.getState());
		return schedule;
	}

	/**
	 * @param uuid
	 * @return
	 */
	private UUID transformStringToUUID(String uuid) {
		return UUID.fromString(uuid);
	}

	/**
	 * @param date
	 * @return
	 */
	private long convertDurationToLong(String date) {
		long timeInMillis = Calendar.getInstance().getTimeInMillis();
		return timeInMillis - transformStringToLong(date);
	}

	/**
	 * @param s
	 * @return
	 */
	private int transformStringToInt(String s) {
		return Integer.parseInt(s);
	}

	/**
	 * @param date
	 * @return
	 */
	private long transformStringToLong(String date) {
		return Long.parseLong(date);
	}

}
