package com.city.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Commuter {
	
	private final static Logger LOGGER =  
            Logger.getLogger(Commuter.class.getName()); 
	
	@Value("${data.file}")
	private String cityFilePath;
	
	private Map<String, List<String>>  cityMap = new HashMap<>();
	
	/**
	 * This post construct method reads and loads all the connected city listed from the txt file.
	 */
	@PostConstruct
	private void loadAllCityData() {
		try (Stream<String> stream = Files.lines(Paths.get(cityFilePath))) {
            stream.forEach(cities -> {
	            String[] city = cities.split(",");
	            String origin = city[0].toUpperCase().trim();
	            String destination = city[1].toUpperCase().trim();
            
	            constructCity(origin, destination);
                constructCity(destination, origin);
            });
        } catch (IOException ioException) {
        	LOGGER.log(Level.SEVERE,ioException.getMessage());
        }
	}

	/**
	 * Constructs CityMap which holds the connected cites.
	 * 
	 * @param origin
	 * @param destination
	 */
	private void constructCity(String origin, String destination) {
		
		if(cityMap.get(origin) == null) {
		    List<String> oiginList = new ArrayList<>();
		    oiginList.add(destination);
		    cityMap.put(origin, oiginList);
		} else {
		    cityMap.get(origin).add(destination);
		}
	}
	
	public Map<String, List<String>> getCityMap() {
		return cityMap;
	}
}
