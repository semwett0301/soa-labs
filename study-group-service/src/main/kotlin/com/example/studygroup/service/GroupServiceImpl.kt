package com.example.studygroup.service

import com.example.studygroup.dto.StudyGroupCreationRequest
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
import java.time.LocalDate

@Service
@Transactional
class GroupServiceImpl(val repo: StudyGroupRepository) : GroupService {

    override fun getAllStudyGroups(
        pageable: Pageable,
        name: String?,
        studentsCount: Int?,
        formOfEducation: FormOfEducation?,
        semesterEnum: Semester?,
        creationDateEq: LocalDate?
    ): Page<StudyGroup> {
        val spec = Specification<StudyGroup> { root, _, cb ->
            val predicates = mutableListOf<Predicate>()

            if (!name.isNullOrBlank()) {
                predicates.add(cb.like(root.get("name"), "%$name%"))
            }
            creationDateEq?.let { predicates.add(cb.equal(root.get<LocalDate>("creationDate"),it)) }

            formOfEducation?.let { predicates.add(cb.equal(root.get<FormOfEducation>("formOfEducation"), it)) }
            semesterEnum?.let { predicates.add(cb.equal(root.get<Semester>("semesterEnum"), it)) }
            studentsCount?.let { predicates.add(cb.equal(root.get<Int>("studentsCount"), it)) }
            cb.and(*predicates.toTypedArray())
        }

        return repo.findAll(spec, pageable)
    }

    override fun createGroup(studyGroup: StudyGroupCreationRequest): StudyGroup {
        val newStudyGroup = studyGroup.toEntity()
        return repo.save(newStudyGroup)
    }

    override fun getById(id: Int): StudyGroup = repo.findById(id).orElseThrow { EntityNotFoundException("group with Id:$id not found ") }
    override fun updateById(id: Int, updatedStudyGroup: StudyGroupCreationRequest): StudyGroup {
        val findById = repo.findById(id).orElseThrow { EntityNotFoundException("group with Id:$id not found ") }
        findById.apply {
            name = updatedStudyGroup.name
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