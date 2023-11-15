package beans;

import dto.GroupCountByNameResponse;
import dto.StudyGroupCreationRequest;
import entity.FormOfEducation;
import entity.Semester;
import entity.StudyGroup;
import interfaces.RepositoryService;
import interfaces.StudyGroupService;
import jakarta.ejb.EJB;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import utils.BestMapperEver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Stateless(name = "StudyGroupServiceEjb")
@Remote(StudyGroupService.class)
public class StudyGroupServiceEjb implements StudyGroupService {
    
    @EJB
    private RepositoryService repositoryService;
    
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

        return repositoryService.getStudyGroupRepository().findAll(spec, pageable);
    }

    public StudyGroup createGroup(StudyGroupCreationRequest studyGroup) {
        StudyGroup newStudyGroup = BestMapperEver.toEntity(studyGroup);
        return repositoryService.getStudyGroupRepository().save(newStudyGroup);
    }

    public StudyGroup getById(int id) {
        return repositoryService.getStudyGroupRepository().findById(id).orElseThrow(() -> new EntityNotFoundException("group with Id:" + id + " not found "));
    }

    public StudyGroup updateById(int id, StudyGroupCreationRequest updatedStudyGroup) {
        StudyGroup findById = repositoryService.getStudyGroupRepository().findById(id).orElseThrow(() -> new EntityNotFoundException("group with Id:" + id + " not found "));
        findById.setName(updatedStudyGroup.name());
        findById.setStudentsCount(updatedStudyGroup.studentsCount());
        findById.setFormOfEducation(updatedStudyGroup.formOfEducation());
        findById.setSemesterEnum(updatedStudyGroup.semesterEnum());
        findById.setGroupAdmin(BestMapperEver.toEntity(updatedStudyGroup.groupAdmin()));
        return repositoryService.getStudyGroupRepository().save(findById);
    }

    public void deleteById(int id) {
        repositoryService.getStudyGroupRepository().findById(id).orElseThrow(() -> new RuntimeException("group with Id:" + id + " not found "));
        repositoryService.getStudyGroupRepository().deleteById(id);
    }

    public StudyGroup findMinAdmin() {
        return repositoryService.getStudyGroupRepository().findTopByOrderByGroupAdmin_HeightDesc();
    }

    public StudyGroup findMaxAdmin() {
        return repositoryService.getStudyGroupRepository().findTopByOrderByGroupAdmin_HeightAsc();
    }

    public List<GroupCountByNameResponse> groupCountByName() {
        return repositoryService.getStudyGroupRepository().groupCountByName();
    }
}
