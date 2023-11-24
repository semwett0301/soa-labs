package com.example.studygroup.controller

import dto.StudyGroupCreationRequest
import entity.StudyGroup
import interfaces.StudyGroupService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/groups")
class GroupsController(val groupService: StudyGroupService) {
    @CrossOrigin("http://localhost:3000")
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