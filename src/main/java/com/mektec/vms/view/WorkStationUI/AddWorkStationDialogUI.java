package com.mektec.vms.view.WorkStationUI;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

/**
 * Created by mektec on 16-4-29.
 */
public class AddWorkStationDialogUI extends Window {

    public TextField stationIdText;
    public TextField stationSnText;
    public TextField stationCodeText;
    public TextField stationNameText;
    public TextField lotNumText;
    public TextField descriptionText;
    public TextField lineIdText;
    public Button saveButton;

    public AddWorkStationDialogUI() {
        super("添加新工站");
        center();
        setModal(true);
        this.setWidth(650, Unit.POINTS);
        this.setHeight(150, Unit.POINTS);
        this.setResizable(false);

        HorizontalLayout gridLayout = new HorizontalLayout();
        gridLayout.setMargin(true);
        gridLayout.setSpacing(true);

        stationIdText = new TextField("工站ID");
        stationIdText.setWidth(70, Sizeable.Unit.POINTS);
        stationIdText.setVisible(false);

        stationSnText = new TextField("工站序号");
        stationSnText.setWidth(70, Sizeable.Unit.POINTS);
        stationSnText.setVisible(false);

        stationCodeText = new TextField("工站编号");
        stationCodeText.setWidth(70, Sizeable.Unit.POINTS);

        stationNameText = new TextField("工站名称");
        stationNameText.setWidth(70, Sizeable.Unit.POINTS);

        lotNumText = new TextField("LotNum");
        lotNumText.setWidth(70, Sizeable.Unit.POINTS);

        descriptionText = new TextField("Description");
        descriptionText.setWidth(70, Sizeable.Unit.POINTS);

        lineIdText = new TextField("LineId");
        lineIdText.setWidth(70, Sizeable.Unit.POINTS);
        lineIdText.setVisible(false);

        saveButton = new Button("保存", FontAwesome.SAVE);

        gridLayout.setSpacing(true);
        gridLayout.addComponent(stationIdText);
        gridLayout.addComponent(stationSnText);
        gridLayout.addComponent(stationCodeText);
        gridLayout.addComponent(stationNameText);
        gridLayout.addComponent(lotNumText);
        gridLayout.addComponent(descriptionText);
        gridLayout.addComponent(lineIdText);
        gridLayout.addComponent(saveButton);
        gridLayout.setComponentAlignment(saveButton, Alignment.BOTTOM_LEFT);

        this.setContent(gridLayout);
    }

}
