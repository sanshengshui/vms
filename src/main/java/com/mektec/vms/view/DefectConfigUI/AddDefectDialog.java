package com.mektec.vms.view.DefectConfigUI;

import com.mektec.common.UID;
import com.mektec.vms.MyUI;
import com.mektec.vms.domain.Defect;
import com.mektec.vms.domain.WorkStation;
import com.mektec.vms.service.WorkStationService;
import com.vaadin.ui.UI;

/**
 * Created by mektec on 16-5-10.
 */
public class AddDefectDialog extends AddDefectDialogUI {

    private WorkStationService workStationService;
    public AddDefectDialog() {
    }

    public void setDefectDialog(Defect defect){
        ui = (MyUI) UI.getCurrent();
        workStationService = (WorkStationService) ui.getBean("workStationService");

        defect.setDefectId(UID.generate());

        defect.setDefectName(defectNameText.getValue());
        defect.setShortcut(shortcutText.getValue());
//        defect.setBaseId(baseIdText.getValue());
        defect.setDeleted(false);
    }
}
