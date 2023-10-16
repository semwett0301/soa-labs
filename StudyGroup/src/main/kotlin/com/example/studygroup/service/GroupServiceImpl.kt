package com.example.studygroup.service

import com.example.studygroup.dto.GroupCountByNameResponse
import com.example.studygroup.dto.StudyGroupCreationRequest
import com.example.studygroup.entity.Coordinates
import com.example.studygroup.entity.FormOfEducation
import com.example.studygroup.entity.Semester
import com.example.studygroup.entity.StudyGroup
import com.example.studygroup.mapper.toEntity
import com.example.studygroup.repo.StudyGroupRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.criteria.Predicate
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Transactional
class GroupServiceImpl(val repo: StudyGroupRepository) : GroupService {

    override fun getAllStudyGroups(
        pageable: Pageable,
        name: String?,
        coordinateXFrom: Int?,
        coordinateXTo: Int?,
        coordinateYFrom: Int?,
        coordinateYTo: Int?,
        creationDateFrom: LocalDateTime?,
        creationDateTo: LocalDateTime?,
        studentsCount: Int?,
        formOfEducation: FormOfEducation?,
        semesterEnum: Semester?
    ): Page<StudyGroup> {
        val spec = Specification<StudyGroup> { root, _, cb ->
            val predicates = mutableListOf<Predicate>()

            if (!name.isNullOrBlank()) {
                predicates.add(cb.like(root.get("name"), "%$name%"))
            }

            val coordinatesJoin = root.join<StudyGroup, Coordinates>("coordinates")
            coordinateXFrom?.let {
                predicates.add(cb.greaterThanOrEqualTo(coordinatesJoin.get("x"), it))
            }

            coordinateXTo?.let {
                predicates.add(cb.lessThanOrEqualTo(coordinatesJoin.get("x"), it))
            }

            coordinateYFrom?.let {
                predicates.add(cb.greaterThanOrEqualTo(coordinatesJoin.get("y"), it))
            }

            coordinateYTo?.let {
                predicates.add(cb.lessThanOrEqualTo(coordinatesJoin.get("y"), it))
            }
            creationDateFrom?.let { predicates.add(cb.greaterThanOrEqualTo(root.get("creationDate"), it)) }
            creationDateTo?.let { predicates.add(cb.lessThan(root.get("creationDate"), it)) }

            formOfEducation?.let { predicates.add(cb.equal(root.get<FormOfEducation>("formOfEducation"), it)) }
            semesterEnum?.let { predicates.add(cb.equal(root.get<Semester>("semesterEnum"), it)) }
            studentsCount?.let { predicates.add(cb.equal(root.get<Int>("studentsCount"), it)) }
            cb.and(*predicates.toTypedArray())
        }

        return repo.findAll(spec, pageable)
    }

    override fun createGroup(studyGroup: StudyGroupCreationRequest) {
        val newStudyGroup = studyGroup.toEntity()
        repo.save(newStudyGroup)
    }

    override fun getById(id: Int): StudyGroup = repo.findById(id).orElseThrow { EntityNotFoundException("group with Id:$id not found ") }
    override fun updateById(id: Int, updatedStudyGroup: StudyGroupCreationRequest): StudyGroup {
        val findById = repo.findById(id).orElseThrow { EntityNotFoundException("group with Id:$id not found ") }
        findById.apply {
            name = updatedStudyGroup.name
            coordinates.x = updatedStudyGroup.coordinates.x
            coordinates.y = updatedStudyGroup.coordinates.y
            studentsCount = updatedStudyGroup.studentsCount
            formOfEducation = updatedStudyGroup.formOfEducation
            semesterEnum = updatedStudyGroup.semesterEnum
            groupAdmin =updatedStudyGroup.groupAdmin?.toEntity()
        }
        return repo.save(findById)
    }

    override fun deleteById(id: Int) {
        repo.findById(id).orElseThrow { EntityNotFoundException("group with Id:$id not found ") }
        repo.deleteById(id)
    }

    override fun findMinAdmin(): StudyGroup =
        repo.findTopByOrderByGroupAdmin_HeightDesc()


    override fun findMaxAdmin(): StudyGroup =
        repo.findTopByOrderByGroupAdmin_HeightAsc()

    override fun groupCountByName(): List<GroupCountByNameResponse> =
        repo.groupCountByName()

}