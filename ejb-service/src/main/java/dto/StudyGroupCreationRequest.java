package dto;



import entity.FormOfEducation;
import entity.Semester;

public record StudyGroupCreationRequest(String name, Long studentsCount, FormOfEducation formOfEducation,
                                        Semester semesterEnum, AdminCreationRequest groupAdmin) {

}
