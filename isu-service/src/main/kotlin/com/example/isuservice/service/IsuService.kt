package com.example.isuservice.service

import entity.FormOfEducation
import entity.StudyGroup
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service

@Service
class IsuService(
    val studyGroupApi: StudyGroupApi
) {
    fun expelAll(id: Int): Result {
        val studyGroup = when (val studyGroupById = studyGroupApi.studyGroupById(id)) {
            is Result.Error -> return studyGroupById
            is Result.Success -> studyGroupById.data
        }.apply {
            studentsCount = 0
            groupAdmin = null
        }
        return studyGroupApi.putStudyGroup(studyGroup)
    }

    fun changeEduForm(id: Int, newFormOfEducation: FormOfEducation): Result {
        val studyGroup = when (val studyGroupById = studyGroupApi.studyGroupById(id)) {
            is Result.Error -> return studyGroupById
            is Result.Success -> studyGroupById.data
        }.apply {
            formOfEducation = newFormOfEducation
        }
        return studyGroupApi.putStudyGroup(studyGroup)
    }

}

sealed interface Result {
    data class Success(val data: StudyGroup) : Result
    data class Error(val status: HttpStatusCode, val message: String?) : Result

}