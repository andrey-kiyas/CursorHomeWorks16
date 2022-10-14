package com.CursorHomeWorks16.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserDto {

    @NotNull(message = "Please provide a firstName")
    private String firstName;
    @NotNull(message = "Please provide a lastName")
    private String lastName;
    @NotNull(message = "Please provide an age")
    @Min(value = 0, message = "age cannot be less than 0")
    @Max(value = 100, message = "Max age is 100")
    private int age;

}
