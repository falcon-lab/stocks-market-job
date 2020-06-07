package com.stocksmarket.job.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stocksmarket.job.exception.JobSchedulerException;
import com.stocksmarket.job.model.ScheduleRequest;
import com.stocksmarket.job.model.ScheduleResponse;
import com.stocksmarket.job.service.SchedulerService;

@RestController
@Validated
@RequestMapping(SchedulerController.PATH)
public class SchedulerController {

	public static final String PATH = "scheduler/v1";
	public static final String RESOURCE_JOB = "/job";

	@Autowired
	private SchedulerService schedulerService;

	@ResponseBody
	@PostMapping(value = RESOURCE_JOB, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity registerSchedule(@RequestBody ScheduleRequest request) {
		try {
			UUID jobUUID = schedulerService.handleJobSchedule(request);
			String resource = PATH + RESOURCE_JOB + "/" + jobUUID;
			
			return ResponseEntity.created(URI.create(resource)).build();
		}
		catch (JobSchedulerException e) {
			return ResponseEntity.accepted().body(e.getLocalizedMessage());

		}
	}

	@ResponseBody
	@GetMapping(value = RESOURCE_JOB, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity retrieveSchedules() {
		try {
			ScheduleResponse response = schedulerService.handleRetrieveSchedules();
			return ResponseEntity.ok(response);
		}
		catch (JobSchedulerException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
