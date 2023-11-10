package com.example.studygroup.mapper

import com.example.studygroup.dto.AdminCreationRequest
import com.example.studygroup.entity.Person

fun AdminCreationRequest.toEntity()= Person(
    name = this.name,
    birthday =  this.birthday,
    height = this.height,
    weight =  this.weight,
    passportID = this.passportID
)
