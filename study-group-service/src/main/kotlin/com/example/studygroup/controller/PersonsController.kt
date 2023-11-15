package com.example.studygroup.controller

import entity.Person
import interfaces.PersonService
import interfaces.TryInterface
import org.springframework.context.annotation.DependsOn
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/persons")
@DependsOn("context")
class PersonsController(val personService: PersonService, val tryInterface: TryInterface) {
    @GetMapping
    fun getAllPersons(): MutableList<Person> {
        if (personService.all != null) {
            return personService.all
        }

        return arrayListOf()
    }

    @GetMapping("/try")
    fun getTry(): String {
        return tryInterface.nothing()
    }
}