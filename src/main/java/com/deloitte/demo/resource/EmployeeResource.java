// EmployeeResource.java 

package com.deloitte.demo.resource;

import java.util.List;

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

import com.deloitte.demo.model.Employee;
import com.deloitte.demo.service.EmployeeService;

@Path("/employees")
public class EmployeeResource {

	private EmployeeService empService = new EmployeeService();
	// private EmployeeService empService = EmployeeService.getInstance();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getAllEmployees() {
		return empService.getAllEmployees();
	}

	@POST
	@Path("/addEmployee")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEmployee(Employee employee) {
		Employee emp = empService.addEmployee(employee);
		return Response.status(Response.Status.CREATED).entity(emp).header("message", "employee added successfully!")
				.build();
	}

	@GET
	@Path("/employee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployeeById(@PathParam("id") int Id) {
		return empService.getEmployeeById(Id);
	}

	// implement these methods -
	// getEmployeeById
	// updateEmployee
	// deleteEmployee

	@PUT
	@Path("/updateEmployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee(@PathParam("id") int id, Employee employee) {
		Employee emp = empService.updateEmployee(id, employee);
		if (emp != null) {
			return Response.status(Response.Status.OK).entity(emp)
					.header("message", "Employee updated successfully!")
					.build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
		}
	}

	@DELETE
	@Path("/DeleteEmployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEmployeeById(@PathParam("id") int Id) {
		if (empService.deleteEmployeeById(Id)) {
			return Response.status(Response.Status.OK)
					.entity("Employee with ID " + Id + " deleted successfully")
					.header("message", "Employee deleted successfully!")
					.build();
		} else
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Employee with ID " + Id + " not found")
					.header("message", "No employee found with ID " + Id)
					.build();
	}
}

// Delete employee by ID
// @DELETE
// @Path("/employee/{id}")
// @Produces(MediaType.APPLICATION_JSON)
// public Response deleteEmployee(@PathParam("id") int id) {
// Employee employee = empService.getEmployeeById(id);
// if (employee != null) {
// empService.deleteEmployee(id);
// return Response.ok().header("message", "Employee deleted
// successfully!").build();
// } else {
// return Response.status(Response.Status.NOT_FOUND).entity("Employee not
// found").build();
// }
// }
