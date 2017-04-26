package com.mektec.vms.view.WorkStationUI;

import com.mektec.common.UID;
import com.mektec.vms.domain.ProductLine;
import com.mektec.vms.domain.WorkStation;

/**
 * Created by mektec on 16-4-29.
 */
public class AddWorkStationDialog extends AddWorkStationDialogUI {

    public AddWorkStationDialog() {

    }
    public void setStationInfo(WorkStation workStation){

        ProductLine productLine = new ProductLine();
        productLine.setLineId(lineIdText.getValue());

        workStation.setStationId(UID.generate());
        workStation.setStationCode(stationCodeText.getValue());
        workStation.setStationName(stationNameText.getValue());

        workStation.setLotNum(lotNumText.getValue());
        workStation.setDescription(descriptionText.getValue());
        workStation.setProductLine(productLine);
    }
}
