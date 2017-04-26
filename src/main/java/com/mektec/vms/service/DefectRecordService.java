package com.mektec.vms.service;

import com.mektec.vms.domain.DefectRecord;
import com.mektec.vms.domain.api.ApiDefectRecordCount;
import com.mektec.vms.domain.api.ApiDefectStatistics;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
public interface DefectRecordService {

    void createRecord(DefectRecord record);
    DefectRecord findRecordById(String recordId);
    List<DefectRecord> sumByStation(String stationId,String recordTime);
    void updateRecord(DefectRecord record);
    void deleteRecord(DefectRecord record);
    List<ApiDefectRecordCount> findDefectRecordSum(String stationId);


    List<DefectRecord> findDefectRecordList(String terminalCode,String recordTime,int sum);
    List<DefectRecord> findAllRecord();
}
