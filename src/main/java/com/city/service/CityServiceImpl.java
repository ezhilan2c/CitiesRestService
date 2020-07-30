package com.city.service;

import static com.city.constants.Constants.NO;
import static com.city.constants.Constants.YES;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService{
	
	private Commuter commuter;
	
	@Autowired
	public CityServiceImpl(Commuter commuter) {
		this.commuter=commuter;
	}

	/**
	 * This method is used to get the map of cities and look for the given origin and destination.
	 * If inputs are matching it returns 'YES' Otherwise 'NO'.
	 */
	@Override
	public String findCitiesConnected(String origin, String destination) {
		List<String> sourceList = new ArrayList<String>();
		List<String> destinationList = new ArrayList<String>();
		
		if(commuter.getCityMap().get(origin.trim())!=null) {
			sourceList = new ArrayList<String>(commuter.getCityMap().get(origin.trim()));
		}
		
		if(commuter.getCityMap().get(destination.trim())!=null) {
			destinationList = new ArrayList<String>(commuter.getCityMap().get(destination.trim()));
		}
		
		if(sourceList.contains(destination.trim()) || sourceList.retainAll(destinationList) && sourceList.size()>0) {
    		return YES;
    	}
        
		return NO;
	}
	

}
