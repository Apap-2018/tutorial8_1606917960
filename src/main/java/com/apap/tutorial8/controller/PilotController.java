package com.apap.tutorial8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial8.model.FlightModel;
import com.apap.tutorial8.model.PilotModel;
import com.apap.tutorial8.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
		
	@RequestMapping("/index")
	private String index() {
		return "index";
		
	}
	
	@RequestMapping(value="/pilot/add", method=RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		model.addAttribute("title", "Tambah Pilot");
		return "addPilot";
	}
	
	@RequestMapping(value="/pilot/add", method=RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot, Model model) {
		pilotService.addPilot(pilot);
		model.addAttribute("title", "Tambah");
		return "add";
	}
	
//	@RequestMapping(value="/pilot/view", method=RequestMethod.GET)
//	private String viewPilot(@RequestParam("licenseNumber") String licenseNumber, Model model) {
//		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
//		model.addAttribute("pilot", pilot);
//		
//		List<FlightModel> flightList = pilot.getPilotFlight();
//		model.addAttribute("flightList", flightList);
//		
//		return "view-pilot";
//	}
	
	@RequestMapping(value="/pilot/view", method=RequestMethod.GET)
	private String viewPilot(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("title", "View Pilot");
		model.addAttribute("pilot", pilot);
		return "view-pilot";
	}
	
	@RequestMapping(value="/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
	private String deletePilot(@PathVariable(value="licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		pilotService.deletePilot(pilot);
		model.addAttribute("title", "Hapus");
		return "delete";
	}
	
	@RequestMapping(value="/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	private String updatePilot(@PathVariable(value="licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", pilot);
		model.addAttribute("newPilot", new PilotModel());
		model.addAttribute("title", "Update Pilot");
		return "update-pilot";
	}
	
	@RequestMapping(value="/pilot/update", method = RequestMethod.POST)
	private String updatePilotSubmit(@ModelAttribute PilotModel pilot, Model model) {
		pilotService.updatePilot(pilot);
		model.addAttribute("title", "Update");
		return "update";
	}
		
}
