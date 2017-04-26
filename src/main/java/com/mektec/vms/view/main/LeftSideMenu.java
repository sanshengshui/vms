package com.mektec.vms.view.main;

import com.mektec.vms.view.TerminalUI.TerminalDialogUI;
import com.mektec.vms.view.WorkStationUI.WorkStationConfig;
import com.mektec.vms.view.login.Check;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;

import java.util.ArrayList;
import java.util.List;


public class LeftSideMenu extends GridLayout {
    public Button homeItem;
    public Button chartItem;
    public Button mapItem;

    public Button terminalItem;
    public Button configItem;
    public Button stationItem;
    public Button userMgtItem;

    public Button settingItem;

    private List<Button> items;
    //
    public Button selectedItem;

    public MainViewUI mainViewUI;

	public LeftSideMenu() {
        super(1, 10);
        items = new ArrayList<>();

		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);
        //主页面
        homeItem = new Button(FontAwesome.HOME);

        //地图
        mapItem = new Button(FontAwesome.MAP_MARKER);

        //产线
        chartItem = new Button(FontAwesome.BAR_CHART_O);

        //工站
        stationItem = new Button(FontAwesome.ENVELOPE_O);

        //终端
        terminalItem = new Button(FontAwesome.CROP);

        //配置
        configItem = new Button(FontAwesome.CUBE);

        //用户管理
        userMgtItem = new Button(FontAwesome.USERS);

        //系统设置
        settingItem = new Button(FontAwesome.COGS);

        //addItem(homeItem, "主页面");
        //addItem(mapItem, "产线信息");
        addItem(chartItem, "图表");
        //addItem(stationItem, "工站信息");
        //addItem(terminalItem, "终端信息");
        addItem(configItem, "配置");
        //addItem(userMgtItem, "用户管理");
        //addItem(settingItem, "系统设置");
        this.setRowExpandRatio(6, 100);
        this.addStyleName("my-side-left-side-menu");
        setSelectedItem(homeItem);

        homeItem.addClickListener(e ->{

        });

    }

    public void addItem(Button item, String description) {
        item.setPrimaryStyleName("my-side-menu-item");
        item.setWidth(100, Unit.PERCENTAGE);
        item.setDescription(description);
        this.addComponent(item);
        items.add(item);
        item.addClickListener(clickEvent -> {
            setSelectedItem(item);
        });
    }

    public void setSelectedItem(Button selectedItem) {
        if(this.selectedItem != null) {
            this.selectedItem.removeStyleName("my-side-menu-item-selected");
        }

        this.selectedItem = selectedItem;
        this.selectedItem.addStyleName("my-side-menu-item-selected");

    }

}
