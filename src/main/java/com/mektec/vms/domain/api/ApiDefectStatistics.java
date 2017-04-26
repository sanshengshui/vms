package com.mektec.vms.domain.api;

import com.mektec.vms.domain.DefectStatistics;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "DefectStatistics")
public class ApiDefectStatistics {
    private String defectId;
    private Integer count;

    
   public ApiDefectStatistics(DefectStatistics defectStatistics){
       defectId=defectStatistics.getDefectId();
       count=defectStatistics.getCount();

   }





    public String getDefectId() {
        return defectId;
    }

    public void setDefectId(String defectId) {
        this.defectId = defectId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }





}
