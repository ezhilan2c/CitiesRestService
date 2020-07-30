package com.city.controller;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.city.service.CityService;

@RestController
public class CityController {

	private CityService cityService;
	
	@Autowired
	public CityController(CityService cityService) {
		this.cityService=cityService;
	}
	
	/**
	 * FindCitiesConnected method is used to find if Origin and destination exist or not.
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	@GetMapping("connected")
	public String findCitiesConnected(@NotNull @PathParam(value = "origin") String origin, @NotNull @PathParam(value = "destination") String destination) {
		return cityService.findCitiesConnected(origin, destination);
		
	}
	
}
