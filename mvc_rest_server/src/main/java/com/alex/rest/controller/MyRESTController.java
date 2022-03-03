package com.alex.rest.controller;

import com.alex.rest.entity.Employee;
import com.alex.rest.exception_handling.NoSuchEmployeeException;
import com.alex.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    //получение всех работников
    @GetMapping("/employees")
    public List<Employee> showAllEmployees (){
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    //получение работника по id
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id){

        Employee employee = employeeService.getEmployee(id);

        if(employee == null){
            throw new NoSuchEmployeeException("Employee " + id + " is empty");
        }
        return employee;
    }

    //добавление нового работника
    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){

        employeeService.saveEmployee(employee);
        return employee;
    }

    //обновление существующего работника
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){

        employeeService.saveEmployee(employee);
        return employee;
    }

    //удаление работника
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){

        Employee employee = employeeService.getEmployee(id);
        if(employee==null){
            throw new NoSuchEmployeeException("Employee " + id + " is empty");
        }

        employeeService.deleteEmployee(id);
        return "Employee with " + id + "was deleted";
    }
}
