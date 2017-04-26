package com.mektec.vms.view.DefectConfigUI;

import com.mektec.vms.MyUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

/**
 * Created by mektec on 16-5-10.
 */
public class AddDefectDialogUI extends Window {

    public TextField defectIdText;
    public TextField baseIdText;
    public TextField defectNameText;
    public TextField shortcutText;

    public Button saveButton;
    MyUI ui;

    public AddDefectDialogUI(){
        super("添加新缺陷");
        center();
        setModal(true);
        this.setWidth(450, Unit.POINTS);
        this.setHeight(150, Unit.POINTS);
        this.setResizable(false);

        HorizontalLayout gridLayout = new HorizontalLayout();
        gridLayout.setMargin(true);
        gridLayout.setSpacing(true);

        defectIdText = new TextField("缺陷ID");
        defectIdText.setWidth(80, Sizeable.Unit.POINTS);
        defectIdText.setVisible(false);

//        baseIdText = new TextField("baseId");
//        baseIdText.setWidth(80, Sizeable.Unit.POINTS);
//        baseIdText.setVisible(false);

        defectNameText = new TextField("缺陷名称");
        defectNameText.setWidth(80, Sizeable.Unit.POINTS);

        shortcutText = new TextField("shortcut");
        shortcutText.setWidth(80, Sizeable.Unit.POINTS);

        saveButton = new Button("保存", FontAwesome.SAVE);

        gridLayout.setSpacing(true);
        gridLayout.addComponent(defectIdText);
        gridLayout.addComponent(defectNameText);
        gridLayout.addComponent(shortcutText);
//        gridLayout.addComponent(baseIdText);
        gridLayout.addComponent(saveButton);
        gridLayout.setComponentAlignment(saveButton, Alignment.BOTTOM_LEFT);

        this.setContent(gridLayout);
    }

}
