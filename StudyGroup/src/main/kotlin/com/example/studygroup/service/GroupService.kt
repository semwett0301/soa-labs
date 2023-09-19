package com.example.studygroup.service

import com.example.studygroup.entity.StudyGroup
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest


interface GroupService {
    fun getAllStudyGroups(pageRequest: PageRequest): Page<StudyGroup>

}
