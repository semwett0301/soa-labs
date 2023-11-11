package interfaces;


import dto.GroupCountByNameResponse;
import dto.StudyGroupCreationRequest;
import entity.FormOfEducation;
import entity.Semester;
import entity.StudyGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface GroupService {

    Page<StudyGroup> getAllStudyGroups(
            Pageable pageable,
            String name,
            Integer studentsCount,
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

