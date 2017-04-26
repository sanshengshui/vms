package com.mektec.vms.controller.api;

import com.mektec.vms.domain.DefectRecord;
import com.mektec.vms.domain.ProductLine;
import com.mektec.vms.domain.Terminal;
import com.mektec.vms.domain.WorkStation;
import com.mektec.vms.domain.api.*;
import com.mektec.vms.service.DefectRecordService;
import com.mektec.vms.service.ProductLineService;
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
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Path("defect-statistics")
public class StatisticsResource {
    private TerminalService terminalService;
    private WorkStationService workStationService;
    private DefectRecordService defectRecordService;
    private ProductLineService productLineService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApiDefectRecordList> getDefectRecordCount(
            @Context ServletContext servletContext,
            @QueryParam("terminal-code") final String terminalCode){
        ApplicationContext ctx = WebApplicationContextUtils.
                getWebApplicationContext(servletContext);
        defectRecordService = ctx.getBean("defectRecordService", DefectRecordService.class);
        productLineService=ctx.getBean("productLineService",ProductLineService.class);
        terminalService= ctx.getBean("terminalService",
                TerminalService.class);

        Terminal terminal = terminalService.findTerminalByCode(terminalCode);
        WorkStation workStation = terminal.getWorkStation();
        ProductLine productLine=workStation.getProductLine();

        ProductLine productLine1=productLineService.findPassCountByLineCode(productLine.getLineCode());
        int sum1=Integer.parseInt(productLine1.getPasscount());
        int sum=1000000/sum1;



        if(terminal == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String recordTime = String.valueOf(sdf.format(new Date()));



        List<DefectRecord> defectRecordList=defectRecordService.findDefectRecordList(terminalCode,recordTime,sum);

        List<ApiDefectRecordList> apiDefectRecordLists=new ArrayList<>();

        for(DefectRecord defectRecord:defectRecordList){
            apiDefectRecordLists.add(ApiDefectRecordList.formDefectRecordList(defectRecord));
        }

        return  apiDefectRecordLists;

    }

    /*


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApiDefectRecordList> getDefectRecordCount1(
            @Context ServletContext servletContext,
            @QueryParam("terminal-code1") final String terminalCode
            ){
        ApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(servletContext);
        terminalService=ctx.getBean("terminalService",TerminalService.class);
        defectRecordService=ctx.getBean("defectRecordService",DefectRecordService.class);
        Terminal terminal=terminalService.findTerminalByCode(terminalCode);
        WorkStation workStation = terminal.getWorkStation();
        if(terminal==null){
            return null;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String recordTime1=String.valueOf(sdf.format(new Date()));


        List<DefectRecord> defectRecords=defectRecordService.sumByStation(workStation.getStationId(),recordTime1);
        List<ApiDefectRecordList> apiDefectRecordListList=new ArrayList<>();
        for(DefectRecord defectRecord:defectRecords){
            apiDefectRecordListList.add(ApiDefectRecordList.formDefectRecordList(defectRecord));
        }
        return apiDefectRecordListList;
    }
    这个方法的问题在于

    */







    /*
    public List<ApiDefectStatistics> getStatistics(
            @Context ServletContext servletContext,
            @QueryParam("terminal-code") final String terminalCode) {

        ApplicationContext ctx = WebApplicationContextUtils.
                getWebApplicationContext(servletContext);
        defectRecordService = ctx.getBean("defectRecordService", DefectRecordService.class);

        terminalService = ctx.getBean("terminalService", TerminalService.class);

        //根据terminal-code获取terminal
        Terminal terminal = terminalService.findTerminalByCode(terminalCode);

        //根据terminal获取workStation
        WorkStation workStation = terminal.getWorkStation();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        int date = Integer.parseInt(sdf.format(terminal.getUpdateTime()));

        //获取统计信息
        List<ApiDefectStatistics> statisticsList =
                defectRecordService.sumByStation(
                        workStation.getStationId(),
                        date,
                        terminal.getOperator());

        return statisticsList;
    }

    这个方法 会导致返回缺陷统计时 返回空值

    edit by Mektec mushuwei
    116193
    **/

    @Autowired
    public void setDefectRecordService(DefectRecordService defectRecordService) {
        this.defectRecordService = defectRecordService;
    }

    @Autowired
    public void setTerminalService(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

}
