package com.ebit.tree.family.service;

import com.ebit.tree.family.entity.Person;
import com.ebit.tree.family.exception.PersonNotFoundException;
import com.ebit.tree.family.payload.PersonAddDto;
import com.ebit.tree.family.payload.PersonGetDto;
import com.ebit.tree.family.repository.PersonRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService{
    @Autowired
    private PersonRepo personRepo;

    public Person savePerson(PersonAddDto personAddDto) {
        Person person = new Person();
        person.setName(personAddDto.getName());
        person.setGeneration(personAddDto.getGeneration());
        person.setGender(personAddDto.getGender());
        person.setBirthdate(personAddDto.getBirthdate());
        person.setFather(personAddDto.getFather());
        person.setMother(personAddDto.getMother());
        person.setImage(person.getImage());

        return personRepo.save(person);
    }

    public List<PersonGetDto> getAllPersons() {
        return personRepo.findAll().stream()
                .map(person -> new PersonGetDto(person.getId(), person.getName(), person.getGeneration(),
                        person.getGender(), person.getBirthdate(), person.getChildrenFromFather(), person.getFather(), person.getMother()))
                .collect(Collectors.toList());
    }

    public PersonGetDto getById(Long id) {
        Optional<Person> person = personRepo.findById(id);
        if (person.isPresent()) {
            Person existsPerson = person.get();
            PersonGetDto personGetDto = new PersonGetDto();
            personGetDto.setId(existsPerson.getId());
            personGetDto.setName(existsPerson.getName());
            personGetDto.setGender(existsPerson.getGender());
            personGetDto.setGeneration(existsPerson.getGeneration());
            personGetDto.setBirthdate(existsPerson.getBirthdate());
            personGetDto.setChildren(existsPerson.getChildrenFromFather());
            personGetDto.setFather(existsPerson.getFather());
            personGetDto.setMother(existsPerson.getMother());
            return personGetDto;
        }
        throw new PersonNotFoundException("person not found with id: " + id);
    }

    public void generateFamilyTree(int generations) {
        // Starting parents (generation 1)
        Person father = new Person();
        father.setName("John Sr");
        father.setGender("M");
        father.setBirthdate(new Date());
        Person mother = new Person();
        mother.setName("Jane Sr");
        mother.setGender("F");
        mother.setBirthdate(new Date());
        // Save root parents
        father = personRepo.save(father);
        mother = personRepo.save(mother);

        List<Person> previousGeneration = new ArrayList<>();
        previousGeneration.add(father);
        previousGeneration.add(mother);

        // Create children for each generation
        for (int gen = 2; gen <= generations; gen++) {
            List<Person> currentGeneration = new ArrayList<>();

            for (int i = 0; i < previousGeneration.size() / 2; i++) {
                Person fatherFromPrevGen = previousGeneration.get(2 * i);
                Person motherFromPrevGen = previousGeneration.get(2 * i + 1);

                // Generate two children for each parent pair (this can be adjusted)
                Person child1 = new Person();
                child1.setName("Child " + gen + "-1");
                child1.setGender("M");
                child1.setBirthdate(new Date());
                Person child2 = new Person();
                child2.setName("Child " + gen + "-2");
                child2.setGender("F");
                child2.setBirthdate(new Date());

                // Set parents for child1 and child2
                child1.setFather(fatherFromPrevGen);
                child1.setMother(motherFromPrevGen);
                child2.setFather(fatherFromPrevGen);
                child2.setMother(motherFromPrevGen);

                // Save the children
                child1 = personRepo.save(child1);
                child2 = personRepo.save(child2);

                // Add children to current generation list
                currentGeneration.add(child1);
                currentGeneration.add(child2);
            }

            // Move to the next generation
            previousGeneration = currentGeneration;
        }
    }
}
