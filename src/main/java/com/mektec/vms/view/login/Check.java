package com.mektec.vms.view.login;

import com.mektec.vms.MyUI;
import com.mektec.vms.domain.WorkStation;
import com.mektec.vms.service.UserService;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import java.util.List;

/**
 * Created by mektec on 16-5-17.
 */
public class Check extends CheckUI {
    private UserService userService;

    MyUI ui;
    Integer count = 1;

    public Check() {

        ui = (MyUI) UI.getCurrent();
        userService = (UserService) ui.getBean("userService");

        List<WorkStation.User> userList = userService.findAllUsers();
        for(WorkStation.User user : userList){
            if(user.getDeleted() == true){
                addUserRow(user);
                count+=1;
            }
        }
    }

    public void addUserRow(WorkStation.User user){
        //注册序号
        TextField userSnText = new TextField();
        userSnText.addFocusListener(event -> userItemTable.select(user));
        userSnText.setWidth(50, Unit.POINTS);
        userSnText.setValue(count.toString());
        userSnText.setReadOnly(true);

        //注册姓名
        TextField userNameText = new TextField();
        userNameText.addFocusListener(event -> userItemTable.select(user));
        userNameText.setWidth(75, Unit.POINTS);
        userNameText.setValue(String.valueOf(user.getUserName()));
        userNameText.setReadOnly(true);

        //注册帐号
        TextField userAccountText = new TextField();
        userAccountText.addFocusListener(event -> userItemTable.select(user));
        userAccountText.setWidth(80, Unit.POINTS);
        userAccountText.setValue(String.valueOf(user.getAccount()));
        userAccountText.setReadOnly(true);

        //注册密码
        TextField userPassText = new TextField();
        userPassText.addFocusListener(event ->  userItemTable.select(user));
        userPassText.setWidth(75, Unit.POINTS);
        userPassText.setValue(String.valueOf(user.getPassword()));
        userPassText.setReadOnly(true);

        //注册邮箱
        TextField userEmailText = new TextField();
        userEmailText.addFocusListener(event -> userItemTable.select(user));
        userEmailText.setWidth(100, Unit.POINTS);
        userEmailText.setValue(String.valueOf(user.getEmail()));
        userEmailText.setReadOnly(true);

        //注册电话
        TextField userPhoneText = new TextField();
        userPhoneText.addFocusListener(event ->  userItemTable.select(user));
        userPhoneText.setWidth(90, Unit.POINTS);
        userPhoneText.setValue(String.valueOf(user.getPhoneNumber()));
        userPhoneText.setReadOnly(true);

        //确认按钮
        Button ensureButton = new Button("审核通过");
        ensureButton.addClickListener(event ->{
            userItemTable.removeAllItems();

            userService.updateUser(user);

            count = 1;
            List<WorkStation.User> userList = userService.findAllUsers();
            for(WorkStation.User user1 : userList){
                if(user1.getDeleted() == true){
                    addUserRow(user1);
                    count+=1;
                }
            }
        });

        //取消按钮
        Button cancelButton = new Button("审核失败");
//        ensureButton.addClickListener(event ->{
//            userItemTable.removeAllItems();
//
//            userService.deleteUser(user);
//
//            List<User> userList = userService.findAllUsers();
//            for(User user1 : userList){
//                if(user1.getDeleted() == true){
//                    addUserRow(user1);
//                }
//            }
//        });

        userItemTable.addItem(new Object[] {
                        userSnText, userAccountText,
                        userNameText,userPassText,
                        userEmailText, userPhoneText,
                        ensureButton,cancelButton},
                user);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
