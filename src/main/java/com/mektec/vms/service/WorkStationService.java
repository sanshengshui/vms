package com.mektec.vms.service;

import com.mektec.vms.domain.WorkStation;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
public interface WorkStationService {

    void createWorkStation(WorkStation workStation);
    WorkStation findWorkStationById(String stationId);
    WorkStation findWorkStationByCode(String stationCode);
    void updateWorkStation(WorkStation workStation);
    void deleteWorkStation(WorkStation workStation);
    List<WorkStation> findAllWorkStations();
    List<WorkStation> findWorkStationsByLineCode(String lineCode);
}
