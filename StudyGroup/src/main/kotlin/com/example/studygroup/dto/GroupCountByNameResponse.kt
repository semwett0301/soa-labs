package com.example.studygroup.dto

data class GroupCountByNameResponse(
    val groupName: String,
    val count: Long
)