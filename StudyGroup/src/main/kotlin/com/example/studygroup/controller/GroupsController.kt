package com.example.studygroup.controller

import com.example.studygroup.dto.StudyGroupCreationRequest
import com.example.studygroup.entity.FormOfEducation
import com.example.studygroup.entity.Semester
import com.example.studygroup.entity.StudyGroup
import com.example.studygroup.service.GroupService
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest.*
import org.springframework.data.domain.Sort.*
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/groups")
class GroupsController(val groupService: GroupService) {

    @GetMapping
    @ResponseBody
    @CrossOrigin("*")
    fun getAllStudyGroups(
        @Valid @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
        @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") pageSize: Int,
        @Valid @RequestParam(value = "name", required = false) name: String?,
        @Valid @RequestParam(value = "coordinateXFrom", required = false) coordinateXFrom: Int?,
        @Valid @RequestParam(value = "coordinateXTo", required = false) coordinateXTo: Int?,
        @Valid @RequestParam(value = "coordinateYFrom", required = false) coordinateYFrom: Int?,
        @Valid @RequestParam(value = "coordinateYTo", required = false) coordinateYTo: Int?,
        @Valid @RequestParam(
            value = "creationDateFrom",
            required = false
        ) @JsonFormat(pattern = "yyyy-MM-dd") creationDateFrom: LocalDateTime?,
        @Valid @RequestParam(value = "creationDateTo", required = false)
        @JsonFormat(pattern = "yyyy-MM-dd")
        creationDateTo: LocalDateTime?,
        @Valid @RequestParam(value = "studentsCount", required = false) studentsCount: Int?,
        @Valid @RequestParam(value = "formOfEducation", required = false) formOfEducation: FormOfEducation?,
        @Valid @RequestParam(value = "semesterEnum", required = false) semesterEnum: Semester?,
        @Valid @RequestParam(value = "sort", required = false) sort: List<String>?
    ): Page<StudyGroup> {
        val orders = sort?.let { list -> list.map { it.mapToOrder() } }.orEmpty()
        val pageRequest = of(page, pageSize, by(orders))
        return groupService.getAllStudyGroups(
            pageable = pageRequest,
            name = name,
            coordinateXFrom = coordinateXFrom,
            coordinateXTo = coordinateXTo,
            coordinateYFrom = coordinateYFrom,
            coordinateYTo = coordinateYTo,
            creationDateFrom = creationDateFrom,
            creationDateTo = creationDateTo,
            studentsCount = studentsCount,
            formOfEducation = formOfEducation,
            semesterEnum = semesterEnum
        )
    }

    fun String.mapToOrder(): Order {
        val directionString: String = this.first().toString()
        val property: String = this.substring(1)
        val direction: Direction = if (directionString == "+") Direction.ASC else Direction.DESC
        return Order(direction, property)
    }

    @PostMapping
    fun createGroup(@Valid @RequestBody studyGroup: StudyGroupCreationRequest) {
        groupService.createGroup(studyGroup)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int) = groupService.getById(id)

    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Int, @Valid @RequestBody studyGroup: StudyGroupCreationRequest) =
        groupService.updateById(id, studyGroup)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id:Int) = groupService.deleteById(id)

    @PostMapping("/min-admin")
    fun minAdmin() = groupService.findMinAdmin()

    @PostMapping("/max-admin")
    fun maxAdmin() = groupService.findMaxAdmin()
    @PostMapping("/group-count-by-name")
    fun groupCountByName() = groupService.groupCountByName()

}