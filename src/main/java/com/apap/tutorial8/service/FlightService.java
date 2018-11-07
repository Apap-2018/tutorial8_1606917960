package com.apap.tutorial8.service;

import com.apap.tutorial8.model.FlightModel;

public interface FlightService {
	void addFlight(FlightModel pilot);
	void deleteFlight(FlightModel flight);
	void updateFlight(FlightModel flight);
	void deleteFlightById(long Id);
	FlightModel getFlightDetailById(long Id);
}
