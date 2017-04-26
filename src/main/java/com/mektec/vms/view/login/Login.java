package com.mektec.vms.view.login;

import com.mektec.common.UID;
import com.mektec.vms.MyUI;
import com.mektec.vms.domain.WorkStation;
import com.mektec.vms.service.UserService;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class Login extends LoginUI {

    private UserService userService;

    public Login(){

        ui = (MyUI) UI.getCurrent();
        userService = (UserService) ui.getBean("userService");

        okButton.addClickListener(e ->{
            login();
        });

        registButton.addClickListener(e ->{
            regist();
        });
    }

    private void login() {
        if (accountField.getValue().isEmpty()) {
            accountField.focus();
            Notification.show("帐户不能为空!请重新输入 ");
            return;
        }

        if (passwordField.getValue().isEmpty()) {
            passwordField.focus();
            Notification.show("密码不能为空!请重新输入 ");
            return;
        }

        String account = accountField.getValue();
        String password = passwordField.getValue();

        WorkStation.User tempUser = userService.findUserByAccount(account);

        if (tempUser == null) {
            Notification.show("无此帐户! ");
            return;
        }

        if (!password.equals(tempUser.getPassword())) {

            Notification.show("无此帐户! ");
            passwordField.focus();
            return;
        }

        if(tempUser.getDeleted() == true){
            Notification.show("请等待审核! ");
            return;
        }
        else {
            Notification.show("登录成功！");
            this.setVisible(false);
        }
    }

    private void regist() {

        ensureButton.addClickListener(e ->{

            if(regAccountField.getValue().isEmpty()) {
                regAccountField.focus();
                Notification.show("信息不能为空!请重新输入 ");
                return;
            }

            if(regUserNameField.getValue().isEmpty()) {
                regUserNameField.focus();
                Notification.show("信息不能为空!请重新输入 ");
                return;
            }

            if(regPasswordField.getValue().isEmpty()) {
                regPasswordField.focus();
                Notification.show("信息不能为空!请重新输入 ");
                return;
            }

            if(regEmailField.getValue().isEmpty()) {
                regEmailField.focus();
                Notification.show("信息不能为空!请重新输入 ");
                return;
            }

            if(regPhoneField.getValue().isEmpty()) {
                regEmailField.focus();
                Notification.show("信息不能为空!请重新输入 ");
                return;
            }

            WorkStation.User existUser = userService.findUserByAccount(regAccountField.getValue());
            if (existUser != null) {
                    Notification.show("用户已经存在! ");
                    return;
            }
            else {

                WorkStation.User user = new WorkStation.User();
                user.setUserId(UID.generate("USR"));
                user.setAccount(regAccountField.getValue());
                user.setUserName(regUserNameField.getValue());
                user.setPassword(regPasswordField.getValue());
                user.setEmail(regEmailField.getValue());
                user.setPhoneNumber(regPhoneField.getValue());

                userService.createUser(user);
                user.setDeleted(true);
                Notification.show("您已成功注册, 请等待审核!");

            }
        });
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

