package com.mektec.vms.view.main;

import com.mektec.vms.view.main.Logo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;

@SuppressWarnings("serial")
public class Toolbar extends GridLayout {
	public Logo logo;
	public Button userButton;
	public Toolbar() {
		super(3,1);
		addStyleName("my-toolbar");

		this.setWidth(100, Unit.PERCENTAGE);
        this.setHeight(49, Unit.POINTS);
		logo = new Logo();

		addComponent(logo);

        userButton = new Button();
        userButton.setWidth(100, Unit.POINTS);
        userButton.setPrimaryStyleName("link");

        setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
		setColumnExpandRatio(1,100);

	}
}
