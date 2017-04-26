package com.mektec.vms.controller.api;

import com.mektec.vms.domain.Terminal;
import com.mektec.vms.domain.api.ApiAllTerminal;
import com.mektec.vms.service.TerminalService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamesmsw on 17-2-15.
 */
@Path("allterminals")
public class AllTerminalResource {
    private TerminalService terminalService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApiAllTerminal> getAllTerminalOfLine(
            @Context ServletContext servletContext,
            @QueryParam("lineId") final String lineId
    ){
        ApplicationContext ctx = WebApplicationContextUtils.
                getWebApplicationContext(servletContext);
        terminalService = ctx.getBean("terminalService",
                TerminalService.class);
        List<Terminal> terminalAllList=terminalService.findAllTerminalOfLine(lineId);

        List<ApiAllTerminal> apiAllTerminalList=new ArrayList<>();

        for (Terminal terminal:terminalAllList){
            apiAllTerminalList.add(ApiAllTerminal.fromApiAllTerminalList(terminal));
        }
        return apiAllTerminalList;


    }
}