package com.example.studygroup.controller

import com.example.studygroup.entity.Person
import com.example.studygroup.service.PersonService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin("*")
@RestController
@RequestMapping("/persons")
class PersonsController (val personService: PersonService){
    @GetMapping
    fun getAllPersons(): MutableList<Person> {
        return personService.getAll()
    }
}