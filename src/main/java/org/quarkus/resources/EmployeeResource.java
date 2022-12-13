package org.quarkus.resources;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.quarkus.entities.Employee;
import org.quarkus.service.EmployeeService;

@Path("/emp")
public class EmployeeResource {

	@Inject
	EmployeeService employeeService;
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmpById(@PathParam("id") Long id) {
		return employeeService.getEmpById(id);
	}
	
	@GET
	@Path("/getAllEmp")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEmployee() {
		return employeeService.getAllEmp();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response saveEmp(Employee employee) {
		return employeeService.saveEmp(employee);
	}
	
	@PUT
	@Path("/updateEmployee/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Transactional
	public Response updateEmployee(@PathParam("id") Long id, Employee employee) {
		return employeeService.updateEmployee(id, employee);
	}
	
	@DELETE
	@Path("/deleteEmployeeById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Transactional
	public Response deleteEmployee(@PathParam("id") Long id) {
		return employeeService.deleteEmployee(id);
	}
}
