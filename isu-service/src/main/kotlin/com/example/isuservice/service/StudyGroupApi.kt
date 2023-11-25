package com.example.isuservice.service

import com.fasterxml.jackson.databind.ObjectMapper
import entity.StudyGroup
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import java.net.URI

@Service
class StudyGroupApi(
    val restTemplate: RestTemplate,
    val host: String = "http://study-group-service",
    val objectMapper: ObjectMapper
) {
    fun studyGroupById(id: Int): Result =
        try {
            val exchange = restTemplate.exchange(
                URI("$host/groups/$id"),
                HttpMethod.GET,
                null,
                String::class.java
            )
            val readValue = objectMapper.readValue(exchange.body, StudyGroup::class.java)
            Result.Success(readValue)
        } catch (e: HttpClientErrorException) {
            Result.Error(e.statusCode, e.message)
        }


    fun putStudyGroup(studyGroup: StudyGroup): Result =
        try {
            val exchange = restTemplate.exchange(
                URI("$host/groups/${studyGroup.id}"),
                HttpMethod.PUT,
                HttpEntity(studyGroup),
                String::class.java
            )
            val readValue = objectMapper.readValue(exchange.body, StudyGroup::class.java)
            Result.Success(readValue)
        } catch (e: HttpClientErrorException) {
            Result.Error(e.statusCode, e.message)
        }
}