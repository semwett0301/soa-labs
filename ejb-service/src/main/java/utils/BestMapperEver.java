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
                adminCreationRequest.name(),
                adminCreationRequest.birthday(),
                adminCreationRequest.height(),
                adminCreationRequest.weight(),
                adminCreationRequest.passportID()
        );
    }

    public static StudyGroup toEntity(StudyGroupCreationRequest studyGroupCreationRequest) {
        return new StudyGroup(
                null,
                studyGroupCreationRequest.name(),
                LocalDate.now(),
                studyGroupCreationRequest.studentsCount(),
                studyGroupCreationRequest.formOfEducation(),
                studyGroupCreationRequest.semesterEnum(),
                BestMapperEver.toEntity(studyGroupCreationRequest.groupAdmin())
        );
    }

}
