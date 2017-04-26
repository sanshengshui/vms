package com.mektec.vms.view.Chart;

import com.mektec.vms.MyUI;
import com.mektec.vms.domain.Defect;
import com.mektec.vms.domain.Lot;
import com.mektec.vms.domain.ProductLine;
import com.mektec.vms.domain.WorkStation;
import com.mektec.vms.service.*;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by mektec on 16-6-1.
 */
public class PieChartConfigUI extends GridLayout {

    public ProductLineService productLineService;
    public DefectRecordService defectRecordService;
    public WorkStationService workStationService;
    public DefectService defectService;
    public LotService lotService;

    public OptionGroup optionGroup;
    public PopupDateField dateText;
    public Button leftButton;
    public Button rightButton;
    public Button ensureButton;

    public GridLayout chartLayout;

    public ProductLineTree productLineTree;
    public ProductLine line;

    MyUI ui;

    public PieChartConfigUI() {
        ui = (MyUI)UI.getCurrent();
        defectRecordService = (DefectRecordService) ui.getBean("defectRecordService");
        defectService = (DefectService) ui.getBean("defectService");
        productLineService = (ProductLineService) ui.getBean("productLineService");
        workStationService = (WorkStationService) ui.getBean("workStationService");
        lotService = (LotService) ui.getBean("lotService");

        Panel panel = new Panel();
        GridLayout layout = new GridLayout(2,1);

        //
        Panel rightPanel = new Panel();
        VerticalLayout rightGridLayout = new VerticalLayout();

        chartLayout = new GridLayout();
        chartLayout.setHeight(300,Unit.POINTS);

        Panel conditionLayout = new Panel();
        conditionLayout.setContent(selectCondition());

        rightGridLayout.addComponent(chartLayout);
        rightGridLayout.addComponent(conditionLayout);
        rightGridLayout.setComponentAlignment(chartLayout,Alignment.MIDDLE_CENTER);
        rightGridLayout.setSpacing(true);
        rightGridLayout.setMargin(true);

        rightPanel.setContent(rightGridLayout);
        rightPanel.setWidth(725,Unit.POINTS);
        rightPanel.setHeight(100,Unit.PERCENTAGE);

        layout.addComponent(lineTreePanel());
        layout.addComponent(rightPanel);
        layout.setMargin(true);
        layout.setSpacing(true);

        panel.setContent(layout);
        panel.setHeight(100,Unit.PERCENTAGE);
        setMargin(true);

        addComponent(panel);
    }

    public Panel lineTreePanel(){

        Panel panel = new Panel("产线");

        productLineTree = new ProductLineTree();
        ui = (MyUI) UI.getCurrent();
        line = ui.getLine();
        productLineTree.load();
        productLineTree.setCurrentLine(line);

        productLineTree.setSizeFull();

        panel.setContent(productLineTree);
        panel.setWidth(150,Unit.POINTS);
        panel.setHeight(408,Unit.POINTS);

        return panel;
    }

    public HorizontalLayout selectCondition() {

        HorizontalLayout timeLayout = new HorizontalLayout();
        HorizontalLayout radioButtonLayout = new HorizontalLayout();
        HorizontalLayout dateLayout = new HorizontalLayout();

        optionGroup = new OptionGroup();
        optionGroup.addItem("年");
        optionGroup.addItem("月");
        optionGroup.addItem("日");
        optionGroup.setNullSelectionAllowed(false);
        optionGroup.setMultiSelect(false);
        optionGroup.setPrimaryStyleName("v-select-option-horizontal");

        leftButton = new Button(FontAwesome.ARROW_CIRCLE_O_LEFT);
        leftButton.setWidth(50,Unit.POINTS);
        leftButton.addStyleName("my-color-green");

        dateText = new PopupDateField();
        dateText.setWidth(200, Unit.POINTS);

        rightButton = new Button(FontAwesome.ARROW_CIRCLE_O_RIGHT);
        rightButton.setWidth(50,Unit.POINTS);
        rightButton.addStyleName("my-color-green");

        ensureButton = new Button("图示");

        radioButtonLayout.addComponent(optionGroup);

        radioButtonLayout.setSpacing(true);
        radioButtonLayout.setMargin(true);

        dateLayout.addComponent(leftButton);
        dateLayout.addComponent(dateText);
        dateLayout.addComponent(rightButton);
        dateLayout.setMargin(true);

        timeLayout.addComponent(radioButtonLayout);
        timeLayout.addComponent(dateLayout);
        timeLayout.addComponent(ensureButton);
        timeLayout.setComponentAlignment(ensureButton,Alignment.MIDDLE_CENTER);
        timeLayout.setComponentAlignment(radioButtonLayout,Alignment.BOTTOM_LEFT);
        timeLayout.setComponentAlignment(dateLayout,Alignment.BOTTOM_LEFT);

        timeLayout.setSpacing(true);
        timeLayout.setMargin(true);

        return timeLayout;
    }

}
