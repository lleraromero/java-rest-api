package com.example.controller;

import com.example.Main;
import com.example.domain.Employee;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Random;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeesResource {

    @GET
    public List<Employee> getAll() {
        Session session = Main.SESSION_FACTORY.openSession();
        return session.createQuery("from Employee order by id asc", Employee.class).list();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        Session session = Main.SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        Employee e1 = new Employee("pepe", DateTime.now().minusYears(new Random().nextInt(30)));
        session.persist(e1);
        transaction.commit();
        session.close();
        return Response.ok(e1).build();
    }

    @POST
    public Response create(Employee employee) {
        Session session = Main.SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(employee);
        transaction.commit();
        session.close();
        return Response.status(Response.Status.CREATED).entity(employee).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, Employee e) {
        Session session = Main.SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        if (employee != null) {
            employee.setName(e.getName());
            employee.setBirthday(e.getBirthday());
            session.persist(employee);
        }
        transaction.commit();
        session.close();
        if (employee == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(employee).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Session session = Main.SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        if (employee != null) {
            session.remove(employee);
        }
        transaction.commit();
        session.close();
        if (employee != null) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}