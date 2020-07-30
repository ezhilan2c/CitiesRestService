package com.city.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.city.service.CityService;
import com.city.service.CityServiceImpl;
import com.city.service.Commuter;

public class CityServiceImplTest {

	private CityService subject;
	
	@Mock
	private Commuter commuter;
	
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule(); 
	
	private Map<String, List<String>>  cityMap;
	
	@Before
	public void init() {
		subject = new CityServiceImpl(commuter);
	}
	
	@Test
	public void testFindCitiesConnected() {
		when(commuter.getCityMap()).thenReturn(getCityMap());
		assertEquals(subject.findCitiesConnected("Boston", "New York"), "YES");
		assertEquals(subject.findCitiesConnected("California", "New York"), "NO");
		assertEquals(subject.findCitiesConnected("Boston", "New York"), "YES");
		assertNotEquals(subject.findCitiesConnected("Boston", "Texas"), "YES");
		assertNotEquals(subject.findCitiesConnected("Newark", "Vermont"), "YES");
	}
	
	private Map<String, List<String>> getCityMap(){
		cityMap=new HashMap<>();
		List<String> list = new ArrayList<String>();
		list.add("New York");
		list.add("Newark");
		cityMap.put("Boston", list);
		list = new ArrayList<String>();
		list.add("Boston");
		cityMap.put("Newark", list);
		list = new ArrayList<String>();
		list.add("Boston");
		cityMap.put("New York", list);
	  return cityMap;
	}
}
