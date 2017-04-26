package com.mektec.vms.service.Impl;

import com.mektec.vms.domain.Location;
import com.mektec.vms.mapper.LocationMapper;
import com.mektec.vms.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tom on 16-4-6.
 */
@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private LocationMapper locationMapper;

    @Autowired
    public void setLocationMapper(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }

    @Override
    public void createLocation(Location location) {
        locationMapper.createLocation(location);
    }

    @Override
    public Location findLocationById(String locationId) {

        return locationMapper.findLocationById(locationId);
    }

    @Override
    public void updateLocation(Location location) {
        locationMapper.updateLocation(location);
    }

    @Override
    public void deleteLocation(Location location) {
        locationMapper.deleteLocation(location);
    }

    @Override
    public List<Location> findAllLocation() {

        return locationMapper.findAllLocation();
    }
}
