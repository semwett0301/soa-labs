package beans;

import interfaces.RepositoryService;
import jakarta.ejb.Local;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Data;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import repo.PersonRepository;
import repo.StudyGroupRepository;

@Data
@Singleton
@Local(RepositoryService.class)
public class RepositoryServiceEjb implements RepositoryService {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("db_unit");
    private final JpaRepositoryFactory jrf = new JpaRepositoryFactory(emf.createEntityManager());


    @Override
    public StudyGroupRepository getStudyGroupRepository() {
        return jrf.getRepository(StudyGroupRepository.class);
    }

    @Override
    public PersonRepository getPersonRepository() {
        return jrf.getRepository(PersonRepository.class);
    }
}
