package org.quarkus.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.quarkus.entities.Employee;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped

public class EmployeeRepository implements PanacheRepository<Employee>{

}
