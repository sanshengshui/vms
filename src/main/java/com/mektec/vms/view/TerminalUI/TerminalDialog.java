package com.mektec.vms.view.TerminalUI;

import com.mektec.vms.MyUI;
import com.mektec.vms.domain.Terminal;
import com.mektec.vms.domain.WorkStation;
import com.mektec.vms.service.TerminalService;
import com.mektec.vms.service.WorkStationService;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by mektec on 16-4-28.
 */
public class TerminalDialog extends TerminalDialogUI {

    private TerminalService terminalService;
    private WorkStationService workStationService;

    public TerminalDialog() {

        ui = (MyUI)UI.getCurrent();
        terminalService = (TerminalService) ui.getBean("terminalService");
        workStationService = (WorkStationService) ui.getBean("workStationService");
    }

    public void addTerminalRow(Terminal terminal){

        //增删操作
        GridLayout commandLayout = new GridLayout(2,1);
        commandLayout.setWidth(40, Unit.POINTS);

        //增
        Button newRowButton = new Button (FontAwesome.PLUS);
        newRowButton.setPrimaryStyleName("v-label");
        newRowButton.addStyleName("my-color-green");
        newRowButton.addClickListener(event -> {
            AddTerminalDialog addTerminalDialog = new AddTerminalDialog();
            UI.getCurrent().addWindow(addTerminalDialog);

            addTerminalDialog.saveButton.addClickListener(e ->{

                //创建新的Terminal
                Terminal termi = new Terminal();
                addTerminalDialog.setTerminalDialog(termi);

                //设置当前的Terminal的Workstation
                WorkStation workStation =
                        workStationService.findWorkStationById(stationIdText.getValue());
                termi.setWorkStation(workStation);

                List<Terminal> terminalList =
                        terminalService.findTerminalByStationId(workStation.getStationId());

                boolean flag = true;
                if (termi.getTerminalCode().isEmpty() || termi.getTerminalName().isEmpty()) {
                    Notification.show("信息不能为空！请重新输入...");
                    flag = false;
                }
                else{
                    flag = true;
                    for (Terminal terminal1 : terminalList) {
                        if (termi.getTerminalCode().equals(terminal1.getTerminalCode())) {
                            Notification.show("该终端编号已存在！请重新输入...");
                            flag = false;
                        }
                    }
                }
                if(flag == false){
                    addTerminalDialog.terminalCodeText.setValue("");
                    addTerminalDialog.terminalNameText.setValue("");
                }
                else {
                    Notification.show("数据插入成功！");
                    terminalService.createTerminal(termi);            //数据保存到数据库
                    addTerminalDialog.setVisible(false);
                    addTerminalRow(termi);
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
            terminalService.deleteTerminal(terminal);
            itemTable.removeItem(terminal);
        });
        commandLayout.addComponent(newRowButton);
        commandLayout.addComponent(removeRowButton);
        commandLayout.setComponentAlignment(newRowButton, Alignment.MIDDLE_CENTER);
        commandLayout.setComponentAlignment(removeRowButton, Alignment.MIDDLE_CENTER);

        //终端编号
        TextField terminalCodeText = new TextField();
        terminalCodeText.addFocusListener(event -> itemTable.select(terminal));
        terminalCodeText.setWidth(80, Unit.POINTS);
        terminalCodeText.setValue(String.valueOf(terminal.getTerminalCode()));
        terminalCodeText.addValueChangeListener(event ->{
            saveButton.addClickListener(e ->{
                terminal.setTerminalCode(terminalCodeText.getValue());
                terminalService.updateTerminal(terminal);
                Notification.show("更新成功...");
            });
        });

        //终端名称
        TextField terminalNameText = new TextField();
        terminalNameText.addFocusListener(event -> itemTable.select(terminal));
        terminalNameText.setWidth(80, Unit.POINTS);
        terminalNameText.setValue(String.valueOf(terminal.getTerminalName()));
        terminalNameText.addValueChangeListener(event ->{
            saveButton.addClickListener(e ->{
                terminal.setTerminalName(terminalNameText.getValue());
                terminalService.updateTerminal(terminal);
                Notification.show("更新成功...");
            });
        });

        //终端类型
        ComboBox terminalTypeText = new ComboBox();
        terminalTypeText.addItem("DISPLAY");
        terminalTypeText.addItem("INPUT");
        terminalTypeText.addItem("SINGLE");
        terminalTypeText.setNullSelectionAllowed(false);
        terminalTypeText.addFocusListener(event -> itemTable.select(terminal));
        terminalTypeText.setWidth(85, Unit.POINTS);
        terminalTypeText.setValue(String.valueOf(terminal.getTerminalType()));
        terminalTypeText.addValueChangeListener(event ->{
            saveButton.addClickListener(e ->{
                terminal.setTerminalType((String) terminalTypeText.getValue());
                terminalService.updateTerminal(terminal);
                Notification.show("更新成功...");
            });
        });

        itemTable.addItem(new Object[] {
                commandLayout, terminalCodeText,
                terminalNameText,terminalTypeText,
        }, terminal);

        updateButton.addClickListener(e ->{
            itemTable.removeAllItems();
            List<Terminal> terminalList = terminalService.findTerminalByStationId(stationIdText.getValue());
            for(Terminal t : terminalList){
                addTerminalRow(t);
            }
        });
    }

    public void setStationInfo(WorkStation workStation){

        stationIdText.setValue(workStation.getStationId());
        stationCodeText.setValue(workStation.getStationCode());
        stationNameText.setValue(workStation.getStationName());
        stationSnText.setValue(String.valueOf(workStation.getSn()));
    }

}

