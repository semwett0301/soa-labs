package beans;

import interfaces.RepositoryService;
import jakarta.ejb.Local;
import jakarta.ejb.Singleton;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import repo.PersonRepository;
import repo.StudyGroupRepository;

@Singleton
@Local(RepositoryService.class)
public class RepositoryServiceEjb implements RepositoryService {
    private final StudyGroupRepository studyGroupRepository;
    private final PersonRepository personRepository;

    public RepositoryServiceEjb() {
        JpaRepositoryFactory jrf = new JpaRepositoryFactory(Persistence.createEntityManagerFactory("db_unit").createEntityManager());
        this.personRepository = jrf.getRepository(PersonRepository.class);
        this.studyGroupRepository = jrf.getRepository(StudyGroupRepository.class);
    }

    @Override
    public synchronized StudyGroupRepository getStudyGroupRepository() {
        return this.studyGroupRepository;
    }

    @Override
    public synchronized PersonRepository getPersonRepository() {
        return this.personRepository;
    }
}
