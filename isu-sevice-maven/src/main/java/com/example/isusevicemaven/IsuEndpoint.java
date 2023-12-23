package com.example.isusevicemaven;


import com.fasterxml.jackson.core.JsonProcessingException;
import entity.FormOfEducation;
import entity.StudyGroup;
import io.spring.guides.gs_producing_web_service.ChangeEduFormRequest;
import io.spring.guides.gs_producing_web_service.ChangeEduFormResponse;
import io.spring.guides.gs_producing_web_service.ExpelAllRequest;
import io.spring.guides.gs_producing_web_service.ExpelAllResponse;
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
    public ChangeEduFormResponse changeEduForm(@RequestPayload ChangeEduFormRequest request) throws JsonProcessingException, DatatypeConfigurationException {
        entity.StudyGroup studyGroup = studyGroupApi.studyGroupById(request.getId());
        studyGroup.setFormOfEducation(FormOfEducation.valueOf(request.getNewFormOfEducation().name()));
        StudyGroup studyGroup1 = studyGroupApi.putStudyGroup(studyGroup);
        ChangeEduFormResponse response = new ChangeEduFormResponse();
        response.setStudyGroup(BestMapperEver.from(studyGroup1));
        return response;
    }

}
