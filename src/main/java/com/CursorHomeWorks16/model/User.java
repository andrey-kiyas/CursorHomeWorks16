package com.CursorHomeWorks16.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "MyUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;

}
