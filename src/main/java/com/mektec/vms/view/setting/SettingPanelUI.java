package com.mektec.vms.view.setting;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;

public class SettingPanelUI extends GridLayout {

    public CheckBox chxEnableEmail;

    public CheckBox chxEnableSms;

    public CheckBox chxEnableSelfCheck;

    public SettingPanelUI() {
        super(1,3);

        chxEnableEmail = new CheckBox("启用邮件通知");
        chxEnableSms = new CheckBox("启用短信通知");
        chxEnableSelfCheck = new CheckBox("启用实时自检");
        addComponent(chxEnableEmail);
        addComponent(chxEnableSms);
        addComponent(chxEnableSelfCheck);
        this.setSpacing(true);
        this.setMargin(true);
    }
}
