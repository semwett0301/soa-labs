package com.example.studygroup.mapper

import com.example.studygroup.dto.CoordinatesCreationRequest
import com.example.studygroup.entity.Coordinates


fun CoordinatesCreationRequest.toEntity() =
    Coordinates(
        x = this.x,
        y = this.y
    )
