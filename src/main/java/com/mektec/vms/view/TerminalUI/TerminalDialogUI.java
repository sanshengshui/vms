package com.mektec.vms.view.TerminalUI;

import com.mektec.vms.MyUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

/**
 * Created by mektec on 16-4-27.
 */
public class TerminalDialogUI extends Window{

    public Panel settingPanel;

    public TextField stationIdText;
    public TextField stationCodeText;
    public TextField stationNameText;
    public TextField stationSnText;

    public Button saveButton;
    public Button updateButton;

    public Table itemTable;

    MyUI ui;

    public TerminalDialogUI() {
        super("终端信息");
        center();
        setModal(true);
        this.setWidth(550, Unit.POINTS);
        this.setHeight(300, Unit.POINTS);
        this.setResizable(false);

        Panel panel = new Panel();
        GridLayout layout = new GridLayout();
        GridLayout innerLayout = new GridLayout(1,2);
        layout.setMargin(true);
        layout.setSpacing(true);

        settingPanel = new Panel();

        GridLayout gridLayout = new GridLayout(7,1);

        stationIdText = new TextField("工站Id");
        stationIdText.setWidth(60, Sizeable.Unit.POINTS);
        stationIdText.setVisible(false);

        stationSnText = new TextField("工站序号");
        stationSnText.setWidth(60, Sizeable.Unit.POINTS);

        stationCodeText = new TextField("工站编号");
        stationCodeText.setWidth(80, Sizeable.Unit.POINTS);

        stationNameText = new TextField("工站名称");
        stationNameText.setWidth(80, Sizeable.Unit.POINTS);

        saveButton = new Button("保存", FontAwesome.SAVE);
        updateButton = new Button("更新",FontAwesome.SAVE);
        updateButton.setVisible(false);

        gridLayout.setSpacing(true);
        gridLayout.addComponent(stationIdText);
        gridLayout.addComponent(stationSnText);
        gridLayout.addComponent(stationCodeText);
        gridLayout.addComponent(stationNameText);
        gridLayout.addComponent(saveButton);
        gridLayout.addComponent(updateButton);
        gridLayout.setComponentAlignment(saveButton, Alignment.BOTTOM_LEFT);

        itemTable = new Table() ;
        itemTable.addContainerProperty("增 删", GridLayout.class, null);
        itemTable.addContainerProperty("终端编号", TextField.class, null);
        itemTable.addContainerProperty("终端名称", TextField.class, null);
        itemTable.addContainerProperty("终端类型", ComboBox.class, null);

        itemTable.setColumnAlignments(Table.Align.CENTER,
                Table.Align.CENTER, Table.Align.CENTER,
                Table.Align.CENTER);
        itemTable.setSelectable(true);
        itemTable.setImmediate(true);
        itemTable.setMultiSelect(false);
        itemTable.setNullSelectionAllowed(false);
        itemTable.setWidth(480,Unit.POINTS);
        itemTable.setHeight(160,Unit.POINTS);

        innerLayout.addComponent(gridLayout);
        innerLayout.addComponent(itemTable);
        innerLayout.setMargin(true);
        innerLayout.setSpacing(true);

        layout.addComponent(innerLayout);
        layout.setComponentAlignment(innerLayout,Alignment.MIDDLE_CENTER);
        layout.setMargin(true);

        panel.setContent(layout);
        this.setContent(panel);
    }

}
