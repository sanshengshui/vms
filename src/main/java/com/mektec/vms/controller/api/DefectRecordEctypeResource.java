package com.mektec.vms.controller.api;

import com.mektec.vms.domain.Defect;
import com.mektec.vms.domain.DefectRecord;
import com.mektec.vms.domain.Terminal;
import com.mektec.vms.domain.WorkStation;
import com.mektec.vms.domain.api.ApiDefectRecord;
import com.mektec.vms.service.DefectRecordService;
import com.mektec.vms.service.DefectService;
import com.mektec.vms.service.TerminalService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by jamesmsw on 16-11-15.
 */

@Path("defectectype-records")
public class DefectRecordEctypeResource {
    private TerminalService terminalService;
    private DefectService defectService;
    private DefectRecordService defectRecordService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createRecoord(@Context ServletContext servletContext,
                                 final ApiDefectRecord record) {

        //TODO: 根据终端提交上来的（精简)缺陷记录, 转换成服务端的缺陷记录,保存到数据库

        ApplicationContext ctx = WebApplicationContextUtils.
                getWebApplicationContext(servletContext);
        defectRecordService = ctx.getBean("defectRecordService", DefectRecordService.class);

        terminalService = ctx.getBean("terminalService", TerminalService.class);

        defectService = ctx.getBean("defectService", DefectService.class);
        //新建一个记录


        DefectRecord defectRecord = new DefectRecord();


        //1. 根据终端编号查找终端信息（记录人）
        Terminal terminal = terminalService.findTerminalByCode(record.getTerminalCode());
        defectRecord.setTerminalId(terminal.getTerminalId());

        Defect defect = defectService.findDefectById(record.getDefectId());


        //2. 根据缺陷ID查找缺陷信息（工站）
        WorkStation workStation = defect.getWorkStation();

        //3. 根据工站ID查找此缺陷信息所在的终端,将这个终端放在GoalTerminal
        Terminal terminal1=terminalService.findTerminalByStation(workStation.getStationId());
        defectRecord.setGoalTerminal(terminal1.getTerminalCode());
        defectRecord.setstationId(workStation.getStationId());
        defectRecord.setDefect(defect);
        defectRecord.setLotNum(workStation.getLotNum());


        //4. 根据工站ID查找显示终端信息
        List<Terminal> terminalList = terminalService.
                findTerminalByStationId(workStation.getStationId());

        for(Terminal displayTerminal : terminalList) {
            //5. 转换记录为服务端类型
            if(displayTerminal.getTerminalType().equals(Terminal.DISPLAY)) {
                //最终目的：找到Operator
                defectRecord.setResponser(displayTerminal.getOperator());
            }
            else if(displayTerminal.getTerminalType().equals(Terminal.INPUT)){
                defectRecord.setRecorder(displayTerminal.getOperator());
            }else if (displayTerminal.getTerminalType().equals(Terminal.SINGLE))
            {
                defectRecord.setRecorder(displayTerminal.getOperator());

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

        //6. 保存到数据库
        //判断记录中是否已经存在该record
        if(defectRecordService.findRecordById(record.getRecordId())!=null) {
            System.out.println("该record已有数据----------");
            return null;
        }
        else{
            defectRecord.setRecordId(record.getRecordId());
            defectRecordService.createRecord(defectRecord);
            System.out.println("该record数据插入成功----------");
        }

        return Response.noContent().build();
    }

}
