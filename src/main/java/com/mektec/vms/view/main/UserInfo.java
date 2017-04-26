package com.mektec.vms.view.main;


import com.mektec.vms.domain.WorkStation;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

public class UserInfo extends GridLayout {
    private Image userPic;
    private Label userNameLabel;
    private WorkStation.User user;
    public UserInfo() {
        super(1, 2);
        setWidth(100, Unit.PERCENTAGE);
        setHeight(80, Unit.POINTS);
        userPic = new Image();
        userPic.setWidth(32, Unit.POINTS);
        userPic.setHeight(32, Unit.POINTS);
        userPic.setPrimaryStyleName("my-user-pic");
        userPic.setSource(LocalResource.get("images/user.jpg"));
        this.addComponent(userPic);
        this.setComponentAlignment(userPic, Alignment.MIDDLE_CENTER);

        userNameLabel = new Label();
        userNameLabel.setPrimaryStyleName("my-user-name");
        this.addComponent(userNameLabel);
        this.setComponentAlignment(userNameLabel, Alignment.MIDDLE_CENTER);
    }

    public WorkStation.User getUser() {
        return user;
    }

    public void setUser(WorkStation.User user) {
        this.user = user;
//        if(user.getPicture() == null || user.getPicture().isEmpty()) {
//            userPic.setSource(LocalResource.get("images/user.jpg"));
//        }
        userNameLabel.setValue(user.getUserName());
    }
}
