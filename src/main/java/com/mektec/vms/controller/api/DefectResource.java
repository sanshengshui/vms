package com.mektec.vms.controller.api;

import com.mektec.vms.domain.Defect;
import com.mektec.vms.domain.api.ApiDefect;
import com.mektec.vms.domain.api.ApiWorkStation;
import com.mektec.vms.service.DefectService;
import com.mektec.vms.service.WorkStationService;
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


@Path("defects")
public class DefectResource {
    private DefectService defectService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApiDefect> getDefectListByProductLine(
            @Context ServletContext servletContext,
            @QueryParam("line-id") final String lineId) {

        //获取服务
        ApplicationContext ctx = WebApplicationContextUtils.
                getWebApplicationContext(servletContext);
        defectService = ctx.getBean("defectService",
                DefectService.class);
        List<Defect> defectList = defectService.findDefectListByLine(lineId);


        //转化成ApiDefect类型
        List<ApiDefect> apiDefectList = new ArrayList<>();

        for(Defect defect: defectList) {
            apiDefectList.add(ApiDefect.fromDefect(defect));
        }

        return apiDefectList;
    }
}

