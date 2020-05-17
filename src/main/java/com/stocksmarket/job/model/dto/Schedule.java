package com.stocksmarket.job.model.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "uuid", "startedAt", "duration", "state", "priority" })
public class Schedule implements ScheduleDTO {

	private UUID uuid;

	private long startedAt;

	private long duration;

	private String state;

	private int priority;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public long getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(long startedAt) {
		this.startedAt = startedAt;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
