package com.mektec.vms;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import com.mektec.vms.domain.ProductLine;
import com.mektec.vms.view.main.MainViewUI;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedHttpSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.UI;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("com.mektec.vms.MyAppWidgetset")
public class MyUI extends UI {

    private ApplicationContext appContext;
    private ProductLine line;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        loadContextConfig(vaadinRequest);

        MainViewUI ui = new MainViewUI();
        this.setContent(ui);
//        PieChart pieChartDemo = new PieChart();
//        this.setContent(pieChartDemo);
    }

    public Object getBean(String bean) {
        return getAppContext().getBean(bean);
    }

    public ApplicationContext getAppContext() {
        return appContext;
    }

    public void loadContextConfig(VaadinRequest vaadinRequest) {
        WrappedSession session = vaadinRequest.getWrappedSession();
        HttpSession httpSession = ((WrappedHttpSession)session).getHttpSession();
        ServletContext servletContext = httpSession.getServletContext();
        appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }

    public ProductLine getLine() {
        return line;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
