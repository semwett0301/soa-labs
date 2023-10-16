package com.example.studygroup.dto

import com.example.studygroup.entity.FormOfEducation
import com.example.studygroup.entity.Semester
import java.time.LocalDateTime

data class StudyGroupCreationRequest(
    val name: String,
    val coordinates: CoordinatesCreationRequest,
    val studentsCount: Long? = null,
    val formOfEducation: FormOfEducation,
    val semesterEnum: Semester,
    val groupAdmin: AdminCreationRequest?
)

data class CoordinatesCreationRequest(
    val x: Long,
    val y: Int
)

data class AdminCreationRequest(
    val name: String,
    val birthday: LocalDateTime,
    val height: Long = 0,
    val weight: Float = 0f,
    val passportID: String
)