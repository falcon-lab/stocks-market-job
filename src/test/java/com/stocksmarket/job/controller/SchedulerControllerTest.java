package com.stocksmarket.job.controller;

import com.stocksmarket.job.exception.JobSchedulerException;
import com.stocksmarket.job.model.ScheduleRequest;
import com.stocksmarket.job.service.SchedulerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class SchedulerControllerTest {

    @InjectMocks
    private SchedulerController schedulerController;

    private static final UUID TEST_UUID = UUID.fromString("5490695f-67f5-3b92-81e4-79d367e46c8a");

    @Mock
    private SchedulerService schedulerService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void handleShouldScheduleJobWhenScheduleRequestIsValid() throws JobSchedulerException {

        Mockito.when(schedulerService.handleJobSchedule(Mockito.any())).thenReturn(TEST_UUID);
        ResponseEntity response = schedulerController.registerSchedule(Mockito.mock(ScheduleRequest.class));
        Assert.assertEquals(201,response.getStatusCodeValue());
    }

    @Test
    public void handleShouldScheduleJobWhenScheduleRequestIsNotValid() throws JobSchedulerException {

        Mockito.doThrow(JobSchedulerException.class).when(schedulerService).handleJobSchedule(Mockito.any());
        ResponseEntity response = schedulerController.registerSchedule(Mockito.mock(ScheduleRequest.class));
        Assert.assertEquals(202,response.getStatusCodeValue());
    }
}