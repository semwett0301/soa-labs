package com.example.studygroup.mapper

import com.example.studygroup.dto.StudyGroupCreationRequest
import com.example.studygroup.entity.StudyGroup

fun StudyGroupCreationRequest.toEntity()=
    StudyGroup(
        name = this.name,
        studentsCount =  this.studentsCount,
        formOfEducation =  this.formOfEducation,
        semesterEnum =  this.semesterEnum,
        groupAdmin = this.groupAdmin?.toEntity()
    )