package com.mektec.vms.domain.api;

import com.mektec.vms.domain.ProductLine;
import com.mektec.vms.domain.WorkStation;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "WorkStation")
public class ApiWorkStation {
    private String stationId;
    private String stationName;
    private String lotNum;
    private String modelNum;
    private ApiProductLine productLine;


    public ApiWorkStation() {
        productLine = new ApiProductLine();
    }

    public static ApiWorkStation fromWorkStation(WorkStation workStation) {
        ApiWorkStation apiWorkStation = new ApiWorkStation();

        apiWorkStation.setStationId(workStation.getStationId());
        apiWorkStation.setStationName(workStation.getStationName());
        apiWorkStation.setLotNum(workStation.getLotNum());

        apiWorkStation.setProductLine(ApiProductLine.
                fromProducLine(workStation.getProductLine()));

        return apiWorkStation;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public String getModelNum() {
        return modelNum;
    }

    public void setModelNum(String modelNum) {
        this.modelNum = modelNum;
    }

    public ApiProductLine getProductLine() {
        return productLine;
    }

    public void setProductLine(ApiProductLine productLine) {
        this.productLine = productLine;
    }
}
