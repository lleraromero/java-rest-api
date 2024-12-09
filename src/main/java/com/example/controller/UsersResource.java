package com.example.controller;

import com.example.domain.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {

    private static ObjectMapper MAPPER = new ObjectMapper()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    @GET
    public List<User> getAll() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://reqres.in/api/users?page=2"))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            String body = response.body();
            Map<String, Object> responseMap = MAPPER.readValue(body, Map.class);
            List<Object> data = (List<Object>) responseMap.get("data");
            var result = new ArrayList<User>();
            for (Object u : data) {
                Map<String, Object> uMap = (Map<String, Object>) u;
                result.add(new User(Integer.parseInt(String.valueOf(uMap.get("id"))),
                        String.valueOf(uMap.get("email")),
                        String.valueOf(uMap.get("first_name")),
                        String.valueOf(uMap.get("last_name"))));
            }
            return result;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
