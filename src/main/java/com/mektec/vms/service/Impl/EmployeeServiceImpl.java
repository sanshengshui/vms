package com.mektec.vms.service.Impl;

import com.mektec.vms.domain.Employee;
import com.mektec.vms.domain.Lot;
import com.mektec.vms.service.EmployeeService;
import com.mektec.vms.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

/**
 * Created by tom on 16-4-13.
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private SettingService settingService;

    @Override
    public Employee findEmployeeById(String employeeId) {
        Employee employee  = null;
        Connection connection = null;
        try{
            connection = getHRConnection();

            PreparedStatement statement = connection.
                    prepareStatement("SELECT EID ,CARDNO, NAME \n" +
                            "FROM USER_BASE WHERE EID = ?");
            statement.setString(1,employeeId);

            //执行语句并返回结果
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee();
                employee.setEmployeeId(employeeId);
                employee.setCardCode(resultSet.getString("CARDNO").trim());
                employee.setEmployeeName(resultSet.getString("NAME").trim());
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

        return employee;
    }

    @Override
    public Employee findEmployeeByCard(String cardCode) {
        Employee employee  = null;
        Connection connection = null;
        try{
            connection = getHRConnection();

            PreparedStatement statement = connection.
                    prepareStatement("SELECT EID ,CARDNO, NAME \n" +
                            "FROM USER_BASE WHERE CARDNO = ?");
            statement.setString(1,cardCode);

            //执行语句并返回结果
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee();
                employee.setEmployeeId(resultSet.getString("EID").trim());
                employee.setCardCode(cardCode);
                employee.setEmployeeName(resultSet.getString("NAME").trim());
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

        if(employee == null) {
            employee = new Employee();
            employee.setEmployeeId(cardCode);
            employee.setCardCode(cardCode);
            employee.setEmployeeName("临时工");
        }

        return employee;
    }


    private Connection getHRConnection() throws ClassNotFoundException, SQLException {
        String host = settingService.getLocalValue("HR_HOST","192.168.208.207");
        String db = settingService.getLocalValue("HR_DB", "Manage");
        String user = settingService.getLocalValue("HR_USER", "suzmektec");
        String password = settingService.getLocalValue("HR_PWD", "suzmek");
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:jtds:sqlserver://"+ host + ":1433;DatabaseName="+ db,
                user,
                password);
    }

    @Autowired
    public void setSettingService(SettingService settingService) {
        this.settingService = settingService;
    }
}
