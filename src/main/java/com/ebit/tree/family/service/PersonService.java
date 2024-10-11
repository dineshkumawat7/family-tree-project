package com.ebit.tree.family.service;

import com.ebit.tree.family.entity.Person;
import com.ebit.tree.family.exception.PersonNotFoundException;
import com.ebit.tree.family.payload.PersonAddDto;
import com.ebit.tree.family.payload.PersonGetDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface PersonService {
    public Person savePerson(PersonAddDto personAddDto);

    public List<PersonGetDto> getAllPersons();

    public PersonGetDto getById(Long id);

    public void generateFamilyTree(int generations);
}
