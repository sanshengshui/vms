package com.mektec.vms.mapper;

import com.mektec.vms.domain.Lot;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
public interface LotMapper {

    @Insert("INSERT INTO Lot( \n" +
            "lotNum,modelNum,count, \n" +
            "createUser,createTime,updateUser,updateTime,deleted) \n" +
            "VALUES (\n" +
            "#{lotNum},#{modelNum},#{count}, \n" +
            "#{createUser},#{createTime},#{updateUser},#{updateTime},'0')")
    void createProductLot(Lot lot);

    @Select("SELECT * FROM Lot WHERE lotNum = #{lotNum}")
    Lot findLotByLotNum(String lotNum);

    @Update("UPDATE Lot \n" +
            "SET lotNum = #{lotNum},modelNum = #{modelNum},count = #{count}, \n" +
            "createUser = #{createUser},createTime = #{createTime},updateUser = #{updateUser}, \n" +
            "updateTime = #{updateTime} \n" +
            "WHERE lotNum = #{lotNum}")
    void updateLot(Lot lot);

    @Delete("UPDATE Lot SET deleted = '1' WHERE lotNum = #{lotNum}")
    void deleteLot(Lot lot);

    @Select("SELECT * FROM Lot WHERE deleted='0'")
    List<Lot> findAllLots();
}


