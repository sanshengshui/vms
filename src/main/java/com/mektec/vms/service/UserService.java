package com.mektec.vms.service;

import com.mektec.vms.domain.WorkStation;

import java.util.List;


public interface UserService {

	public List<WorkStation.User> findAllUsers();
	
	public void createUser(WorkStation.User user);

    public WorkStation.User findUserById(String userId);

	public WorkStation.User findUserByAccount(String account);

    public void updateUser(WorkStation.User user);

    public void deleteUser(WorkStation.User user);



	
}
