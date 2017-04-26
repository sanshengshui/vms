package com.mektec.vms.controller.api;

import com.mektec.vms.domain.Employee;
import com.mektec.vms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("employees")
public class EmployeeResource {
    private EmployeeService employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeByCard(
            @Context ServletContext servletContext,
            @QueryParam("card-code") final String cardCode) {

        //获取服务
        ApplicationContext ctx = WebApplicationContextUtils.
                getWebApplicationContext(servletContext);
        employeeService = ctx.getBean("employeeService",
                EmployeeService.class);

        Employee employee = employeeService.findEmployeeByCard(cardCode);

        return employee;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{employee-id}")
    public Employee getEmployeeByEmployeeId(
            @Context ServletContext servletContext,
            @PathParam("employee-id") final String employeeId) {

        //获取服务
        ApplicationContext ctx = WebApplicationContextUtils.
                getWebApplicationContext(servletContext);
        employeeService = ctx.getBean("employeeService",
                EmployeeService.class);

        Employee employee = employeeService.findEmployeeById(employeeId);

        return employee;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
