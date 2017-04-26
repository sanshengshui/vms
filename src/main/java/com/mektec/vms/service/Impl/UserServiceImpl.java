package com.mektec.vms.service.Impl;

import com.mektec.vms.domain.WorkStation;
import com.mektec.vms.mapper.UserMapper;
import com.mektec.vms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<WorkStation.User> findAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public void createUser(WorkStation.User user) {
        userMapper.createUser(user);
    }

    @Override
    public WorkStation.User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public WorkStation.User findUserByAccount(String account) {
        return userMapper.findUserByAccount(account);
    }

    @Override
    public void updateUser(WorkStation.User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(WorkStation.User user) {
        userMapper.deleteUser(user);
    }


    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
