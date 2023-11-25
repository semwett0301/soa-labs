package utils;

import dto.AdminCreationRequest;
import dto.StudyGroupCreationRequest;
import entity.Person;
import entity.StudyGroup;

import java.time.LocalDate;

public class BestMapperEver {
    public static Person toEntity(AdminCreationRequest adminCreationRequest) {
        return new Person(
                null,
                adminCreationRequest.getName(),
                adminCreationRequest.getBirthday(),
                adminCreationRequest.getHeight(),
                adminCreationRequest.getWeight(),
                adminCreationRequest.getPassportID()
        );
    }

    public static StudyGroup toEntity(StudyGroupCreationRequest studyGroupCreationRequest) {
        return new StudyGroup(
                null,
                studyGroupCreationRequest.getName(),
                LocalDate.now(),
                studyGroupCreationRequest.getStudentsCount(),
                studyGroupCreationRequest.getFormOfEducation(),
                studyGroupCreationRequest.getSemesterEnum(),
                BestMapperEver.toEntity(studyGroupCreationRequest.getGroupAdmin())
        );
    }

}
