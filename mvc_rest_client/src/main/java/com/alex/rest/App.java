package com.alex.rest;

import com.alex.rest.configuration.MyConfig;
import com.alex.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class App 
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication",Communication.class);

        //List<Employee> allEmployees =  communication.getAllEmployees(); // вывод всех работников
        //System.out.println(allEmployees);

        //Employee EmpById = communication.getEmployee(4); //вывод работника по ID
        //System.out.println(EmpById);

        //Employee emp = new Employee("George", "Bush", "USA", 189); //save or update
        //emp.setId(9);
        //communication.saveEmployee(emp);

        communication.deleteEmployee(9);  // delete

    }
}
