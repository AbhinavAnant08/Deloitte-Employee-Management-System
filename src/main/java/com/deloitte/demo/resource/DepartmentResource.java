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

    @PUT
    @Path("/updateDepartment/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDepartment(@PathParam("id") int id, Department department) {
        Department dep = departmentService.updateDepartment(id, department);
        if (dep != null) {
            return Response.status(Response.Status.OK).entity(dep)
                    .header("message", "Department updated successfully!")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Department not found").build();
        }
    }

    @DELETE
    @Path("/DeleteDepartment/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDepartmentById(@PathParam("id") int Id) {
        if (departmentService.deleteDepartmentById(Id)) {
            return Response.status(Response.Status.OK)
                    .entity("Department with ID " + Id + " deleted successfully")
                    .header("message", "Department deleted successfully!")
                    .build();
        } else
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Department with ID " + Id + " not found")
                    .header("message", "No department found with ID " + Id)
                    .build();
    }

}
