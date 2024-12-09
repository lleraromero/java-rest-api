package com.example;

import com.example.controller.EmployeesResource;
import com.example.controller.UsersResource;
import com.example.domain.Employee;
import com.fasterxml.jackson.core.util.JacksonFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

    public static SessionFactory SESSION_FACTORY;

    public static void main(String[] args) throws Exception {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Employee.class)
                .getMetadataBuilder()
                .build();

        ServletContextHandler handler = buildUsingResourceConfig();
        Server server = new Server(8080);
        server.setHandler(handler);
        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()) {
            SESSION_FACTORY = sessionFactory;
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }

    static ServletContextHandler buildUsingResourceConfig() {
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        handler.setContextPath("/");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule()); // Enable Joda-Time support
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // Use ISO-8601 format

        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(EmployeesResource.class);
        resourceConfig.register(UsersResource.class);
        resourceConfig.register(new JacksonJaxbJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));
        resourceConfig.register(JacksonFeature.class); // Register Jackson JSON provider)
        handler.addServlet(new ServletHolder(new ServletContainer(resourceConfig)), "/api/*");
        return handler;
    }
}
