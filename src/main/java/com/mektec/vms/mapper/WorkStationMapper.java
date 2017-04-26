package com.mektec.vms.mapper;

import com.mektec.vms.domain.WorkStation;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * Created by mektec on 16-3-30.
 */
public interface WorkStationMapper {

    @Insert("INSERT INTO WorkStation( \n" +
            "stationId,stationCode, stationName,lotNum,sn, \n" +
            "description,lineId,\n" +
            "createUser,createTime, \n" +
            "updateUser, updateTime, deleted) \n" +
            "VALUES (\n" +
            "#{stationId},#{stationCode},#{stationName},#{lotNum},#{sn}, \n"+
            "#{description},#{productLine.lineId},\n" +
            "#{createUser},#{createTime}, \n" +
            "#{updateUser}, #{updateTime},'0')")
    void createWorkStation(WorkStation workStation);

    @Select("SELECT * FROM WorkStation WHERE stationId = #{stationId}")
    @Results({
            @Result(property = "productLine", column = "lineId",
                    one=@One(select = "com.mektec.vms.mapper.ProductLineMapper.findLineById"))})
    WorkStation findWorkStationById(String stationId);

    @Select("SELECT * FROM WorkStation WHERE stationCode = #{stationCode} AND deleted='0' ")
    @Results({
            @Result(property = "productLine", column = "lineId",
                    one=@One(select = "com.mektec.vms.mapper.ProductLineMapper.findLineById"))})
    WorkStation findWorkStationByCode(String stationCode);


    @Update("UPDATE WorkStation \n" +
            "SET stationCode = #{stationCode},stationName = #{stationName}, \n" +
            "lotNum = #{lotNum},sn = #{sn},description = #{description}, \n" +
            "lineId = #{productLine.lineId},createUser = #{createUser},createTime = #{createTime}, \n" +
            "updateUser = #{updateUser},updateTime = #{updateTime} \n" +
            "WHERE stationId = #{stationId}")
    int updateWorkStation(WorkStation workStation);

    @Delete("UPDATE WorkStation SET deleted = '1' WHERE stationId = #{stationId}")
    int deleteWorkStation(WorkStation workStation);

    @Select("SELECT * FROM WorkStation WHERE deleted='0'")
    @Results({
            @Result(property = "productLine", column = "lineId",
                    one=@One(select = "com.mektec.vms.mapper.ProductLineMapper.findLineById"))})
    List<WorkStation> findAllWorkStations();

    @Select("SELECT * FROM WorkStation INNER JOIN ProductLine \n" +
            "ON WorkStation.lineId = ProductLine.lineId \n" +
            "WHERE ProductLine.lineCode = #{lineCode} AND \n" +
            "WorkStation.deleted = '0'")
    List<WorkStation> findWorkStationsByLineCode(String lineCode);


}

