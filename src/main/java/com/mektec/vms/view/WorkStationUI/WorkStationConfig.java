package com.mektec.vms.view.WorkStationUI;

import com.mektec.vms.MyUI;
import com.mektec.vms.domain.*;
import com.mektec.vms.service.*;
import com.mektec.vms.view.DefectConfigUI.AddDefectDialog;
import com.mektec.vms.view.TerminalUI.TerminalDialog;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.SynchronizedRequestHandler;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by mektec on 16-4-27.
 */
public class WorkStationConfig extends WorkStationConfigUI {

    private ProductLineService productLineService;
    private WorkStationService workStationService;
    private TerminalService terminalService;
    private DefectService defectService;
    private LocationService locationService;

    private String lineCode;

    public List<Terminal> terminalList;
    public String workStationText;

    public WorkStationConfig() {

        ui = (MyUI)UI.getCurrent();
        productLineService = (ProductLineService) ui.getBean("productLineService");
        workStationService = (WorkStationService) ui.getBean("workStationService");
        terminalService = (TerminalService) ui.getBean("terminalService");
        defectService = (DefectService) ui.getBean("defectService");
        locationService = (LocationService) ui.getBean("locationService");

        lineOptionGroup.addValueChangeListener(event ->{

        stationItemTable.removeAllItems();
        defectItemTable.removeAllItems();

        lineCode = (String)event.getProperty().getValue();

        ProductLine productLine =
                productLineService.findLineByLineCode(lineCode);

        productLineText.setValue(productLine.getLineId());
        lineCodeText.setValue(productLine.getLineCode());
        locationText.setValue(String.valueOf(productLine.getCreateTime()));

        List<WorkStation> workStationList = workStationService.
                findWorkStationsByLineCode(lineCode);
        for(WorkStation workStation : workStationList){

            workStationText = workStation.getStationId();
            addStationRow(workStation);
        }
        });

        buildEvents();
    }

    public void addStationRow(WorkStation workStation){

        List<Defect> defectList = defectService.findDefectListByStationId(workStation.getStationId());

        List<WorkStation> workStationList = workStationService.
                findWorkStationsByLineCode(lineCodeText.getValue());

        //增删操作
        GridLayout commandLayout = new GridLayout(2,1);
        commandLayout.setWidth(40, Unit.POINTS);

        //增
        Button newRowButton = new Button (FontAwesome.PLUS);
        newRowButton.setPrimaryStyleName("v-label");
        newRowButton.addStyleName("my-color-green");
        newRowButton.addClickListener(event -> {

            AddWorkStationDialog addWorkStationDialog = new AddWorkStationDialog();
            UI.getCurrent().addWindow(addWorkStationDialog);

            addWorkStationDialog.saveButton.addClickListener(e ->{

                //创建新的workstation
                WorkStation ws = new WorkStation();

                //设置一些基本信息
                addWorkStationDialog.setStationInfo(ws);

                //设置当前的LineId与Sn
                addWorkStationDialog.lineIdText.setValue(productLineText.getValue());
                ws.setSn(workStationList.size()+1);

                boolean flag = true;
                if (ws.getStationCode().isEmpty() ||ws.getStationName().isEmpty()
                        || ws.getLotNum().isEmpty() ||ws.getDescription().isEmpty()) {
                    Notification.show("信息不能为空！请重新输入...");
                    flag = false;
                }
                else{
                    flag = true;
                    for (WorkStation work : workStationList) {
                        if (ws.getStationCode().equals(work.getStationCode())) {
                            Notification.show("该工站编号已存在！请重新输入...");
                            flag = false;
                        }
                    }
                }
                if(flag == false) {
                    addWorkStationDialog.stationCodeText.setValue("");
                    addWorkStationDialog.stationNameText.setValue("");
                    addWorkStationDialog.descriptionText.setValue("");
                    addWorkStationDialog.lotNumText.setValue("");
                }
                else{
                    Notification.show("数据插入成功！");
                    workStationService.createWorkStation(ws);      //数据保存到数据库
                    addWorkStationDialog.setVisible(false);        //关闭当前的界面
                    addStationRow(ws);
                    updateButton.click();
                }
            });
        });

        //删
        Button removeRowButton = new Button(FontAwesome.TIMES);
        removeRowButton.setPrimaryStyleName("v-label");
        removeRowButton.addStyleName("my-color-maroon");
        removeRowButton.addClickListener(event -> {
            //选择框。确定要删除数据。是？否
            Notification.show("数据删除成功！");
            workStationService.deleteWorkStation(workStation);
            stationItemTable.removeItem(workStation);
        });
        commandLayout.addComponent(newRowButton);
        commandLayout.addComponent(removeRowButton);
        commandLayout.setComponentAlignment(newRowButton, Alignment.MIDDLE_CENTER);
        commandLayout.setComponentAlignment(removeRowButton, Alignment.MIDDLE_CENTER);

        //工站序号
        TextField stationSnText = new TextField();
        stationSnText.addFocusListener(e ->{
            stationItemTable.select(workStation);

            defectItemTable.removeAllItems();
            for(Defect defect :defectList) {
               defect.setWorkStation(workStation);
               addDefectRow(defect);
            }
        });
        stationSnText.setWidth(40, Unit.POINTS);
        stationSnText.setValue(String.valueOf(workStation.getSn()));
        stationSnText.setReadOnly(true);

        //工站编号
        TextField stationCodeText = new TextField();
        stationCodeText.addFocusListener(event -> {
            stationItemTable.select(workStation);

            defectItemTable.removeAllItems();
            for(Defect defect :defectList) {
                defect.setWorkStation(workStation);
                addDefectRow(defect);
            }
        });
        stationCodeText.setWidth(70, Unit.POINTS);
        stationCodeText.setValue(String.valueOf(workStation.getStationCode()));
        stationCodeText.addTextChangeListener(event ->{
            saveButton.addClickListener(e ->{
            workStation.setStationCode(event.getText());
            ProductLine productLine = productLineService.findLineByLineCode(lineCodeText.getValue());
            workStation.setProductLine(productLine);
            workStationService.updateWorkStation(workStation);
            Notification.show("更新成功...");
            });
        });

        //工站名称
        TextField stationNameText = new TextField();
        stationNameText.addFocusListener(event -> {
            stationItemTable.select(workStation);
            defectItemTable.removeAllItems();
            for(Defect defect :defectList) {
                defect.setWorkStation(workStation);
                addDefectRow(defect);
            }
        });
        stationNameText.setWidth(80, Unit.POINTS);
        stationNameText.setValue(String.valueOf(workStation.getStationName()));
        stationNameText.addTextChangeListener(event ->{
            saveButton.addClickListener(e ->{
            workStation.setStationName(event.getText());
            ProductLine productLine = productLineService.findLineByLineCode(lineCodeText.getValue());
            workStation.setProductLine(productLine);
            workStationService.updateWorkStation(workStation);
            Notification.show("更新成功...");
            });
        });

        //上下移操作
        GridLayout operatorLayout = new GridLayout(2,1);
        operatorLayout.setWidth(40, Unit.POINTS);

        //上移操作
        Button upButton = new Button(FontAwesome.ARROW_CIRCLE_UP);
        upButton.setPrimaryStyleName("v-label");
        upButton.addStyleName("my-color-green");
        upButton.addClickListener(event -> {

            for(WorkStation ws : workStationList){

                if(workStation.getSn()==(ws.getSn()+1) && workStation.getSn()>1) {

                    String code = workStation.getStationCode();
                    workStation.setStationCode(ws.getStationCode());
                    ws.setStationCode(code);

                    String name = workStation.getStationName();
                    workStation.setStationName(ws.getStationName());
                    ws.setStationName(name);

                    String describe = workStation.getDescription();
                    workStation.setDescription(ws.getDescription());
                    ws.setDescription(describe);

                    ProductLine productLine = productLineService.
                            findLineByLineCode(lineCodeText.getValue());
                    ws.setProductLine(productLine);
                    workStation.setProductLine(productLine);

                    workStationService.updateWorkStation(workStation);
                    workStationService.updateWorkStation(ws);
                }
            }
            updateButton.click();
        });

        //下移操作
        Button downButton = new Button(FontAwesome.ARROW_CIRCLE_DOWN);
        downButton.setPrimaryStyleName("v-label");
        downButton.addStyleName("my-color-green");
        downButton.addClickListener(event -> {

            for(WorkStation ws : workStationList){

                if((workStation.getSn()+1)== ws.getSn() && ws.getSn()<= workStationList.size()) {

                    String code = workStation.getStationCode();
                    workStation.setStationCode(ws.getStationCode());
                    ws.setStationCode(code);

                    String name = workStation.getStationName();
                    workStation.setStationName(ws.getStationName());
                    ws.setStationName(name);

                    ProductLine productLine = productLineService.
                            findLineByLineCode(lineCodeText.getValue());
                    ws.setProductLine(productLine);
                    workStation.setProductLine(productLine);

                    String describe = workStation.getDescription();
                    workStation.setDescription(ws.getDescription());
                    ws.setDescription(describe);

                    workStationService.updateWorkStation(workStation);
                    workStationService.updateWorkStation(ws);
                }
            }
            updateButton.click();
        });

        operatorLayout.addComponent(upButton);
        operatorLayout.addComponent(downButton);
        operatorLayout.setComponentAlignment(upButton, Alignment.MIDDLE_CENTER);
        operatorLayout.setComponentAlignment(downButton, Alignment.MIDDLE_CENTER);

        //显示终端操作
        Button displayTerminalButton = new Button("终端列表");
        displayTerminalButton.setWidth(75, Unit.POINTS);
        displayTerminalButton.addFocusListener(event -> stationItemTable.select(workStation));
        displayTerminalButton.addClickListener(event -> {

            TerminalDialog terminalDialog = new TerminalDialog();
            //在UI界面上添加窗口Window
            UI.getCurrent().addWindow(terminalDialog);

            terminalDialog.setStationInfo(workStation);
            terminalService = (TerminalService) ui.getBean("terminalService");

            terminalList = terminalService.findTerminalByStationId( workStation.getStationId());
            for(Terminal terminal :terminalList){
                terminalDialog.addTerminalRow(terminal);
            }
        });

        //更新按钮
        updateButton.addClickListener(event ->{
            stationItemTable.removeAllItems();

            List<WorkStation> workStationList1 = workStationService.
                    findWorkStationsByLineCode(lineCodeText.getValue());
            for(WorkStation ws : workStationList1){
                addStationRow(ws);
            }
        });

        stationItemTable.addItem(new Object[] {
                commandLayout, stationSnText,
                stationCodeText,stationNameText,
                operatorLayout,
                displayTerminalButton,
                },
                workStation);
    }

    public void addDefectRow(Defect defect) {
        //增删操作
        GridLayout commandLayout = new GridLayout(2,1);
        commandLayout.setWidth(40, Unit.POINTS);

        //增
        Button newRowButton = new Button (FontAwesome.PLUS);
        newRowButton.setPrimaryStyleName("v-label");
        newRowButton.addStyleName("my-color-green");
        newRowButton.addClickListener(event -> {
            AddDefectDialog addDefectDialog =new AddDefectDialog();
            UI.getCurrent().addWindow(addDefectDialog);

            addDefectDialog.saveButton.addClickListener(e ->{
                //创建新的Defect
                Defect def = new Defect();
                addDefectDialog.setDefectDialog(def);

                //设置当前Defect的Workstation
                def.setWorkStation(defect.getWorkStation());

                List<Defect> defectList = defectService.findDefectListByStationId(workStationText);

                boolean flag = true;
                if (def.getShortcut().isEmpty() || def.getDefectName().isEmpty()) {
                    Notification.show("信息不能为空！请重新输入...");
                    flag = false;
                }
                else{
                    flag = true;
                    for(Defect defect1 : defectList){
                        if(def.getShortcut().equals(defect1.getShortcut())) {
                            Notification.show("该shortcut已存在！请重新输入...");
                            flag = false;
                        }
                    }
                }
                if(flag == false){
                    addDefectDialog.defectNameText.setValue("");
                    addDefectDialog.shortcutText.setValue("");
                }
                else {
                    Notification.show("数据插入成功！");
                    defectService.createDefect(def);            //数据保存到数据库
                    addDefectDialog.setVisible(false);
                    addDefectRow(def);
                    updateButton.click();
                }
            });
        });

        //删
        Button removeRowButton = new Button(FontAwesome.TIMES);
        removeRowButton.setPrimaryStyleName("v-label");
        removeRowButton.addStyleName("my-color-maroon");
        removeRowButton.addClickListener(event -> {
            Notification.show("数据删除成功！");
            defectService.deleteDefect(defect);
            defectItemTable.removeItem(defect);
        });
        commandLayout.addComponent(newRowButton);
        commandLayout.addComponent(removeRowButton);
        commandLayout.setComponentAlignment(newRowButton, Alignment.MIDDLE_CENTER);
        commandLayout.setComponentAlignment(removeRowButton, Alignment.MIDDLE_CENTER);

        //缺陷Id
        TextField defectIdText = new TextField();
        defectIdText.addFocusListener(event -> defectItemTable.select(defect));
        defectIdText.setWidth(60, Unit.POINTS);
        defectIdText.setValue(defect.getDefectId());
        defectIdText.setReadOnly(true);

        //缺陷名称
        TextField defectNameText = new TextField();
        defectNameText.addFocusListener(event -> defectItemTable.select(defect));
        defectNameText.setWidth(100, Unit.POINTS);
        defectNameText.setValue(defect.getDefectName());
        defectNameText.addValueChangeListener(event ->{
            saveButton.addClickListener(e ->{
                defect.setDefectName(defectNameText.getValue());
                defectService.updateDefect(defect);
                Notification.show("更新成功...");
            });
        });

        //缺陷shortCut
        TextField shortCutText = new TextField();
        shortCutText.addFocusListener(event -> defectItemTable.select(defect));
        shortCutText.setWidth(60, Unit.POINTS);
        shortCutText.setValue(String.valueOf(defect.getShortcut()));
        shortCutText.addValueChangeListener(event ->{
            saveButton.addClickListener(e ->{
                defect.setShortcut(shortCutText.getValue());
                defectService.updateDefect(defect);
                Notification.show("更新成功...");
            });
        });

        defectItemTable.addItem(new Object[] {
                commandLayout, defectIdText,
                defectNameText,shortCutText,
        }, defect);

        updateButton.addClickListener(e ->{
            defectItemTable.removeAllItems();

            List<Defect> defectList = defectService.findDefectListByStationId(workStationText);
            for(Defect defect1 : defectList){
                addDefectRow(defect1);
            }
        });

    }

    private void buildEvents() {
        productLineText.addTextChangeListener(event -> {
            productLineText.getValue();
            productLineText.setReadOnly(true);
        });

        lineCodeText.addTextChangeListener(event -> {
            lineCodeText.getValue();
            lineCodeText.setReadOnly(true);
        });

        locationText.addTextChangeListener(event -> {
            ProductLine productLine =
                    productLineService.findLineByLineCode(lineCode);

            Location location = productLine.getLocation();
            location.setLocationName(lineCodeText.getValue());
            saveButton.addClickListener(e ->{
            locationService.updateLocation(location);
            });
        });
    }

    public void setProductLineService(ProductLineService productLineService) {
        this.productLineService = productLineService;
    }

    public void setWorkStationService(WorkStationService workStationService) {
        this.workStationService = workStationService;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public void setTerminalService(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    public void setDefectService(DefectService defectService) {
        this.defectService = defectService;
    }

}
