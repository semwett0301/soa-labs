package beans;

import entity.Person;
import interfaces.PersonService;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import org.jboss.ejb3.annotation.Pool;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import repo.PersonRepository;

import java.util.List;

@Remote(PersonService.class)
@Stateless(name = "PersonServiceEjb")
@Pool("slsb-strict-max-pool")
public class PersonServiceEjb implements PersonService {
    @Override
    public List<Person> getAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db_unit");

        JpaRepositoryFactory jrf = new JpaRepositoryFactory(emf.createEntityManager());
        PersonRepository repo = jrf.getRepository(PersonRepository.class);
        System.out.println(repo.findAll());
        return repo.findAll();
    }
}

