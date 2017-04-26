package com.mektec.vms.view.login;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

/**
 * Created by mektec on 16-5-17.
 */
public class CheckUI extends GridLayout {

    public Table userItemTable;

    public CheckUI() {
        super(1, 1);

        addComponent(checkInfo());
    }

    public Panel checkInfo(){
        Panel panel = new Panel();

        GridLayout layout = new GridLayout(1,1);

        userItemTable = new Table() ;
        userItemTable.addContainerProperty("注册序号", TextField.class, null);
        userItemTable.addContainerProperty("帐号", TextField.class, null);
        userItemTable.addContainerProperty("姓名", TextField.class, null);
        userItemTable.addContainerProperty("密码", TextField.class, null);
        userItemTable.addContainerProperty("邮箱", TextField.class, null);
        userItemTable.addContainerProperty("电话", TextField.class, null);
        userItemTable.addContainerProperty("激活", Button.class, null);
        userItemTable.addContainerProperty("激活失败", Button.class, null);

        userItemTable.setColumnAlignments(Table.Align.CENTER,
                Table.Align.CENTER, Table.Align.CENTER,
                Table.Align.CENTER, Table.Align.CENTER,
                Table.Align.CENTER, Table.Align.CENTER,
                Table.Align.CENTER);
        userItemTable.setSelectable(true);
        userItemTable.setImmediate(true);
        userItemTable.setMultiSelect(false);
        userItemTable.setNullSelectionAllowed(false);

        layout.addComponent(userItemTable);
        layout.setComponentAlignment(userItemTable,Alignment.MIDDLE_CENTER);
        layout.setSizeFull();
        layout.setColumnExpandRatio(0,100);

        panel.setCaption("用户激活");
        panel.setContent(layout);

        return panel;
    }
}
