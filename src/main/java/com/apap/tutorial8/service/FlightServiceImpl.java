package com.apap.tutorial8.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial8.model.FlightModel;
import com.apap.tutorial8.repository.FlightDB;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}

	@Override
	public void deleteFlight(FlightModel flight) {
		flightDb.delete(flight);
	}

	@Override
	public void updateFlight(FlightModel flight) {
		FlightModel oldFlight = this.getFlightDetailById(flight.getId());
		oldFlight.setFlightNumber(flight.getFlightNumber());
		oldFlight.setDestination(flight.getDestination());
		oldFlight.setOrigin(flight.getOrigin());
		oldFlight.setTime(flight.getTime());
		
	}

	@Override
	public FlightModel getFlightDetailById(long Id) {
		// TODO Auto-generated method stub
		return flightDb.findById(Id);
	}

	@Override
	public void deleteFlightById(long Id) {
		// TODO Auto-generated method stub
		flightDb.deleteById(Id);
	}
	
	
}
