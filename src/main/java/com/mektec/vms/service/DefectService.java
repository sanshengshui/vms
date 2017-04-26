package com.mektec.vms.service;

import com.mektec.vms.domain.Defect;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
public interface DefectService {

    //基本的CRUD
    void createDefect(Defect defect);
    Defect findDefectById(String defectId);
    void updateDefect(Defect defect);
    void deleteDefect(Defect defect);

    //根据产线ID获取缺陷列表
    List<Defect> findDefectListByLine(String lineId);

    //根据stationId获取缺陷列表
    List<Defect> findDefectListByStationId(String stationId);
}
