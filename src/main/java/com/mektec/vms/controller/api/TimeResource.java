package com.mektec.vms.controller.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;


@Path("time")
public class TimeResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public long getCurrentTime() {
        return new Date().getTime();
    }
}
