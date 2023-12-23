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
    private final IsuService isuService;

    public IsuEndpoint(IsuService isuService) {
        this.isuService = isuService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "expelAllRequest")
    @ResponsePayload
    public ExpelAllResponse expelAll(@RequestPayload ExpelAllRequest request) throws JsonProcessingException, DatatypeConfigurationException {
        ExpelAllResponse expelAllResponse = new ExpelAllResponse();
        entity.StudyGroup studyGroup = isuService.expelAll(request.getId());
        expelAllResponse.setStudyGroup(BestMapperEver.from(studyGroup));
        return expelAllResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "changeEduFormRequest")
    @ResponsePayload
    public ChangeEduFormResponse changeEduForm(@RequestPayload ChangeEduFormRequest request) throws JsonProcessingException, DatatypeConfigurationException {
        ChangeEduFormResponse response = new ChangeEduFormResponse();
        StudyGroup studyGroup = isuService.changeEduForm(request.getId(), FormOfEducation.valueOf(request.getNewFormOfEducation().name()));
        response.setStudyGroup(BestMapperEver.from(studyGroup));
        return response;
    }

}
