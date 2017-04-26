package com.mektec.vms.mapper;

import com.mektec.vms.domain.ProductLine;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
public interface ProductLineMapper {

    @Insert("INSERT INTO ProductLine( \n" +
            "lineId,lineCode,locationId, defectVersion, \n" +
            "createUser,createTime,updateUser,updateTime,deleted) \n" +
            "VALUES (\n" +
            "#{lineId},#{lineCode},#{location.locationId}, #{defectVersion},\n" +
            "#{createUser},#{createTime},#{updateUser},#{updateTime},'0')")
    int createProductLine(ProductLine productLine);

    @Select("SELECT * FROM ProductLine WHERE lineId = #{lineId}")
    @Results({
            @Result(property = "location", column = "locationId",
                    one=@One(select = "com.mektec.vms.mapper.LocationMapper.findLocationById"))})
    ProductLine findLineById(String lineId);

    @Update("UPDATE ProductLine \n" +
            "SET lineCode = #{lineCode},locationId = #{location.locationId}, \n" +
            "defectVersion = #{defectVersion}, \n"+
            "createUser = #{createUser}, createTime = #{createTime}, \n" +
            "updateUser = #{updateUser}, updateTime = #{updateTime} \n" +
            "WHERE lineId = #{lineId}")
    int updateLine(ProductLine productLine);

    @Delete("UPDATE ProductLine SET deleted = '1' WHERE lineId = #{lineId}")
    int deleteLine(ProductLine productLine);

    //根据LocationId获取产线列表
    @Select("SELECT * FROM ProductLine \n" +
            "INNER JOIN Location ON ProductLine.locationId = Location.locationId \n" +
            "WHERE Location.locationId = #{locationId} \n" +
            "AND ProductLine.deleted = '0' ")
    List<ProductLine> findLineListByLocation(String locationId);


    //根据lineCode获取产线通过的passcount
    @Select("select * from ProductLine where lineCode=#{lineCode}")
    ProductLine findPassCountByLineCode(String lineCode);

    @Select("SELECT * FROM ProductLine WHERE ProductLine.deleted = '0'")
    @Results({
            @Result(property = "location", column = "locationId",
                    one=@One(select = "com.mektec.vms.mapper.LocationMapper.findLocationById"))})
    List<ProductLine> findAllLine();

    @Select("SELECT * FROM ProductLine WHERE ProductLine.deleted = '0' AND lineCode = #{lineCode}")
    @Results({
            @Result(property = "location", column = "locationId",
                    one=@One(select = "com.mektec.vms.mapper.LocationMapper.findLocationById"))})
    ProductLine findLineByLineCode(String lineCode);
}

