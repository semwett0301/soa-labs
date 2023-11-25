package com.example.studygroup.controller

import com.fasterxml.jackson.annotation.JsonFormat
import dto.StudyGroupCreationRequest
import entity.FormOfEducation
import entity.Semester
import entity.StudyGroup
import interfaces.StudyGroupService
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest.*
import org.springframework.data.domain.Sort.*
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/groups")
class GroupsController(val groupService: StudyGroupService) {
    @GetMapping
    @ResponseBody
    fun getAllStudyGroups(
        @Valid @RequestParam(value = "page", required = false, defaultValue = "0") @Min(0) page: Int,
        @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") @Min(1) pageSize: Int,
        @Valid @RequestParam(value = "name", required = false) name: String?,
        @Valid @RequestParam(
            value = "creationDate",
            required = false
        ) @JsonFormat(pattern = "yyyy-MM-dd") @DateTimeFormat(pattern = "yyyy-MM-dd") creationDateEq: LocalDate?,
        @Valid @RequestParam(value = "studentsCount", required = false) @Min(0) studentsCount: Long?,
        @Valid @RequestParam(value = "formOfEducation", required = false) formOfEducation: FormOfEducation?,
        @Valid @RequestParam(value = "semesterEnum", required = false) semesterEnum: Semester?,
        @Valid @RequestParam(value = "sort", required = false) sort: List<String>?
    ): Page<StudyGroup> {
        val orders = sort?.let { list -> list.map { it.mapToOrder() } }.orEmpty()
        val pageRequest = of(page, pageSize, by(orders))

        val groupList = groupService.getAllStudyGroups(
            page,
            pageSize,
            name,
            studentsCount,
            formOfEducation,
            semesterEnum,
            creationDateEq
        )

        groupList.forEach() { println(it.id) };

        return PageImpl(groupList, pageRequest, groupList.size.toLong());
    }

    fun String.mapToOrder(): Order {
        val directionString: String = this.first().toString()
        val property: String = this.substring(1)
        val direction: Direction = if (directionString == "+") Direction.ASC else Direction.DESC
        return Order(direction, property)
    }


    @PostMapping
    fun createGroup(@Valid @RequestBody studyGroup: StudyGroupCreationRequest): ResponseEntity<StudyGroup> {
        return ResponseEntity.status(201).body(groupService.createGroup(studyGroup))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): StudyGroup {
        println("ping")
        return groupService.getById(id)
    }

    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Int, @Valid @RequestBody studyGroup: StudyGroupCreationRequest) =
        groupService.updateById(id, studyGroup)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Int) = groupService.deleteById(id)

    @PostMapping("/min-admin")
    fun minAdmin() = groupService.findMinAdmin()

    @PostMapping("/max-admin")
    fun maxAdmin() = groupService.findMaxAdmin()

    @PostMapping("/group-count-by-name")
    fun groupCountByName() = groupService.groupCountByName()
}