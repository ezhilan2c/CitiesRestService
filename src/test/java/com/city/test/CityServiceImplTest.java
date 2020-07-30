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
		assertEquals(subject.findCitiesConnected("BOSTON", "NEW YORK"), "YES");
		assertEquals(subject.findCitiesConnected("CALIFORNIA", "NEW YORK"), "NO");
		assertEquals(subject.findCitiesConnected("BOSTON", "NEW YORK"), "YES");
		assertNotEquals(subject.findCitiesConnected("BOSTON", "TEXAS"), "YES");
		assertNotEquals(subject.findCitiesConnected("NEWARK", "VERMONT"), "YES");
	}
	
	private Map<String, List<String>> getCityMap(){
		cityMap=new HashMap<>();
		List<String> list = new ArrayList<String>();
		list.add("NEW YORK");
		list.add("NEWARK");
		cityMap.put("BOSTON", list);
		list = new ArrayList<String>();
		list.add("BOSTON");
		cityMap.put("NEWARK", list);
		list = new ArrayList<String>();
		list.add("BOSTON");
		cityMap.put("NEW YORK", list);
	  return cityMap;
	}
}
