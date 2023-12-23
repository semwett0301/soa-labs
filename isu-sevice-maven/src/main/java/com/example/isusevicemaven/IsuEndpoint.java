package com.example.isusevicemaven;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.guides.gs_producing_web_service.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;

@Endpoint
public class IsuEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private final StudyGroupApi studyGroupApi;
    private final ObjectMapper objectMapper;

    public IsuEndpoint(StudyGroupApi studyGroupApi, ObjectMapper objectMapper) {
        this.studyGroupApi = studyGroupApi;
        this.objectMapper = objectMapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "expelAllRequest")
    @ResponsePayload
    public ExpelAllResponse expelAll(@RequestPayload ExpelAllRequest request) throws JsonProcessingException {
        entity.StudyGroup studyGroup = studyGroupApi.studyGroupById(request.getId());
        studyGroup.setStudentsCount(0l);
        entity.StudyGroup studyGroup1 = studyGroupApi.putStudyGroup(studyGroup);
        objectMapper.cl
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "changeEduFormRequest")
    @ResponsePayload
    public ChangeEduFormResponse changeEduForm(@RequestPayload ChangeEduFormRequest request) {
        ChangeEduFormResponse response = new ChangeEduFormResponse();
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setId(BigInteger.valueOf(request.getId()));
        response.setStudyGroup(studyGroup);
        return response;
    }


}
