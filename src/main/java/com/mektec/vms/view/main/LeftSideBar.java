package com.mektec.vms.view.main;

import com.vaadin.ui.GridLayout;


public class LeftSideBar extends GridLayout {
    public LeftSideMenu leftSideMenu;
    public UserInfo userInfo;
    public LeftSideBar() {
        super(1, 2);
        setWidth(48, Unit.POINTS);
        setHeight(100, Unit.PERCENTAGE);
        userInfo = new UserInfo();
        this.addComponent(userInfo);


        leftSideMenu = new LeftSideMenu();

        this.addComponent(leftSideMenu);

        this.setRowExpandRatio(1,100);
        addStyleName("my-left-side-menu");
    }
}
