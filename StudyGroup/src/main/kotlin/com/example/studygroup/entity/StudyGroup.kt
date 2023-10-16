package com.example.studygroup.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
class StudyGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var name: String,
    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    val coordinates: Coordinates,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    var studentsCount: Long? = null,//Значение поля должно быть больше 0, Поле может быть null
    @Enumerated(EnumType.STRING)
    var formOfEducation: FormOfEducation? = null,
    @Enumerated(EnumType.STRING)
    var semesterEnum: Semester? = null,
    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var groupAdmin: Person? = null
)