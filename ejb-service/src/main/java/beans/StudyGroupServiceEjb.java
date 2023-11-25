package beans;

import dto.GroupCountByNameResponse;
import dto.StudyGroupCreationRequest;
import entity.FormOfEducation;
import entity.Person;
import entity.Semester;
import entity.StudyGroup;
import interfaces.StudyGroupService;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.Data;
import utils.BestMapperEver;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Stateless(name = "StudyGroupServiceEjb")
@Remote(StudyGroupService.class)
public class StudyGroupServiceEjb implements StudyGroupService {

    @PersistenceContext(unitName = "db_unit")
    private EntityManager entityManager;

    @Override
    public List<StudyGroup> getAllStudyGroups(Integer page, Integer pageSize, String sort, String name, Long studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, LocalDate creationDateEq) {
        String baseRequest = "SELECT sg FROM StudyGroup sg " +
                "WHERE (:name IS NULL OR sg.name = :name) AND (:semesterEnum IS NULL OR sg.semesterEnum = :semesterEnum) " +
                "AND (:creationDate IS NULL OR sg.creationDate = :creationDate) AND (:studentsCount IS NULL OR sg.studentsCount = :studentsCount) " +
                "AND (:formOfEducation IS NULL OR sg.formOfEducation = :formOfEducation)";

        Set<String> fieldSet = Arrays.stream(StudyGroup.class.getDeclaredFields())
                .map(el -> el.getName())
                .collect(Collectors.toSet());

        if (sort != null && fieldSet.contains(sort.substring(1))) {
            String order = sort.charAt(0) == '+' ? "ASC" : "DESC";
            baseRequest = baseRequest + " ORDER BY sg." + sort.substring(1) + " " + order;
        }

        Query query = entityManager.createQuery(baseRequest, StudyGroup.class);

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

    @Transactional
    public StudyGroup createGroup(StudyGroupCreationRequest studyGroup) {
        StudyGroup newStudyGroup = BestMapperEver.toEntity(studyGroup);
        Person admin = entityManager.find(Person.class, studyGroup.getGroupAdmin().getId());
        newStudyGroup.setGroupAdmin(admin);

        entityManager.persist(newStudyGroup);
        return newStudyGroup;
    }

    public StudyGroup getById(int id) {
        return entityManager.find(StudyGroup.class, id);
    }

    @Transactional
    public StudyGroup updateById(int id, StudyGroupCreationRequest updatedStudyGroup) {
        StudyGroup studyGroup = entityManager.find(StudyGroup.class, id);
        studyGroup.setName(updatedStudyGroup.getName());
        studyGroup.setStudentsCount(updatedStudyGroup.getStudentsCount());
        studyGroup.setFormOfEducation(updatedStudyGroup.getFormOfEducation());
        studyGroup.setSemesterEnum(updatedStudyGroup.getSemesterEnum());

        if (updatedStudyGroup.getGroupAdmin() != null) {
            Person admin = studyGroup.getGroupAdmin();
            admin.setBirthday(updatedStudyGroup.getGroupAdmin().getBirthday());
            admin.setHeight(updatedStudyGroup.getGroupAdmin().getHeight());
            admin.setWeight(updatedStudyGroup.getGroupAdmin().getWeight());
            admin.setName(updatedStudyGroup.getGroupAdmin().getName());
            admin.setPassportID(updatedStudyGroup.getGroupAdmin().getPassportID());
        }

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
