package com.mektec.vms.mapper;

import com.mektec.vms.domain.DefectRecord;
import com.mektec.vms.domain.api.ApiDefectRecordCount;
import com.mektec.vms.domain.api.ApiDefectStatistics;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
public interface DefectRecordMapper {

    @Insert("INSERT INTO DefectRecord( \n" +
            "recordId,defectId,terminalId,stationId,lotNum, \n" +
            "count,date,time,recorder,responser,recordTime,deleted,goalTerminal,peopleNumber) \n" +
            "VALUES (\n" +
            "#{recordId},#{defect.defectId},#{terminalId}, \n" +
            "#{stationId},#{lotNum}, \n" +
            "#{count},#{date},#{time},#{recorder},#{responser},#{recordTime},'0', \n" +
            "#{goalTerminal},#{peopleNumber})")
    int createDefectRecord(DefectRecord record);

    @Select("SELECT * FROM DefectRecord WHERE recordId = #{recordId}")
    @Results({
            @Result(property = "defect", column = "defectId",
                    one=@One(select = "com.mektec.vms.mapper.DefectMapper.findDefectById"))})
    DefectRecord findRecordById(String recordId);


    @Select("SELECT defectId,sum(count) AS count,date,responser from DefectRecord where stationId=#{stationId}" +
            "GROUP BY defectId,date,responser")
    List<ApiDefectRecordCount> findDefectRecordSum(String stationId);

    @Select("select defectId,sum(count) as count,date,responser from DefectRecord \n" +
            "where stationId=#{param1} and recordTime=#{param2}" +
            "group by defectId")
    List<DefectRecord> sumByStation(String stationId,String recordTime);

    @Update("UPDATE DefectRecord \n" +
            "SET defectId = #{defect.defectId},terminalId = #{terminalId}, \n" +
            "stationId = #{stationId}, \n" +
            "lotNum = #{lotNum},count = #{count},date = #{date},time= #{time}, \n" +
            "recorder = #{recorder},responser = #{responser},recordTime = #{recordTime} \n" +
            "WHERE recordId = #{recordId}")
    int updateRecord(DefectRecord record);

    @Delete("UPDATE DefectRecord SET deleted = '1' WHERE recordId = #{recordId}")
    int deleteRecord(DefectRecord record);

    @Select("SELECT * FROM DefectRecord")
    @Results({
            @Result(property = "defect", column = "defectId",
                    one=@One(select = "com.mektec.vms.mapper.DefectMapper.findDefectById"))})
    List<DefectRecord> findAllRecord();



    @Select("select defectId,sum(count)  as count ,sum(percent) * #{param3} as percent,date,responser from DefectRecord \n" +
            "where goalTerminal=#{param1} and date=#{param2} \n" +
            "group by defectId")

    List<DefectRecord> findDefectRecordList(String terminalCode,String recordTime,int sum);
}

