package com.mektec.vms.controller.api;

import com.mektec.vms.domain.DefectRecord;
import com.mektec.vms.domain.Terminal;
import com.mektec.vms.domain.WorkStation;
import com.mektec.vms.domain.api.ApiDefectRecordList;
import com.mektec.vms.service.DefectRecordService;
import com.mektec.vms.service.TerminalService;
import com.mektec.vms.service.WorkStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jamesmsw on 16-11-15.
 */

@Path("defectectype-statistics")
public class StatisticsEctypeResource {

    private TerminalService terminalService;
    private WorkStationService workStationService;
    private DefectRecordService defectRecordService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApiDefectRecordList> getDefectRecordCount1(
            @Context ServletContext servletContext,
            @QueryParam("terminal-code1") final String terminalCode
    ){
        ApplicationContext ctx= WebApplicationContextUtils.getWebApplicationContext(servletContext);
        terminalService=ctx.getBean("terminalService",TerminalService.class);
        defectRecordService=ctx.getBean("defectRecordService",DefectRecordService.class);
        Terminal terminal=terminalService.findTerminalByCode(terminalCode);
        WorkStation workStation = terminal.getWorkStation();
        if(terminal==null){
            return null;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String recordTime=String.valueOf(sdf.format(new Date()));


        List<DefectRecord> defectRecords=defectRecordService.sumByStation(workStation.getStationId(),recordTime);
        List<ApiDefectRecordList> apiDefectRecordListList=new ArrayList<>();
        for(DefectRecord defectRecord:defectRecords){
            apiDefectRecordListList.add(ApiDefectRecordList.formDefectRecordList(defectRecord));
        }
        return apiDefectRecordListList;
    }

    @Autowired
    public void setDefectRecordService(DefectRecordService defectRecordService) {
        this.defectRecordService = defectRecordService;
    }

    @Autowired
    public void setTerminalService(TerminalService terminalService) {
        this.terminalService = terminalService;
    }


}
