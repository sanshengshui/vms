package com.mektec.vms.mapper;

import com.mektec.vms.domain.Location;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;



public interface LocationMapper {

    //基本CRUD
    @Insert("INSERT INTO Location( \n" +
            "locationId,locationName, \n" +
            "createUser,createTime,updateUser,updateTime,deleted) \n" +
            "VALUES (\n" +
            "#{locationId},#{locationName}, \n" +
            "#{createUser},#{createTime},#{updateUser},#{updateTime},'0')")
    int createLocation(Location location);

    @Select("SELECT * FROM Location WHERE locationId = #{locationId}")

    Location findLocationById(String locationId);

    @Update("UPDATE Location \n" +
            "SET locationName = #{locationName},createUser = #{createUser}, \n" +
            "createTime = #{createTime},updateUser = #{updateUser}, \n" +
            "updateTime = #{updateTime} \n" +
            "WHERE locationId = #{locationId}")
    int updateLocation(Location location);

    @Delete("UPDATE Location SET deleted = '1' WHERE locationId = #{locationId}")
    int deleteLocation(Location location);

    //获取所有位置
    @Select("SELECT * FROM Location")
    List<Location> findAllLocation();
}
