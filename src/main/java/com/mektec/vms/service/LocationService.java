package com.mektec.vms.service;

import com.mektec.vms.domain.Location;

import java.util.List;



public interface LocationService {

    //基本CRUD
    void createLocation(Location location);
    Location findLocationById(String locationId);
    void updateLocation(Location location);
    void deleteLocation(Location location);

    //获取所有位置
    List<Location> findAllLocation();
}
