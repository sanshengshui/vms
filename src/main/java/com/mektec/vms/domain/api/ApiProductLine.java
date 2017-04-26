package com.mektec.vms.domain.api;

import com.mektec.vms.domain.ProductLine;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;


@XmlRootElement(name = "ProductLine")
public class ApiProductLine {

    private String lineId;
    private String lineCode;
    private String defectVersion;

    public static ApiProductLine fromProducLine(ProductLine productLine) {
        ApiProductLine apiProductLine = new ApiProductLine();
        apiProductLine.setLineId(productLine.getLineId());
        apiProductLine.setLineCode(productLine.getLineCode());
        apiProductLine.setDefectVersion(productLine.getDefectVersion());

        return apiProductLine;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getDefectVersion() {
        return defectVersion;
    }

    public void setDefectVersion(String defectVersion) {
        this.defectVersion = defectVersion;
    }
}
