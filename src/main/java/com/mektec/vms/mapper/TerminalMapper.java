package com.mektec.vms.mapper;

import com.mektec.vms.domain.Terminal;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * Terminal的操作映射
 * Created by mektec on 16-3-30.
 */
public interface TerminalMapper {

    @Insert("INSERT INTO Terminal( \n" +
            "terminalId,terminalCode,terminalName,terminalType, \n" +
            "stationId,createUser,createTime,updateUser,updateTime, \n" +
            "operator,deleted) \n" +
            "VALUES (\n" +
            "#{terminalId},#{terminalCode},#{terminalName},#{terminalType}, \n" +
            "#{workStation.stationId},#{createUser},#{createTime},#{updateUser},#{updateTime}, \n" +
            "#{operator},'0')")
    int createTerminal(Terminal terminal);

    @Select("SELECT * FROM Terminal WHERE terminalId = #{terminalId}")
    @Results({
            @Result(property = "workStation", column = "stationId",
                    one=@One(select = "com.mektec.vms.mapper.WorkStationMapper.findWorkStationById"))})
    Terminal findTerminalById(String terminalId);



    @Select("SELECT *  FROM Terminal WHERE terminalCode = #{terminalCode} AND deleted = '0'")
    @Results({
            @Result(property = "workStation", column = "stationId",
                    one=@One(select = "com.mektec.vms.mapper.WorkStationMapper.findWorkStationById"))})
    Terminal findTerminalByCode(String terminalCode);


    @Select("SELECT * FROM Terminal where stationId=#{stationId}")
    Terminal findTerminalByStation(String stationId);


    @Select("select terminalCode,terminalType from Terminal where terminalCode=#{terminalCode}")
    List<Terminal> findTerminalListByStationId(String terminalCode);

    @Select("SELECT * FROM Terminal \n" +
            "WHERE stationId  =#{stationId} AND deleted = '0'")
    @Results({
            @Result(property = "workStation", column = "stationId",
                    one=@One(select = "com.mektec.vms.mapper.WorkStationMapper.findWorkStationById"))})
    List<Terminal> findTerminalByStationId(String stationId);


    @Update("UPDATE Terminal \n" +
            "SET terminalCode = #{terminalCode},terminalName = #{terminalName}, \n" +
            "terminalType = #{terminalType},stationId = #{workStation.stationId}, \n" +
            "createUser = #{createUser},createTime = #{createTime},updateUser = #{updateUser}, \n" +
            "updateTime = #{updateTime},operator=#{operator} \n" +
            "WHERE terminalId = #{terminalId}")
    int updateTerminal(Terminal terminal);

    @Delete("UPDATE Terminal SET deleted = '1' WHERE terminalId = #{terminalId}")
    int deleteTerminal(Terminal terminal);

    @Select(" select terminalCode,terminalName from Terminal \n" +
            " inner join WorkStation on Terminal.stationId=WorkStation.stationId \n" +
            " where WorkStation.lineId=#{lineId} and Terminal.deleted='0' ")
    List<Terminal> findAllTerminalOfLine(String lineId);
}


