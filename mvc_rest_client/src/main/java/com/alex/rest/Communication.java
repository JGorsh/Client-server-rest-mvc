package com.alex.rest;

import com.alex.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/mvc_rest/api/employees";

    // получения списка работников
    public List<Employee> getAllEmployees(){
        ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Employee>>() {});

        List<Employee> allEmployees = responseEntity.getBody();

        return allEmployees;
    }

    //получение работника по id
    public Employee getEmployee(int id){

        Employee employee = restTemplate.getForObject(URL+"/"+ id, Employee.class);
        return employee;
    }

    //сохранение нового работника или обновление имеющегося
    public void saveEmployee(Employee employee){

        int id = employee.getId();

        if(id==0){
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, employee, String.class);
            System.out.println("New employee was added in DB");
            System.out.println(responseEntity.getBody());
        }

        else{
            restTemplate.put(URL, employee);
            System.out.println("Empployee with ID " + id + " was updated");
        }
    }

    //удаление работника
    public void deleteEmployee(int id){
        restTemplate.delete(URL+"/"+ id);

        System.out.println("Empployee with ID " + id + " was deleted");
    }
}
