package org.quarkus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.quarkus.entities.Employee;
import org.quarkus.repositories.EmployeeRepository;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.transaction.annotations.Rollback;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(value = true)
public class EmployeeTest {

	
	@Inject
	EmployeeRepository employeeRepository;
	
	
	// Junit test for saveEmployee
		@Test
		@Order(1)
		@Transactional
		
		public void saveEmpTest() {
			Employee employee = new Employee();
			employee.setName("Neeraj Yadav");
			employee.setEmail("nrjydv1997@gmail.com");
			employee.setSalary(20000);
			employeeRepository.persist(employee);
			assertTrue(employeeRepository.isPersistent(employee));
		}
	
		@Test
		@Order(2)
		public void getEmpById() {
			Employee empById = employeeRepository.findById(41L);
			assertEquals("Neeraj Yadav",empById.getName());
		}
		
		@Test
		@Order(3)
		@Transactional
		public void updateEmployeeTest() {
			
			Employee updateEmployee = employeeRepository.findById(41L);

			updateEmployee.setEmail("neerajyadav1997@gmail.com");
			employeeRepository.persist(updateEmployee);
			
			assertEquals("neerajyadav1997@gmail.com", updateEmployee.getEmail());

		}
		
		@Test
		@Order(4)
		@Transactional
		public void deleteEmployee() {
			Employee employee = employeeRepository.findById(41L);

			employeeRepository.delete(employee);
			
			assertFalse(employeeRepository.isPersistent(employee));
		}
	
}
