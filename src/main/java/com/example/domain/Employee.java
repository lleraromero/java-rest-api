package com.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.joda.time.DateTime;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private DateTime birthday;

    public Employee() {
    }

    public Employee(String name, DateTime birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(DateTime birthday) {
        this.birthday = birthday;
    }
}
