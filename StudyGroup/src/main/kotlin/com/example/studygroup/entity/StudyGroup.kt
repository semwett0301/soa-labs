package com.example.studygroup.entity

import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime


@Entity
class StudyGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var name: String,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val creationDate: LocalDate = LocalDate.now(),
    var studentsCount: Long? = null,//Значение поля должно быть больше 0, Поле может быть null
    @Enumerated(EnumType.STRING)
    var formOfEducation: FormOfEducation? = null,
    @Enumerated(EnumType.STRING)
    var semesterEnum: Semester? = null,
    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var groupAdmin: Person? = null
)