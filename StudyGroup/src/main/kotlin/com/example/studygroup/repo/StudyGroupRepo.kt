package com.example.studygroup.repo

import com.example.studygroup.entity.StudyGroup
import org.springframework.data.repository.PagingAndSortingRepository

interface StudyGroupRepo : PagingAndSortingRepository<StudyGroup, Int>