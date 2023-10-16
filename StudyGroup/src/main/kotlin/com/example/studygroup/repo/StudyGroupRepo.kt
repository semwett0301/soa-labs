package com.example.studygroup.repo

import com.example.studygroup.dto.GroupCountByNameResponse
import com.example.studygroup.entity.StudyGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface StudyGroupRepository : JpaRepository<StudyGroup, Int>, JpaSpecificationExecutor<StudyGroup> {
    fun findTopByOrderByGroupAdmin_HeightAsc(): StudyGroup
    fun findTopByOrderByGroupAdmin_HeightDesc(): StudyGroup


    @Query("select new com.example.studygroup.dto.GroupCountByNameResponse(stgr.name,count(stgr)) from StudyGroup stgr group by stgr.name")
    fun groupCountByName(): List<GroupCountByNameResponse>
}
