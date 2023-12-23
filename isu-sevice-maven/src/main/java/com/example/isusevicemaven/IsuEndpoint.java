package com.example.isusevicemaven;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.guides.gs_producing_web_service.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class IsuEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private final StudyGroupApi studyGroupApi;

    public IsuEndpoint(StudyGroupApi studyGroupApi) {
        this.studyGroupApi = studyGroupApi;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "expelAllRequest")
    @ResponsePayload
    public ExpelAllResponse expelAll(@RequestPayload ExpelAllRequest request) throws JsonProcessingException, DatatypeConfigurationException {
        entity.StudyGroup studyGroup = studyGroupApi.studyGroupById(request.getId());
        studyGroup.setStudentsCount(0l);
        entity.StudyGroup studyGroup1 = studyGroupApi.putStudyGroup(studyGroup);
        ExpelAllResponse expelAllResponse = new ExpelAllResponse();
        expelAllResponse.setStudyGroup(BestMapperEver.from(studyGroup1));
        return expelAllResponse;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "changeEduFormRequest")
    @ResponsePayload
    public ChangeEduFormResponse changeEduForm(@RequestPayload ChangeEduFormRequest request) {
        ChangeEduFormResponse response = new ChangeEduFormResponse();
        StudyGroup studyGroup = new StudyGroup();
        response.setStudyGroup(studyGroup);
        return response;
    }

}
