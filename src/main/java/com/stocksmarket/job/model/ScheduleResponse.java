package com.stocksmarket.job.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stocksmarket.job.model.dto.Schedule;

public class ScheduleResponse {

	@JsonProperty("data")
	private List<Schedule> data;

	public ScheduleResponse(List<Schedule> data) {
		this.data = data;
	}

	public List<Schedule> getData() {
		return data;
	}
}
