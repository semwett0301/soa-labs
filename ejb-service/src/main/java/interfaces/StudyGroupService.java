package interfaces;


import dto.GroupCountByNameResponse;
import dto.StudyGroupCreationRequest;
import entity.StudyGroup;

import java.util.List;

public interface StudyGroupService {

    StudyGroup createGroup(StudyGroupCreationRequest studyGroup);

    StudyGroup getById(int id);

    StudyGroup updateById(int id, StudyGroupCreationRequest updatedStudyGroup);

    void deleteById(int id);

    StudyGroup findMinAdmin();

    StudyGroup findMaxAdmin();

    List<GroupCountByNameResponse> groupCountByName();
}

