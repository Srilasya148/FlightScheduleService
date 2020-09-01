package com.cg.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.flight.dao.AirportDao;
import com.cg.flight.dao.FlightDao;
import com.cg.flight.dao.ScheduledFlightDao;
import com.cg.flight.dto.ScheduleDto;
import com.cg.flight.entity.Airport;
import com.cg.flight.entity.Flight;
import com.cg.flight.entity.ScheduledFlight;
import com.cg.flight.exceptions.AirportException;
import com.cg.flight.exceptions.FlightException;
import com.cg.flight.util.ScheduleConstants;

@Service("myservice")
@Transactional
public class FlightScheduleServiceImpl implements FlightScheduleService {

	@Autowired
	private FlightDao flightDao;
	@Autowired
	private AirportDao airportDao;
	@Autowired
	private ScheduledFlightDao scheduledflightDao;

	@Override
	public String addFlightSchedule(ScheduleDto schedule)
			throws AirportException, FlightException {
	
			String scheduledFlightId = schedule.getFlightId() + schedule.getArrivalTime();

			ScheduledFlight scheduledflight = new ScheduledFlight();
			scheduledflight.setScheduledFlightId(scheduledFlightId);

			scheduledflight.setAvailableSeats(schedule.getAvailableSeats());
			scheduledflight.setArrivalTime(schedule.getArrivalTime());
			scheduledflight.setMinutes(schedule.getMinutes());
			scheduledflight.setDepartureTime(schedule.getDepartureTime());
			scheduledflight.setScheduleStatus(schedule.getScheduleStatus());
			scheduledflight.setFare(schedule.getFare());
			Airport srcairport = null;
			srcairport = airportDao.getAirport(schedule.getSrcAirport());
			if (srcairport == null)
				throw new AirportException(ScheduleConstants.AIRPORT_NOT_AVAILABLE);
			Airport dstairport = null;
			dstairport = airportDao.getAirport(schedule.getDstAirport());
			if (dstairport == null)
				throw new AirportException(ScheduleConstants.AIRPORT_NOT_AVAILABLE);
			Flight flight = null;
			flight = flightDao.getFlight(schedule.getFlightId());
			if (flight == null)
				throw new FlightException(ScheduleConstants.FLIGHT_NOT_AVAILABLE);
			scheduledflight.setSourceAirport(srcairport);
			scheduledflight.setDestinationAirport(dstairport);
			scheduledflight.setFlight(flight);
			ScheduledFlight scheduledFlight = scheduledflightDao.save(scheduledflight);

			return scheduledFlight.getScheduledFlightId();
	
	}

	
		
}