package interfaces;

import repo.PersonRepository;
import repo.StudyGroupRepository;

public interface RepositoryService {
    StudyGroupRepository getStudyGroupRepository();
    PersonRepository getPersonRepository();
}
