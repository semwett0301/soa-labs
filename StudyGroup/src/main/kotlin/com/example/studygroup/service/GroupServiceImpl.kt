package com.example.studygroup.service

import com.example.studygroup.entity.StudyGroup
import com.example.studygroup.repo.StudyGroupRepo
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class GroupServiceImpl(val repo: StudyGroupRepo) : GroupService {
    override fun getAllStudyGroups(pageRequest: PageRequest): Page<StudyGroup> {
        return repo.findAll(pageRequest)
    }

}