package com.deloitte.demo.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.deloitte.demo.model.Department;
import com.deloitte.demo.service.DepartmentService;

@Path("/departments")
public class DepartmentResource {
    private DepartmentService departmentService = new DepartmentService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @POST
    @Path("/addDepartment")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(Department department) {
        Department dep = departmentService.addDepartment(department);
        return Response.status(Response.Status.CREATED).entity(dep).header("messsage", "department added successfully!")
                .build();
    }

    @GET
    @Path("/department/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Department> getDepartmentById(@PathParam("id") int Id) {
        return departmentService.getDepartmentById(Id);
    }

}
