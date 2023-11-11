package repo;

import dto.GroupCountByNameResponse;
import entity.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyGroupRepository extends JpaRepository<StudyGroup, Integer>, JpaSpecificationExecutor<StudyGroup> {
    StudyGroup findTopByOrderByGroupAdmin_HeightAsc();

    StudyGroup findTopByOrderByGroupAdmin_HeightDesc();

    @Query("SELECT new dto.GroupCountByNameResponse(stgr.name, COUNT(stgr)) FROM StudyGroup stgr GROUP BY stgr.name")
    List<GroupCountByNameResponse> groupCountByName();

}
