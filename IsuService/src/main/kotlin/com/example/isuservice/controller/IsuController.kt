package com.example.isuservice.controller

import com.example.isuservice.dto.FormOfEducation
import com.example.isuservice.service.IsuService
import com.example.isuservice.service.Result
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/isu/groups")
class IsuController(
    val isuService: IsuService
) {
    @PostMapping("/{groupId}/expel-all")
    fun expelAll(@PathVariable groupId: Int): ResponseEntity<out Any> {
        return when (val expelAll = isuService.expelAll(groupId)) {
            is Result.Error -> ResponseEntity.status(expelAll.status).body(expelAll.message)
            is Result.Success -> ResponseEntity.ok().body(expelAll.data)
        }
    }


    @PostMapping("/{groupId}/change-edu-form/{formOfEducation}")
    fun changeEduForm(
        @PathVariable groupId: Int, @PathVariable formOfEducation: FormOfEducation
    ): ResponseEntity<out Any> {
        return when (val expelAll = isuService.changeEduForm(groupId, formOfEducation)) {
            is Result.Error -> ResponseEntity.status(expelAll.status).body(expelAll.message)
            is Result.Success -> ResponseEntity.ok().body(expelAll.data)
        }
    }
}

