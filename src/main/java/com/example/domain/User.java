package com.example.domain;

public class User {
    private int id;
    private String email;
    private String firstName;
    private String lastName;

    public User(int id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


//    "id": 2,
//            "email": "janet.weaver@reqres.in",
//            "first_name": "Janet",
//            "last_name": "Weaver",
//            "avatar": "https://reqres.in/img/faces/2-image.jpg"
}
