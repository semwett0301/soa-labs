package utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import repo.PersonRepository;

public class Repositories {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("db_unit");
    private static final JpaRepositoryFactory jrf = new JpaRepositoryFactory(emf.createEntityManager());

    public static PersonRepository PERSON_REPOSITORY = jrf.getRepository(PersonRepository.class);
}
