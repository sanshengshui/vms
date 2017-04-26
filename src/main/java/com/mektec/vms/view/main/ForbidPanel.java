package com.mektec.vms.view.main;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;


public class ForbidPanel extends Panel {
    public ForbidPanel() {
        setSizeFull();
        GridLayout contentLayout = new GridLayout(1,1);
        contentLayout.setSizeFull();

        Label infoLabel = new Label();
        infoLabel.setWidth(100, Unit.POINTS);
        infoLabel.setHeight(100, Unit.POINTS);
        infoLabel.setIcon(FontAwesome.LOCK);
        infoLabel.addStyleName("my-font-size-20");

        contentLayout.addComponent(infoLabel);

        contentLayout.setComponentAlignment(infoLabel, Alignment.MIDDLE_CENTER);

        setContent(contentLayout);
    }
}
