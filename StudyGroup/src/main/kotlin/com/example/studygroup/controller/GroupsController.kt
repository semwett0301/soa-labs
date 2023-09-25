package com.example.studygroup.controller

import com.example.studygroup.entity.StudyGroup
import com.example.studygroup.service.GroupService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest.*
import org.springframework.data.domain.Sort.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/groups")
class GroupsController(val groupService: GroupService) {

    @GetMapping
    @ResponseBody
    fun getAllStudyGroups(
        @Valid @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
        @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") pageSize: Int,
        @Valid @RequestParam(value = "name", required = false) name: String?,
        @Valid @RequestParam(value = "coordinateXFrom", required = false) coordinateXFrom: Int?,
        @Valid @RequestParam(value = "coordinateXTo", required = false) coordinateXTo: Int?,
        @Valid @RequestParam(value = "coordinateYFrom", required = false) coordinateYFrom: Int?,
        @Valid @RequestParam(value = "coordinateYTo", required = false) coordinateYTo: Int?,
        @Valid @RequestParam(value = "creationDateFrom", required = false) creationDateFrom: String?,
        @Valid @RequestParam(value = "creationDateTo", required = false) creationDateTo: String?,
        @Valid @RequestParam(value = "studentsCount", required = false) studentsCount: Int?,
        @Valid @RequestParam(value = "formOfEducation", required = false) formOfEducation: String?,
        @Valid @RequestParam(value = "semesterEnum", required = false) semesterEnum: String?,
        @Valid @RequestParam(value = "sort", required = false) sort: List<String?>?
    ): Page<StudyGroup> {
        val orders = sort?.let { list -> list.map { it!!.mapToOrder() } }.orEmpty()
        val pageRequest = of(page, pageSize, by(orders))
        return groupService.getAllStudyGroups(pageRequest)
    }

    fun String.mapToOrder(): Order {
        val directionString: String = this.first().toString()
        val property: String = this.substring(1)
        val direction: Direction = if (directionString == "+") Direction.ASC else Direction.DESC
        return Order(direction, property)
    }

    @PostMapping
    fun createGroup(@RequestBody studyGroup: StudyGroup) {
        groupService.createGroup() {

        }
    }

}