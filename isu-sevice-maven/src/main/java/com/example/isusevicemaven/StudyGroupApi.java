package com.example.isusevicemaven;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.StudyGroup;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class StudyGroupApi {

    private final RestTemplate restTemplate;
    private final String host;
    private final ObjectMapper objectMapper;

    public StudyGroupApi(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.host = "http://study-group-service";
        this.objectMapper = objectMapper;
    }

    public StudyGroup studyGroupById(int id) throws JsonProcessingException {
        var exchange = restTemplate.exchange(
                URI.create(host + "/groups/" + id),
                HttpMethod.GET,
                null,
                String.class
        );
        return objectMapper.readValue(exchange.getBody(), StudyGroup.class);
    }

    public StudyGroup putStudyGroup(StudyGroup studyGroup) throws JsonProcessingException {
            var exchange = restTemplate.exchange(
                    URI.create(host + "/groups/" + studyGroup.getId()),
                    HttpMethod.PUT,
                    new HttpEntity<>(studyGroup),
                    String.class
            );
            return objectMapper.readValue(exchange.getBody(), StudyGroup.class);
    }
}
