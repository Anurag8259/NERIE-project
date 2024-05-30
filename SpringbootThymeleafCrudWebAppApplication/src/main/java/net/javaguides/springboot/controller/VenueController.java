package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.*;
import net.javaguides.springboot.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class VenueController {

	@Autowired
	private VenueRepository venueRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private OfficeRepository officeRepository;


	@GetMapping("/get-districts")
	public String getDistrictsByState(@RequestParam("stateCode") String stateCode, Model model) {
		m_states state = stateRepository.findByStateCode(stateCode);
		if (state != null) {
			System.out.println(state.getStateCode());
			System.out.println(state.getStateName());

			List<m_districts> districts = districtRepository.findByState_StateCode(stateCode);
			System.out.println(districts);
			model.addAttribute("districts", districts);
		} else {
			System.out.println("State not found for code: " + stateCode);
			model.addAttribute("districts", List.of());//Add empty list to model if state not found
		}
		return "fragments/districts :: districtsOptions"; // return the fragment HTML template name
	}

	@GetMapping("/add-venues")
	public String showVenueForm(Model model) {
		List<m_offices> offices = officeRepository.findAll(); // Fetch offices from the repository
		model.addAttribute("offices", offices);
		List<m_states> states = stateRepository.findAll();
		model.addAttribute("states", states);
		List<m_districts> districts=districtRepository.findAll();
		model.addAttribute("districts",districts);
		return "add_venues_form"; // return the HTML template name
	}

	@PostMapping("/add-venues")
	public String venueMap(@RequestParam("venueCode") String venueCode,
						   @RequestParam("venueName") String venueName,
						   @RequestParam("stateCode") String stateCode,
						   @RequestParam("districtCode") String districtCode,
						   @RequestParam("officeCode") String officeCode) {
		m_venues venue = new m_venues();
		venue.setVenueCode(venueCode);
		venue.setVenueName(venueName);
		m_states state = stateRepository.findByStateCode(stateCode);
		if (state != null) {
			venue.setState(state);

			m_districts district = districtRepository.findByDistrictCode(districtCode);
			if (district != null) {
				venue.setDistrict(district);

				m_offices office = officeRepository.findByOfficeCode(officeCode);
				if (office != null) {
					venue.setOffice(office);
					venueRepository.save(venue);
				} else {
					System.out.println("Office not found for code: " + officeCode);
				}
			} else {
				System.out.println("District not found for code: " + districtCode);
			}
		} else {
			System.out.println("State not found for code: " + stateCode);
		}

		return "redirect:/initialization";
	}
}