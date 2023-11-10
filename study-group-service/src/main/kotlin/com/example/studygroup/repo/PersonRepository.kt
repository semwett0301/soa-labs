package com.example.studygroup.repo

import com.example.studygroup.entity.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository :JpaRepository<Person,Int>
