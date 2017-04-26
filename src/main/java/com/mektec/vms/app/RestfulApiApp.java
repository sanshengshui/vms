package com.mektec.vms.app;



import com.mektec.vms.controller.api.*;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class RestfulApiApp extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(TimeResource.class);
        classes.add(TerminalResource.class);
        classes.add(DefectRecordResource.class);
        classes.add(WorkStationResource.class);
        classes.add(DefectResource.class);
        classes.add(DefectRecordResource.class);
        classes.add(EmployeeResource.class);
        classes.add(StatisticsResource.class);
        classes.add(StatisticsEctypeResource.class);
        classes.add(DefectRecordEctypeResource.class);
        classes.add(AllTerminalResource.class);
        return classes;
    }

}
