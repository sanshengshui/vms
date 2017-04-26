package com.mektec.vms.view.login;


import com.mektec.vms.MyUI;
import com.mektec.vms.service.UserService;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


public class LoginUI extends Window {

    public TextField accountField;
    public PasswordField passwordField;
    public Button okButton;
    public Button registButton;
    public Button ensureButton;

    public TextField regAccountField;
    public TextField regUserNameField;
    public PasswordField regPasswordField;
    public TextField regEmailField;
    public TextField regPhoneField;

    MyUI ui;

    public LoginUI() {
        super("登录确认");
        center();

        ensureLoginInfo();
    }

    private void ensureLoginInfo(){
        GridLayout layout = new GridLayout(1,3);

        //账号
        accountField = new TextField("用户");
        accountField.setInputPrompt("工号");
        accountField.setWidth(80, Sizeable.Unit.PERCENTAGE);
        accountField.focus();

        //密码
        passwordField = new PasswordField("密码");
        passwordField.setInputPrompt("********");
        passwordField.setWidth(80, Sizeable.Unit.PERCENTAGE);

        //登录按钮
        okButton = new Button("登录");
        okButton.addStyleName("primary");
        okButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        okButton.setWidth(80, Sizeable.Unit.POINTS);

        //注册按钮
        registButton = new Button("注册");
        registButton.setWidth(80, Sizeable.Unit.POINTS);
        registButton.addClickListener(e ->{
            createRegistLayout();
        });

        GridLayout gridLayout = new GridLayout(2,1);
        gridLayout.addComponent(okButton);
        gridLayout.addComponent(registButton);

        layout.addComponent(accountField);
        layout.addComponent(passwordField);
        layout.addComponent(gridLayout);
        layout.setComponentAlignment(accountField,Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(passwordField,Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(gridLayout,Alignment.MIDDLE_CENTER);
        layout.setRowExpandRatio(3,100);

        layout.setSpacing(true);
        layout.setMargin(true);
        layout.setSizeFull();
        this.setWidth(220, Unit.POINTS);
        this.setHeight(220, Unit.POINTS);
        this.setResizable(false);
        this.setResizable(false);
        this.setContent(layout);

        this.setDraggable(true);
        this.setClosable(true);
    }

    private void createRegistLayout() {
        //注册Tab
        GridLayout layout = new GridLayout(1,6);

        //账号
        regAccountField = new TextField("新用户");
        regAccountField.setInputPrompt("工号");
        regAccountField.setWidth(100, Sizeable.Unit.PERCENTAGE);

        //姓名
        regUserNameField = new TextField("姓名");
        regUserNameField.setInputPrompt("******");
        regUserNameField.setWidth(100, Sizeable.Unit.PERCENTAGE);

        //密码
        regPasswordField = new PasswordField("密码");
        regPasswordField.setInputPrompt("******");
        regPasswordField.setWidth(100, Sizeable.Unit.PERCENTAGE);

        //邮件
        regEmailField = new TextField("E-Mail");
        regEmailField.setInputPrompt("308754761@qq.com");
        regEmailField.setWidth(100, Sizeable.Unit.PERCENTAGE);

        //Phone
        regPhoneField = new TextField("电话");
        regPhoneField.setInputPrompt("15501528821");
        regPhoneField.setWidth(100, Sizeable.Unit.PERCENTAGE);

        //确定按钮
        ensureButton = new Button("确定");
        ensureButton.addStyleName("primary");
        ensureButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        ensureButton.setWidth(80, Sizeable.Unit.POINTS);
        ensureButton.addClickListener(e ->{
            ensureLoginInfo();
        });

        layout.addComponent(regAccountField);
        layout.addComponent(regUserNameField);
        layout.addComponent(regPasswordField);
        layout.addComponent(regEmailField);
        layout.addComponent(regPhoneField);
        layout.addComponent(ensureButton);
        layout.setComponentAlignment(ensureButton, Alignment.MIDDLE_CENTER);
        layout.setRowExpandRatio(7,100);

        layout.setSpacing(true);
        this.setHeight(70,Unit.PERCENTAGE);
        this.setWidth(220, Unit.POINTS);
        layout.setMargin(true);
        layout.setSizeFull();
        this.setResizable(true);

        this.setContent(layout);
        this.setDraggable(true);
        this.setClosable(true);
    }


}
