package com.mektec.vms.view.WorkStationUI;

import com.mektec.vms.MyUI;
import com.mektec.vms.domain.ProductLine;
import com.mektec.vms.service.ProductLineService;
import com.mektec.vms.service.WorkStationService;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by mektec on 16-4-26.
 */
public class WorkStationConfigUI extends GridLayout {

    private ProductLineService productLineService;
    private WorkStationService workStationService;
    private List<ProductLine> productLineList;

    public TextField productLineText;
    public TextField lineCodeText;
    public TextField locationText;

    public Button saveButton;
    public Button updateButton;

    public Table stationItemTable;
    public Table defectItemTable;

    public OptionGroup lineOptionGroup;

    MyUI ui;

    public WorkStationConfigUI() {
        ui = (MyUI)UI.getCurrent();
        productLineService = (ProductLineService) ui.getBean("productLineService");
        workStationService = (WorkStationService) ui.getBean("workStationService");

        Panel panel = new Panel();
        GridLayout layout = new GridLayout(3,1);

        layout.addComponent(linePanel());
        layout.addComponent(stationPanel());
        layout.addComponent(defectPanel());
        layout.setMargin(true);
        layout.setSpacing(true);

        panel.setContent(layout);
        panel.setHeight(100,Unit.PERCENTAGE);
        panel.setWidth(100,Unit.PERCENTAGE);
        setMargin(true);

        addComponent(panel);
    }

    public Panel linePanel(){

        Panel panel = new Panel("产线");

        productLineList = productLineService.findAllLine();

        lineOptionGroup = new OptionGroup();
        lineOptionGroup.setSizeFull();

        for(ProductLine productLine :productLineList) {
            lineOptionGroup.addItem(productLine.getLineCode());
        }

        panel.setWidth(80,Unit.POINTS);
        panel.setHeight(408,Unit.POINTS);
        setSizeFull();
        panel.setContent(lineOptionGroup);

        return panel;
    }

    public Panel stationPanel(){

        Panel panel = new Panel("产线");
        GridLayout layout = new GridLayout(1,2);

        GridLayout gridLayout = new GridLayout(5,1);
        productLineText = new TextField("产线");
        productLineText.setWidth(60,Unit.POINTS);

        lineCodeText = new TextField("产线编号");
        lineCodeText.setWidth(60,Unit.POINTS);

        locationText = new TextField("位置");
        locationText.setWidth(60,Unit.POINTS);

        saveButton = new Button("保存", FontAwesome.SAVE);
        updateButton =  new Button("更新", FontAwesome.SAVE);
        updateButton.setVisible(false);

        gridLayout.addComponent(productLineText);
        gridLayout.addComponent(lineCodeText);
        gridLayout.addComponent(locationText);
        gridLayout.addComponent(saveButton);
        gridLayout.setComponentAlignment(saveButton, Alignment.BOTTOM_CENTER);
        gridLayout.addComponent(updateButton);
        gridLayout.setComponentAlignment(updateButton, Alignment.BOTTOM_LEFT);
        gridLayout.setSpacing(true);

        stationItemTable = new Table() ;
        stationItemTable.addContainerProperty("增  删", GridLayout.class,null);
        stationItemTable.addContainerProperty("序号", TextField.class, null);
        stationItemTable.addContainerProperty("工站编号", TextField.class, null);
        stationItemTable.addContainerProperty("工站名称", TextField.class, null);
        stationItemTable.addContainerProperty("上下移操作", GridLayout.class, null);
        stationItemTable.addContainerProperty("终端列表", Button.class, null);

        stationItemTable.setColumnAlignments(Table.Align.CENTER,
                Table.Align.CENTER, Table.Align.CENTER,
                Table.Align.CENTER, Table.Align.CENTER,
                Table.Align.CENTER);

        stationItemTable.setSelectable(true);
        stationItemTable.setImmediate(true);
        stationItemTable.setMultiSelect(false);
        stationItemTable.setNullSelectionAllowed(false);
        stationItemTable.setHeight(300,Unit.POINTS);
        stationItemTable.setWidth(430,Unit.POINTS);

        layout.addComponent(gridLayout);
        layout.addComponent(stationItemTable);
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.setSizeFull();

        panel.setContent(layout);
        panel.setWidth(450,Unit.POINTS);
        panel.setHeight(100,Unit.PERCENTAGE);
        setMargin(true);
        setSizeFull();

        return panel;
    }

    public Panel defectPanel() {

        Panel panel = new Panel("不良项信息");
        GridLayout layout = new GridLayout(1,1);

        defectItemTable = new Table();
        defectItemTable.addContainerProperty("增  删", GridLayout.class, null);
        defectItemTable.addContainerProperty("缺陷ID", TextField.class, null);
        defectItemTable.addContainerProperty("缺陷名称", TextField.class, null);
        defectItemTable.addContainerProperty("快捷键", TextField.class, null);

        defectItemTable.setColumnAlignments(Table.Align.CENTER,
                Table.Align.CENTER, Table.Align.CENTER,
                Table.Align.CENTER);

        defectItemTable.setSelectable(true);
        defectItemTable.setImmediate(true);
        defectItemTable.setMultiSelect(false);
        defectItemTable.setNullSelectionAllowed(false);
        defectItemTable.setSizeFull();

        layout.addComponent(defectItemTable);
        layout.setMargin(true);
        layout.setSizeFull();

        panel.setContent(layout);
        panel.setHeight(100,Unit.PERCENTAGE);
        panel.setWidth(335,Unit.POINTS);
        setMargin(true);
        setSizeFull();

        return panel;
    }

    public void setProductLineService(ProductLineService productLineService) {
        this.productLineService = productLineService;
    }

    public void setWorkStationService(WorkStationService workStationService) {
        this.workStationService = workStationService;
    }
}
