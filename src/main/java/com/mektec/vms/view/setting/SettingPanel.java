package com.mektec.vms.view.setting;


import com.mektec.vms.MyUI;
import com.mektec.vms.service.SettingService;
import com.vaadin.ui.UI;


public class SettingPanel extends SettingPanelUI {
    final String ENABLE_CHECK = "ENABLE_CHECK";
    final String ENABLE_EMAIL = "ENABLE_EMAIL";
    final String ENABLE_SMS = "ENABLE_SMS";

    private SettingService settingService;
    private MyUI ui;
    public SettingPanel() {


        ui = (MyUI) UI.getCurrent();

        settingService = (SettingService)ui.getBean("settingService");
        chxEnableEmail.setValue(settingService.getLocalValue(ENABLE_EMAIL, false));
        chxEnableSms.setValue(settingService.getLocalValue(ENABLE_SMS, false));
        chxEnableSelfCheck.setValue(settingService.getLocalValue(ENABLE_CHECK, false));

        buildEvents();
    }

    private void buildEvents() {
        chxEnableEmail.addValueChangeListener(event -> {
            settingService.setLocalValue (ENABLE_EMAIL,
                    (Boolean)event.getProperty().getValue());
        });

        chxEnableSms.addValueChangeListener(event -> {
            settingService.setLocalValue (ENABLE_SMS,
                    (Boolean)event.getProperty().getValue());
        });

        chxEnableSelfCheck.addValueChangeListener(event -> {
            settingService.setLocalValue (ENABLE_CHECK,
                    (Boolean)event.getProperty().getValue());
        });
    }
}
