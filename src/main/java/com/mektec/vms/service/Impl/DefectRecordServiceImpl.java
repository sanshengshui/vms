package com.mektec.vms.service.Impl;

import com.mektec.vms.domain.DefectRecord;
import com.mektec.vms.domain.api.ApiDefectRecordCount;
import com.mektec.vms.domain.api.ApiDefectStatistics;
import com.mektec.vms.mapper.DefectRecordMapper;
import com.mektec.vms.service.DefectRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
@Service
@Transactional
public class DefectRecordServiceImpl implements DefectRecordService{

    private DefectRecordMapper defectRecordMapper;

    @Autowired
    public void setDefectRecordMapper(DefectRecordMapper defectRecordMapper) {
        this.defectRecordMapper = defectRecordMapper;
    }

    @Override
    public void createRecord(DefectRecord record) {

        defectRecordMapper.createDefectRecord(record);
    }

    @Override
    public DefectRecord findRecordById(String recordId) {

        return defectRecordMapper.findRecordById(recordId);
    }

    @Override
    public List<DefectRecord> sumByStation(String stationId,String recordTime) {
        return defectRecordMapper.sumByStation(stationId,recordTime);
    }
    @Override
    public List<ApiDefectRecordCount> findDefectRecordSum(String stationId){
        return defectRecordMapper.findDefectRecordSum(stationId);
    }

    @Override
    public void updateRecord(DefectRecord record) {

        defectRecordMapper.updateRecord(record);
    }

    @Override
    public void deleteRecord(DefectRecord record) {

        defectRecordMapper.deleteRecord(record);
    }


    @Override
    public List<DefectRecord> findDefectRecordList(String terminalCode,String recordTime,int sum){
        return defectRecordMapper.findDefectRecordList(terminalCode,recordTime,sum);}


    @Override
    public List<DefectRecord> findAllRecord() {
         return defectRecordMapper.findAllRecord();
    }
}