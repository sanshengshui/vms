package com.mektec.vms.view.main;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class Logo extends GridLayout {
	public Logo() {
		setWidth(200, Unit.POINTS);
		setHeight(48, Unit.POINTS);

		Label company = new Label("产品不良项统计系统");
		company.setPrimaryStyleName("my-logo");
		addComponent(company);
		this.setMargin(new MarginInfo(10));
		this.setComponentAlignment(company, Alignment.MIDDLE_CENTER);
		
	}
}
