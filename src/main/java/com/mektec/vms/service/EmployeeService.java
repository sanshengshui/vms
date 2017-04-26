package com.mektec.vms.service;

import com.mektec.vms.domain.Employee;



public interface EmployeeService {
    Employee findEmployeeById(String employeeId);
    Employee findEmployeeByCard(String cardCode);
}
