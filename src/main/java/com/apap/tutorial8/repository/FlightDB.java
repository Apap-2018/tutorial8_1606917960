package com.apap.tutorial8.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial8.model.FlightModel;

@Repository
public interface FlightDB extends JpaRepository<FlightModel, Long> {
	FlightModel findByFlightNumber(String flightNumber);
	FlightModel findById(long id);
	List<FlightModel> findByPilotLicenseNumber(String licenseNumber);
	
}
