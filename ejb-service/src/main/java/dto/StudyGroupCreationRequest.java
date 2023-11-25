package dto;


import entity.FormOfEducation;
import entity.Semester;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyGroupCreationRequest implements Serializable {
    String name;
    Long studentsCount;
    FormOfEducation formOfEducation;
    Semester semesterEnum;
    AdminCreationRequest groupAdmin;
}
