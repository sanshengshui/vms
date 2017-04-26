package com.mektec.vms.view.Chart;

import com.mektec.vms.MyUI;
import com.mektec.vms.domain.ProductLine;
import com.mektec.vms.domain.WorkStation;
import com.mektec.vms.service.ProductLineService;
import com.mektec.vms.service.WorkStationService;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mektec on 16-6-21.
 */
public class ProductLineTree extends Tree {

    private ProductLineService productLineService;
    private WorkStationService workStationService;

    private List<ProductLine> lineList;
    private List<WorkStation> stations ;

    private ProductLine currentLine;

    public ProductLineTree() {
        ui = (MyUI) UI.getCurrent();
        setNullSelectionAllowed(false);
    }

    private MyUI ui;

    public void load() {

        ui = (MyUI)UI.getCurrent();

        productLineService = (ProductLineService) ui.getBean("productLineService");
        workStationService = (WorkStationService) ui.getBean("workStationService");

        lineList = productLineService.findAllLine();

        for(ProductLine line :lineList) {

            addItem(line.getLineCode());

            stations = new ArrayList<>();
            stations = workStationService.findWorkStationsByLineCode(line.getLineCode());

            for (WorkStation ws : stations) {

                addItem(ws.getStationCode()+":"+ws.getStationName());
                setParent(ws.getStationCode()+":"+ws.getStationName(), line.getLineCode());
                setChildrenAllowed(ws.getStationCode()+":"+ws.getStationName(), false);
            }
        }

    }

    public void setProductLineService(ProductLineService productLineService) {
        this.productLineService = productLineService;
    }

    public void setWorkStationService(WorkStationService workStationService) {
        this.workStationService = workStationService;
    }

    public ProductLine getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(ProductLine currentLine) {
        this.currentLine = currentLine;

        if(currentLine == null) {
            return;
        }
        for(ProductLine line:lineList) {
            if(line.getLineId().equals(currentLine)) {
                this.select(line);
            }
        }
    }
}
