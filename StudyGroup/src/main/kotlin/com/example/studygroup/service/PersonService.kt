package com.example.studygroup.service

import com.example.studygroup.entity.Person
import com.example.studygroup.repo.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService (val personRepository: PersonRepository){
    fun getAll(): MutableList<Person> {
        return personRepository.findAll()
    }
}