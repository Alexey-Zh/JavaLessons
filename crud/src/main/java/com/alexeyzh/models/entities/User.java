package com.alexeyzh.models.entities;

import com.alexeyzh.config.CustomBooleanSerializer;
import com.alexeyzh.config.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(schema = "test", name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Column(name = "name")
    public String name;

    @NotNull
    @Column(name = "age")
    public int age;

    @NotNull
    @Column(name = "isAdmin", columnDefinition = "bit", length = 1)
    @JsonSerialize(using = CustomBooleanSerializer.class)
    public boolean isAdmin;

    @NotNull
    @Column(name = "createDate")
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date createDate;

    public User() {}

    public User(String name, int age, boolean isAdmin, Date createDate) {
        this.age = age;
        this.name = name;
        this.isAdmin = isAdmin;
        this.createDate = createDate;
    }

    public long getId() {
        return id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
