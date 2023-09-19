package com.example.studygroup.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
class StudyGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String,
    @OneToOne
    val coordinates: Coordinates, //Поле не может быть null
    val creationDate: LocalDateTime? = LocalDateTime.now(),
    val studentsCount: Long? = null,//Значение поля должно быть больше 0, Поле может быть null
    @Enumerated(value = EnumType.STRING)
    val formOfEducation: FormOfEducation,
    @Enumerated(value = EnumType.STRING)
    val semesterEnum: Semester,
    @OneToOne
    val groupAdmin: Person
)