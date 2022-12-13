package org.quarkus.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.quarkus.entities.Employee;
import org.quarkus.repositories.EmployeeRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@ApplicationScoped
public class EmployeeService {

	@Inject
	EmployeeRepository employeeRepository;
	
	public Response getEmpById(Long id) {
		Employee empById = employeeRepository.findById(id);
		System.out.println("Employee ---"+empById.getId());
		
		
		  if (!empById.getName().isEmpty()) { 
			  return Response.ok(empById).build();
			  
			  }
		  else {
			  throw new WebApplicationException("Employee not found!", Response.Status.NOT_FOUND); 
		}
		 
		
	}
	
	public Response getAllEmp() {
		List<Employee> empList = (List<Employee>) employeeRepository.findAll().list();	
		
		 if (empList.isEmpty()) {
			 throw new WebApplicationException("Employee not found!", Response.Status.NOT_FOUND); }
		 
		return Response.ok(empList).build();
	}
	
	public Response saveEmp(Employee employee) {
		
			employeeRepository.persist(employee);
			
			return Response.ok(employee).status(Response.Status.CREATED).build();
	}
	
	public Response updateEmployee(Long id,Employee employee) {
		Employee updateEmp = employeeRepository.findById(id);
		if (updateEmp==null) {
			return Response.ok("Employee not found with id : "+id).status(Response.Status.NO_CONTENT).build();
		}
		updateEmp.setName(employee.getName());
		updateEmp.setEmail(employee.getEmail());
		updateEmp.setSalary(employee.getSalary());
		employeeRepository.persist(updateEmp);
		return Response.ok(updateEmp).status(Response.Status.CREATED).build();
	}
	
	public Response deleteEmployee(Long id) {
		boolean deleteById = employeeRepository.deleteById(id);
		if (deleteById) {
			return Response.ok("Employee deleted successfully").status(Response.Status.ACCEPTED).build();
		}else {
			return Response.ok("Employee not found with id : "+id).status(Response.Status.NOT_FOUND).build();
		}
	}
}
