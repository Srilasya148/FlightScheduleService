package com.cg.flight.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.flight.dto.ScheduleDto;
import com.cg.flight.exceptions.AirportException;
import com.cg.flight.exceptions.FlightException;
import com.cg.flight.service.FlightScheduleServiceImpl;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class FlightScheduleWebService {
	Logger logger = LoggerFactory.getLogger(FlightScheduleWebService.class);
	@Autowired
	private FlightScheduleServiceImpl flightService;
	
	@PostMapping("addflightschedule")
	public String addFlightSchedule(@RequestBody ScheduleDto schedule) throws 
	          AirportException, FlightException {
		logger.info(schedule.getFlightId());
		String scheduledFlightId = flightService.addFlightSchedule(schedule);
		return "Schedule is created and the scheduled Flight Id is" + scheduledFlightId;
		
	}
	
}
