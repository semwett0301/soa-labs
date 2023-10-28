package com.example.studygroup.service

import com.example.studygroup.dto.GroupCountByNameResponse
import com.example.studygroup.dto.StudyGroupCreationRequest
import com.example.studygroup.entity.FormOfEducation
import com.example.studygroup.entity.Semester
import com.example.studygroup.entity.StudyGroup
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate


interface GroupService {

    fun getAllStudyGroups(
        pageable: Pageable,
        name: String?,
        studentsCount: Int?,
        formOfEducation: FormOfEducation?,
        semesterEnum: Semester?,
        creationDateEq: LocalDate?,
    ): Page<StudyGroup>

    fun createGroup(studyGroup: StudyGroupCreationRequest): StudyGroup
    fun getById(id: Int): StudyGroup
    fun updateById(id: Int, updatedStudyGroup: StudyGroupCreationRequest): StudyGroup
    fun deleteById(id: Int): Unit
    fun findMinAdmin(): StudyGroup
    fun findMaxAdmin(): StudyGroup
    fun groupCountByName(): List<GroupCountByNameResponse>

}
