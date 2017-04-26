package com.mektec.vms.service.Impl;

import com.mektec.vms.domain.Lot;
import com.mektec.vms.mapper.LocationMapper;
import com.mektec.vms.mapper.LotMapper;
import com.mektec.vms.service.LotService;
import com.mektec.vms.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
@Service
@Transactional
public class LotServiceImpl implements LotService{

    private LotMapper lotMapper;

    private SettingService settingService;

    @Override
    public void createLot(Lot lot) {

        lotMapper.createProductLot(lot);
    }

    @Override
    public Lot findLotByLotNum(String lotNum) {
        // 先在本系统的数据库中查询，
        // 如果查询不到，再通过基本的JDBC连接到AS400进行查询，并将查询结果插入本系统数据库
        Lot lot = lotMapper.findLotByLotNum(lotNum);
        if(lot == null){
            //通过基本的JDBC连接到AS400进行查询
            lot = findLotFromMRP(lotNum);
            if(lot != null) {
                lotMapper.createProductLot(lot);
            }
        }

        return lot;
    }

    @Override
    public void updateLot(Lot lot) {

        lotMapper.updateLot(lot);
    }

    @Override
    public void deleteLot(Lot lot) {

        lotMapper.deleteLot(lot);
    }

    @Override
    public List<Lot> findAllLots() {
        return lotMapper.findAllLots();
    }

    private Lot findLotFromMRP(String lotNum) {
        String host = settingService.getLocalValue("MRP_HOST","192.168.208.252");
        String db = settingService.getLocalValue("MRP_DB", "MEKFLIB");
        String user = settingService.getLocalValue("MRP_USER", "mmcsusr");
        String password = settingService.getLocalValue("MRP_PWD", "mmcsusr");

        Lot lot = null;
        Connection connection = null;
        try{
            Class.forName("com.ibm.as400.access.AS400JDBCConnection");
            connection = DriverManager.getConnection(
                    "jdbc:as400://" + host + "/" + db,
                    user,
                    password);

            PreparedStatement statement = connection.
                    prepareStatement("SELECT ZHHMCD FROM MEKFLIB.MZSODRP \n" +
                                        "WHERE ZHSZNO = ? AND  ZHLTNO = ?");
            statement.setString(1,lotNum.substring(0,lotNum.length() - 3));
            statement.setString(2,lotNum.substring(lotNum.length()-3,lotNum.length()));

            //执行语句并返回结果
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                lot  = new Lot();
                lot.setLotNum(lotNum);
                lot.setModelNum(resultSet.getString("ZHHMCD").trim());
            }

        }catch(Exception e){
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return lot;
    }
    @Autowired
    public void setLotService(LotMapper lotMapper) {
        this.lotMapper = lotMapper;
    }

    @Autowired
    public void setSettingService(SettingService settingService) {
        this.settingService = settingService;
    }
}
