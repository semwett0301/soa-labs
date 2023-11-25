package interfaces;


import dto.GroupCountByNameResponse;
import dto.StudyGroupCreationRequest;
import entity.FormOfEducation;
import entity.Semester;
import entity.StudyGroup;

import java.time.LocalDate;
import java.util.List;

public interface StudyGroupService {

    List<StudyGroup> getAllStudyGroups(
            Integer page,
            Integer pageSize,
            String sort,
            String name,
            Long studentsCount,
            FormOfEducation formOfEducation,
            Semester semesterEnum,
            LocalDate creationDateEq
    );

    StudyGroup createGroup(StudyGroupCreationRequest studyGroup);

    StudyGroup getById(int id);

    StudyGroup updateById(int id, StudyGroupCreationRequest updatedStudyGroup);

    void deleteById(int id);

    StudyGroup findMinAdmin();

    StudyGroup findMaxAdmin();

    List<GroupCountByNameResponse> groupCountByName();
}

