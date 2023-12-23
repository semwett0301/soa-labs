package com.example.isusevicemaven.service;

import com.example.isusevicemaven.utils.StudyGroupApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import entity.FormOfEducation;
import entity.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public class IsuService {
    private final StudyGroupApi studyGroupApi;

    public IsuService(StudyGroupApi studyGroupApi) {
        this.studyGroupApi = studyGroupApi;
    }

    public StudyGroup expelAll(int groupId) throws JsonProcessingException {
        entity.StudyGroup studyGroup = studyGroupApi.studyGroupById(groupId);
        studyGroup.setStudentsCount(0L);
        return studyGroupApi.putStudyGroup(studyGroup);
    }

    public StudyGroup changeEduForm(int groupId, FormOfEducation formOfEducation) throws JsonProcessingException {
        entity.StudyGroup studyGroup = studyGroupApi.studyGroupById(groupId);
        studyGroup.setFormOfEducation(formOfEducation);
        return studyGroupApi.putStudyGroup(studyGroup);
    }
}
