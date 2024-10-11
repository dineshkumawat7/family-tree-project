package com.ebit.tree.family.controller;

import com.ebit.tree.family.entity.Person;
import com.ebit.tree.family.payload.PersonAddDto;
import com.ebit.tree.family.service.PersonServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServiceImpl personService;

    @PostMapping("/add")
    public ResponseEntity<?> addPerson(@Valid @RequestBody PersonAddDto personAddDto){
        System.out.println(personAddDto);
//        Person addedPerson = personService.savePerson(personAddDto);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAllPersons(@PathVariable("id") Long id){
        return ResponseEntity.ok(personService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPersons(){
        return ResponseEntity.ok(personService.getAllPersons());
    }
}
