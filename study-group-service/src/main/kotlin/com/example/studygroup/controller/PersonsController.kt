package com.example.studygroup.controller

import com.example.studygroup.entity.Person
import com.example.studygroup.service.PersonService
import interfaces.TryInterface
import org.springframework.context.annotation.DependsOn
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin("*")
@RestController
@RequestMapping("/persons")
@DependsOn("context")
class PersonsController(val personService: PersonService, val tryInterface: TryInterface) {
    @GetMapping
    fun getAllPersons(): MutableList<Person> {
        return personService.getAll()
    }

    @GetMapping("/try")
    fun getTry(): String {
        return tryInterface.nothing()
    }
}