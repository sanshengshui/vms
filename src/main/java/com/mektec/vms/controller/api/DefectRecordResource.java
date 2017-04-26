package com.mektec.vms.controller.api;

import com.google.gwt.json.client.JSONObject;
import com.mektec.vms.domain.Defect;
import com.mektec.vms.domain.DefectRecord;
import com.mektec.vms.domain.Terminal;
import com.mektec.vms.domain.WorkStation;
import com.mektec.vms.domain.api.ApiDefectRecord;
import com.mektec.vms.domain.api.ApiDefectStatistics;
import com.mektec.vms.service.DefectRecordService;
import com.mektec.vms.service.DefectService;
import com.mektec.vms.service.TerminalService;
import org.atmosphere.config.service.Get;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Path("defect-records")
public class DefectRecordResource {
    private TerminalService terminalService;
    private DefectService defectService;
    private DefectRecordService defectRecordService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createRecord(@Context ServletContext servletContext,
                                 final ApiDefectRecord record) {

        //TODO: 根据终端提交上来的（精简)缺陷记录, 转换成服务端的缺陷记录,保存到数据库

        ApplicationContext ctx = WebApplicationContextUtils.
                getWebApplicationContext(servletContext);
        defectRecordService = ctx.getBean("defectRecordService", DefectRecordService.class);

        terminalService = ctx.getBean("terminalService", TerminalService.class);

        defectService = ctx.getBean("defectService", DefectService.class);

        //新建一个记录

        //输入goalTerminal所在的工站记录
        DefectRecord defectRecord = new DefectRecord();

        //输入缺陷所在的工站需要在添加一条记录
        DefectRecord defectRecord1=new DefectRecord();


        //1. 根据终端编号查找终端信息（记录人）
        Terminal terminal = terminalService.findTerminalByCode(record.getTerminalCode());
        defectRecord.setTerminalId(terminal.getTerminalId());
        defectRecord.setPeopleNumber(record.getPeopleNumber());
        defectRecord.setGoalTerminal(record.getGoalTerminal());

        defectRecord1.setTerminalId(terminal.getTerminalId());

        Defect defect = defectService.findDefectById(record.getDefectId());


        //2. 根据缺陷ID查找缺陷信息（工站）
        WorkStation workStation = defect.getWorkStation();
        defectRecord.setstationId(workStation.getStationId());
        defectRecord.setDefect(defect);
        defectRecord.setLotNum(workStation.getLotNum());



        //3. 根据工站ID查找此缺陷信息所在的终端,将这个终端放在GoalTerminal
        Terminal terminal2=terminalService.findTerminalByStation(workStation.getStationId());
        defectRecord1.setGoalTerminal(terminal2.getTerminalCode());
        defectRecord1.setstationId(workStation.getStationId());
        defectRecord1.setDefect(defect);
        defectRecord1.setLotNum(workStation.getLotNum());


        //3. 根据工站ID查找显示终端信息
        List<Terminal> terminalList = terminalService.
                findTerminalByStationId(workStation.getStationId());

        for(Terminal displayTerminal : terminalList) {
            //4. 转换记录为服务端类型
            if(displayTerminal.getTerminalType().equals(Terminal.DISPLAY)) {
                //最终目的：找到Operator
                defectRecord.setResponser(displayTerminal.getOperator());
                defectRecord1.setRecorder(displayTerminal.getOperator());
            }
            else if(displayTerminal.getTerminalType().equals(Terminal.INPUT)){
                defectRecord.setRecorder(displayTerminal.getOperator());
                defectRecord1.setRecorder(displayTerminal.getOperator());
            }
        }

        Timestamp dateTime = record.getRecordTime();
        int date = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(dateTime));
        int time = Integer.parseInt(new SimpleDateFormat("HHmm").format(dateTime));
        defectRecord.setDate(date);
        defectRecord.setTime(time);
        defectRecord.setRecordTime(
                new SimpleDateFormat("yyyyMMddHHmm").format(dateTime));


        defectRecord.setCount(1);
        defectRecord.setDeleted(false);


        defectRecord1.setDate(date);
        defectRecord1.setTime(time);
        defectRecord1.setRecordTime(
                new SimpleDateFormat("yyyyMMddHHmm").format(dateTime));


        defectRecord1.setCount(1);
        defectRecord1.setDeleted(false);

        String str1=record.getRecordId()+"1";


        //5. 保存到数据库
        //判断记录中是否已经存在该record
        if(defectRecordService.findRecordById(record.getRecordId())!=null) {
            System.out.println("该record已有数据----------");
            return null;
        }
        else{
            defectRecord.setRecordId(record.getRecordId());
            defectRecord1.setRecordId(str1);

            defectRecordService.createRecord(defectRecord);
            defectRecordService.createRecord(defectRecord1);
            System.out.println("该record数据插入成功----------");
        }

        return Response.noContent().build();
    }

}