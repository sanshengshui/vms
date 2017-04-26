package com.mektec.vms.view.TerminalUI;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

/**
 * Created by mektec on 16-4-29.
 */
public class AddTerminalDialogUI extends Window {

    public TextField terminalIdText;
    public TextField terminalCodeText;
    public TextField terminalNameText;
    public ComboBox terminalTypeText;
    public TextField stationIdText;
    public Button saveButton;

    public AddTerminalDialogUI() {
        super("添加新终端");
        center();
        setModal(true);
        this.setWidth(650, Unit.POINTS);
        this.setHeight(150, Unit.POINTS);
        this.setResizable(false);

        GridLayout layout = new GridLayout();
        HorizontalLayout gridLayout = new HorizontalLayout();
        gridLayout.setMargin(true);
        gridLayout.setSpacing(true);

        terminalIdText = new TextField("终端ID");
        terminalIdText.setWidth(80, Sizeable.Unit.POINTS);
        terminalIdText.setVisible(false);

        terminalCodeText = new TextField("终端编号");
        terminalCodeText.setWidth(80, Sizeable.Unit.POINTS);

        terminalNameText = new TextField("终端名称");
        terminalNameText.setWidth(80, Sizeable.Unit.POINTS);

        terminalTypeText = new ComboBox("终端类型");
        terminalTypeText.addItem("DISPLAY");
        terminalTypeText.addItem("INPUT");
        terminalTypeText.addItem("SINGLE");
        terminalTypeText.select("DISPLAY");
        terminalTypeText.setNullSelectionAllowed(false);
        terminalTypeText.setTextInputAllowed(false);
        terminalTypeText.setWidth(90, Sizeable.Unit.POINTS);

        stationIdText = new TextField("工站Id");
        stationIdText.setWidth(80, Sizeable.Unit.POINTS);
        stationIdText.setVisible(false);

        saveButton = new Button("保存", FontAwesome.SAVE);

        gridLayout.setSpacing(true);
        gridLayout.addComponent(terminalIdText);
        gridLayout.addComponent(terminalCodeText);
        gridLayout.addComponent(terminalNameText);
        gridLayout.addComponent(terminalTypeText);
        gridLayout.addComponent(stationIdText);
        gridLayout.addComponent(saveButton);
        gridLayout.setComponentAlignment(saveButton, Alignment.BOTTOM_LEFT);

        layout.addComponent(gridLayout);
        layout.setComponentAlignment(gridLayout,Alignment.MIDDLE_CENTER);

        this.setContent(layout);
    }
}
