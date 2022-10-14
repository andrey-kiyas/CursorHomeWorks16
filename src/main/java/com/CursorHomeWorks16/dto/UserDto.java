package com.CursorHomeWorks16.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull(message = "Please provide a firstName")
    private String firstName;

    @NotNull(message = "Please provide a lastName")
    private String lastName;

    @NotNull(message = "Please provide an age")
    private int age;
}
