package beans;

import entity.Person;
import interfaces.PersonService;
import interfaces.TryInterface;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.jboss.ejb3.annotation.Pool;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import repo.PersonRepository;

import java.util.List;

@Remote(PersonService.class)
@Stateless(name = "PersonServiceEjb")
@Pool("slsb-strict-max-pool")
public class PersonServiceEjb implements PersonService {

    @Produces
    @Dependent
    @PersistenceContext(unitName = "db_unit")
    private static EntityManager emPeb;

    @Override
    public List<Person> getAll() {
        JpaRepositoryFactory jrf = new JpaRepositoryFactory(emPeb);
        PersonRepository repo = jrf.getRepository(PersonRepository.class);
        return repo.findAll();
    }
}

