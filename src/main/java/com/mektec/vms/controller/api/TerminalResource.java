package com.mektec.vms.controller.api;

import com.mektec.vms.domain.Terminal;
import com.mektec.vms.domain.api.ApiTerminal;
import com.mektec.vms.domain.api.ApiTerminalList;
import com.mektec.vms.service.TerminalService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("terminals")
public class TerminalResource {
    private TerminalService terminalService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApiTerminalList> getTerminalListByStationId(
            @Context ServletContext servletContext,
            @QueryParam("terminal-code") final String terminalCode
    ){
        ApplicationContext ctx = WebApplicationContextUtils.
                getWebApplicationContext(servletContext);
        terminalService = ctx.getBean("terminalService",
                TerminalService.class);
        List<Terminal> terminalList=terminalService.findTerminalListByStationId(terminalCode);

        List<ApiTerminalList> apiTerminalLists=new ArrayList<>();

        for(Terminal terminal: terminalList){

            apiTerminalLists.add(ApiTerminalList.fromApiTerminalList(terminal));
        }

        return apiTerminalLists;

    }
/*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ApiTerminal getTerminalByCode(
            @Context ServletContext servletContext,
            @QueryParam("terminal-code") final String terminalCode) {
        //获取服务
        ApplicationContext ctx = WebApplicationContextUtils.
                getWebApplicationContext(servletContext);

        terminalService = ctx.getBean("terminalService",
                TerminalService.class);

        Terminal terminal = terminalService.findTerminalByCode(terminalCode);
        ApiTerminal apiTerminal = new ApiTerminal(terminal);


        return apiTerminal;

    }
    */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("operator")
    public Response updateOperator(@Context ServletContext servletContext,
                                 final ApiTerminal terminal) {
        //获取服务
        ApplicationContext ctx = WebApplicationContextUtils.
                getWebApplicationContext(servletContext);
        terminalService = ctx.getBean("terminalService",
                TerminalService.class);

        Terminal t = terminalService.findTerminalByCode(terminal.getTerminalCode());
        if(t != null) {
            t.setOperator(terminal.getOperator());
            t.setUpdateTime(terminal.getUpdateTime());
            terminalService.updateTerminal(t);
        }
        return Response.noContent().build();
    }

}
