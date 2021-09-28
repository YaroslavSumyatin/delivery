package com.delivery.database.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CityTest {

    private City city;

    @Before
    public void initCity() {
        city = new City();
    }

    @Test
    public void testSetGetCityRegion() {
        String cityName = "Харків";
        String region = "Донецька";
        city.setName(cityName);
        city.setRegion(region);
        String expected = cityName + " " + region;
        String actual = city.getName() + " " + city.getRegion();
        assertEquals(expected, actual);
    }

}
