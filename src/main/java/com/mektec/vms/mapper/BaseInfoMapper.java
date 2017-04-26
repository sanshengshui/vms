package com.mektec.vms.mapper;

import com.mektec.vms.domain.BaseInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;



public interface BaseInfoMapper {

    @Insert("INSERT INTO BaseInfo( \n" +
            "   baseId, value, purpose, \n" +
            "   createUser, createTime, deleted) \n" +
            "VALUES(#{baseId}, #{value}, #{purpose}, \n" +
            "   #{createUser}, #{createTime}, '0')")
    public int insertInfo(BaseInfo info);

    @Select("SELECT * FROM BaseInfo WHERE id = #{id}")
    public BaseInfo selectInfoById(String id);

    @Select("SELECT * FROM BaseInfo WHERE purpose = #{purpose}")
    public List<BaseInfo> selectInfoByPurpose(String purpose);


    @Update("UPDATE BaseInfo \n" +
            "SET value = #{value},\n" +
            "   purpose = #{purpose},\n" +
            "   updateUser = #{updateUser},\n" +
            "   updateTime = #{updateTime}\n" +
            "WHERE id = #{id}")
    public int updateInfo(BaseInfo info);

    @Delete("UPDATE BaseInfo \n" +
            "SET deleted = '1',\n" +
            "   updateUser = #{updateUser},\n" +
            "   updateTime = #{updateTime}\n" +
            "WHERE id = #{id}")
    public int deleteInfo(BaseInfo info);
}
