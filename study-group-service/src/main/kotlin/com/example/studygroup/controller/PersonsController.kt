package com.example.studygroup.controller

import com.example.studygroup.entity.Person
import com.example.studygroup.service.PersonService
import interfaces.TryInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin("*")
@RestController
@RequestMapping("/persons")
class PersonsController (val personService: PersonService, val tryBean: TryInterface){
    @GetMapping
    fun getAllPersons(): MutableList<Person> {
        return personService.getAll()
    }

    @GetMapping("/try")
    fun getTry(): String {
        return tryBean.nothing();
    }
}