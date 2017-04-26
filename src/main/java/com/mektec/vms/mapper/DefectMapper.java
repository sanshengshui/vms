package com.mektec.vms.mapper;

import com.mektec.vms.domain.Defect;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
public interface DefectMapper {

    @Insert("INSERT INTO Defect( \n" +
            "defectId,defectName,shortcut,baseId,stationId, \n" +
            "createUser,createTime,updateUser,updateTime,deleted) \n" +
            "VALUES (\n" +
            "#{defectId},#{defectName},#{shortcut},#{baseId},#{workStation.stationId}, \n" +
            "#{createUser},#{createTime},#{updateUser},#{updateTime},'0')")
    int createDefect(Defect defect);

    @Select("SELECT * FROM Defect WHERE defectId = #{defectId}")
    @Results({
            @Result(property = "workStation", column = "stationId",
                    one=@One(select = "com.mektec.vms.mapper.WorkStationMapper.findWorkStationById"))})
    Defect findDefectById(String defectId);

    @Update("UPDATE Defect \n" +
            "SET defectName = #{defectName},shortcut = #{shortcut},baseId = #{baseId}, \n" +
            "createUser = #{createUser},createTime = #{createTime},updateUser = #{updateUser}, \n" +
            "updateTime = #{updateTime},stationId = #{workStation.stationId} \n" +
            "WHERE defectId = #{defectId}")
    int updateDefect(Defect defect);

    @Delete("UPDATE Defect SET deleted = '1' WHERE defectId = #{defectId}")
    int deleteDefect(Defect defect);


    //根据产线ID获取缺陷列表
    @Select("SELECT * FROM Defect INNER JOIN WorkStation \n" +
            "ON Defect.stationId = WorkStation.stationId WHERE WorkStation.lineId =#{lineId} \n" +
            "AND Defect.deleted = '0'")
    List<Defect> findDefectListByLine(String lineId);

    @Select("SELECT * FROM Defect INNER JOIN WorkStation \n" +
            "ON Defect.stationId = WorkStation.stationId WHERE WorkStation.stationId =#{stationId} \n" +
            "AND Defect.deleted = '0'")
    List<Defect> findDefectListByStationId(String stationId);
}

