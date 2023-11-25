package beans;

import dto.GroupCountByNameResponse;
import dto.StudyGroupCreationRequest;
import entity.FormOfEducation;
import entity.Semester;
import entity.StudyGroup;
import interfaces.StudyGroupService;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.Data;
import utils.BestMapperEver;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Stateless(name = "StudyGroupServiceEjb")
@Remote(StudyGroupService.class)
public class StudyGroupServiceEjb implements StudyGroupService {

    @PersistenceContext(unitName = "db_unit")
    private EntityManager entityManager;

    @Override
    public List<StudyGroup> getAllStudyGroups(Integer page, Integer pageSize, String name, Long studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, LocalDate creationDateEq) {
        Query query = entityManager.createQuery("SELECT sg FROM StudyGroup sg " +
                "WHERE (:name IS NULL OR sg.name = :name) AND (:semesterEnum IS NULL OR sg.semesterEnum = :semesterEnum) " +
                "AND (:creationDate IS NULL OR sg.creationDate = :creationDate) AND (:studentsCount IS NULL OR sg.studentsCount = :studentsCount) " +
                "AND (:formOfEducation IS NULL OR sg.formOfEducation = :formOfEducation)", StudyGroup.class);

        query.setMaxResults(pageSize);
        query.setFirstResult(page * pageSize);

        query.setParameter("name", name);
        query.setParameter("semesterEnum", semesterEnum);
        query.setParameter("creationDate", creationDateEq);
        query.setParameter("studentsCount", studentsCount);
        query.setParameter("formOfEducation", formOfEducation);

        List<StudyGroup> result = query.getResultList();

        return result;
    }

    public StudyGroup createGroup(StudyGroupCreationRequest studyGroup) {
        StudyGroup newStudyGroup = BestMapperEver.toEntity(studyGroup);
        entityManager.persist(newStudyGroup);
        return newStudyGroup;
    }

    public StudyGroup getById(int id) {
        return entityManager.find(StudyGroup.class, id);
    }

    public StudyGroup updateById(int id, StudyGroupCreationRequest updatedStudyGroup) {
        StudyGroup studyGroup = entityManager.find(StudyGroup.class, id);
        studyGroup.setName(updatedStudyGroup.name());
        studyGroup.setStudentsCount(updatedStudyGroup.studentsCount());
        studyGroup.setFormOfEducation(updatedStudyGroup.formOfEducation());
        studyGroup.setSemesterEnum(updatedStudyGroup.semesterEnum());
        studyGroup.setGroupAdmin(BestMapperEver.toEntity(updatedStudyGroup.groupAdmin()));
        entityManager.persist(studyGroup);
        return studyGroup;
    }

    public void deleteById(int id) {
        Query query = entityManager.createQuery("DELETE FROM StudyGroup s WHERE s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public StudyGroup findMinAdmin() {
        List<StudyGroup> studyGroupList = entityManager.createQuery("SELECT sg FROM StudyGroup sg ORDER BY sg.groupAdmin.height").getResultList();

        if (studyGroupList.size() > 0) {
            return studyGroupList.get(0);
        } else {
            return null;
        }
    }

    public StudyGroup findMaxAdmin() {
        List<StudyGroup> studyGroupList = entityManager.createQuery("SELECT sg FROM StudyGroup sg ORDER BY sg.groupAdmin.height DESC").getResultList();

        if (studyGroupList.size() > 0) {
            return studyGroupList.get(0);
        } else {
            return null;
        }
    }

    public List<GroupCountByNameResponse> groupCountByName() {
        List<Object[]> objects = entityManager.createQuery("SELECT sg.name, COUNT(*) FROM StudyGroup sg GROUP BY sg.name").getResultList();
        return objects.stream().map(arr -> new GroupCountByNameResponse((String) arr[0], (Long) arr[1])).collect(Collectors.toList());
    }
}
