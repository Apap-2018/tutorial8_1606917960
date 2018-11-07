package com.apap.tutorial8.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial8.model.FlightModel;
import com.apap.tutorial8.model.PilotModel;
import com.apap.tutorial8.repository.FlightDB;
import com.apap.tutorial8.repository.PilotDB;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
	@Autowired
	private PilotDB pilotDb;
	
	@Autowired
	private FlightDB flightDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}

	@Override
	public void deletePilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		pilotDb.delete(pilot);
	}

	@Override
	public void updatePilot(PilotModel pilot) {
		PilotModel oldPilot = this.getPilotDetailByLicenseNumber(pilot.getLicenseNumber());
		oldPilot.setName(pilot.getName());
		oldPilot.setFlyHour(pilot.getFlyHour());
	}

	@Override
	public List<FlightModel> getListFlightbyLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		return flightDb.findByPilotLicenseNumber(licenseNumber);
	}
	
	
}
