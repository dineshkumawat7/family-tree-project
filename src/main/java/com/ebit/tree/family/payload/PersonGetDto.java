package com.ebit.tree.family.payload;

import com.ebit.tree.family.entity.Person;
import com.ebit.tree.family.repository.PersonRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonGetDto {

    private Long id;

    private String name;

    private Integer generation;

    private String gender;

    private Date birthdate;

    private List<Person> children;

    private Person father;

    private Person mother;
}
