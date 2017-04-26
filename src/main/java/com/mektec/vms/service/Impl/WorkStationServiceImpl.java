package com.mektec.vms.service.Impl;

import com.mektec.vms.domain.WorkStation;
import com.mektec.vms.mapper.WorkStationMapper;
import com.mektec.vms.service.WorkStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
@Service
@Transactional
public class WorkStationServiceImpl implements WorkStationService {

    private WorkStationMapper workStationMapper;

    @Autowired
    public void setWorkStationMapper(WorkStationMapper workStationMapper) {
        this.workStationMapper = workStationMapper;
    }

    @Override
    public void createWorkStation(WorkStation workStation) {

        workStationMapper.createWorkStation(workStation);
    }

    @Override
    public WorkStation findWorkStationById(String stationId) {

        return workStationMapper.findWorkStationById(stationId);
    }

    @Override
    public WorkStation findWorkStationByCode(String stationCode) {
        return workStationMapper.findWorkStationByCode(stationCode);
    }

    @Override
    public void updateWorkStation(WorkStation workStation) {

        workStationMapper.updateWorkStation(workStation);
    }

    @Override
    public void deleteWorkStation(WorkStation workStation) {

        workStationMapper.deleteWorkStation(workStation);
    }

    @Override
    public List<WorkStation> findAllWorkStations() {

        return workStationMapper.findAllWorkStations();
    }

    @Override
    public List<WorkStation> findWorkStationsByLineCode(String lineCode) {

        return workStationMapper.findWorkStationsByLineCode(lineCode);
    }


}
