package com.ebit.tree.family.payload;

import com.ebit.tree.family.entity.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonAddDto {
    private Long id;

    @NotNull(message = "name is required")
    private String name;

    private Integer generation;

    @NotNull(message = "gender is required")
    private String gender;

    private Date birthdate;

    private Person mother;

    private Person father;
}
