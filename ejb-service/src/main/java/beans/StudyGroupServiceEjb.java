package beans;

import dto.GroupCountByNameResponse;
import dto.StudyGroupCreationRequest;
import entity.FormOfEducation;
import entity.Semester;
import entity.StudyGroup;
import interfaces.StudyGroupService;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import utils.BestMapperEver;
import utils.Repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Stateless(name = "StudyGroupServiceEjb")
@Remote(StudyGroupService.class)
public class StudyGroupServiceEjb implements StudyGroupService {
    public Page<StudyGroup> getAllStudyGroups(Pageable pageable, String name, Integer studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, LocalDate creationDateEq) {
        Specification<StudyGroup> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isBlank()) {
                predicates.add(cb.like(root.get("name"), "%" + name + "%"));
            }
            if (creationDateEq != null) {
                predicates.add(cb.equal(root.get("creationDate"), creationDateEq));
            }
            if (formOfEducation != null) {
                predicates.add(cb.equal(root.get("formOfEducation"), formOfEducation));
            }
            if (semesterEnum != null) {
                predicates.add(cb.equal(root.get("semesterEnum"), semesterEnum));
            }
            if (studentsCount != null) {
                predicates.add(cb.equal(root.get("studentsCount"), studentsCount));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return Repositories.STUDY_GROUP_REPOSITORY.findAll(spec, pageable);
    }

    public StudyGroup createGroup(StudyGroupCreationRequest studyGroup) {
        StudyGroup newStudyGroup = BestMapperEver.toEntity(studyGroup);
        return Repositories.STUDY_GROUP_REPOSITORY.save(newStudyGroup);
    }

    public StudyGroup getById(int id) {
        return Repositories.STUDY_GROUP_REPOSITORY.findById(id).orElseThrow(() -> new EntityNotFoundException("group with Id:" + id + " not found "));
    }

    public StudyGroup updateById(int id, StudyGroupCreationRequest updatedStudyGroup) {
        StudyGroup findById = Repositories.STUDY_GROUP_REPOSITORY.findById(id).orElseThrow(() -> new EntityNotFoundException("group with Id:" + id + " not found "));
        findById.setName(updatedStudyGroup.name());
        findById.setStudentsCount(updatedStudyGroup.studentsCount());
        findById.setFormOfEducation(updatedStudyGroup.formOfEducation());
        findById.setSemesterEnum(updatedStudyGroup.semesterEnum());
        findById.setGroupAdmin(BestMapperEver.toEntity(updatedStudyGroup.groupAdmin()));
        return Repositories.STUDY_GROUP_REPOSITORY.save(findById);
    }

    public void deleteById(int id) {
        Repositories.STUDY_GROUP_REPOSITORY.findById(id).orElseThrow(() -> new RuntimeException("group with Id:" + id + " not found "));
        Repositories.STUDY_GROUP_REPOSITORY.deleteById(id);
    }

    public StudyGroup findMinAdmin() {
        return Repositories.STUDY_GROUP_REPOSITORY.findTopByOrderByGroupAdmin_HeightDesc();
    }

    public StudyGroup findMaxAdmin() {
        return Repositories.STUDY_GROUP_REPOSITORY.findTopByOrderByGroupAdmin_HeightAsc();
    }

    public List<GroupCountByNameResponse> groupCountByName() {
        return Repositories.STUDY_GROUP_REPOSITORY.groupCountByName();
    }
}
