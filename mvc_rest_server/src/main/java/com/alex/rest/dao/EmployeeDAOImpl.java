package com.alex.rest.dao;

import com.alex.rest.entity.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private SessionFactory sessionFactory;

    //получение списка работников из базы данных
    @Override
    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession();
        List<Employee> allEmployees = session.createQuery("from Employee", Employee.class).getResultList();
        return allEmployees;
    }

    //сохранение или обновление работников в базе данных
    @Override
    public void saveEmployee(Employee employee) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);

    }

    //получение работника из базы данных по Id
    @Override
    public Employee getEmployee(int id) {

        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    //удаление работника из базы данных
    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Employee " + "where id =: employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
