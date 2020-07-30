package com.city.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.city.controller.CityController;
import com.city.service.CityService;

public class CityControllerTest {
	
	private CityController subject;

	@Mock
	private CityService cityService;
	
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule(); 
	
	@Before
	public void init() {
		subject = new CityController(cityService);
	}
	
	@Test
	public void testFindCitiesConnected() {
		when(cityService.findCitiesConnected(anyString(), anyString())).thenReturn("YES");
		assertEquals(subject.findCitiesConnected(anyString(), anyString()),"YES");
		when(cityService.findCitiesConnected(anyString(), anyString())).thenReturn("NO");
		assertNotEquals(subject.findCitiesConnected(anyString(), anyString()),"YES");
		assertEquals(subject.findCitiesConnected(anyString(), anyString()),"NO");
	}
}
