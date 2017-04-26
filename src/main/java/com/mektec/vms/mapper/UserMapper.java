package com.mektec.vms.mapper;

import com.mektec.vms.domain.WorkStation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface UserMapper {

    @Insert("INSERT INTO User (\n" +
            "   userId, userName, account, password, \n" +
            "   email, phoneNumber, deleted) \n" +
            "VALUES (\n" +
            "   #{userId}, #{userName}, #{account}, #{password}, \n" +
            "   #{email}, #{phoneNumber}, '1')")
    public int createUser(WorkStation.User user);

    @Select("SELECT * FROM User WHERE userId = #{userId}")
    public WorkStation.User findUserById(String userId);

    @Select("SELECT * FROM User ")
	public List<WorkStation.User> selectAllUsers();

	@Select("SELECT * FROM User WHERE account = #{account}")
	public WorkStation.User findUserByAccount(String account);

    @Update("UPDATE User \n" +
            "SET userName = #{userName}, account = #{account}, password = #{password}, \n" +
            "   email = #{email}, phoneNumber = #{phoneNumber}, deleted = '0' \n" +
            "WHERE userId = #{userId}")
	public int updateUser(WorkStation.User user);

    @Delete("UPDATE User SET deleted = '1' WHERE userId = #{userId}")
    public int deleteUser(WorkStation.User user);

}
