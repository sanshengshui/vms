package com.mektec.vms.service.Impl;

import com.mektec.vms.domain.Defect;
import com.mektec.vms.mapper.DefectMapper;
import com.mektec.vms.service.DefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
@Service
@Transactional
public class DefectServiceImpl implements DefectService{

    private DefectMapper defectMapper;

    @Autowired
    public void setDefectMapper(DefectMapper defectMapper) {
        this.defectMapper = defectMapper;
    }

    @Override
    public void createDefect(Defect defect) {

        defectMapper.createDefect(defect);
    }

    @Override
    public Defect findDefectById(String defectId) {

        return defectMapper.findDefectById(defectId);
    }

    @Override
    public void updateDefect(Defect defect) {

        defectMapper.updateDefect(defect);
    }

    @Override
    public void deleteDefect(Defect defect) {

        defectMapper.deleteDefect(defect);
    }

    @Override
    public List<Defect> findDefectListByLine(String lineId) {

        return defectMapper.findDefectListByLine(lineId);
    }

    @Override
    public List<Defect> findDefectListByStationId(String stationId) {
        return defectMapper.findDefectListByStationId(stationId);
    }
}
