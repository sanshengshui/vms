package com.mektec.vms.controller.api;

import com.mektec.vms.domain.*;
import com.mektec.vms.domain.api.ApiDefect;
import com.mektec.vms.domain.api.ApiProductLine;
import com.mektec.vms.domain.api.ApiWorkStation;
import com.mektec.vms.service.LotService;
import com.mektec.vms.service.ProductLineService;
import com.mektec.vms.service.TerminalService;
import com.mektec.vms.service.WorkStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;



@Path("workstations")
public class WorkStationResource {
    private TerminalService terminalService;
    private WorkStationService stationService;
    private LotService lotService;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ApiWorkStation getWorkStationByTerminal(
            @Context ServletContext servletContext,
            @QueryParam("terminal-code") final String terminalCode) {

        //获取服务
        ApplicationContext ctx = WebApplicationContextUtils.
                getWebApplicationContext(servletContext);
        stationService= ctx.getBean("workStationService",
                WorkStationService.class);
        terminalService= ctx.getBean("terminalService",
                TerminalService.class);

        lotService= ctx.getBean("lotService",
                LotService.class);

        //TODO: 1.根据终端编号获取终端信息
        //      2.根据终端信息中的"terminalID"获取Workstation信息
        //      3.将WorkStation转化成ApiWorkStation类型的对象
        //      4.返回ApiWorkStation的对象
        Terminal terminal = terminalService.findTerminalByCode(terminalCode);
        if(terminal == null){
            return null;
        }

        WorkStation workStation = terminal.getWorkStation();
        ApiWorkStation apiWorkStation = ApiWorkStation.fromWorkStation(workStation);

        if(!workStation.getLotNum().isEmpty()) {
            Lot lot = lotService.findLotByLotNum(workStation.getLotNum());
            apiWorkStation.setModelNum(lot.getModelNum());
        }

        return apiWorkStation;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ApiWorkStation getWorkStationByCode(
            @Context ServletContext servletContext,
            @PathParam("station-code") final String stationCode) {

        //获取服务
        ApplicationContext ctx = WebApplicationContextUtils.
                getWebApplicationContext(servletContext);
        stationService= ctx.getBean("workStationService",
                WorkStationService.class);

        WorkStation workStation = stationService.findWorkStationByCode(stationCode);
        ApiWorkStation apiWorkStation = ApiWorkStation.fromWorkStation(workStation);

        if(!workStation.getLotNum().isEmpty()) {
            Lot lot = lotService.findLotByLotNum(workStation.getLotNum());
            apiWorkStation.setModelNum(lot.getModelNum());
        }

        return apiWorkStation;
    }

    @Autowired
    public void setStationService(WorkStationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setTerminalService(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @Autowired
    public void setLotService(LotService lotService) {
        this.lotService = lotService;
    }
}
