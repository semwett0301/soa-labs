package com.example.isuservice.dto

import java.time.LocalDateTime


data class StudyGroup(
    var id: Int? = null,
    var name: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    var studentsCount: Long? = null,//Значение поля должно быть больше 0, Поле может быть null
    var formOfEducation: FormOfEducation? = null,
    var semesterEnum: Semester? = null,
    var groupAdmin: Person? = null
)
data class Person(
    val id: Int? = null,
    var name: String,
    var birthday: LocalDateTime,
    val height: Long = 0,
    val weight: Float = 0f,
    val passportID: String
)