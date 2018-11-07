package com.apap.tutorial8.service;

import com.apap.tutorial8.model.PilotModel;

import java.util.List;

import com.apap.tutorial8.model.FlightModel;

public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	List<FlightModel> getListFlightbyLicenseNumber(String licenseNumber);
	void addPilot(PilotModel pilot);
	void deletePilot(PilotModel pilot);
	void updatePilot(PilotModel pilot);
}
