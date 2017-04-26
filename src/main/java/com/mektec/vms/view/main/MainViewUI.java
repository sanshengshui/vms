package com.mektec.vms.view.main;

import com.mektec.vms.view.Chart.PieChart;
import com.mektec.vms.view.WorkStationUI.WorkStationConfig;
import com.mektec.vms.view.login.Check;
import com.mektec.vms.view.login.Login;
import com.mektec.vms.view.setting.SettingPanel;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MainViewUI extends GridLayout {

    public LeftSideBar leftSideBar;
	public Toolbar toolbar;
	public AbsoluteLayout contentLayout;
    public GridLayout centerLayout;

	public MainViewUI() {
		super(2, 1);
		setSizeFull();
        leftSideBar = new LeftSideBar();
        addComponent(leftSideBar);
        setColumnExpandRatio(1, 100);

//        //登陆界面
//        Login login = new Login();
//        UI.getCurrent().addWindow(login);

        centerLayout = new GridLayout(1,2);

		toolbar = new Toolbar();
        centerLayout.addComponent(toolbar);

        centerLayout.setSizeFull();
        centerLayout.setRowExpandRatio(1, 100);

        leftSideMenuButtonClick();

        contentLayout = new AbsoluteLayout();
		contentLayout.addStyleName("my-content-layout");

        contentLayout.setSizeFull();

        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setSizeFull();
        vLayout.setMargin(true);
        vLayout.addComponent(contentLayout);
        centerLayout.addComponent(vLayout);

        addComponent(centerLayout);

	}

    public void leftSideMenuButtonClick(){
        leftSideBar.leftSideMenu.stationItem.addClickListener(e ->{

        });

        leftSideBar.leftSideMenu.terminalItem.addClickListener(e ->{

        });

        leftSideBar.leftSideMenu.userMgtItem.addClickListener(e ->{
            Check check = new Check();
            check.setSizeFull();
            centerLayout.removeAllComponents();
            centerLayout.addComponent(toolbar);
            centerLayout.addComponent(check);
        });

        leftSideBar.leftSideMenu.mapItem.addClickListener(e ->{

        });

        leftSideBar.leftSideMenu.chartItem.addClickListener(e ->{
            PieChart pieChart = new PieChart();
            pieChart.setSizeFull();
            centerLayout.removeAllComponents();
            centerLayout.addComponent(toolbar);
            centerLayout.addComponent(pieChart);
        });

        leftSideBar.leftSideMenu.settingItem.addClickListener(e ->{
            SettingPanel settingPanel = new SettingPanel();
            settingPanel.setSizeFull();
            centerLayout.removeAllComponents();
            centerLayout.addComponent(toolbar);
            centerLayout.addComponent(settingPanel);
        });

        //配置
        leftSideBar.leftSideMenu.configItem.addClickListener(e ->{
            WorkStationConfig workStationConfig = new WorkStationConfig();
            workStationConfig.setSizeFull();
            centerLayout.removeAllComponents();
            centerLayout.addComponent(toolbar);
            centerLayout.addComponent(workStationConfig);
        });

        leftSideBar.leftSideMenu.homeItem.addClickListener(e ->{

        });
    }
}
