package com.mektec.vms.service;

import com.mektec.vms.domain.Location;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
/**
 * Created by mektec on 16-4-8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.mektec.vms.config.AppConfigForTest.class})
public class LocationServiceTest {

    private SqlScriptService sqlScriptService;
    private LocationService locationService;

    @Before
    public void setUp() throws Exception {
        sqlScriptService.executeScript(SqlScriptService.DbType.H2DB, "create-tables.sql");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testLocation(){
        Location location = new Location();
        location.setLocationId("3232");
        location.setLocationName("aera222");

        locationService.createLocation(location);
        Location lo = locationService.findLocationById(location.getLocationId());
        assertNotNull(lo);

        locationService.findAllLocation();
        lo = locationService.findLocationById(location.getLocationId());
        assertNotNull(lo);

        locationService.updateLocation(location);
        lo = locationService.findLocationById(location.getLocationId());
        assertNotNull(lo);


        locationService.deleteLocation(location);
        lo = locationService.findLocationById(location.getLocationId());
        assertTrue(lo.isDeleted());
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }
    @Autowired
    public void setSqlScriptService(SqlScriptService sqlScriptService) {
        this.sqlScriptService = sqlScriptService;
    }

}
